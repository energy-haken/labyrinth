package model;

import java.util.ArrayList;

public class Turn {
    private int _id;
    private Player _currentPlayer;
    private ArrayList<Player> _playerList = new ArrayList<>();

    /**
     * Create the turn
     * @param playerList the list of players
     */
    public Turn(ArrayList<Player> playerList) {
        _id = 0;
        this._playerList = playerList;
        this._currentPlayer = playerList.get(0);
    }

    public Player nextTurn() {
        _id++;
        _currentPlayer.validateObjective();
        _currentPlayer = _playerList.get(_id % 4);
        return _currentPlayer;
    }

    public Player getCurrentPlayer() {
        return _currentPlayer;
    }
}
