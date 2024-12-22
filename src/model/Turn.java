package model;

import java.util.ArrayList;

public class Turn {
    private int id;
    private Player currentPlayer;
    private ArrayList<Player> playerList = new ArrayList<>();

    /**
     * Create the turn
     * @param playerList the list of players
     */
    public Turn(ArrayList<Player> playerList) {
        id = 0;
        this.playerList = playerList;
        this.currentPlayer = playerList.get(0);
    }

    public Player nextTurn() {
        id++;
        currentPlayer.verifyObjective();
        currentPlayer = playerList.get(id % 4);
        return currentPlayer;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
