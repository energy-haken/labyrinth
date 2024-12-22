package model;

import model.tuiles.*;

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
    private ArrayList<Joueur> m_joueurs = new ArrayList<>();

    private Tuile m_tuile;


    public Plateau(){



        ArrayList<Objectif> listeObjectif = new ArrayList<>() ;
        ArrayList<Objectif> objectifs1 = new ArrayList<>() ;
        ArrayList<Objectif> objectifs2 = new ArrayList<>() ;
        ArrayList<Objectif> objectifs3 = new ArrayList<>() ;
        ArrayList<Objectif> objectifs4 = new ArrayList<>() ;

        for(int i = 0 ; i < 24 ; i++){
            listeObjectif.add(Objectif.values()[i]);
        }

        for(int i = 0 ; i < listeObjectif.size()/4 ; i++){
            objectifs1.add(listeObjectif.get(i));
        }
        for(int i = 6 ; i < listeObjectif.size()/4 + 6 ; i++){
            objectifs2.add(listeObjectif.get(i));
        }
        for(int i = 12 ; i < listeObjectif.size()/4 + 12; i++){
            objectifs3.add(listeObjectif.get(i));
        }
        for(int i = 18 ; i < listeObjectif.size() ; i++){
            objectifs4.add(listeObjectif.get(i));
        }






        int numTuile = 0;
        /*20 angles dont 4 sont fixes et 16 sont déplaçables, 6 obj
        12 sections droites toutes déplaçables,
        18 en forme de "T" dont 12 sont fixes et 6 sont déplaçables. 18 obj
        Pour le moment elles sont créé dans le vent, il faut encore les associer */

        //4Af |12 TfO
        for (int j = 0; j < 7; j+=2) {
            for (int k = 0; k < 7; k+=2) {
                if((j==0||j==6)&&(k==0||k==6)){
                    this.m_plateau.get(j).set(k,new TuileCoin(true, Direction.WEST, j , k, numTuile));
                    numTuile++;

                }
                else{
                    this.m_plateau.get(j).set(k,new TuileT(true, Direction.WEST, j , k ,numTuile , listeObjectif.get(listeObjectif.size()-1)));
                    numTuile++;
                    listeObjectif.remove(listeObjectif.size()-1);
                }
                if((j==0)&&(k==0)){
                    Joueur joueurRouge = new Joueur(m_plateau.get(0).get(0) , "Rouge", objectifs1) ;
                    m_joueurs.add(joueurRouge) ;

                }
                if((j == 0)&&(k == 6)){
                    this.m_plateau.get(j).get(k).tournerTuile(Direction.NORTH);

                    Joueur joueurVert = new Joueur(m_plateau.get(0).get(6) , "Vert", objectifs3) ;
                    m_joueurs.add(joueurVert) ;


                }
                if((j == 6)&&(k == 0)){
                    this.m_plateau.get(j).get(k).tournerTuile(Direction.SOUTH);

                    Joueur joueurBleu = new Joueur(m_plateau.get(6).get(0) , "Bleu", objectifs2) ;
                    m_joueurs.add(joueurBleu) ;


                }
                if((j == 6)&&(k == 6)){
                    this.m_plateau.get(j).get(k).tournerTuile(Direction.EAST);

                    Joueur joueurJaune = new Joueur(m_plateau.get(6).get(6) , "Jaune", objectifs4) ;
                    m_joueurs.add(joueurJaune) ;
                }
            }
        }


        // Generation des Tuiles Déplacables
        for(int i = 0 ; i < 7 ; i++) {
            for (int j = 0; j < 7; j++) {
            if (m_plateau.get(i).get(j) == null) {
                Pattern pattern = checkPattern();
                Direction direction = checkDirection();
                if (checkObjectif() && !listeObjectif.isEmpty()) { // Tuile avec objectif
                    if(pattern == Pattern.T){
                        m_plateau.get(i).set(j , new TuileT(false , direction , i , j , numTuile, listeObjectif.get(listeObjectif.size() -1))) ;
                        numTuile++;
                    }
                    else if(pattern == Pattern.DROIT){
                        m_plateau.get(i).set(j , new TuileLigne(false , direction , i , j , numTuile, listeObjectif.get(listeObjectif.size() -1))) ;
                        numTuile++;
                    }
                    else{
                        m_plateau.get(i).set(j , new TuileCoin(false , direction , i , j , numTuile, listeObjectif.get(listeObjectif.size() -1))) ;
                        numTuile++;
                    }
                listeObjectif.remove(listeObjectif.size() - 1);
                }
                else { // Tuile Sans Objectif
                    if(pattern == Pattern.T){
                        m_plateau.get(i).set(j , new TuileT(false , direction , i , j , numTuile)) ;
                        numTuile++;
                    }
                    else if(pattern == Pattern.DROIT){
                        m_plateau.get(i).set(j , new TuileLigne(false , direction , i , j , numTuile)) ;
                        numTuile++;
                    }
                    else{
                        m_plateau.get(i).set(j , new TuileCoin(false , direction , i , j , numTuile)) ;
                        numTuile++;
                    }

                }

            }
            }
        }


        for(int i = 0 ; i < 7 ; i++){
            for(int j = 0 ; j < 7 ; j++){
                if((i == 0 && j == 2) || (i == 0 && j == 4) || (i == 2 && j == 4)){
                    this.m_plateau.get(i).get(j).tournerTuile(Direction.SOUTH);
                }
                if((i == 2 && j == 0) || (i == 4 && j == 0) || (i == 2 && j == 2)){
                    this.m_plateau.get(i).get(j).tournerTuile(Direction.EAST);
                }
                if((i == 2 && j == 6) || (i == 4 && j == 6) || (i == 4 && j == 4)){
                    this.m_plateau.get(i).get(j).tournerTuile(Direction.WEST);
                }
                if((i == 6 && j == 2) || (i == 6 && j == 4) || (i == 4 && j == 2)){
                    this.m_plateau.get(i).get(j).tournerTuile(Direction.NORTH);
                }


            }
        }



        // Ajout du Joueur



        // Ajout de La 50eme Tuile

        Pattern pattern = checkPattern();
        if(pattern == Pattern.T){
            this.m_tuile = new TuileT(false , Direction.NORTH ,-1 , -1 , numTuile) ;
        }
        else if(pattern == Pattern.DROIT){
            this.m_tuile = new TuileLigne(false , Direction.NORTH , -1 , -1 , numTuile) ;
        }
        else{
            this.m_tuile = new TuileCoin(false , Direction.NORTH , -1 , -1 , numTuile) ;
        }
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
                    m_plateau.get(i).get(colonne).setCoordonee(i,colonne);
                }
                m_plateau.get(m_plateau.size() - 1).set(colonne, m_tuile);
                m_tuile.setCoordonee(m_plateau.size() - 1, colonne);
                break;
            case SOUTH:
                tuileSortante = m_plateau.get(m_plateau.size() - 1).get(colonne);
                for (int i = m_plateau.size() - 1; i > 0; i--) {
                    m_plateau.get(i).set(colonne, m_plateau.get(i - 1).get(colonne));
                    m_plateau.get(i).get(colonne).setCoordonee(i,colonne);
                }
                m_plateau.get(0).set(colonne, m_tuile);
                m_tuile.setCoordonee(0, colonne);

                break;
            case EAST:
                tuileSortante = m_plateau.get(colonne).get(m_plateau.size() - 1);
                for (int i = m_plateau.size() - 1; i > 0; i--) {
                    m_plateau.get(colonne).set(i, m_plateau.get(colonne).get(i - 1));
                    m_plateau.get(colonne).get(i).setCoordonee(colonne, i);
                }
                m_plateau.get(colonne).set(0, m_tuile);
                m_tuile.setCoordonee(colonne,0);

                break;
            case WEST:
                tuileSortante = m_plateau.get(colonne).get(0);
                for (int i = 0; i < m_plateau.size() - 1; i++) {
                    m_plateau.get(colonne).set(i, m_plateau.get(colonne).get(i + 1));
                    m_plateau.get(colonne).get(i).setCoordonee(colonne, i);
                }
                m_plateau.get(colonne).set(m_plateau.size() - 1, m_tuile);
                m_tuile.setCoordonee(colonne,m_plateau.size() - 1);
                break;
        }
        tuileSortante.setCoordonee(-1,-1);
        for (Joueur j:m_joueurs) {
            if(j.getTuile().getCoordoneeX()==-1){
                j.setTuile(m_tuile);
            }
            if(j.verifObjectif()){
                j.validationObjectif();
            }
        }
        m_tuile = tuileSortante;
        return tuileSortante;
    }



    public Tuile getTuile(){
        return m_tuile;
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

    public void printBoard(){
        for (List<Tuile> lT: this.m_plateau) {
            System.out.print("\n");
            for (Tuile T: lT) {
                if(T!=null) {
                    System.out.print(T.getName() + " (" + T.getCoordoneeX()+ T.getCoordoneeY()+ ")\t\t\t");
                }
            }

        }
    }

    public void deplacerJoueur(Joueur joueur , Direction direction){
        if(joueur.getTuile().checkSortieTuile(direction)){
            int coorX = joueur.getTuile().getCoordoneeX();
            int coorY = joueur.getTuile().getCoordoneeY();
            switch (direction) {
                case NORTH:
                    if(((coorX - 1) < 0) || (m_plateau.get(coorX - 1).get(coorY) == null)){
                        System.out.println("Tuile non accessible");
                        System.out.println(m_plateau.get(coorX-1).get(coorY).getDirection()) ;
                    }
                    else if(m_plateau.get(coorX-1).get(coorY).checkSortieTuile(Direction.SOUTH)){
                        joueur.setTuile(m_plateau.get(coorX-1).get(coorY));
                        System.out.println("Nouvelle tuile : " + joueur.getTuile().getCoordoneeX() + " " + joueur.getTuile().getCoordoneeY());
                    }

                    else{
                        System.out.println("Tuile non accessible");
                        System.out.println(m_plateau.get(coorX-1).get(coorY).getDirection()) ;
                    }
                    break;
                case SOUTH:
                    if(coorX + 1 >= m_plateau.size() || m_plateau.get(coorX+1).get(coorY) == null){
                        System.out.println("Tuile non accessible");
                        System.out.println(m_plateau.get(coorX+1).get(coorY).getDirection()) ;
                    }
                    else if(m_plateau.get(coorX+1).get(coorY).checkSortieTuile(Direction.NORTH)){
                        joueur.setTuile(m_plateau.get(coorX+1).get(coorY));
                        System.out.println("Nouvelle tuile : " + joueur.getTuile().getCoordoneeX() + " " + joueur.getTuile().getCoordoneeY());
                    }

                    else{
                        System.out.println("Tuile non accessible");
                        System.out.println(m_plateau.get(coorX+1).get(coorY).getDirection()) ;
                    }
                    break;
                case EAST:
                    if(coorY + 1 >= m_plateau.get(coorX).size() || m_plateau.get(coorX).get(coorY+1) == null){
                        System.out.println("Tuile non accessible");
                        System.out.println(m_plateau.get(coorX).get(coorY+1).getDirection()) ;
                    }
                    else if(m_plateau.get(coorX).get(coorY+1).checkSortieTuile(Direction.WEST)){
                        joueur.setTuile(m_plateau.get(coorX).get(coorY+1));
                        System.out.println("Nouvelle tuile : " + joueur.getTuile().getCoordoneeX() + " " + joueur.getTuile().getCoordoneeY());
                    }

                    else{
                        System.out.println("Tuile non accessible");
                        System.out.println(m_plateau.get(coorX).get(coorY+1).getDirection()) ;
                    }
                    break;
                case WEST:
                    if(coorY - 1 < 0 || m_plateau.get(coorX).get(coorY-1) == null){
                        System.out.println("Tuile non accessible");
                        System.out.println(m_plateau.get(coorX).get(coorY-1).getDirection()) ;
                    }
                    else if(m_plateau.get(coorX).get(coorY-1).checkSortieTuile(Direction.EAST)){
                        joueur.setTuile(m_plateau.get(coorX).get(coorY-1));
                        System.out.println("Nouvelle tuile : " + joueur.getTuile().getCoordoneeX() + " " + joueur.getTuile().getCoordoneeY());
                    }

                    else{
                        System.out.println("Tuile non accessible");
                        System.out.println(m_plateau.get(coorX).get(coorY-1).getDirection()) ;
                    }
                    break;

            }
            if(joueur.verifObjectif()){
                joueur.validationObjectif();
            }
        }

    }

    public Joueur getJoueur(){
        Random random = new Random();
        int joueurChoisi = random.nextInt(m_joueurs.size());
        return m_joueurs.get(joueurChoisi);
    }

    public ArrayList<Joueur> getJoueursDuPlateau(){
        return m_joueurs;
    }
}
