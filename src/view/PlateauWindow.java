package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import helpers.ImageHelper;

public class PlateauWindow extends JFrame {
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

    public PlateauWindow(int rows, int cols) throws IOException {
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

        // Load and scale the image
        BufferedImage myPicture = loadImage("img/Corner.png");
        if (myPicture != null) {
            Image scaledImage = myPicture.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            BufferedImage rotatedImage = ImageHelper.rotateClockwise(myPicture);
            Image scaledImage2 = rotatedImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);


            BufferedImage rotatedImage2 = ImageHelper.rotateClockwise(myPicture);
            rotatedImage2 = ImageHelper.rotateClockwise(rotatedImage2);
            Image scaledImage3 = rotatedImage2.getScaledInstance(100, 100, Image.SCALE_SMOOTH);

            BufferedImage rotatedImage3 = ImageHelper.rotateCounterClockwise(myPicture);
            Image scaledImage4 = rotatedImage3.getScaledInstance(100, 100, Image.SCALE_SMOOTH);



            // Add images to specific panels
            panels[0][0].add(new JLabel(new ImageIcon(scaledImage4))); // Top-left corner
            panels[0][cols - 1].add(new JLabel(new ImageIcon(scaledImage))); // Top-right corner
            panels[rows - 1][0].add(new JLabel(new ImageIcon(scaledImage3))); // Bottom-left corner
            panels[rows - 1][cols - 1].add(new JLabel(new ImageIcon(scaledImage2))); // Bottom-right corner
        }

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
    private BufferedImage loadImage(String filePath) {
        try {
            return ImageIO.read(new File(filePath));
        } catch (IOException e) {
            System.err.println("Failed to load image: " + filePath);
            return null;
        }
    }


}