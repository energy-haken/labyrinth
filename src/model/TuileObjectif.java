package model;

public class TuileObjectif extends Tuile {
    private Objectif m_objectifAssocie;


    /**
     * Enfant de la classe abstraite Tuile
     * @param fixe : Boolean : Si la tuile sur le plateau est fixe ou non
     * @param pattern : Le style de la tuile
     * @param objectifAssocie : L'objectif présent sur la tuile
     */
    public TuileObjectif(boolean fixe, Pattern pattern , int coordoneeX , int coordoneeY  , Objectif objectifAssocie) {
        super(fixe, pattern , coordoneeX , coordoneeY);
        m_objectifAssocie = objectifAssocie;
    }

    /**
     *
     * @return l'objectif présent
     */
    @Override
    public Objectif getObjectif() {
        return m_objectifAssocie;
    }

}
