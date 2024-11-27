package model;

public class TuileObjectif extends Tuile {
    private Objectifs m_objectifAssocie;


    public TuileObjectif(boolean fixe, Pattern pattern , Objectifs objectifAssocie) {
        super(fixe, pattern);
        m_objectifAssocie = objectifAssocie;
    }

    @Override
    public Objectifs getObjectif() {
        return m_objectifAssocie;
    }

}
