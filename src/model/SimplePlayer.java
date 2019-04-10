package model;

import model.enumeration.BetType;
import model.interfaces.Player;

public class SimplePlayer implements Player {

    private String playerName;
    private String playerId;
    private int points;
    private int bet;
    private BetType betType;


    public SimplePlayer(String playerId, String playerName, int points) {
        this.playerId = playerId;
        setPlayerName(playerName);
        setPoints(points);
    }

    @Override
    public String getPlayerName() {
        return playerName;
    }

    @Override
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public int getPoints() {
        return this.points;
    }

    @Override
    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String getPlayerId() {
        return this.playerId;
    }

    @Override
    public boolean setBet(int bet) {
        if (bet >= 0 && bet <= points) {
            this.bet = bet;
            return true;
        } else
            return false;
    }

    @Override
    public int getBet() {
        return this.bet;
    }

    @Override
    public BetType getBetType() {
        return this.betType;
    }

    @Override
    public void setBetType(BetType betType) {
        this.betType = betType;
    }

    @Override
    public void resetBet() {
        this.bet = 0;
    }

    @Override
    public String toString() {
        return "SimplePlayer{" +
                "playerName='" + playerName + '\'' +
                ", playerId='" + playerId + '\'' +
                ", points=" + points +
                ", bet=" + bet +
                ", betType=" + betType +
                '}';
    }
}
