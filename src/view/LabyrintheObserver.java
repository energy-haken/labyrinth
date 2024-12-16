package view;

import model.Direction;
import model.Joueur;
import model.tuiles.Tuile;

import java.io.IOException;

public interface LabyrintheObserver {
    void updateInsertTuile(Direction direction , int colonne);
    void updateTournerTuile(Direction direction) ;
    void updateDeplacerJoueur(Joueur joueur , Direction direction);


    public void doBecauseMazeChange(Tuile tile) throws IOException;

}
