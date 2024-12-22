package view;

import helpers.ImageHelper;
import model.Direction;
import model.Player;
import model.tiles.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagePanel extends JPanel implements LabyrinthObserver {
    private BufferedImage image;

    public ImagePanel(BufferedImage image) {
        this.image = image;
        // Listener to repaint on resize
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
            // Resize the image to fit the panel size
            int panelWidth = getWidth();
            int panelHeight = getHeight();
            Image scaledImage = image.getScaledInstance(panelWidth, panelHeight, Image.SCALE_SMOOTH);
            g.drawImage(scaledImage, 0, 0, panelWidth, panelHeight, null);
        }
    }

    public void updateImage(ImagePanel panel) {
        BufferedImage newImage = panel.getImage();
        if (newImage != null) {
            this.image = newImage;
            repaint();
        }
    }

    public static ImagePanel getImageByTile(Tile tile) throws IOException {
        BufferedImage tileImage;
        switch (tile.getPattern()) {
            case T:
                if (tile.getObjective() != null) {
                    tileImage = ImageHelper.merge("img/T.png", "img/Objectives/" + tile.getObjective().name() + ".png");
                } else {
                    tileImage = loadImage("img/T.png");
                }
                break;
            case CORNER:
                if (tile.getObjective() != null) {
                    tileImage = ImageHelper.merge("img/Corner.png", "img/Objectives/" + tile.getObjective().name() + ".png");
                } else {
                    tileImage = loadImage("img/Corner.png");
                }
                break;
            case LINE:
                if (tile.getObjective() != null) {
                    tileImage = ImageHelper.merge("img/LINE.png", "img/Objectives/" + tile.getObjective().name() + ".png");
                } else {
                    tileImage = loadImage("img/LINE.png");
                }
                break;
            default:
                tileImage = new BufferedImage(1, 1, 1);
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
        ImagePanel panel = new ImagePanel(tileImage);
        panel.setSize(100, 100);
        return panel;
    }

    private static BufferedImage loadImage(String filePath) {
        try {
            return ImageIO.read(new File(filePath));
        } catch (IOException e) {
            System.err.println("Failed to load image: " + filePath);
            return null;
        }
    }

    public BufferedImage getImage() {
        return this.image;
    }

    @Override
    public void updateInsertTile(Direction direction, int column) {
        // Implementation here
    }

    @Override
    public void updateRotateTile(Direction direction) {
        // Implementation here
    }

    @Override
    public void updateMovePlayer(Player player, Direction direction) {
        // Implementation here
    }

    @Override
    public void doBecauseMazeChange(Tile tile) throws IOException {
        this.updateImage(getImageByTile(tile));
    }
}