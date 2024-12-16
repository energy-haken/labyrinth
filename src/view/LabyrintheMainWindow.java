package view;

import model.Direction;
import model.Plateau;
import model.tuiles.Tuile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        super("LA VERSION NUMERIQUE DU LABYRINTHE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Plateau = new PlateauWindow(new Plateau());
        setSize(700,700);
        //setSize(Toolkit.getDefaultToolkit().getScreenSize());


        JPanel mainButton = new JPanel();
        setLayout(new BorderLayout());

        JPanel mainButton2= new JPanel();
        setLayout(new BorderLayout());

        JPanel arrow = new JPanel();
        arrow.setLayout(new GridLayout(2,3));

        JPanel panelTuileRestante = new JPanel();
        panelTuileRestante.setLayout(new GridLayout(1,1));

        Tuile tuilreRestante = Plateau.getPlateauModel().getTuile() ;

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



        mainButton2.add(turnButton, BorderLayout.WEST);
        turnButton.setText("Tourner la tuile");




        mainButton2.add(finDeTourButton, BorderLayout.CENTER);
        mainButton.add(arrow, BorderLayout.CENTER);

        JPanel panelsButtonHaut = new JPanel() ;


        panelsButtonHaut.setLayout(new GridLayout(1,7));

        JButton insert1 = new JButton();
        insert1.setText("Insert Colonne 1");

        insert1.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent actionEvent ) {
                getPlateau().pousserTuile(Direction.SOUTH, 1);
                try {
                    Plateau.updateMaze(Direction.SOUTH,1);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                getPlateau().printBoard();
            }
        });

        JButton insert2 = new JButton();
        insert2.setText("Insert Colonne 2");

        insert2.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent actionEvent ) {
                getPlateau().pousserTuile(Direction.SOUTH, 3);
                try {
                    Plateau.updateMaze(Direction.SOUTH,3);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                getPlateau().printBoard();
            }
        });

        JButton insert3 = new JButton();
        insert3.setText("Insert Colonne 3");

        insert3.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent actionEvent ) {
                getPlateau().pousserTuile(Direction.SOUTH, 5);
                try {
                    Plateau.updateMaze(Direction.SOUTH,5);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                getPlateau().printBoard();
            }
        });

        panelsButtonHaut.add(new JPanel()) ;
        panelsButtonHaut.add(insert1);
        panelsButtonHaut.add(new JPanel()) ;
        panelsButtonHaut.add(insert2);
        panelsButtonHaut.add(new JPanel()) ;
        panelsButtonHaut.add(insert3);
        panelsButtonHaut.add(new JPanel()) ;
        panelsButtonHaut.setVisible(true);

        JPanel panelButtonBas = new JPanel();
        panelButtonBas.setLayout(new GridLayout(1,7));

        JButton insert4 = new JButton();
        insert4.setText("Insert Colonne");
        insert4.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent actionEvent ) {
                getPlateau().pousserTuile(Direction.NORTH, 1);
                try {
                    Plateau.updateMaze(Direction.NORTH,1);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                getPlateau().printBoard();
            }
        });
        JButton insert5 = new JButton();
        insert5.setText("Insert Colonne 2");

        insert5.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent actionEvent ) {
                getPlateau().pousserTuile(Direction.NORTH, 3);
                try {
                    Plateau.updateMaze(Direction.NORTH,3);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                getPlateau().printBoard();
            }
        });

        JButton insert6 = new JButton();
        insert6.setText("Insert Colonne 3");

        insert6.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent actionEvent ) {
                getPlateau().pousserTuile(Direction.NORTH, 5);
                try {
                    Plateau.updateMaze(Direction.NORTH,5);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                getPlateau().printBoard();
            }
        });

        panelButtonBas.add(mainButton);
        panelButtonBas.add(insert4);
        panelButtonBas.add(new JPanel());
        panelButtonBas.add(insert5);
        panelButtonBas.add(new JPanel());
        panelButtonBas.add(insert6);
        panelButtonBas.add(mainButton2) ;
        panelButtonBas.setVisible(true);



        JPanel panelButtonDroite = new JPanel();
        panelButtonDroite.setLayout(new GridLayout(7,1));

        JButton insert7 = new JButton();
        insert7.setText("Insert Ligne 1");

        insert7.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent actionEvent ) {
                getPlateau().pousserTuile(Direction.WEST, 1);
                try {
                    Plateau.updateMaze(Direction.WEST,1);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                getPlateau().printBoard();
            }
        });
        JButton insert8 = new JButton();
        insert8.setText("Insert Ligne 2");
        insert8.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent actionEvent ) {
                getPlateau().pousserTuile(Direction.WEST, 3);
                try {
                    Plateau.updateMaze(Direction.WEST,3);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                getPlateau().printBoard();
            }
        });
        JButton insert9 = new JButton();
        insert9.setText("Insert Ligne 3");
        insert9.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent actionEvent ) {
                getPlateau().pousserTuile(Direction.WEST, 5);
                try {
                    Plateau.updateMaze(Direction.WEST,5);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                getPlateau().printBoard();
            }
        });

        panelButtonDroite.add(new JPanel());
        panelButtonDroite.add(insert7);
        panelButtonDroite.add(new JPanel());
        panelButtonDroite.add(insert8);
        panelButtonDroite.add(new JPanel());
        panelButtonDroite.add(insert9);
        panelButtonDroite.add(new JPanel());
        panelButtonDroite.setVisible(true);

        JPanel panelButtonGauche = new JPanel();
        panelButtonGauche.setLayout(new GridLayout(7,1));

        JButton insert10 = new JButton();
        insert10.setText("Insert Ligne 1");
        insert10.addActionListener( new ActionListener() {
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
        JButton insert11 = new JButton();
        insert11.setText("Insert Ligne 2");
        insert11.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent actionEvent ) {
                getPlateau().pousserTuile(Direction.EAST, 3);
                try {
                    Plateau.updateMaze(Direction.EAST,3);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                getPlateau().printBoard();
            }
        });
        JButton insert12 = new JButton();
        insert12.setText("Insert Ligne 3");
        insert12.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent actionEvent ) {
                getPlateau().pousserTuile(Direction.EAST, 5);
                try {
                    Plateau.updateMaze(Direction.EAST,5);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                getPlateau().printBoard();
            }
        });

        panelButtonGauche.add(new JPanel());
        panelButtonGauche.add(insert10);
        panelButtonGauche.add(new JPanel());
        panelButtonGauche.add(insert11);
        panelButtonGauche.add(new JPanel());
        panelButtonGauche.add(insert12);
        panelButtonGauche.add(new JPanel());
        panelButtonGauche.setVisible(true);




        Window.setLayout(new BorderLayout());
        Window.add(panelsButtonHaut, BorderLayout.NORTH);
        Window.add(Plateau, BorderLayout.CENTER);
        Window.add(panelButtonBas, BorderLayout.SOUTH);
        Window.add(panelButtonDroite, BorderLayout.EAST);
        Window.add(panelButtonGauche, BorderLayout.WEST);
    //    Window.add(mainButton, BorderLayout.SOUTH);
        Plateau.setVisible(true);



        setContentPane(Window);
        setVisible(true);

    }

    public Plateau getPlateau() {
        return Plateau.getPlateauModel();
    }
}
