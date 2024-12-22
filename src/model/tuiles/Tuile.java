package model.tuiles;

import model.Direction;
import model.Objectif;

public abstract class Tuile {


    private boolean m_fixe;
    private Pattern m_pattern;
    private Direction m_direction;
    private int m_coordoneeX;
    private int m_coordoneeY;
    private Objectif m_objectif ;

    private int m_name;


    /**
     *
     * @param fixe : Boolean : Si la tuile sur le plateau est fixe ou non
     * @param pattern : Le style de la tuile
     */
    public Tuile(boolean fixe, Pattern pattern , Direction direction, int coordoneeX , int coordoneeY, int name) {
        m_fixe = fixe;
        m_pattern = pattern;
        m_coordoneeX = coordoneeX;
        m_coordoneeY = coordoneeY;
        m_name = name;
        m_objectif = null;
        m_direction = direction;
    }

    public Tuile(boolean fixe, Pattern pattern , Direction direction, int coordoneeX , int coordoneeY, int name , Objectif objectif) {
        m_fixe = fixe;
        m_pattern = pattern;
        m_coordoneeX = coordoneeX;
        m_coordoneeY = coordoneeY;
        m_name = name;
        m_objectif = objectif;
        m_direction = direction;
    }


    public Objectif getObjectif(){
        return m_objectif;
    }

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

    public void setCoordonee(int x, int y){
        m_coordoneeX = x;
        m_coordoneeY = y;
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



    public abstract boolean checkSortieTuile(Direction direction) ;

    public int getName(){ return m_name; }
}
