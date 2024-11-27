package model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import model.Objectifs ;

public abstract class Tuile {


    private boolean m_fixe;
    private Pattern m_pattern;
    private ArrayList<Joueur> m_joueurs;

    public Tuile(boolean fixe, Pattern pattern) {
        m_fixe = fixe;
        m_pattern = pattern;
    }
    public Objectifs getObjectif(){
        return null ;
    }
}
