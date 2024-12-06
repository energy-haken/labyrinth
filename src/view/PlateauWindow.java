package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import helpers.ImageHelper;
import model.Plateau;
import model.Tuile;

import static model.Pattern.*;

public class PlateauWindow extends JFrame {
    //charges les images
    private static final BufferedImage Tpic = loadImage("img/T.png");
    private static final BufferedImage ANGLEpic = loadImage("img/Corner.png");
    private static final BufferedImage DROITpic = loadImage("img/LINEpng.png");
    private JPanel Plateau;
    private JPanel L1C1;
    private JPanel L1C2;
    private JPanel L1C3;
    private JPanel L1C4;
    private JPanel L1C5;
    private JPanel L1C6;
    private JPanel L1C7;
    private JPanel L2C1;
    private JPanel L3C1;
    private JPanel L4C1;
    private JPanel L5C1;
    private JPanel L6C1;
    private JPanel L7C1;
    private JPanel L2C2;
    private JPanel L3C2;
    private JPanel L4C2;
    private JPanel L5C2;
    private JPanel L6C2;
    private JPanel L7C2;
    private JPanel L2C3;
    private JPanel L2C4;
    private JPanel L2C5;
    private JPanel L2C6;
    private JPanel L2C7;
    private JPanel L3C3;
    private JPanel L4C3;
    private JPanel L5C3;
    private JPanel L6C3;
    private JPanel L7C3;
    private JPanel L3C4;
    private JPanel L3C5;
    private JPanel L3C6;
    private JPanel L3C7;
    private JPanel L4C4;
    private JPanel L4C5;
    private JPanel L4C6;
    private JPanel L4C7;
    private JPanel L5C4;
    private JPanel L6C4;
    private JPanel L7C4;
    private JPanel L5C5;
    private JPanel L6C5;
    private JPanel L7C5;
    private JPanel L5C6;
    private JPanel L6C6;
    private JPanel L7C6;
    private JPanel L5C7;
    private JPanel L6C7;
    private JPanel L7C7;

    public PlateauWindow(int rows, int cols, Plateau plateau) throws IOException {
        super("Plateau");
        setSize(700, 700);

        // Initialize the main panel with a dynamic grid layout
        Plateau = new JPanel();
        Plateau.setLayout(new GridLayout(rows, cols));

        // Create a 2D array for panels
        JPanel[][] panels = new JPanel[rows][cols];

        // Initialize and add all panels to the Plateau
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                panels[row][col] = new JPanel();
                panels[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Optional: add borders for visibility
                Plateau.add(panels[row][col]);
            }
        }

        // Use the plateau object to set up the initial state of the grid
        List<List<Tuile>> grid = plateau.getPlateau();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Tuile tuile = grid.get(row).get(col);
                if (tuile != null) {
                    BufferedImage image = null;
                    switch (tuile.getPattern()) {
                        case ANGLE:
                            image = ANGLEpic;
                            break;
                        case DROIT:
                            image = DROITpic;
                            break;
                        case T:
                            image = Tpic;
                            break;
                    }
                    switch (tuile.getDirection()) {
                        case NORTH:
                            image = ImageHelper.rotateCounterClockwise(image);
                            break;
                        case EAST:
                            image = ImageHelper.rotateClockwise(image);
                            break;
                        case SOUTH:
                            image = ImageHelper.rotateClockwise(ImageHelper.rotateClockwise(image));
                            break;
                        case WEST:
                            break;
                        case null :
                            break;
                    }
                    if (image != null) {
                        Image scaledImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                        panels[row][col].add(new JLabel(new ImageIcon(scaledImage)));
                    }
                }
            }
        }

        //scale les images
        /*
        if (ANGLEpic != null) {
            Image scaledImage = ANGLEpic.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            panels[0][cols - 1].add(new JLabel(new ImageIcon(scaledImage))); // Top-right corner

            BufferedImage rotatedImage = ImageHelper.rotateClockwise(ANGLEpic);
            scaledImage = rotatedImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            panels[rows - 1][cols - 1].add(new JLabel(new ImageIcon(scaledImage))); // Bottom-right corner


            rotatedImage = ImageHelper.rotateClockwise(ImageHelper.rotateClockwise(ANGLEpic));
            scaledImage = rotatedImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            panels[rows - 1][0].add(new JLabel(new ImageIcon(scaledImage))); // Bottom-left corner


            rotatedImage = ImageHelper.rotateCounterClockwise(ANGLEpic);
            scaledImage = rotatedImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            panels[0][0].add(new JLabel(new ImageIcon(scaledImage))); // Top-left corner




            this.addComponentListener(new ComponentAdapter() {
                public void componentResized(ComponentEvent componentEvent) {
                    for (JPanel[] listePanel: panels) {
                        for (JPanel pan: listePanel) {
                            for (Component l : pan.getComponents()) {
                                if ( l instanceof JLabel ) {
                                    ((JLabel) l).setIcon(new ImageIcon(scaledImage.getScaledInstance(100,100,Image.SCALE_SMOOTH)));
                                }
                            }
                        }
                    }
                }
            });


        }
        */

        // Set the content pane
        setContentPane(Plateau);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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



}