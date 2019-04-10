package view;

import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.Slot;
import view.interfaces.GameEngineCallback;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Skeleton/Partial example implementation of GameEngineCallback showing Java logging behaviour
 *
 * @author Caspar Ryan
 * @see view.interfaces.GameEngineCallback
 */
public class GameEngineCallbackImpl implements GameEngineCallback {
    private static final Logger logger = Logger.getLogger(GameEngineCallback.class.getName());

    public GameEngineCallbackImpl() {
        // FINE shows wheel spinning output, INFO only shows result
        logger.setLevel(Level.FINE);
        Logger rootLog = Logger.getLogger("");
        rootLog.setLevel(Level.FINE);
        rootLog.getHandlers()[0].setLevel(Level.FINE);
    }

    @Override
    public void nextSlot(Slot slot, GameEngine engine) {
        // intermediate results logged at Level.FINE
        //logger.log(Level.FINE, "Intermediate data to log .. String.format() is good here!");
        int pos = slot.getPosition();
        int num = slot.getNumber();
        model.enumeration.Color col = slot.getColor();
        logger.log(Level.FINE, String.format("Position: %s, Color: %s, Number: %s", pos, col, num));
    }

    @Override
    public void result(Slot result, GameEngine engine) {
        // final results logged at Level.INFO
        //logger.log(Level.FINE, "Result data to log .. String.format() is good here!");
        int pos = result.getPosition();
        int num = result.getNumber();
        model.enumeration.Color col = result.getColor();
        logger.log(Level.INFO, String.format("RESULT = Position: %s, Color: %s, Number: %s\n", pos, col, num));
        engine.calculateResult(result);
        logger.log(Level.INFO, "FINAL PLAYER POINT BALANCES");
        String results = "";
        for (Player player : engine.getAllPlayers()) {
            String id = player.getPlayerId();
            String name = player.getPlayerName();
            int bet = player.getBet();
            BetType betType = player.getBetType();
            int points = player.getPoints();
            results += String.format("\nPlayer: id:%s, name=%s, bet=%s, betType=%s, points=%s",
                    id, name, bet, betType, points);
        }
        logger.log(Level.INFO, results);

    }
}
