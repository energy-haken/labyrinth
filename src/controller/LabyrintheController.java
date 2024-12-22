package controller;

import model.Direction;
import model.Board;
import model.Player;

public class LabyrintheController {
    private Board m_board;
    public void modifyPushTuile(Direction direction , int colonne) {
        this.m_board.pushTile(direction, colonne);
    }

    public void modifyDeplacerJoueur(Player player, Direction direction) {
        this.m_board.movePlayer(player, direction);
    }
}
