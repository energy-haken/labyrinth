package model;

public abstract class Tuile {


    private boolean m_fixe;
    private Pattern m_pattern;
    private Direction m_direction;
    private int m_coordoneeX;
    private int m_coordoneeY;



    /**
     *
     * @param fixe : Boolean : Si la tuile sur le plateau est fixe ou non
     * @param pattern : Le style de la tuile
     */
    public Tuile(boolean fixe, Pattern pattern , int coordoneeX , int coordoneeY) {
        m_fixe = fixe;
        m_pattern = pattern;
        m_coordoneeX = coordoneeX;
        m_coordoneeY = coordoneeY;
    }


    public abstract Objectif getObjectif() ;

    public Direction getDirection() {
        return m_direction;
    }

    /**
     *:
     * @param direction orientation de la tuile
     * @return la nouvelle tuile avec la nouvelle rotation
     */
    public void tournerTuile(Direction direction) {
        this.m_direction = direction;
    }

    public boolean isFixe() {
        return m_fixe;
    }

    public int getCoordoneeX() {
        return m_coordoneeX;
    }

    public int getCoordoneeY() {
        return m_coordoneeY;
    }

    public Pattern getPattern() {
        return m_pattern;
    }

    public void setDirection(Direction direction) {
        this.m_direction = direction;
    }

    public boolean checkSortieTuile(Direction direction){
        switch (this.getPattern()){
            case T :
                switch(direction){
                    case NORTH :
                        if(this.getDirection() == Direction.SOUTH){
                            return false;
                        }
                        else if(this.getDirection() == Direction.WEST){
                            return true;
                        }
                        else if(this.getDirection() == Direction.EAST){
                            return true;
                        }
                        else{
                            return true;
                        }

                    case EAST :
                        if(this.getDirection() == Direction.WEST){
                            return false;
                        }
                        else if(this.getDirection() == Direction.NORTH){
                            return true;
                        }
                        else if(this.getDirection() == Direction.SOUTH){
                            return true;
                        }
                        else{
                            return true;
                        }
                    case SOUTH :
                        if(this.getDirection() == Direction.NORTH){
                            return false;
                        }
                        else if(this.getDirection() == Direction.WEST){
                            return true;
                        }
                        else if(this.getDirection() == Direction.EAST){
                            return true;
                        }
                        else{
                            return true;
                        }

                    case WEST :
                        if(this.getDirection() == Direction.EAST){
                            return false;
                        }
                        else if(this.getDirection() == Direction.NORTH){
                            return true;
                        }
                        else if(this.getDirection() == Direction.SOUTH){
                            return true;
                        }
                        else{
                            return true;
                        }

                }
                break;
            case DROIT:
                switch(direction){
                    case NORTH :
                        if(this.getDirection() == Direction.SOUTH){
                            return true;
                        }
                        else if(this.getDirection() == Direction.WEST){
                            return false;
                        }
                        else if(this.getDirection() == Direction.EAST){
                            return false;
                        }
                        else{
                            return true;
                        }

                    case EAST :
                        if(this.getDirection() == Direction.WEST){
                            return true;
                        }
                        else if(this.getDirection() == Direction.NORTH){
                            return false;
                        }
                        else if(this.getDirection() == Direction.SOUTH){
                            return false;
                        }
                        else{
                            return true;
                        }

                    case SOUTH :
                        if(this.getDirection() == Direction.NORTH){
                            return true;
                        }
                        else if(this.getDirection() == Direction.WEST){
                            return false;
                        }
                        else if(this.getDirection() == Direction.EAST){
                            return false;
                        }
                        else{
                            return true;
                        }

                    case WEST :
                        if(this.getDirection() == Direction.EAST){
                            return true;
                        }
                        else if(this.getDirection() == Direction.NORTH){
                            return false;
                        }
                        else if(this.getDirection() == Direction.SOUTH){
                            return false;
                        }
                        else{
                            return true;
                        }

                }
                break;
            case ANGLE:
                switch(direction){
                    case NORTH :
                        if(this.getDirection() == Direction.SOUTH){
                            return true;
                        }
                        else if(this.getDirection() == Direction.WEST){
                            return false;
                        }
                        else if(this.getDirection() == Direction.EAST){
                            return true;
                        }
                        else{
                            return false;
                        }

                    case EAST :
                        if(this.getDirection() == Direction.WEST){
                            return true;
                        }
                        else if(this.getDirection() == Direction.NORTH){
                            return false;
                        }
                        else if(this.getDirection() == Direction.SOUTH){
                            return true;
                        }
                        else{
                            return false;
                        }

                    case SOUTH :
                        if(this.getDirection() == Direction.NORTH){
                            return true;
                        }
                        else if(this.getDirection() == Direction.WEST){
                            return true;
                        }
                        else if(this.getDirection() == Direction.EAST){
                            return false;
                        }
                        else{
                            return false;
                        }

                    case WEST :
                        if(this.getDirection() == Direction.EAST){
                            return true;
                        }
                        else if(this.getDirection() == Direction.NORTH){
                            return true;
                        }
                        else if(this.getDirection() == Direction.SOUTH){
                            return false;
                        }
                        else{
                            return false;
                        }

                }
                break;
        }
        return false;
    }
}
