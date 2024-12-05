import controller.Background;
import model.*;
import view.PlateauWindow;

import java.io.IOException;
import java.util.ArrayList;

public class LabyrintheApp {
    public static void main(String[] args) throws IOException {
         ;
        ArrayList objectifsList = new ArrayList();
        objectifsList.add(Objectif.HERBE) ;
        objectifsList.add(Objectif.PORTE_AMICALE) ;
        TuileLibre tuile = new TuileLibre(true , Pattern.T);
        Joueur joueur1 = new Joueur(tuile , objectifsList);
        joueur1.verifObjectif() ;
        //Plateau cUnPlateau = new Background().lancementJeu();

        Background background = new Background() ;
        background.lancementJeu() ;


            try {
                // Create a PlateauWindow with a 7x7 grid
                new PlateauWindow(7, 7);
            } catch (IOException e) {
                System.err.println("Error initializing PlateauWindow: " + e.getMessage());
            }

    }
}
