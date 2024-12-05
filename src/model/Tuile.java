package model;

public abstract class Tuile {


    private boolean m_fixe;
    private Pattern m_pattern;
    private Direction m_direction;



    /**
     *
     * @param fixe : Boolean : Si la tuile sur le plateau est fixe ou non
     * @param pattern : Le style de la tuile
     */
    public Tuile(boolean fixe, Pattern pattern) {
        m_fixe = fixe;
        m_pattern = pattern;
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
}
