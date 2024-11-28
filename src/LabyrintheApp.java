import controller.Background;
import model.*;
import view.PlateauWindow;

import java.awt.*;
import java.util.ArrayList;

public class LabyrintheApp {
    public static void main(String[] args) {
         ;
        ArrayList objectifsList = new ArrayList();
        objectifsList.add(Objectifs.HERBE) ;
        objectifsList.add(Objectifs.PORTE_AMICALE) ;
        TuileLibre tuile = new TuileLibre(true , Pattern.T);
        Joueur joueur1 = new Joueur(tuile , objectifsList);
        joueur1.verifObjectif() ;
        //Plateau cUnPlateau = new Background().lancementJeu();

    }
}
