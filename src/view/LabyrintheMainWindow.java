package view;

import model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LabyrintheMainWindow extends JFrame{
    private JButton finDeTourButton;


    private JButton upButton = new JButton();

    private JButton downButton = new JButton();
    private JButton leftButton = new JButton();
    private JButton rightButton = new JButton();

    private JButton turnButton = new JButton();


    private ImagePanel remainingTilePanel;

    private JPanel centerPanel = new JPanel();
    private JPanel window;
    private PlateauWindow PlateauW;

    private Tour m_turn ;


    public LabyrintheMainWindow() throws IOException {
        super("LA VERSION NUMERIQUE DU LABYRINTHE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        PlateauW = new PlateauWindow(new Plateau());
        m_turn= new Tour(getPlateau().getJoueursDuPlateau());
        for (Joueur j:getPlateau().getJoueursDuPlateau()) {
            PlateauW.loadPlayers(j);
        }
        setSize(1000,800);
        BufferedImage imageJoueur = ImageIO.read(new File("img/Pion" + m_turn.getJoueurDuTour().getCouleur() +  ".png")) ;
        ImagePanel panelImageJoueur = new ImagePanel(imageJoueur);

        JPanel mainButton = new JPanel();
        mainButton.setLayout(new GridLayout(2,8));

        JPanel arrow = new JPanel();
        arrow.setLayout(new GridLayout(2,3));

        JPanel panelTuileRestante = new JPanel();
        panelTuileRestante.setLayout(new GridLayout(1,1));


        arrow.add(new JPanel());
        arrow.add(upButton);
        upButton.setText("⬆️");
        arrow.add(new JPanel());
        arrow.add(leftButton);
        leftButton.setText("⬅️");
        arrow.add(downButton);
        downButton.setText("⬇️");
        arrow.add(rightButton);
        rightButton.setText("➡️");

        upButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int oldTileID = m_turn.getJoueurDuTour().getTuile().getCoordoneeX()*7 + m_turn.getJoueurDuTour().getTuile().getCoordoneeY();
                PlateauW.getPlateauModel().deplacerJoueur(m_turn.getJoueurDuTour() , Direction.NORTH);
                try {
                    PlateauW.updateTileByID(oldTileID);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    for (Joueur j :getPlateau().getJoueursDuPlateau()) {
                        PlateauW.loadPlayers(j);
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int oldTileID = m_turn.getJoueurDuTour().getTuile().getCoordoneeX()*7 + m_turn.getJoueurDuTour().getTuile().getCoordoneeY();
                PlateauW.getPlateauModel().deplacerJoueur(m_turn.getJoueurDuTour() , Direction.WEST);
                try {
                    PlateauW.updateTileByID(oldTileID);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    for (Joueur j :getPlateau().getJoueursDuPlateau()) {
                        PlateauW.loadPlayers(j);
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int oldTileID = m_turn.getJoueurDuTour().getTuile().getCoordoneeX()*7 + m_turn.getJoueurDuTour().getTuile().getCoordoneeY();
                PlateauW.getPlateauModel().deplacerJoueur(m_turn.getJoueurDuTour() , Direction.EAST);
                try {
                    PlateauW.updateTileByID(oldTileID);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    for (Joueur j :getPlateau().getJoueursDuPlateau()) {
                        PlateauW.loadPlayers(j);
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        downButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int oldTileID = m_turn.getJoueurDuTour().getTuile().getCoordoneeX()*7 + m_turn.getJoueurDuTour().getTuile().getCoordoneeY();
                PlateauW.getPlateauModel().deplacerJoueur(m_turn.getJoueurDuTour() , Direction.SOUTH);
                try {
                    PlateauW.updateTileByID(oldTileID);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    for (Joueur j :getPlateau().getJoueursDuPlateau()) {
                        PlateauW.loadPlayers(j);
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        remainingTilePanel = ImagePanel.getImageByTile(this.getPlateau().getTuile());
        PlateauW.addObserver(remainingTilePanel);

        JPanel objectifPanel = new JPanel();
        objectifPanel.setLayout(new BorderLayout());
        JLabel objectifLabel = new JLabel(m_turn.getJoueurDuTour().getCurrentGoal().name()) ;
        objectifPanel.add(objectifLabel, BorderLayout.EAST) ;
        objectifPanel.add(panelImageJoueur , BorderLayout.WEST) ;

        mainButton.add(new JPanel());
        mainButton.add(turnButton);
        mainButton.add(new JPanel());
        mainButton.add(new JPanel());
        mainButton.add(new JPanel());
        mainButton.add(new JPanel());
        mainButton.add(new JPanel());
        mainButton.add(new JPanel());
        mainButton.add(new JPanel());

        mainButton.add(new JPanel());
        mainButton.add(remainingTilePanel);
        mainButton.add(new JPanel());
        mainButton.add(new JPanel());
        mainButton.add(arrow);
        mainButton.add(new JPanel());
        mainButton.add(objectifPanel);
        turnButton.setText("Tourner la tuile");
        mainButton.add(new JPanel());
        turnButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent actionEvent ) {
                switch (getPlateau().getTuile().getDirection()) {
                    case NORTH -> getPlateau().getTuile().tournerTuile(Direction.EAST);
                    case EAST -> getPlateau().getTuile().tournerTuile(Direction.SOUTH);
                    case SOUTH -> getPlateau().getTuile().tournerTuile(Direction.WEST);
                    case WEST -> getPlateau().getTuile().tournerTuile(Direction.NORTH);

                }
                try {
                    PlateauW.notifyObserversMazeChange();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });



        mainButton.add(finDeTourButton);
        finDeTourButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent actionEvent ) {
                m_turn.tourSuivant();
                objectifLabel.setText(m_turn.getJoueurDuTour().getCurrentGoal().name());
                try {
                    BufferedImage imageJoueur2 = ImageIO.read(new File("img/Pion" + m_turn.getJoueurDuTour().getCouleur() +  ".png")) ;
                    ImagePanel panelImageJoueur2 = new ImagePanel(imageJoueur2) ;
                    objectifPanel.remove(panelImageJoueur);
                    objectifPanel.add(panelImageJoueur2, BorderLayout.WEST);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println( m_turn.getJoueurDuTour().getCurrentGoal().name());
            }
        });

        JPanel panelsButtonHaut = new JPanel() ;


        panelsButtonHaut.setLayout(new GridLayout(1,7));

        JButton insert1 = new JButton();
        insert1.setText("Insert Colonne 1");

        insert1.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent actionEvent ) {
                getPlateau().pousserTuile(Direction.SOUTH, 1);
                try {
                    PlateauW.updateMaze(Direction.SOUTH,1);
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
                    PlateauW.updateMaze(Direction.SOUTH,3);
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
                    PlateauW.updateMaze(Direction.SOUTH,5);
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
        panelButtonBas.setLayout(new GridLayout(1,5));

        JButton insert4 = new JButton();
        insert4.setText("Insert Colonne");
        insert4.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent actionEvent ) {
                getPlateau().pousserTuile(Direction.NORTH, 1);
                try {
                    PlateauW.updateMaze(Direction.NORTH,1);
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
                    PlateauW.updateMaze(Direction.NORTH,3);
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
                    PlateauW.updateMaze(Direction.NORTH,5);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                getPlateau().printBoard();
            }
        });

        panelButtonBas.add(insert4);
        panelButtonBas.add(new JPanel());
        panelButtonBas.add(insert5);
        panelButtonBas.add(new JPanel());
        panelButtonBas.add(insert6);
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
                    PlateauW.updateMaze(Direction.WEST,1);
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
                    PlateauW.updateMaze(Direction.WEST,3);
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
                    PlateauW.updateMaze(Direction.WEST,5);
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
                    PlateauW.updateMaze(Direction.EAST,1);
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
                    PlateauW.updateMaze(Direction.EAST,3);
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
                    PlateauW.updateMaze(Direction.EAST,5);
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




        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(panelsButtonHaut, BorderLayout.NORTH);
        centerPanel.add(PlateauW, BorderLayout.CENTER);
        centerPanel.add(panelButtonBas, BorderLayout.SOUTH);
        centerPanel.add(panelButtonDroite, BorderLayout.EAST);
        centerPanel.add(panelButtonGauche, BorderLayout.WEST);
        window.setLayout(new BorderLayout());
        window.add(centerPanel, BorderLayout.CENTER);
        window.add(mainButton, BorderLayout.SOUTH);


        setContentPane(window);
        setVisible(true);

    }

    public Plateau getPlateau() {
        return PlateauW.getPlateauModel();
    }

}
