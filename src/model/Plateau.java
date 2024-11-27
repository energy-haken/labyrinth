package model;

import java.util.ArrayList;

public class Plateau {
    private ArrayList<ArrayList<Tuile>> m_tuiles;
    private ArrayList<Joueur> m_joueurs;


    public Tuile pousserTuile(Tuile tuilePousse, String direction){
        for(int i = 0; i < m_tuiles.size(); i++){
            for(int j = 0; j < m_tuiles.get(i).size(); j++){
                if(m_tuiles.get(i).get(j).equals(tuilePousse)){
                   // m_tuiles.get(i).get(j+1) = this.changerTuile(m_tuiles.get(i).get(j+1) , m_tuiles.get(i).get(j)) ;
                }
            }
        }
        return null;
    }

    private Tuile changerTuile(Tuile tuileOriginal , Tuile tuileNew) {
        return tuileOriginal = tuileNew ;
    }
}
