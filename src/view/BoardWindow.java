package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import helpers.ImageHelper;
import model.Board;
import model.Direction;
import model.Player;
import model.tiles.Tile;

public class BoardWindow extends JPanel {
    // Load images
    private static final BufferedImage Tpic = loadImage("img/T.png");
    private static final BufferedImage ANGLEpic = loadImage("img/Corner.png");
    private static final BufferedImage LINEpic = loadImage("img/LINE.png");

    private JPanel boardPanel;
    private ArrayList<LabyrinthObserver> observerList = new ArrayList<>();

    private Board boardModel;

    private JPanel[][] panels;
    private HashMap<Integer, ImagePanel> imagePanelById = new HashMap<>();

    public BoardWindow(Board board) throws IOException {
        super();
        int rows = 7;
        int cols = 7;
        // Initialize the main panel with a dynamic grid layout
        this.setLayout(new GridLayout(rows, cols));

        boardModel = new Board();

        // Create a 2D array for panels
        panels = new JPanel[rows][cols];
        // Initialize and add all panels to the board
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                panels[row][col] = new JPanel();
                panels[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Optional: add borders for visibility
                //this.add(panels[row][col]);
            }
        }

        // Use the board object to set up the initial state of the grid
        List<List<Tile>> grid = boardModel.get_board();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Tile tile = grid.get(row).get(col);
                int idImage = (row * 7) + col;
                if (tile != null) {
                    imagePanelById.put(idImage, ImagePanel.getImageByTile(tile));
                    add(imagePanelById.get(idImage));
                }
            }
        }

        setVisible(true);
    }

    /**
     * Utility method to load an image from a file.
     *
     * @param filePath Path to the image file.
     * @return BufferedImage if successful, null otherwise.
     */
    private static BufferedImage loadImage(String filePath) {
        try {
            return ImageIO.read(new File(filePath));
        } catch (IOException e) {
            System.err.println("Failed to load image: " + filePath);
            return null;
        }
    }

    public Board getBoardModel() {
        return boardModel;
    }

    public void loadPlayers(Player player) throws IOException {
        int x = player.getTile().getCoordinateX();
        int y = player.getTile().getCoordinateY();
        Tile tile = boardModel.get_board().get(x).get(y);
        int idImage = 7 * x + y;

        // Find the existing ImagePanel by idImage
        ImagePanel existingImagePanel = imagePanelById.get(idImage);

        if (existingImagePanel != null) {
            // Update the existing ImagePanel with the new image
            ImagePanel newImagePanel = new ImagePanel(ImageHelper.mergeB(existingImagePanel.getImage(), "img/Pion" + player.getColor() + ".png"));
            existingImagePanel.updateImage(newImagePanel);
            // Revalidate and repaint to ensure the component is properly displayed
            existingImagePanel.revalidate();
            existingImagePanel.repaint();
        } else {
            System.err.println("No existing ImagePanel found for id: " + idImage);
        }
    }

    public void updateMaze(Direction direction, int index) throws IOException {
        if (direction == Direction.NORTH || direction == Direction.SOUTH) {
            for (int i = 0; i <= 6; i++) {
                int idImage = (i * 7) + index;
                Tile tile = boardModel.get_board().get(i).get(index);
                imagePanelById.get(idImage).updateImage(ImagePanel.getImageByTile(tile));
            }
        } else {
            for (int i = 0; i <= 6; i++) {
                int idImage = i + (index * 7);
                Tile tile = boardModel.get_board().get(index).get(i);
                imagePanelById.get(idImage).updateImage(ImagePanel.getImageByTile(tile));
            }
        }
        for (Player player : boardModel.getPlayersOnBoard()) {
            loadPlayers(player);
        }
        notifyObserversMazeChange();
    }

    public void updateTileByID(int id) throws IOException {
        int y = id % 7;
        int x = (id - y) / 7;
        Tile tile = boardModel.get_board().get(x).get(y);

        imagePanelById.get(id).updateImage(ImagePanel.getImageByTile(tile));
    }

    public void addObserver(LabyrinthObserver o) {
        observerList.add(o);
    }

    public void notifyObserversMazeChange() throws IOException {
        for (LabyrinthObserver o : observerList) {
            o.doBecauseMazeChange(getBoardModel().getTile());
        }
    }

}