package view;

import model.Direction;
import model.Plateau;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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
        setSize(700,700);
        //setSize(Toolkit.getDefaultToolkit().getScreenSize());


        JPanel mainButton = new JPanel();
        setLayout(new BorderLayout());

        JPanel arrow = new JPanel();
        arrow.setLayout(new GridLayout(2,3));

        arrow.add(new JPanel());
        arrow.add(upButton);
        upButton.setText("haut");
        arrow.add(new JPanel());
        arrow.add(leftButton);
        leftButton.setText("gauche");
        arrow.add(downButton);
        downButton.setText("bas");
        arrow.add(rightButton);
        rightButton.setText("droite");


        mainButton.add(turnButton, BorderLayout.WEST);
        turnButton.setText("Tourner la tuile");

        turnButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent actionEvent ) {
                getPlateau().pousserTuile(Direction.EAST, 1);
                try {
                    Plateau.updateMaze(Direction.EAST,1);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                getPlateau().printBoard();
            }
        });

        mainButton.add(finDeTourButton, BorderLayout.CENTER);
        mainButton.add(arrow, BorderLayout.EAST);


        Window.setLayout(new BorderLayout());
        Window.add(Plateau, BorderLayout.CENTER);
        Window.add(mainButton, BorderLayout.SOUTH);
        Plateau.setVisible(true);

        setContentPane(Window);
        setVisible(true);

    }

    public Plateau getPlateau() {
        return Plateau.getPlateauModel();
    }
}
