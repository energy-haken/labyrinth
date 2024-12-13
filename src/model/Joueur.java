package model;

import model.tuiles.Tuile;

import java.util.ArrayList;

public class Joueur {
    private ArrayList<Objectif> m_listeObjectifs;
    private int m_objectifRestant;
    private int m_objectifRecup;
    private Tuile m_tuile;

    /**
     * Creation d'un Joueur
     * @param tuile La tuile sur lequel le Joueur va être positionné
     * @param listeObjectifs La liste des objectifs que le Joueur devra effectuer pour gagner
     */
    public Joueur(Tuile tuile , ArrayList<Objectif> listeObjectifs){
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

    public void validationObjectif(){
        if(verifObjectif()){
            m_listeObjectifs.remove(0);
            m_objectifRestant -= 1 ;
            m_objectifRecup += 1 ;
        }
        else{

        }
    }

    public void deplacement(Direction versOu, int nombreDeCase){

    }

    /**
     * Recuperer le nombre d'objectifs qu'il reste au joueur a faire
     * @return le nombre d'objectifs restant
     */
    public int getObjectifRestant() {
        return m_objectifRestant;
    }

    /**
     * Recuper le nombre d'objectifs déja réalisé
     * @return le nombre d'objectifs déja réalisé
     */
    public int getObjectifRecup() {
        return m_objectifRecup;
    }

    /**
     *
     * @return la tuile sur laquelle est le Joueur
     */
    public Tuile getTuile() {
        return m_tuile;
    }

    public void setTuile(Tuile tuile) {
        m_tuile = tuile;
    }
}
