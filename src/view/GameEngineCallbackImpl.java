package view;

import model.interfaces.GameEngine;
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
        //making color proper case
        String col = slot.getColor().name();
        col = col.substring(0, 1).toUpperCase() + col.substring(1).toLowerCase();
        String result = String.format("Position: %s, Color: %s, Number: %s", pos, col, num);
        logger.log(Level.FINE, result);
    }


    @Override
    public void result(Slot result, GameEngine engine) {
        // final results logged at Level.INFO
        //logger.log(Level.FINE, "Result data to log .. String.format() is good here!");
        int pos = result.getPosition();
        int num = result.getNumber();
        //making color title case
        String col = result.getColor().name();
        col = col.substring(0, 1).toUpperCase() + col.substring(1).toLowerCase();
        logger.log(Level.INFO, String.format("RESULT = Position: %s, Color: %s, Number: %s\n", pos, col, num));
        engine.calculateResult(result);
        logger.log(Level.INFO, "FINAL PLAYER POINT BALANCES");
        logger.log(Level.INFO, engine.getAllPlayers().toString().replace("[", "").replace("]",
                ""));
    }


}
