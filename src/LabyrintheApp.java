import controller.Background;
import model.*;
import view.LabyrintheMainWindow;
import view.PlateauWindow;

import java.io.IOException;
import java.util.ArrayList;

public class LabyrintheApp {
    public static void main(String[] args) throws IOException {

        ArrayList objectifsList = new ArrayList();
        objectifsList.add(Objectif.HERBE) ;
        objectifsList.add(Objectif.PORTE_AMICALE) ;
        TuileLibre tuile = new TuileLibre(true , Pattern.T , 1 , 2, 999);
        Joueur joueur1 = new Joueur(tuile , objectifsList);
        joueur1.verifObjectif() ;
        //Plateau cUnPlateau = new Background().lancementJeu();

        //Background background = new Background() ;
        /*background.lancementJeu();

            try {
                // Create a PlateauWindow with a 7x7 grid and the plateau object
                new PlateauWindow(7, 7, plateau);
            } catch (IOException e) {
                System.err.println("Error initializing PlateauWindow: " + e.getMessage());
            }


         */

        LabyrintheMainWindow ForPity = new LabyrintheMainWindow();
        ForPity.getPlateau().printBoard();
        System.out.println(ForPity.getPlateau().getTuile().getName());
        ForPity.getPlateau().pousserTuile(Direction.NORTH, 1);
        System.out.println("pousser");
        ForPity.getPlateau().printBoard();

    }
}
