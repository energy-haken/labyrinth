package model;

import java.util.ArrayList;

public class Tour {
    private int m_id;
    private Joueur m_joueurDuTour;
    private ArrayList<Joueur> m_playerList = new ArrayList<>();


    /**
     * Create the tour
     * @param playerList la liste des joueurs
     */
    public Tour(ArrayList<Joueur> playerList) {
        m_id = 0 ;
        m_playerList = playerList;
        this.m_joueurDuTour = playerList.get(0);
    }

    public Joueur tourSuivant(){
        m_id++;
        m_joueurDuTour = m_playerList.get(m_id%4);
        return m_joueurDuTour;

    }

    public Joueur getJoueurDuTour(){
        return m_joueurDuTour;
    }
}
