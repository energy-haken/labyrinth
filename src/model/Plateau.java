package model;

import view.PlateauWindow;
import model.Direction ;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
        for (int j = 0; j < 7; j+=2) {
            for (int k = 0; k < 7; k+=2) {
                if((j==0||j==6)&&(k==0||k==6)){
                    this.m_plateau.get(j).set(k,new TuileLibre(true, Pattern.ANGLE , j , k));

                }
                else{
                    this.m_plateau.get(j).set(k,new TuileObjectif(true, Pattern.T, j , k ,listeObjectif.get(listeObjectif.size()-1)));
                    listeObjectif.remove(listeObjectif.size()-1);
                }
                if((j == 0)&&(k == 0)){
                    this.m_plateau.get(j).get(k).setDirection(Direction.WEST);
                }
                if((j == 0)&&(k == 6)){
                    this.m_plateau.get(j).get(k).setDirection(Direction.NORTH);
                }
                if((j == 6)&&(k == 0)){
                    this.m_plateau.get(j).get(k).setDirection(Direction.SOUTH);
                }
                if((j == 6)&&(k == 6)){
                    this.m_plateau.get(j).get(k).setDirection(Direction.EAST);
                }
            }
        }
        /*
        for (int i = 4; i < 38; i++) {
            if (i<10) {
                placable.add(new TuileObjectif(false, Pattern.ANGLE, i , i , listeObjectif.get(listeObjectif.size()-1)));
                listeObjectif.remove(listeObjectif.size()-1);


            } else if (10<=i && i<20) {
                placable.add(new TuileLibre(false, Pattern.ANGLE, i , i ));

            } else if (20<=i && i<32) {
                placable.add(new TuileLibre(false, Pattern.DROIT, i , i ));

            } else if (32<=i && i<38) {
                placable.add(new TuileObjectif(false, Pattern.T, i , i , listeObjectif.get(listeObjectif.size()-1)));
                listeObjectif.remove(listeObjectif.size()-1);

            }



        }
            */

        // Generation des Tuiles Déplacables
        for(int i = 0 ; i < 7 ; i++) {
            for (int j = 0; j < 7; j++) {
            if (m_plateau.get(i).get(j) == null) {
                Pattern pattern = checkPattern();
                Direction direction = checkDirection();
                if (checkObjectif() && !listeObjectif.isEmpty()) {
                m_plateau.get(i).set(j, new TuileObjectif(false, pattern, i, j, listeObjectif.get(listeObjectif.size() - 1)));
                m_plateau.get(i).get(j).setDirection(direction);
                placable.add(m_plateau.get(i).get(j));

                listeObjectif.remove(listeObjectif.size() - 1);

                // DEBUG
                System.out.println("Objectif associé à la tuile : " + m_plateau.get(i).get(j).getObjectif());

                }
                else {
                    m_plateau.get(i).set(j, new TuileLibre(false, pattern, i, j));
                    m_plateau.get(i).get(j).setDirection(direction);
                    placable.add(m_plateau.get(i).get(j));
                }

            }
            }
        }


        for(int i = 0 ; i < 7 ; i++){
            for(int j = 0 ; j < 7 ; j++){
                if((i == 0 && j == 2) || (i == 0 && j == 4) || (i == 2 && j == 4)){
                    this.m_plateau.get(i).get(j).setDirection(Direction.SOUTH);
                }
                if((i == 2 && j == 0) || (i == 4 && j == 0) || (i == 2 && j == 2)){
                    this.m_plateau.get(i).get(j).setDirection(Direction.EAST);
                }
                if((i == 2 && j == 6) || (i == 4 && j == 6) || (i == 4 && j == 4)){
                    this.m_plateau.get(i).get(j).setDirection(Direction.WEST);
                }
                if((i == 6 && j == 2) || (i == 6 && j == 4) || (i == 4 && j == 2)){
                    this.m_plateau.get(i).get(j).setDirection(Direction.NORTH);
                }


            }
        }

        /*
        for (List<Tuile> ligne: this.m_plateau) {
            for (Tuile t: ligne) {
                if(t == null){
                    t = placable.get( (int) (Math.random() * placable.size()));
                }
            }
        }

        */

    }


    /**
     * Pousse une tuile sur le plateau
     * @param direction : la direction dans laquelle la ligne va être décalé (NORTH, SOUTH, EAST , WEST)
     * @return
     */

    // Fonction faite avec COPILOT
    public Tuile pousserTuile(Direction direction, Integer colonne) {
        Tuile tuileSortante = null;
        switch (direction) {
            case NORTH:
                tuileSortante = m_plateau.get(0).get(colonne);
                for (int i = 0; i < m_plateau.size() - 1; i++) {
                    m_plateau.get(i).set(colonne, m_plateau.get(i + 1).get(colonne));
                }
                m_plateau.get(m_plateau.size() - 1).set(colonne, m_tuile);
                break;
            case SOUTH:
                tuileSortante = m_plateau.get(m_plateau.size() - 1).get(colonne);
                for (int i = m_plateau.size() - 1; i > 0; i--) {
                    m_plateau.get(i).set(colonne, m_plateau.get(i - 1).get(colonne));
                }
                m_plateau.get(0).set(colonne, m_tuile);
                break;
            case EAST:
                tuileSortante = m_plateau.get(colonne).get(m_plateau.size() - 1);
                for (int i = m_plateau.size() - 1; i > 0; i--) {
                    m_plateau.get(colonne).set(i, m_plateau.get(colonne).get(i - 1));
                }
                m_plateau.get(colonne).set(0, m_tuile);
                break;
            case WEST:
                tuileSortante = m_plateau.get(colonne).get(0);
                for (int i = 0; i < m_plateau.size() - 1; i++) {
                    m_plateau.get(colonne).set(i, m_plateau.get(colonne).get(i + 1));
                }
                m_plateau.get(colonne).set(m_plateau.size() - 1, m_tuile);
                break;
        }
        m_tuile = tuileSortante;
        return tuileSortante;
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

    public List<List<Tuile>> getPlateau() {
        return m_plateau;
    }

    public Pattern checkPattern(){
        /*20 angles dont 4 sont fixes et 16 sont déplaçables, 6 obj
        12 sections droites toutes déplaçables,
        18 en forme de "T" dont 12 sont fixes et 6 sont déplaçables. 18 obj
         */

        int angle = 16 ;
        int droit = 12 ;
        int t = 6 ;

        Random random = new Random();
        int nb = random.nextInt(3);
        switch (nb){
            case 0 :
                if(angle > 0){
                    angle -= 1 ;
                    return Pattern.ANGLE;
                }
                else{
                    return checkPattern();
                }

            case 1 :
                if(droit > 0){
                    droit -= 1 ;
                    return Pattern.DROIT;
                }
                else{
                    return checkPattern();
                }

            case 2 :
                if(t > 0){
                    t -= 1 ;
                    return Pattern.T;
                }
                else{
                    return checkPattern();
                }


        }


        return null;
    }

    public Direction checkDirection(){
        Random random = new Random();
        int nb = random.nextInt(4);
        switch (nb){
            case 0 :
                return Direction.NORTH;
            case 1 :
                return Direction.SOUTH;
            case 2 :
                return Direction.EAST;
            case 3 :
                return Direction.WEST;
        }
        return null;
    }

    public boolean checkObjectif(){

        Random random = new Random();
        int nb = random.nextInt(2);
        switch (nb){
            case 0 :
                return true;
            case 1 :
                return false;
        }
        return false;
    }
}
