package view;

import model.Direction;
import model.Joueur;

public interface LabyrintheObserver {
    void updateInsertTuile(Direction direction , int colonne);
    void updateTournerTuile(Direction direction) ;
    void updateDeplacerJoueur(Joueur joueur , Direction direction);




}
