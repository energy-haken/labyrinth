package view;

import model.Plateau;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class LabyrintheMainWindow extends JFrame{
    private JButton finDeTourButton;
    private JPanel Window;
    private PlateauWindow Plateau;
    public LabyrintheMainWindow() throws IOException {
        super("Jeu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Plateau = new PlateauWindow(7,7);
        setSize(700, 700);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());

        int i = 3;
        int j = 3;
        Window.setLayout(new BorderLayout());
//        JPanel[][] panelHolder = new JPanel[i][j];
//
//        for(int m = 0; m < i; m++) {
//            for(int n = 0; n < j; n++) {
//                panelHolder[m][n] = new JPanel();
//                Window.add(panelHolder[m][n]);
//            }
//        }
//        panelHolder[0][0].add(finDeTourButton);
//        panelHolder[1][2].add(Plateau);
        Window.add(finDeTourButton, BorderLayout.SOUTH);
        Window.add(Plateau, BorderLayout.CENTER);
        repaint();
        revalidate();
        Plateau.setVisible(true);

        setContentPane(Window);
        setVisible(true);
        while(true){

        }
    }
}
