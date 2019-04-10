package model;

import model.enumeration.BetType;
import model.enumeration.Color;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.Slot;
import view.interfaces.GameEngineCallback;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;


public class GameEngineImpl implements GameEngine {
    Collection<Player> players = new ArrayList<Player>();
    Collection<Slot> slots = new ArrayList<Slot>();
    Collection<GameEngineCallback> callbacks = new ArrayList<GameEngineCallback>();
    GameEngineCallback gameEngineCallback;


    @Override
    public void spin(int initialDelay, int finalDelay, int delayIncrement) {
        Random rand = new Random();
        List list = new ArrayList(slots);
        int i = rand.nextInt(Slot.WHEEL_SIZE);
        while (initialDelay < finalDelay) {
            if (i > Slot.WHEEL_SIZE - 1) {
                i = 0;
            }
            SlotImpl slot = (SlotImpl) list.get(i);
            this.gameEngineCallback.nextSlot(slot, this);
            try {
                Thread.sleep(initialDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            initialDelay += delayIncrement;
            i++;
        }
        SlotImpl winningSlot = (SlotImpl) list.get(i);
        gameEngineCallback.result(winningSlot, this);
    }

    @Override

    public void calculateResult(Slot winningSlot) {
        for (Player player : players) {
            player.getBetType().applyWinLoss(player, winningSlot);
        }
    }

    @Override
    public void addPlayer(Player player) {
        this.players.add(player);
    }

    @Override
    public Player getPlayer(String playerId) {
        for (Player player : players) {
            if (player.getPlayerId().contentEquals(playerId)) {
                return player;
            }
        }
        return null;
    }

    @Override
    public boolean removePlayer(Player player) {
        if (this.players.contains(player)) {
            this.players.remove(player);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
        this.gameEngineCallback = gameEngineCallback;
    }

    @Override
    public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
        if (callbacks.contains(gameEngineCallback)) {
            callbacks.remove(gameEngineCallback);
            return true;
        }
        return false;
    }

    @Override
    public Collection<Player> getAllPlayers() {
        return this.players;
    }

    @Override
    public boolean placeBet(Player player, int bet, BetType betType) {
        try {
            player.setBet(bet);
            player.setBetType(betType);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage() + player.getPlayerName());
            return false;
        }
        return true;
    }

    @Override
    public Collection<Slot> getWheelSlots() {
        int[] numbers = {00, 27, 10, 25, 29, 12, 8, 19, 31, 18, 6, 21, 33, 16, 4, 23, 35, 14, 2, 0, 28, 9, 26, 30, 11,
                7, 20, 32, 17, 5, 22, 34, 15, 3, 24, 36, 13, 1};
        Color[] colors = {Color.GREEN00, Color.RED, Color.BLACK, Color.RED, Color.BLACK, Color.RED, Color.BLACK,
                Color.RED, Color.BLACK, Color.RED, Color.BLACK, Color.RED, Color.BLACK, Color.RED, Color.BLACK,
                Color.RED, Color.BLACK, Color.RED, Color.BLACK, Color.GREEN0, Color.BLACK, Color.RED, Color.BLACK,
                Color.RED, Color.BLACK, Color.RED, Color.BLACK, Color.RED, Color.BLACK, Color.RED, Color.BLACK,
                Color.RED, Color.BLACK, Color.RED, Color.BLACK, Color.RED, Color.BLACK, Color.RED,};
        for (int i = 0; i < colors.length; i++) {
            SlotImpl slot = new SlotImpl(i, numbers[i], colors[i]);
            slots.add(slot);
        }
        return this.slots;
    }
}