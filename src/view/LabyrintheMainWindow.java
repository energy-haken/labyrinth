package view;

import model.Plateau;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class LabyrintheMainWindow extends JFrame{
    private JButton finDeTourButton;


    private JButton upButton = new JButton();
    private JButton downButton = new JButton();
    private JButton leftButton = new JButton();
    private JButton rightButton = new JButton();

    private JButton turnButton = new JButton();




    private JPanel Window;
    private PlateauWindow Plateau;
    public LabyrintheMainWindow() throws IOException {
        super("Jeu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Plateau = new PlateauWindow(new Plateau());
        setSize(700, 700);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());


        JPanel mainButton = new JPanel();
        setLayout(new BorderLayout());

        JPanel arrow = new JPanel();
        arrow.setLayout(new GridLayout(2,3));

        arrow.add(new JPanel());
        arrow.add(upButton);
        arrow.add(new JPanel());
        arrow.add(downButton);
        arrow.add(leftButton);
        arrow.add(rightButton);

        Window.setLayout(new BorderLayout());
        Window.add(Plateau, BorderLayout.CENTER);
        Window.add(mainButton, BorderLayout.SOUTH);
        mainButton.add(finDeTourButton, BorderLayout.CENTER);
        mainButton.add(arrow, BorderLayout.EAST);
        repaint();
        revalidate();
        Plateau.setVisible(true);

        setContentPane(Window);
        setVisible(true);
    }

    public Plateau getPlateau() {
        return Plateau.getPlateauModel();
    }
}
