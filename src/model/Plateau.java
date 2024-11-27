package model;

import java.util.ArrayList;

public class Plateau {
    private ArrayList<ArrayList<Tuile>> m_tuiles;
    private ArrayList<Joueur> m_joueurs;


    /**
     * Pousse une tuile sur le plateau
     * @param tuilePousse la tuile poussé a un endroit sur le plateau
     * @param direction : la direction dans laquelle la ligne va être décalé (NORTH, SOUTH, EAST , WEST)
     * @return
     */

    // PAS FINI
    public Tuile pousserTuile(Tuile tuilePousse, Direction direction){
        for(int i = 0; i < m_tuiles.size(); i++){
            for(int j = 0; j < m_tuiles.get(i).size(); j++){
                if(m_tuiles.get(i).get(j).equals(tuilePousse)){
                   // m_tuiles.get(i).get(j+1) = this.changerTuile(m_tuiles.get(i).get(j+1) , m_tuiles.get(i).get(j)) ;
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
    private Tuile changerTuile(Tuile tuileOriginal , Tuile tuileNew) {
        return tuileOriginal = tuileNew ;
    }
}
