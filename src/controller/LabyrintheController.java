package controller;

import model.Direction;
import model.Plateau;
import model.Joueur;

public class LabyrintheController {
    private Plateau m_plateau;
    public void modifyPushTuile(Direction direction , int colonne) {
        this.m_plateau.pousserTuile(direction, colonne);
    }

    public void modifyDeplacerJoueur(Joueur joueur , Direction direction) {
        this.m_plateau.deplacerJoueur(joueur, direction);
    }
}
