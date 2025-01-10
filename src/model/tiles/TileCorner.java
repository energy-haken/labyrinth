package model.tiles;

import model.Direction;
import model.Objective;

public class TileCorner extends Tile {

    public TileCorner(boolean fixe, Direction direction, int coordoneeX, int coordoneeY, int name) {
        super(fixe, Pattern.CORNER, direction, coordoneeX, coordoneeY, name);
    }

    public TileCorner(boolean fixe, Direction direction , int coordoneeX, int coordoneeY, int name, Objective objective) {
        super(fixe, Pattern.CORNER, direction, coordoneeX, coordoneeY, name, objective);
    }

    @Override
    public boolean checkTileExit(Direction direction) {
        switch (direction) {
            case NORTH:
                if (this.getDirection() == Direction.SOUTH) {
                    return true;
                } else if (this.getDirection() == Direction.WEST) {
                    return false;
                } else if (this.getDirection() == Direction.EAST) {
                    return true;
                } else {
                    return false;
                }

            case EAST:
                if (this.getDirection() == Direction.WEST) {
                    return true;
                } else if (this.getDirection() == Direction.NORTH) {
                    return false;
                } else if (this.getDirection() == Direction.SOUTH) {
                    return true;
                } else {
                    return false;
                }

            case SOUTH:
                if (this.getDirection() == Direction.NORTH) {
                    return true;
                } else if (this.getDirection() == Direction.WEST) {
                    return true;
                } else if (this.getDirection() == Direction.EAST) {
                    return false;
                } else {
                    return false;
                }

            case WEST:
                if (this.getDirection() == Direction.EAST) {
                    return true;
                } else if (this.getDirection() == Direction.NORTH) {
                    return true;
                } else if (this.getDirection() == Direction.SOUTH) {
                    return false;
                } else {
                    return false;
                }


        }
        return false;
    }
}
