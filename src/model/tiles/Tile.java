package model.tiles;

import model.Direction;
import model.Objective;

public abstract class Tile {

    private boolean fixed;
    private Pattern pattern;
    private Direction direction;
    private int coordinateX;
    private int coordinateY;
    private Objective objective;

    private int name;

    /**
     *
     * @param fixed : Boolean : If the tile on the board is fixed or not
     * @param pattern : The style of the tile
     */
    public Tile(boolean fixed, Pattern pattern, Direction direction, int coordinateX, int coordinateY, int name) {
        this.fixed = fixed;
        this.pattern = pattern;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.name = name;
        this.objective = null;
        this.direction = direction;
    }

    public Tile(boolean fixed, Pattern pattern, Direction direction, int coordinateX, int coordinateY, int name, Objective objective) {
        this.fixed = fixed;
        this.pattern = pattern;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.name = name;
        this.objective = objective;
        this.direction = direction;
    }

    public Objective getObjective() {
        return objective;
    }

    public Direction getDirection() {
        return direction;
    }

    /**
     * @param direction orientation of the tile
     */
    public void rotateTile(Direction direction) {
        this.direction = direction;
    }

    public boolean isFixed() {
        return fixed;
    }

    public void setCoordinate(int x, int y) {
        this.coordinateX = x;
        this.coordinateY = y;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public abstract boolean checkTileExit(Direction direction);

    public int getName() {
        return name;
    }
}
