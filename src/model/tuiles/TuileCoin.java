package model.tuiles;

import model.Direction;
import model.Objectif;
import model.Pattern;

public class TuileCoin extends Tuile {

    public TuileCoin(boolean fixe, Direction direction, int coordoneeX, int coordoneeY, int name) {
        super(fixe, Pattern.ANGLE, direction, coordoneeX, coordoneeY, name);
    }

    public TuileCoin(boolean fixe,Direction direction ,int coordoneeX, int coordoneeY, int name, Objectif objectif) {
        super(fixe, Pattern.ANGLE, direction, coordoneeX, coordoneeY, name, objectif);
    }

    @Override
    public boolean checkSortieTuile(Direction direction) {
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
