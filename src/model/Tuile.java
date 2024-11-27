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

    /**
     *
     * @return L'objectif associé (par defaut : null)
     */
    public Objectifs getObjectif(){
        return null ;
    }
}
