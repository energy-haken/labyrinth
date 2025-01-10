package model.tiles;

import model.Direction;
import model.Objective;

public abstract class Tile {

    private boolean _fixed;
    private Pattern _pattern;
    private Direction _direction;
    private int _coordinateX;
    private int _coordinateY;
    private Objective _objective;
    private int _name;

    /**
     *
     * @param fixed : Boolean : If the tile on the board is fixed or not
     * @param pattern : The style of the tile
     */
    public Tile(boolean fixed, Pattern pattern, Direction direction, int coordinateX, int coordinateY, int name) {
        this._fixed = fixed;
        this._pattern = pattern;
        this._coordinateX = coordinateX;
        this._coordinateY = coordinateY;
        this._name = name;
        this._objective = null;
        this._direction = direction;
    }

    public Tile(boolean fixed, Pattern pattern, Direction direction, int coordinateX, int coordinateY, int name, Objective objective) {
        this._fixed = fixed;
        this._pattern = pattern;
        this._coordinateX = coordinateX;
        this._coordinateY = coordinateY;
        this._name = name;
        this._objective = objective;
        this._direction = direction;
    }

    public Objective getObjective() {
        return _objective;
    }

    public Direction getDirection() {
        return _direction;
    }

    /**
     * @param direction orientation of the tile
     */
    public void rotateTile(Direction direction) {
        this._direction = direction;
    }

    public boolean isFixed() {
        return _fixed;
    }

    public void setCoordinate(int x, int y) {
        this._coordinateX = x;
        this._coordinateY = y;
    }

    public int getCoordinateX() {
        return _coordinateX;
    }

    public int getCoordinateY() {
        return _coordinateY;
    }

    public Pattern getPattern() {
        return _pattern;
    }

    public abstract boolean checkTileExit(Direction direction);

    public int getName() {
        return _name;
    }
}
