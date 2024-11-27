package model;

public class TuileObjectif extends Tuile {
    private Objectifs m_objectifAssocie;


    /**
     * Enfant de la classe abstraite Tuile
     * @param fixe : Boolean : Si la tuile sur le plateau est fixe ou non
     * @param pattern : Le style de la tuile
     * @param objectifAssocie : L'objectif présent sur la tuile
     */
    public TuileObjectif(boolean fixe, Pattern pattern , Objectifs objectifAssocie) {
        super(fixe, pattern);
        m_objectifAssocie = objectifAssocie;
    }

    @Override
    public Objectifs getObjectif() {
        return m_objectifAssocie;
    }

}
