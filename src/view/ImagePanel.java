package view;

import helpers.ImageHelper;
import model.Direction;
import model.Joueur;
import model.tuiles.Tuile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class ImagePanel extends JPanel implements LabyrintheObserver{
    private BufferedImage image;

    public ImagePanel(BufferedImage image) {
        this.image = image;
        // Écouteur pour redessiner lors du redimensionnement
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            // Redimensionner l'image pour s'adapter à la taille du panneau
            int panelWidth = getWidth();
            int panelHeight = getHeight();
            Image scaledImage = image.getScaledInstance(panelWidth, panelHeight, Image.SCALE_SMOOTH);
            g.drawImage(scaledImage, 0, 0, panelWidth, panelHeight, null);
        }
    }


    public void updateImage(ImagePanel Panel){
        Image newImage = Panel.getImage();
        if (newImage != null) {
            // Redimensionner l'image pour s'adapter à la taille du panneau
            int panelWidth = getWidth();
            int panelHeight = getHeight();
            Image scaledImage = newImage.getScaledInstance(panelWidth, panelHeight, Image.SCALE_SMOOTH);
            getGraphics().drawImage(scaledImage, 0, 0, panelWidth, panelHeight, this);
        }
    }
    
    public static ImagePanel getImageByTile(Tuile tile) throws IOException {

        BufferedImage tileImage;
        switch (tile.getPattern()) {
            case T:
                if (tile.getObjectif() != null) {
                    tileImage = ImageHelper.merge("img/T.png", "img/Objectifs/" + tile.getObjectif().name() + ".png");
                } else {
                    tileImage = loadImage("img/T.png");
                }
                break;
            case ANGLE:
                if (tile.getObjectif() != null) {
                    tileImage = ImageHelper.merge("img/Corner.png", "img/Objectifs/" + tile.getObjectif().name() + ".png");
                } else {
                    tileImage = loadImage("img/Corner.png");
                }
                break;
            case DROIT:
                if (tile.getObjectif() != null) {
                    tileImage = ImageHelper.merge("img/LINE.png", "img/Objectifs/" + tile.getObjectif().name() + ".png");
                } else {
                    tileImage = loadImage("img/LINE.png");
                }
                break;
            default:
                tileImage = new BufferedImage(1,1,1);
                break;
        }
        switch (tile.getDirection()) {
            case NORTH:
                tileImage = ImageHelper.rotateCounterClockwise(tileImage);
                break;
            case EAST:
                break;
            case SOUTH:
                tileImage = ImageHelper.rotateClockwise(tileImage);
                break;
            case WEST:
                tileImage = ImageHelper.rotateClockwise(ImageHelper.rotateClockwise(tileImage));
                break;
            default:
                break;
        }
        ImagePanel test = new ImagePanel(tileImage);
        test.setSize(100,100);
        return test;
    }

    private static BufferedImage loadImage(String filePath) {
        try {
            return ImageIO.read(new File(filePath));
        } catch (IOException e) {
            System.err.println("Failed to load image: " + filePath);
            return null;
        }
    }

    public BufferedImage getImage(){
        return this.image;
    }

    @Override
    public void updateInsertTuile(Direction direction, int colonne) {

    }

    @Override
    public void updateTournerTuile(Direction direction) {

    }

    @Override
    public void updateDeplacerJoueur(Joueur joueur, Direction direction) {

    }

    @Override
    public void doBecauseMazeChange(Tuile tile) throws IOException {
         this.updateImage(getImageByTile(tile));
    }
}