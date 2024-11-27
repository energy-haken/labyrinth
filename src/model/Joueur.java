package model;

import java.util.ArrayList;

public class Joueur {
    private ArrayList<Objectifs> m_listeObjectifs;
    private int m_objectifRestant;
    private int m_objectifRecup;
    private Tuile m_tuile;

    public Joueur(Tuile tuile , ArrayList<Objectifs> listeObjectifs){
        m_tuile = tuile;
        m_listeObjectifs = listeObjectifs ; // Est ce que plusieurs joueurs peuvent avoir le même objectif ? Réponse du prof : NON
        m_objectifRecup = 0;
        m_objectifRestant = 6;
    }

    /**
     *
     * Verifie si l'objectif présent sur la case ou est le joueur est l'objectif que le joueur doit réalisé.
     * @return true si l'objectif est le même / false si ca ne correspond pas
     */

    public boolean verifObjectif(){
        if(m_tuile.getObjectif() == m_listeObjectifs.get(0)){
            return true;
        }
        return false;
    }
}
