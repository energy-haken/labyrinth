package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;


public class ImagePanel extends JPanel {
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
            g.drawImage(scaledImage, 0, 0, panelWidth, panelHeight, this);
        }
    }


    public void updateImage(Image newImage){
        if (newImage != null) {
            // Redimensionner l'image pour s'adapter à la taille du panneau
            int panelWidth = getWidth();
            int panelHeight = getHeight();
            Image scaledImage = newImage.getScaledInstance(panelWidth, panelHeight, Image.SCALE_SMOOTH);
            getGraphics().drawImage(scaledImage, 0, 0, panelWidth, panelHeight, this);
        }
    }
}