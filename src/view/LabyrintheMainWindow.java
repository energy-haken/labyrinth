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

public class LabyrintheMainWindow extends JFrame {
    private JButton endTurnButton;

    private JButton upButton = new JButton();
    private JButton downButton = new JButton();
    private JButton leftButton = new JButton();
    private JButton rightButton = new JButton();

    private JButton turnButton = new JButton();

    private ImagePanel remainingTilePanel;

    private JPanel centerPanel = new JPanel();
    private JPanel windowPanel;
    private PlateauWindow plateauWindow;

    private Turn currentTurn;

    public LabyrintheMainWindow() throws IOException {
        super("The Numerical Maze");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        plateauWindow = new PlateauWindow(new Board());
        currentTurn = new Turn(getBoard().getPlayersOnBoard());
        for (Player player : getBoard().getPlayersOnBoard()) {
            plateauWindow.loadPlayers(player);
        }
        setSize(1000, 800);
        BufferedImage playerImage = ImageIO.read(new File("img/Pion" + currentTurn.getCurrentPlayer().getColor() + ".png"));
        ImagePanel playerImagePanel = new ImagePanel(playerImage);
        BufferedImage goalImage = ImageIO.read(new File("img/Objectives/" + currentTurn.getCurrentPlayer().getCurrentGoal().name() + ".png"));
        ImagePanel goalImagePanel = new ImagePanel(goalImage);
        JPanel mainButtonPanel = new JPanel();
        mainButtonPanel.setLayout(new GridLayout(2, 8));

        JPanel arrowPanel = new JPanel();
        arrowPanel.setLayout(new GridLayout(2, 3));

        JPanel remainingTilePanelContainer = new JPanel();
        remainingTilePanelContainer.setLayout(new GridLayout(1, 1));

        arrowPanel.add(new JPanel());
        arrowPanel.add(upButton);
        upButton.setText("⬆️");
        arrowPanel.add(new JPanel());
        arrowPanel.add(leftButton);
        leftButton.setText("⬅️");
        arrowPanel.add(downButton);
        downButton.setText("⬇️");
        arrowPanel.add(rightButton);
        rightButton.setText("➡️");

        upButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int oldTileID = currentTurn.getCurrentPlayer().getTile().getCoordinateX() * 7 + currentTurn.getCurrentPlayer().getTile().getCoordinateY();
                plateauWindow.getBoardModel().movePlayer(currentTurn.getCurrentPlayer(), Direction.NORTH);
                try {
                    plateauWindow.updateTileByID(oldTileID);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    for (Player player : getBoard().getPlayersOnBoard()) {
                        plateauWindow.loadPlayers(player);
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int oldTileID = currentTurn.getCurrentPlayer().getTile().getCoordinateX() * 7 + currentTurn.getCurrentPlayer().getTile().getCoordinateY();
                plateauWindow.getBoardModel().movePlayer(currentTurn.getCurrentPlayer(), Direction.WEST);
                try {
                    plateauWindow.updateTileByID(oldTileID);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    for (Player player : getBoard().getPlayersOnBoard()) {
                        plateauWindow.loadPlayers(player);
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int oldTileID = currentTurn.getCurrentPlayer().getTile().getCoordinateX() * 7 + currentTurn.getCurrentPlayer().getTile().getCoordinateY();
                plateauWindow.getBoardModel().movePlayer(currentTurn.getCurrentPlayer(), Direction.EAST);
                try {
                    plateauWindow.updateTileByID(oldTileID);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    for (Player player : getBoard().getPlayersOnBoard()) {
                        plateauWindow.loadPlayers(player);
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        downButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int oldTileID = currentTurn.getCurrentPlayer().getTile().getCoordinateX() * 7 + currentTurn.getCurrentPlayer().getTile().getCoordinateY();
                plateauWindow.getBoardModel().movePlayer(currentTurn.getCurrentPlayer(), Direction.SOUTH);
                try {
                    plateauWindow.updateTileByID(oldTileID);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    for (Player player : getBoard().getPlayersOnBoard()) {
                        plateauWindow.loadPlayers(player);
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        remainingTilePanel = ImagePanel.getImageByTile(this.getBoard().getTile());
        plateauWindow.addObserver(remainingTilePanel);

        JPanel goalPanel = new JPanel();
        goalPanel.setLayout(new BorderLayout());
        JLabel goalLabel = new JLabel(currentTurn.getCurrentPlayer().getCurrentGoal().name());
        goalPanel.add(goalLabel, BorderLayout.EAST);
        goalPanel.add(goalImagePanel, BorderLayout.CENTER);
        goalPanel.add(playerImagePanel, BorderLayout.WEST);

        mainButtonPanel.add(new JPanel());
        mainButtonPanel.add(turnButton);
        mainButtonPanel.add(new JPanel());
        mainButtonPanel.add(new JPanel());
        mainButtonPanel.add(new JPanel());
        mainButtonPanel.add(new JPanel());
        mainButtonPanel.add(new JPanel());
        mainButtonPanel.add(new JPanel());
        mainButtonPanel.add(new JPanel());

        mainButtonPanel.add(new JPanel());
        mainButtonPanel.add(remainingTilePanel);
        mainButtonPanel.add(new JPanel());
        mainButtonPanel.add(new JPanel());
        mainButtonPanel.add(arrowPanel);
        mainButtonPanel.add(new JPanel());
        mainButtonPanel.add(goalPanel);
        turnButton.setText("Rotate Tile");
        mainButtonPanel.add(new JPanel());
        turnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                switch (getBoard().getTile().getDirection()) {
                    case NORTH -> getBoard().getTile().rotateTile(Direction.EAST);
                    case EAST -> getBoard().getTile().rotateTile(Direction.SOUTH);
                    case SOUTH -> getBoard().getTile().rotateTile(Direction.WEST);
                    case WEST -> getBoard().getTile().rotateTile(Direction.NORTH);
                }
                try {
                    plateauWindow.notifyObserversMazeChange();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        mainButtonPanel.add(endTurnButton);

        JPanel topButtonPanel = new JPanel();
        topButtonPanel.setLayout(new GridLayout(1, 7));

        JButton insertColumn1Bottom = new JButton();
        insertColumn1Bottom.setText("Insert Column 1");
        JButton insertColumn2Bottom = new JButton();
        insertColumn2Bottom.setText("Insert Column 2");
        JButton insertColumn3Bottom = new JButton();
        insertColumn3Bottom.setText("Insert Column 3");
        JButton insertColumn1Top = new JButton();
        insertColumn1Top.setText("Insert Column");
        JButton insertColumn2Top = new JButton();
        insertColumn2Top.setText("Insert Column 2");
        JButton insertColumn3Top = new JButton();
        insertColumn3Top.setText("Insert Column 3");
        JButton insertRow1Left = new JButton();
        insertRow1Left.setText("Insert Row 1");
        JButton insertRow2Left = new JButton();
        insertRow2Left.setText("Insert Row 2");
        JButton insertRow3Left = new JButton();
        insertRow3Left.setText("Insert Row 3");
        JButton insertRow1Right = new JButton();
        insertRow1Right.setText("Insert Row 1");
        JButton insertRow2Right = new JButton();
        insertRow2Right.setText("Insert Row 2");
        JButton insertRow3Right = new JButton();
        insertRow3Right.setText("Insert Row 3");

        insertColumn1Bottom.addActionListener(actionEvent -> {
            getBoard().pushTile(Direction.SOUTH, 1);
            try {
                plateauWindow.updateMaze(Direction.SOUTH, 1);
                insertColumn1Top.setEnabled(false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            getBoard().printBoard();
        });

        insertColumn2Bottom.addActionListener(actionEvent -> {
            getBoard().pushTile(Direction.SOUTH, 3);
            try {
                plateauWindow.updateMaze(Direction.SOUTH, 3);
                insertColumn2Top.setEnabled(false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            getBoard().printBoard();
        });

        insertColumn3Bottom.addActionListener(actionEvent -> {
            getBoard().pushTile(Direction.SOUTH, 5);
            try {
                plateauWindow.updateMaze(Direction.SOUTH, 5);
                insertColumn3Top.setEnabled(false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            getBoard().printBoard();
        });

        topButtonPanel.add(new JPanel());
        topButtonPanel.add(insertColumn1Bottom);
        topButtonPanel.add(new JPanel());
        topButtonPanel.add(insertColumn2Bottom);
        topButtonPanel.add(new JPanel());
        topButtonPanel.add(insertColumn3Bottom);
        topButtonPanel.add(new JPanel());
        topButtonPanel.setVisible(true);

        JPanel bottomButtonPanel = new JPanel();
        bottomButtonPanel.setLayout(new GridLayout(1, 5));

        insertColumn1Top.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                getBoard().pushTile(Direction.NORTH, 1);
                try {
                    plateauWindow.updateMaze(Direction.NORTH, 1);
                    insertColumn1Bottom.setEnabled(false);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                getBoard().printBoard();
            }
        });

        insertColumn2Top.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                getBoard().pushTile(Direction.NORTH, 3);
                try {
                    plateauWindow.updateMaze(Direction.NORTH, 3);
                    insertColumn2Bottom.setEnabled(false);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                getBoard().printBoard();
            }
        });

        insertColumn3Top.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                getBoard().pushTile(Direction.NORTH, 5);
                try {
                    plateauWindow.updateMaze(Direction.NORTH, 5);
                    insertColumn3Bottom.setEnabled(false);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                getBoard().printBoard();
            }
        });

        bottomButtonPanel.add(insertColumn1Top);
        bottomButtonPanel.add(new JPanel());
        bottomButtonPanel.add(insertColumn2Top);
        bottomButtonPanel.add(new JPanel());
        bottomButtonPanel.add(insertColumn3Top);
        bottomButtonPanel.setVisible(true);

        JPanel rightButtonPanel = new JPanel();
        rightButtonPanel.setLayout(new GridLayout(7, 1));

        insertRow1Left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                getBoard().pushTile(Direction.WEST, 1);
                try {
                    plateauWindow.updateMaze(Direction.WEST, 1);
                    insertRow1Right.setEnabled(false);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                getBoard().printBoard();
            }
        });

        insertRow2Left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                getBoard().pushTile(Direction.WEST, 3);
                try {
                    plateauWindow.updateMaze(Direction.WEST, 3);
                    insertRow2Right.setEnabled(false);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                getBoard().printBoard();
            }
        });

        insertRow3Left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                getBoard().pushTile(Direction.WEST, 5);
                try {
                    plateauWindow.updateMaze(Direction.WEST, 5);
                    insertRow3Right.setEnabled(false);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                getBoard().printBoard();
            }
        });

        rightButtonPanel.add(new JPanel());
        rightButtonPanel.add(insertRow1Left);
        rightButtonPanel.add(new JPanel());
        rightButtonPanel.add(insertRow2Left);
        rightButtonPanel.add(new JPanel());
        rightButtonPanel.add(insertRow3Left);
        rightButtonPanel.add(new JPanel());
        rightButtonPanel.setVisible(true);

        JPanel leftButtonPanel = new JPanel();
        leftButtonPanel.setLayout(new GridLayout(7, 1));

        insertRow1Right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                getBoard().pushTile(Direction.EAST, 1);
                try {
                    plateauWindow.updateMaze(Direction.EAST, 1);
                    insertRow1Left.setEnabled(false);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                getBoard().printBoard();
            }
        });

        insertRow2Right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                getBoard().pushTile(Direction.EAST, 3);
                try {
                    plateauWindow.updateMaze(Direction.EAST, 3);
                    insertRow2Left.setEnabled(false);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                getBoard().printBoard();
            }
        });

