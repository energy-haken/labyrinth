package controller;

import model.Direction;
import model.Board;
import model.Player;

public class LabyrinthController {
    private final Board _board;

    public LabyrinthController(Board board) {
        _board = board ;
    }

    public void modifyPushTile(Direction direction , int colonne) {
        this._board.pushTile(direction, colonne);
    }

    public void modifyMovePlayer(Player player, Direction direction) {
        this._board.movePlayer(player, direction);
    }

    public void modifyRotateTile(Direction direction) {
        _board.getTile().rotateTile(direction);
    }
}
