package model;

import java.util.ArrayList;

public class Tour {
    private int m_id;
    private Joueur m_joueurDuTour;


    /**
     * Create the tour
     * @param joueur the first player who will start the game
     */
    public Tour(Joueur joueur) {
        m_id = 1 ;
        this.m_joueurDuTour = joueur;
    }

    public Joueur tourSuivant(ArrayList<Joueur> joueurs){
        m_id += 1 ;
        for(int i = 0 ; i < joueurs.size() ; i++){
            if(joueurs.get(i).equals(m_joueurDuTour) && i != joueurs.size()-1){
                m_joueurDuTour = joueurs.get(i+1);
                return m_joueurDuTour;
            }
            else {
                m_joueurDuTour = joueurs.getFirst();
                return m_joueurDuTour;
            }
        }
        return m_joueurDuTour;

    }

    public Joueur getJoueurDuTour(){
        return m_joueurDuTour;
    }
}
