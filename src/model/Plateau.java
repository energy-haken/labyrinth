package model;

import view.PlateauWindow;

import java.util.ArrayList;

public class Plateau {
    private ArrayList<ArrayList<Tuile>> m_plateau;
    private ArrayList<Joueur> m_joueurs;

    private Tuile m_tuile;


    public Plateau(ArrayList<Joueur> joueurs, ArrayList<ArrayList<Tuile>> plateau, Tuile tuile){
        m_joueurs = joueurs;
        m_plateau = plateau;
        m_tuile = tuile;
    }

    /**
     * Pousse une tuile sur le plateau
     * @param direction : la direction dans laquelle la ligne va être décalé (NORTH, SOUTH, EAST , WEST)
     * @return
     */

    // PAS FINI
    public Tuile pousserTuile(Direction direction){
        for(int i = 0; i < m_plateau.size(); i++){
            for(int j = 0; j < m_plateau.get(i).size(); j++){
                if(m_plateau.get(i).get(j).equals(m_tuile)){
                   // m_plateau.get(i).get(j+1) = this.changerTuile(m_plateau.get(i).get(j+1) , m_plateau.get(i).get(j)) ;
                }
            }
        }
        return null;
    }

    /**
     * Changer la tuile avec une autre (utilisé lors du poussage d'une ligne)
     * @param tuileOriginal : La tuile qui va être remplacé
     * @param tuileNew : La nouvelle tuile qui l'a remplace
     * @return La tuille nouvellement remplacé
     */
    // PAS FINI
    public Tuile changerTuile(Tuile tuileOriginal , Tuile tuileNew) {
        return tuileOriginal = tuileNew ;
    }

    /**
     *
     * @param direction orientation de la tuile
     * @return la nouvelle tuile avec la nouvelle rotation
     */
    public Tuile tournerTuile( Direction direction){
        return null ;
    }

}
