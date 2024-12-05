package model;

import view.PlateauWindow;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Plateau {
    private List<List<Tuile>> m_plateau = Arrays.asList(Arrays.asList(null,null,null,null,null,null,null),
                                                        Arrays.asList(null,null,null,null,null,null,null),
                                                        Arrays.asList(null,null,null,null,null,null,null),
                                                        Arrays.asList(null,null,null,null,null,null,null),
                                                        Arrays.asList(null,null,null,null,null,null,null),
                                                        Arrays.asList(null,null,null,null,null,null,null),
                                                        Arrays.asList(null,null,null,null,null,null,null));
    private ArrayList<Joueur> m_joueurs;

    private Tuile m_tuile;


    public Plateau(ArrayList<Joueur> joueurs){

        m_joueurs = joueurs;

        ArrayList<Tuile> placable = new ArrayList<Tuile>();
        ArrayList<Objectif> listeObjectif = new ArrayList<>() ;


        for(int i = 0 ; i < 24 ; i++){
            listeObjectif.add(Objectif.values()[i]);
        }

        /*20 angles dont 4 sont fixes et 16 sont déplaçables, 6 obj
        12 sections droites toutes déplaçables,
        18 en forme de "T" dont 12 sont fixes et 6 sont déplaçables. 18 obj
        Pour le moment elles sont créé dans le vent, il faut encore les associer */
        for (int j = 0; j < 6; j+=2) {
            for (int k = 0; k < 6; k+=2) {
                if((j==0||j==6)&&(k==0||k==6)){
                    this.m_plateau.get(j).set(k,new TuileLibre(true, Pattern.ANGLE));

                }else{
                    this.m_plateau.get(j).set(k,new TuileObjectif(true, Pattern.T, listeObjectif.get(listeObjectif.size()-1)));
                    listeObjectif.remove(listeObjectif.size()-1);
                }
            }
        }
        for (int i = 4; i < 38; i++) {




            if (4<=i && i<10) {
                placable.add(new TuileObjectif(false, Pattern.ANGLE, listeObjectif.get(listeObjectif.size()-1)));
                listeObjectif.remove(listeObjectif.size()-1);
            } else if (10<=i && i<20) {
                placable.add(new TuileLibre(false, Pattern.ANGLE));
            } else if (20<=i && i<32) {
                placable.add(new TuileLibre(false, Pattern.DROIT));
            } else if (32<=i && i<38) {
                placable.add(new TuileObjectif(false, Pattern.T, listeObjectif.get(listeObjectif.size()-1)));
                listeObjectif.remove(listeObjectif.size()-1);
            }
        }
        for (List<Tuile> ligne: this.m_plateau) {
            for (Tuile t: ligne) {
                if(t == null){
                    t = placable.get( (int) (Math.random() * placable.size()));
                }
            }
        }
    }

    /**
     * Pousse une tuile sur le plateau
     * @param direction : la direction dans laquelle la ligne va être décalé (NORTH, SOUTH, EAST , WEST)
     * @return
     */

    // PAS FINI
    public Tuile pousserTuile(Direction direction, Integer colonne){
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


    public Tuile getTuile(int coordoneeX , int coordoneeY){
        return m_plateau.get(coordoneeX).get(coordoneeY);
    }

}
