package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
    private Plateau plateauModel;

    private JPanel[][] panels;
    private BufferedImage[] images = new BufferedImage[49];
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
                    BufferedImage image = null;
                    switch (tuile.getPattern()) {
                        case ANGLE:
                            if((tuile.getCoordoneeX() == 0) && (tuile.getCoordoneeY() == 0)){
                                image = ImageHelper.merge("img/Corner.png" , "img/SpawnJoueurRouge.png") ;
                                images[idImage] = image;
                            }
                            else if((tuile.getCoordoneeX() == 0) && (tuile.getCoordoneeY() == 6)){
                                image = ImageHelper.merge("img/Corner.png" , "img/SpawnJoueurBleu.png") ;
                                images[idImage] = image;
                            }
                            else if((tuile.getCoordoneeX() == 6) && (tuile.getCoordoneeY() == 0)){
                                image = ImageHelper.merge("img/Corner.png" , "img/SpawnJoueurVert.png") ;
                                images[idImage] = image;
                            }
                            else if((tuile.getCoordoneeX() == 6) && (tuile.getCoordoneeY() == 6)){
                                image = ImageHelper.merge("img/Corner.png" , "img/SpawnJoueurJaune.png") ;
                                images[idImage] = image;
                            }

                            else if(tuile.getObjectif() != null){

                                image = ImageHelper.merge("img/Corner.png" , "img/Objectifs/" + tuile.getObjectif().name() +".png") ;
                                images[idImage] = image;
                            }
                            else{
                                image = ANGLEpic;
                                images[idImage] = image;
                            }

                            break;
                        case DROIT:
                            if(tuile.getObjectif() != null){
                                image = ImageHelper.merge("img/LINE.png" , "img/Objectifs/" + tuile.getObjectif().name() +".png") ;
                                images[idImage] = image;
                            }
                            else{
                                image = DROITpic;
                                images[idImage] = image;
                            }

                            break;
                        case T:
                            if(tuile.getObjectif() != null){
                                image = ImageHelper.merge("img/T.png" , "img/Objectifs/" + tuile.getObjectif().name() +".png") ;
                                images[idImage] = image;
                            }
                            else{
                                image = Tpic;
                                images[idImage] = image;
                            }

                            break;

                    }


                    switch (tuile.getDirection()) {
                        case NORTH:
                            images[idImage] = ImageHelper.rotateCounterClockwise(images[idImage]);
                            break;
                        case EAST:

                            break;
                        case SOUTH:
                            images[idImage] = ImageHelper.rotateClockwise(images[idImage]);
                            break;
                        case WEST:
                            images[idImage] = ImageHelper.rotateClockwise(ImageHelper.rotateClockwise(images[idImage]));
                            break;
                        default:
                            break;
                    }


                }
            }
        }
        int i =0;
        for (BufferedImage image: images) {
            imagePanelById.put(i,new ImagePanel(image));
            add(imagePanelById.get(i));
            i++;
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
        BufferedImage image = loadImage("img/Objectifs/RDS.png");
        

    }
    public void updateMaze(Direction direction, int index) throws IOException {
        if(direction == Direction.NORTH || direction == Direction.SOUTH){
            for (int i=0;i<=6;i++){
                int idImage = (i*7)+index;
                Tuile tuile = plateauModel.getPlateau().get(i).get(index);
                BufferedImage image = loadImage("img/Objectifs/RDS.png");
                switch (tuile.getPattern()){
                    case T :
                        if(tuile.getObjectif() != null){
                            image = ImageHelper.merge("img/T.png" , "img/Objectifs/" + tuile.getObjectif().name() +".png") ;
                            images[idImage] = image;
                        }
                        else{
                            image = Tpic;
                            images[idImage] = image;
                        }
                        break;
                    case ANGLE:
                        if(tuile.getObjectif() != null){
                            image = ImageHelper.merge("img/Corner.png" , "img/Objectifs/" + tuile.getObjectif().name() +".png") ;
                            images[idImage] = image;
                        }
                        else{
                            image = ANGLEpic;
                            images[idImage] = image;
                        }
                        break;
                    case DROIT:
                        if(tuile.getObjectif() != null){
                            image = ImageHelper.merge("img/LINE.png" , "img/Objectifs/" + tuile.getObjectif().name() +".png") ;
                            images[idImage] = image;
                        }
                        else{
                            image = DROITpic;
                            images[idImage] = image;
                        }
                        break;
                }
                switch (tuile.getDirection()) {
                    case NORTH:
                        images[idImage] = ImageHelper.rotateCounterClockwise(images[idImage]);
                        break;
                    case EAST:
                        break;
                    case SOUTH:
                        images[idImage] = ImageHelper.rotateClockwise(images[idImage]);
                        break;
                    case WEST:
                        images[idImage] = ImageHelper.rotateClockwise(ImageHelper.rotateClockwise(images[idImage]));
                        break;
                    default:
                        break;
                }
                imagePanelById.get(idImage).updateImage(images[idImage]);

            }
        }else {
            for (int i = 0; i <= 6; i++) {
                int idImage = i + (index * 7);
                Tuile tuile = plateauModel.getPlateau().get(index).get(i);
                BufferedImage image = loadImage("img/Objectifs/RDS.png");
                switch (tuile.getPattern()) {
                    case T:
                        if (tuile.getObjectif() != null) {
                            image = ImageHelper.merge("img/T.png", "img/Objectifs/" + tuile.getObjectif().name() + ".png");
                            images[idImage] = image;
                        } else {
                            image = Tpic;
                            images[idImage] = image;
                        }
                        break;
                    case ANGLE:
                        if (tuile.getObjectif() != null) {
                            image = ImageHelper.merge("img/Corner.png", "img/Objectifs/" + tuile.getObjectif().name() + ".png");
                            images[idImage] = image;
                        } else {
                            image = ANGLEpic;
                            images[idImage] = image;
                        }
                        break;
                    case DROIT:
                        if (tuile.getObjectif() != null) {
                            image = ImageHelper.merge("img/LINE.png", "img/Objectifs/" + tuile.getObjectif().name() + ".png");
                            images[idImage] = image;
                        } else {
                            image = DROITpic;
                            images[idImage] = image;
                        }
                        break;
                }
                switch (tuile.getDirection()) {
                    case NORTH:
                        images[idImage] = ImageHelper.rotateCounterClockwise(images[idImage]);
                        break;
                    case EAST:
                        break;
                    case SOUTH:
                        images[idImage] = ImageHelper.rotateClockwise(images[idImage]);
                        break;
                    case WEST:
                        images[idImage] = ImageHelper.rotateClockwise(ImageHelper.rotateClockwise(images[idImage]));
                        break;
                    default:
                        break;
                }

                imagePanelById.get(idImage).updateImage(images[idImage]);

            }
        }
    }

}