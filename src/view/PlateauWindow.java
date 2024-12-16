package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import helpers.ImageHelper;
import model.Direction;
import model.Joueur;
import model.Plateau;
import model.tuiles.Tuile;

public class PlateauWindow extends JPanel {
    //charges les images
    private static final BufferedImage Tpic = loadImage("img/T.png");
    private static final BufferedImage ANGLEpic = loadImage("img/Corner.png");
    private static final BufferedImage DROITpic = loadImage("img/LINE.png");

    private JPanel Plateau;
    private ArrayList<LabyrintheObserver> observerList = new ArrayList<>();

    private Plateau plateauModel;

    private JPanel[][] panels;
    private HashMap<Integer,ImagePanel> imagePanelById = new HashMap<>();


    public PlateauWindow(Plateau plateau) throws IOException {
        super();
        int rows= 7 ;
        int cols = 7 ;
        // Initialize the main panel with a dynamic grid layout
        this.setLayout(new GridLayout(rows, cols));

        plateauModel  = new Plateau();
        Joueur joueurRouge = plateauModel.getJoueur() ;

        // Create a 2D array for panels
        panels = new JPanel[rows][cols];
        // Initialize and add all panels to the Plateau
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                panels[row][col] = new JPanel();
                panels[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Optional: add borders for visibility
                //this.add(panels[row][col]);
            }
        }

        // Use the plateau object to set up the initial state of the grid
        List<List<Tuile>> grid = plateauModel.getPlateau();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Tuile tuile = grid.get(row).get(col);
                int idImage = (row * 7) + col;
                if (tuile != null) {
                    imagePanelById.put(idImage, ImagePanel.getImageByTile(tuile));
                    add(imagePanelById.get(idImage));
                }
            }
        }

        loadPlayers(joueurRouge);
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

    public model.Plateau getPlateauModel() {
        return plateauModel;
    }

    public void loadPlayers(Joueur joueur) throws IOException {
        int x = joueur.getTuile().getCoordoneeX();
        int y = joueur.getTuile().getCoordoneeY();
        int idImage = joueur.getTuile().getName();
        Tuile tuile = plateauModel.getPlateau().get(x).get(y);

        //ImagePanel imagePanel = new ImagePanel(ImageHelper.mergeB(ImagePanel.getImageByTile(tuile).getImage(), "img/PionRouge.png")) ;
        //imagePanelById.get(idImage).updateImage(imagePanel);


    }
    public void updateMaze(Direction direction, int index) throws IOException {
        if(direction == Direction.NORTH || direction == Direction.SOUTH){
            for (int i=0;i<=6;i++){
                int idImage = (i*7)+index;
                Tuile tuile = plateauModel.getPlateau().get(i).get(index);

                imagePanelById.get(idImage).updateImage(ImagePanel.getImageByTile(tuile));
            }
        }else {
            for (int i = 0; i <= 6; i++) {
                int idImage = i + (index * 7);
                Tuile tuile = plateauModel.getPlateau().get(index).get(i);
                imagePanelById.get(idImage).updateImage(ImagePanel.getImageByTile(tuile));

            }
        }
        notifyObserversMazeChange();
    }

    public void addObserver(LabyrintheObserver o){
        observerList.add(o);
    }
    private void notifyObserversMazeChange() throws IOException {
        for (LabyrintheObserver o: observerList) {
            o.doBecauseMazeChange(getPlateauModel().getTuile());
        }
    }

}