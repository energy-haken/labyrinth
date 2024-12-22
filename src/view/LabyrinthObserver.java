package view;

import model.Direction;
import model.Player;
import model.tiles.Tile;

import java.io.IOException;

public interface LabyrinthObserver {
    void updateInsertTile(Direction direction , int colonne);
    void updateRotateTile(Direction direction) ;
    void updateMovePlayer(Player player, Direction direction);


    public void doBecauseMazeChange(Tile tile) throws IOException;
}