        insertRow3Right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                getBoard().pushTile(Direction.EAST, 5);
                try {
                    plateauWindow.updateMaze(Direction.EAST, 5);
                    insertRow3Left.setEnabled(false);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                getBoard().printBoard();
            }
        });

        leftButtonPanel.add(new JPanel());
        leftButtonPanel.add(insertRow1Right);
        leftButtonPanel.add(new JPanel());
        leftButtonPanel.add(insertRow2Right);
        leftButtonPanel.add(new JPanel());
        leftButtonPanel.add(insertRow3Right);
        leftButtonPanel.add(new JPanel());
        leftButtonPanel.setVisible(true);

        endTurnButton.addActionListener(actionEvent -> {
            currentTurn.nextTurn();
            goalLabel.setText(currentTurn.getCurrentPlayer().getCurrentGoal().name());
            try {
                BufferedImage playerImage2 = ImageIO.read(new File("img/Pion" + currentTurn.getCurrentPlayer().getColor() + ".png"));
                ImagePanel playerImagePanel2 = new ImagePanel(playerImage2);
                goalPanel.remove(playerImagePanel);
                goalPanel.add(playerImagePanel2, BorderLayout.WEST);
                BufferedImage goalImage2 = ImageIO.read(new File("img/Objectives/" + currentTurn.getCurrentPlayer().getCurrentGoal().name() + ".png"));
                ImagePanel goalImagePanel2 = new ImagePanel(goalImage2);
                goalPanel.remove(goalImagePanel);
                goalPanel.add(goalImagePanel2, BorderLayout.CENTER);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            insertColumn1Bottom.setEnabled(true);
            insertColumn2Bottom.setEnabled(true);
            insertColumn3Bottom.setEnabled(true);
            insertColumn1Top.setEnabled(true);
            insertColumn2Top.setEnabled(true);
            insertColumn3Top.setEnabled(true);
            insertRow1Right.setEnabled(true);
            insertRow2Right.setEnabled(true);
            insertRow3Right.setEnabled(true);
            insertRow1Left.setEnabled(true);
            insertRow2Left.setEnabled(true);
            insertRow3Left.setEnabled(true);

            System.out.println(currentTurn.getCurrentPlayer().getCurrentGoal().name());
        });

        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(topButtonPanel, BorderLayout.NORTH);
        centerPanel.add(plateauWindow, BorderLayout.CENTER);
        centerPanel.add(bottomButtonPanel, BorderLayout.SOUTH);
        centerPanel.add(rightButtonPanel, BorderLayout.EAST);
        centerPanel.add(leftButtonPanel, BorderLayout.WEST);
        windowPanel.setLayout(new BorderLayout());
        windowPanel.add(centerPanel, BorderLayout.CENTER);
        windowPanel.add(mainButtonPanel, BorderLayout.SOUTH);

        setContentPane(windowPanel);
        setVisible(true);
    }

    public Board getBoard() {
        return plateauWindow.getBoardModel();
    }
}
