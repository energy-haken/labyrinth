package model;

import model.tiles.Tile;

import java.util.ArrayList;

public class Player {
    private ArrayList<Objective> objectiveList;
    private int remainingObjectives;
    private int collectedObjectives;
    private Tile tile;
    private String color;

    /**
     * Creation of a Player
     * @param tile The tile on which the Player will be positioned
     * @param objectiveList The list of objectives that the Player must complete to win
     */
    public Player(Tile tile, String color, ArrayList<Objective> objectiveList) {
        this.tile = tile;
        this.objectiveList = objectiveList; // Can multiple players have the same objective? Answer from the professor: NO
        this.collectedObjectives = 0;
        this.remainingObjectives = 6;
        this.color = color;
    }

    /**
     * Verifies if the objective present on the tile where the player is located is the objective that the player must complete.
     * @return true if the objective is the same / false if it does not match
     */
    public boolean verifyObjective() {
        return tile.getObjective() == objectiveList.get(0);
    }

    public void validateObjective() {
        if (verifyObjective()) {
            objectiveList.remove(0);
            remainingObjectives -= 1;
            collectedObjectives += 1;
        }
    }

    /**
     * Get the number of objectives the player has left to complete
     * @return the number of remaining objectives
     */
    public int getRemainingObjectives() {
        return remainingObjectives;
    }

    /**
     * Get the number of objectives already completed
     * @return the number of collected objectives
     */
    public int getCollectedObjectives() {
        return collectedObjectives;
    }

    /**
     * @return the tile on which the Player is located
     */
    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public String getColor() {
        return color;
    }

    public Objective getCurrentGoal() {
        return objectiveList.get(0);
    }
}
