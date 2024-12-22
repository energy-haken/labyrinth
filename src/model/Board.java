package model;

import model.tiles.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Board {
    private List<List<Tile>> board = Arrays.asList(Arrays.asList(null,null,null,null,null,null,null),
                                                   Arrays.asList(null,null,null,null,null,null,null),
                                                   Arrays.asList(null,null,null,null,null,null,null),
                                                   Arrays.asList(null,null,null,null,null,null,null),
                                                   Arrays.asList(null,null,null,null,null,null,null),
                                                   Arrays.asList(null,null,null,null,null,null,null),
                                                   Arrays.asList(null,null,null,null,null,null,null));
    private ArrayList<Player> players = new ArrayList<>();

    private Tile extraTile;

    public Board(){
        ArrayList<Objective> objectiveList = new ArrayList<>();
        ArrayList<Objective> objectives1 = new ArrayList<>();
        ArrayList<Objective> objectives2 = new ArrayList<>();
        ArrayList<Objective> objectives3 = new ArrayList<>();
        ArrayList<Objective> objectives4 = new ArrayList<>();

        for(int i = 0 ; i < 24 ; i++){
            objectiveList.add(Objective.values()[i]);
        }

        for(int i = 0 ; i < objectiveList.size()/4 ; i++){
            objectives1.add(objectiveList.get(i));
        }
        for(int i = 6 ; i < objectiveList.size()/4 + 6 ; i++){
            objectives2.add(objectiveList.get(i));
        }
        for(int i = 12 ; i < objectiveList.size()/4 + 12; i++){
            objectives3.add(objectiveList.get(i));
        }
        for(int i = 18 ; i < objectiveList.size() ; i++){
            objectives4.add(objectiveList.get(i));
        }

        int tileNumber = 0;
        //4Af |12 TfO
        for (int j = 0; j < 7; j+=2) {
            for (int k = 0; k < 7; k+=2) {
                if((j==0||j==6)&&(k==0||k==6)){
                    this.board.get(j).set(k,new TileCorner(true, Direction.WEST, j , k, tileNumber));
                    tileNumber++;
                }
                else{
                    this.board.get(j).set(k,new TileT(true, Direction.WEST, j , k ,tileNumber , objectiveList.get(objectiveList.size()-1)));
                    tileNumber++;
                    objectiveList.remove(objectiveList.size()-1);
                }
                if((j==0)&&(k==0)){
                    Player playerRed = new Player(board.get(0).get(0) , "Red", objectives1) ;
                    players.add(playerRed) ;
                }
                if((j == 0)&&(k == 6)){
                    this.board.get(j).get(k).rotateTile(Direction.NORTH);
                    Player playerGreen = new Player(board.get(0).get(6) , "Green", objectives3) ;
                    players.add(playerGreen) ;
                }
                if((j == 6)&&(k == 0)){
                    this.board.get(j).get(k).rotateTile(Direction.SOUTH);
                    Player playerBlue = new Player(board.get(6).get(0) , "Blue", objectives2) ;
                    players.add(playerBlue) ;
                }
                if((j == 6)&&(k == 6)){
                    this.board.get(j).get(k).rotateTile(Direction.EAST);
                    Player playerYellow = new Player(board.get(6).get(6) , "Yellow", objectives4) ;
                    players.add(playerYellow) ;
                }
            }
        }

        // Generation of Movable Tiles
        for(int i = 0 ; i < 7 ; i++) {
            for (int j = 0; j < 7; j++) {
                if (board.get(i).get(j) == null) {
                    Pattern pattern = checkPattern();
                    Direction direction = checkDirection();
                    if (checkObjective() && !objectiveList.isEmpty()) { // Tile with objective
                        if(pattern == Pattern.T){
                            board.get(i).set(j , new TileT(false , direction , i , j , tileNumber, objectiveList.get(objectiveList.size() -1))) ;
                            tileNumber++;
                        }
                        else if(pattern == Pattern.LINE){
                            board.get(i).set(j , new TileLine(false , direction , i , j , tileNumber, objectiveList.get(objectiveList.size() -1))) ;
                            tileNumber++;
                        }
                        else{
                            board.get(i).set(j , new TileCorner(false , direction , i , j , tileNumber, objectiveList.get(objectiveList.size() -1))) ;
                            tileNumber++;
                        }
                        objectiveList.remove(objectiveList.size() - 1);
                    }
                    else { // Tile without objective
                        if(pattern == Pattern.T){
                            board.get(i).set(j , new TileT(false , direction , i , j , tileNumber)) ;
                            tileNumber++;
                        }
                        else if(pattern == Pattern.LINE){
                            board.get(i).set(j , new TileLine(false , direction , i , j , tileNumber)) ;
                            tileNumber++;
                        }
                        else{
                            board.get(i).set(j , new TileCorner(false , direction , i , j , tileNumber)) ;
                            tileNumber++;
                        }
                    }
                }
            }
        }

        for(int i = 0 ; i < 7 ; i++){
            for(int j = 0 ; j < 7 ; j++){
                if((i == 0 && j == 2) || (i == 0 && j == 4) || (i == 2 && j == 4)){
                    this.board.get(i).get(j).rotateTile(Direction.SOUTH);
                }
                if((i == 2 && j == 0) || (i == 4 && j == 0) || (i == 2 && j == 2)){
                    this.board.get(i).get(j).rotateTile(Direction.EAST);
                }
                if((i == 2 && j == 6) || (i == 4 && j == 6) || (i == 4 && j == 4)){
                    this.board.get(i).get(j).rotateTile(Direction.WEST);
                }
                if((i == 6 && j == 2) || (i == 6 && j == 4) || (i == 4 && j == 2)){
                    this.board.get(i).get(j).rotateTile(Direction.NORTH);
                }
            }
        }

        // Adding the 50th Tile
        Pattern pattern = checkPattern();
        if(pattern == Pattern.T){
            this.extraTile = new TileT(false , Direction.NORTH ,-1 , -1 , tileNumber) ;
        }
        else if(pattern == Pattern.LINE){
            this.extraTile = new TileLine(false , Direction.NORTH , -1 , -1 , tileNumber) ;
        }
        else{
            this.extraTile = new TileCorner(false , Direction.NORTH , -1 , -1 , tileNumber) ;
        }
    }

    /**
     * Pushes a tile onto the board
     * @param direction : the direction in which the row will be shifted (NORTH, SOUTH, EAST , WEST)
     * @return
     */
    public Tile pushTile(Direction direction, Integer column) {
        Tile outgoingTile = null;
        switch (direction) {
            case NORTH:
                outgoingTile = board.get(0).get(column);
                for (int i = 0; i < board.size() - 1; i++) {
                    board.get(i).set(column, board.get(i + 1).get(column));
                    board.get(i).get(column).setCoordinate(i,column);
                }
                board.get(board.size() - 1).set(column, extraTile);
                extraTile.setCoordinate(board.size() - 1, column);
                break;
            case SOUTH:
                outgoingTile = board.get(board.size() - 1).get(column);
                for (int i = board.size() - 1; i > 0; i--) {
                    board.get(i).set(column, board.get(i - 1).get(column));
                    board.get(i).get(column).setCoordinate(i,column);
                }
                board.get(0).set(column, extraTile);
                extraTile.setCoordinate(0, column);
                break;
            case EAST:
                outgoingTile = board.get(column).get(board.size() - 1);
                for (int i = board.size() - 1; i > 0; i--) {
                    board.get(column).set(i, board.get(column).get(i - 1));
                    board.get(column).get(i).setCoordinate(column, i);
                }
                board.get(column).set(0, extraTile);
                extraTile.setCoordinate(column,0);
                break;
            case WEST:
                outgoingTile = board.get(column).get(0);
                for (int i = 0; i < board.size() - 1; i++) {
                    board.get(column).set(i, board.get(column).get(i + 1));
                    board.get(column).get(i).setCoordinate(column, i);
                }
                board.get(column).set(board.size() - 1, extraTile);
                extraTile.setCoordinate(column,board.size() - 1);
                break;
        }
        outgoingTile.setCoordinate(-1,-1);
        for (Player player: players) {
            if(player.getTile().getCoordinateX()==-1){
                player.setTile(extraTile);
            }
            if(player.verifyObjective()){
                player.validateObjective();
            }
        }
        extraTile = outgoingTile;
        return outgoingTile;
    }

    public Tile getTile(){
        return extraTile;
    }

    public List<List<Tile>> getBoard() {
        return board;
    }

    public Pattern checkPattern(){
        int angle = 16;
        int line = 12;
        int t = 6;

        Random random = new Random();
        int nb = random.nextInt(3);
        switch (nb){
            case 0 :
                if(angle > 0){
                    angle -= 1;
                    return Pattern.CORNER;
                }
                else{
                    return checkPattern();
                }
            case 1 :
                if(line > 0){
                    line -= 1;
                    return Pattern.LINE;
                }
                else{
                    return checkPattern();
                }
            case 2 :
                if(t > 0){
                    t -= 1;
                    return Pattern.T;
                }
                else{
                    return checkPattern();
                }
        }
        return null;
    }

    public Direction checkDirection(){
        Random random = new Random();
        int nb = random.nextInt(4);
        switch (nb){
            case 0 :
                return Direction.NORTH;
            case 1 :
                return Direction.SOUTH;
            case 2 :
                return Direction.EAST;
            case 3 :
                return Direction.WEST;
        }
        return null;
    }

    public boolean checkObjective(){
        Random random = new Random();
        int nb = random.nextInt(2);
        switch (nb){
            case 0 :
                return true;
            case 1 :
                return false;
        }
        return false;
    }

    public void printBoard(){
        for (List<Tile> lT: this.board) {
            System.out.print("\n");
            for (Tile T: lT) {
                if(T!=null) {
                    System.out.print(T.getName() + " (" + T.getCoordinateX()+ T.getCoordinateY()+ ")\t\t\t");
                }
            }
        }
    }

    public void movePlayer(Player player, Direction direction){
        if(player.getTile().checkTileExit(direction)){
            int coorX = player.getTile().getCoordinateX();
            int coorY = player.getTile().getCoordinateY();
            switch (direction) {
                case NORTH:
                    if(((coorX - 1) < 0) || (board.get(coorX - 1).get(coorY) == null)){
                        System.out.println("Tile not accessible");
                        System.out.println(board.get(coorX-1).get(coorY).getDirection()) ;
                    }
                    else if(board.get(coorX-1).get(coorY).checkTileExit(Direction.SOUTH)){
                        player.setTile(board.get(coorX-1).get(coorY));
                        System.out.println("New tile: " + player.getTile().getCoordinateX() + " " + player.getTile().getCoordinateY());
                    }
                    else{
                        System.out.println("Tile not accessible");
                        System.out.println(board.get(coorX-1).get(coorY).getDirection()) ;
                    }
                    break;
                case SOUTH:
                    if(coorX + 1 >= board.size() || board.get(coorX+1).get(coorY) == null){
                        System.out.println("Tile not accessible");
                        System.out.println(board.get(coorX+1).get(coorY).getDirection()) ;
                    }
                    else if(board.get(coorX+1).get(coorY).checkTileExit(Direction.NORTH)){
                        player.setTile(board.get(coorX+1).get(coorY));
                        System.out.println("New tile: " + player.getTile().getCoordinateX() + " " + player.getTile().getCoordinateY());
                    }
                    else{
                        System.out.println("Tile not accessible");
                        System.out.println(board.get(coorX+1).get(coorY).getDirection()) ;
                    }
                    break;
                case EAST:
                    if(coorY + 1 >= board.get(coorX).size() || board.get(coorX).get(coorY+1) == null){
                        System.out.println("Tile not accessible");
                        System.out.println(board.get(coorX).get(coorY+1).getDirection()) ;
                    }
                    else if(board.get(coorX).get(coorY+1).checkTileExit(Direction.WEST)){
                        player.setTile(board.get(coorX).get(coorY+1));
                        System.out.println("New tile: " + player.getTile().getCoordinateX() + " " + player.getTile().getCoordinateY());
                    }
                    else{
                        System.out.println("Tile not accessible");
                        System.out.println(board.get(coorX).get(coorY+1).getDirection()) ;
                    }
                    break;
                case WEST:
                    if(coorY - 1 < 0 || board.get(coorX).get(coorY-1) == null){
                        System.out.println("Tile not accessible");
                        System.out.println(board.get(coorX).get(coorY-1).getDirection()) ;
                    }
                    else if(board.get(coorX).get(coorY-1).checkTileExit(Direction.EAST)){
                        player.setTile(board.get(coorX).get(coorY-1));
                        System.out.println("New tile: " + player.getTile().getCoordinateX() + " " + player.getTile().getCoordinateY());
                    }
                    else{
                        System.out.println("Tile not accessible");
                        System.out.println(board.get(coorX).get(coorY-1).getDirection()) ;
                    }
                    break;
            }
            if(player.verifyObjective()){
                player.validateObjective();
            }
        }
    }

    public Player getPlayer(){
        Random random = new Random();
        int chosenPlayer = random.nextInt(players.size());
        return players.get(chosenPlayer);
    }

    public ArrayList<Player> getPlayersOnBoard(){
        return players;
    }
}
