package model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import model.Objectifs ;

public abstract class Tuile {


    private boolean m_fixe;
    private Pattern m_pattern;
    private ArrayList<Joueur> m_joueurs;


    /**
     *
     * @param fixe : Boolean : Si la tuile sur le plateau est fixe ou non
     * @param pattern : Le style de la tuile
     */
    public Tuile(boolean fixe, Pattern pattern) {
        m_fixe = fixe;
        m_pattern = pattern;
    }


    public abstract Objectifs getObjectif() ;


    /**
     *
     * @param tuile La tuile a tourné
     * @param rotation Le degre de rotation de la tuile = 90, 180 , 270 , 0
     * @return la nouvelle tuile avec la nouvelle rotation
     */
    public Tuile tournerTuile(Tuile tuile, int rotation) {
        return null;
    }
}
