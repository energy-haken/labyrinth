package model;

import model.tiles.Tile;

import java.util.ArrayList;

public class Player {
    private ArrayList<Objective> _objectiveList;
    private int _remainingObjectives;
    private int _collectedObjectives;
    private Tile _tile;
    private String _color;

    /**
     * Creation of a Player
     * @param tile The tile on which the Player will be positioned
     * @param objectiveList The list of objectives that the Player must complete to win
     */
    public Player(Tile tile, String color, ArrayList<Objective> objectiveList) {
        this._tile = tile;
        this._objectiveList = objectiveList; // Can multiple players have the same objective? Answer from the professor: NO
        this._collectedObjectives = 0;
        this._remainingObjectives = 6;
        this._color = color;
    }

    /**
     * Verifies if the objective present on the tile where the player is located is the objective that the player must complete.
     * @return true if the objective is the same / false if it does not match
     */
    public boolean verifyObjective() {
        return _tile.getObjective() == _objectiveList.get(0);
    }

    public void validateObjective() {
        if (verifyObjective()) {
            _objectiveList.remove(0);
            _remainingObjectives -= 1;
            _collectedObjectives += 1;
        }
    }

    /**
     * Get the number of objectives the player has left to complete
     * @return the number of remaining objectives
     */
    public int getRemainingObjectives() {
        return _remainingObjectives;
    }

    /**
     * Get the number of objectives already completed
     * @return the number of collected objectives
     */
    public int getCollectedObjectives() {
        return _collectedObjectives;
    }

    /**
     * @return the tile on which the Player is located
     */
    public Tile getTile() {
        return _tile;
    }

    public void setTile(Tile tile) {
        this._tile = tile;
    }

    public String getColor() {
        return _color;
    }

    public Objective getCurrentGoal() {
        return _objectiveList.get(0);
    }
}
