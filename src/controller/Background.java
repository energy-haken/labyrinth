package controller;

import model.*;

public class Background {
    public Plateau lancementJeu(){
        /*20 angles dont 4 sont fixes et 16 sont déplaçables, 6 obj
        12 sections droites toutes déplaçables,
        18 en forme de "T" dont 12 sont fixes et 6 sont déplaçables. 18 obj
        Pour le moment elles sont créé dans le vent, il faut encore les associer au plateau qui sera renvoyé en return*/
        for (int i = 0; i < 50; i++) {
            if(i<4){
                new TuileLibre(true, Pattern.ANGLE);
            } else if (4<=i && i<10) {
                new TuileObjectif(false, Pattern.ANGLE, Objectifs.ECHEC_CONTRE_H);
            } else if (10<=i && i<20) {
                new TuileLibre(false, Pattern.ANGLE);
            } else if (20<=i && i<32) {
                new TuileLibre(false, Pattern.DROIT);
            } else if (32<=i && i<44) {
                new TuileObjectif(false, Pattern.T, Objectifs.PORTE_AMICALE);
            } else if (44<=i && i<50) {
                new TuileObjectif(false, Pattern.T,Objectifs.HERBE);
            }
        }


        return null;
    };
}
