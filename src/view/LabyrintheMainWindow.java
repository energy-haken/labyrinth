package view;

import controller.LabyrintheController;
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

    private JButton _upButton = new JButton();
    private JButton _downButton = new JButton();
    private JButton _leftButton = new JButton();
    private JButton _rightButton = new JButton();
    private JButton _turnButton = new JButton();
    private ImagePanel _remainingTilePanel;
    private JPanel _centerPanel = new JPanel();
    private JPanel _windowPanel;
    private PlateauWindow _plateauWindow;
    private Turn _currentTurn;
    private JButton _insertColumn1Bottom = new JButton();
    private JButton _insertColumn1Top = new JButton();
    private JButton _insertColumn2Bottom = new JButton();
    private JButton _insertColumn2Top = new JButton();
    private JButton _insertColumn3Bottom = new JButton();
    private JButton _insertColumn3Top = new JButton();
    private JButton _insertLine1Left = new JButton();
    private JButton _insertLine1Right = new JButton();
    private JButton _insertLine2Left = new JButton();
    private JButton _insertLine2Right = new JButton();
    private JButton _insertLine3Left = new JButton();
    private JButton _insertLine3Right = new JButton();

    public LabyrintheMainWindow() throws IOException {
        super("The Numerical Maze");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _plateauWindow = new PlateauWindow(new Board());
        _currentTurn = new Turn(getBoard().getPlayersOnBoard());
        LabyrintheController controller = new LabyrintheController(_plateauWindow.getBoardModel());
        for (Player player : getBoard().getPlayersOnBoard()) {
            _plateauWindow.loadPlayers(player);
        }
        setSize(1000, 800);
        BufferedImage playerImage = ImageIO.read(new File("img/Pion" + _currentTurn.getCurrentPlayer().getColor() + ".png"));
        ImagePanel playerImagePanel = new ImagePanel(playerImage);
        BufferedImage goalImage = ImageIO.read(new File("img/Objectives/" + _currentTurn.getCurrentPlayer().getCurrentGoal().name() + ".png"));
        ImagePanel goalImagePanel = new ImagePanel(goalImage);
        JPanel mainButtonPanel = new JPanel();
        mainButtonPanel.setLayout(new GridLayout(2, 8));

        JPanel arrowPanel = new JPanel();
        arrowPanel.setLayout(new GridLayout(2, 3));

        JPanel remainingTilePanelContainer = new JPanel();
        remainingTilePanelContainer.setLayout(new GridLayout(1, 1));

        arrowPanel.add(new JPanel());
        arrowPanel.add(_upButton);
        _upButton.setText("⬆️");
        arrowPanel.add(new JPanel());
        arrowPanel.add(_leftButton);
        _leftButton.setText("⬅️");
        arrowPanel.add(_downButton);
        _downButton.setText("⬇️");
        arrowPanel.add(_rightButton);
        _rightButton.setText("➡️");

        disableButtonsMovement();

        _upButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int oldTileID = _currentTurn.getCurrentPlayer().getTile().getCoordinateX() * 7 + _currentTurn.getCurrentPlayer().getTile().getCoordinateY();
                controller.modifyMovePlayer(_currentTurn.getCurrentPlayer() , Direction.NORTH);
                try {
                    _plateauWindow.updateTileByID(oldTileID);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    for (Player player : getBoard().getPlayersOnBoard()) {
                        _plateauWindow.loadPlayers(player);
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        _leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int oldTileID = _currentTurn.getCurrentPlayer().getTile().getCoordinateX() * 7 + _currentTurn.getCurrentPlayer().getTile().getCoordinateY();
                controller.modifyMovePlayer(_currentTurn.getCurrentPlayer(), Direction.WEST);
                try {
                    _plateauWindow.updateTileByID(oldTileID);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    for (Player player : getBoard().getPlayersOnBoard()) {
                        _plateauWindow.loadPlayers(player);
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        _rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int oldTileID = _currentTurn.getCurrentPlayer().getTile().getCoordinateX() * 7 + _currentTurn.getCurrentPlayer().getTile().getCoordinateY();
                controller.modifyMovePlayer(_currentTurn.getCurrentPlayer(), Direction.EAST);
                try {
                    _plateauWindow.updateTileByID(oldTileID);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    for (Player player : getBoard().getPlayersOnBoard()) {
                        _plateauWindow.loadPlayers(player);
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        _downButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int oldTileID = _currentTurn.getCurrentPlayer().getTile().getCoordinateX() * 7 + _currentTurn.getCurrentPlayer().getTile().getCoordinateY();
                controller.modifyMovePlayer(_currentTurn.getCurrentPlayer(), Direction.SOUTH);
                try {
                    _plateauWindow.updateTileByID(oldTileID);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    for (Player player : getBoard().getPlayersOnBoard()) {
                        _plateauWindow.loadPlayers(player);
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        _remainingTilePanel = ImagePanel.getImageByTile(this.getBoard().getTile());
        _plateauWindow.addObserver(_remainingTilePanel);

        JPanel goalPanel = new JPanel();
        goalPanel.setLayout(new BorderLayout());
        JLabel goalLabel = new JLabel(_currentTurn.getCurrentPlayer().getCurrentGoal().name());
        goalPanel.add(goalLabel, BorderLayout.EAST);
        goalPanel.add(goalImagePanel, BorderLayout.CENTER);
        goalPanel.add(playerImagePanel, BorderLayout.WEST);

        mainButtonPanel.add(new JPanel());
        mainButtonPanel.add(_turnButton);
        mainButtonPanel.add(new JPanel());
        mainButtonPanel.add(new JPanel());
        mainButtonPanel.add(new JPanel());
        mainButtonPanel.add(new JPanel());
        mainButtonPanel.add(new JPanel());
        mainButtonPanel.add(new JPanel());
        mainButtonPanel.add(new JPanel());

        mainButtonPanel.add(new JPanel());
        mainButtonPanel.add(_remainingTilePanel);
        mainButtonPanel.add(new JPanel());
        mainButtonPanel.add(new JPanel());
        mainButtonPanel.add(arrowPanel);
        mainButtonPanel.add(new JPanel());
        mainButtonPanel.add(goalPanel);
        _turnButton.setText("Rotate Tile");
        mainButtonPanel.add(new JPanel());
        _turnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                switch (getBoard().getTile().getDirection()) {
                    case NORTH -> controller.modifyRotateTile(Direction.EAST);
                    case EAST -> controller.modifyRotateTile(Direction.SOUTH);
                    case SOUTH -> controller.modifyRotateTile(Direction.WEST);
                    case WEST -> controller.modifyRotateTile(Direction.NORTH);
                }
                try {
                    _plateauWindow.notifyObserversMazeChange();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        mainButtonPanel.add(endTurnButton);

        // The differents buttons to insert the tile

        JPanel topButtonPanel = new JPanel();
        topButtonPanel.setLayout(new GridLayout(1, 7));

        _insertColumn1Bottom = new JButton();
        _insertColumn1Bottom.setText("Insert Column 1");
        _insertColumn2Bottom = new JButton();
        _insertColumn2Bottom.setText("Insert Column 2");
        _insertColumn3Bottom = new JButton();
        _insertColumn3Bottom.setText("Insert Column 3");
        _insertColumn1Top = new JButton();
        _insertColumn1Top.setText("Insert Column");
        _insertColumn2Top = new JButton();
        _insertColumn2Top.setText("Insert Column 2");
        _insertColumn3Top = new JButton();
        _insertColumn3Top.setText("Insert Column 3");
        _insertLine1Left = new JButton();
        _insertLine1Left.setText("Insert Row 1");
        _insertLine2Left = new JButton();
        _insertLine2Left.setText("Insert Row 2");
        _insertLine3Left = new JButton();
        _insertLine3Left.setText("Insert Row 3");
        _insertLine1Right = new JButton();
        _insertLine1Right.setText("Insert Row 1");
        _insertLine2Right = new JButton();
        _insertLine2Right.setText("Insert Row 2");
        _insertLine3Right = new JButton();
        _insertLine3Right.setText("Insert Row 3");

        // Actions listeners of the buttons

        _insertColumn1Bottom.addActionListener(actionEvent -> {
            controller.modifyPushTile(Direction.SOUTH, 1);
            try {
                _plateauWindow.updateMaze(Direction.SOUTH, 1);
                disableButtonsInsert();
                enableButtonsMovement();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        _insertColumn2Bottom.addActionListener(actionEvent -> {
            controller.modifyPushTile(Direction.SOUTH, 3);
            try {
                _plateauWindow.updateMaze(Direction.SOUTH, 3);
                disableButtonsInsert();
                enableButtonsMovement();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        _insertColumn3Bottom.addActionListener(actionEvent -> {
            controller.modifyPushTile(Direction.SOUTH, 5);
            try {
                _plateauWindow.updateMaze(Direction.SOUTH, 5);
                disableButtonsInsert();
                enableButtonsMovement();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });



        JPanel bottomButtonPanel = new JPanel();
        bottomButtonPanel.setLayout(new GridLayout(1, 5));

        _insertColumn1Top.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controller.modifyPushTile(Direction.NORTH, 1);
                try {
                    _plateauWindow.updateMaze(Direction.NORTH, 1);
                    disableButtonsInsert();
                    enableButtonsMovement();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        _insertColumn2Top.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controller.modifyPushTile(Direction.NORTH, 3);
                try {
                    _plateauWindow.updateMaze(Direction.NORTH, 3);
                    disableButtonsInsert();
                    enableButtonsMovement();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        _insertColumn3Top.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controller.modifyPushTile(Direction.NORTH, 5);
                try {
                    _plateauWindow.updateMaze(Direction.NORTH, 5);
                    disableButtonsInsert();
                    enableButtonsMovement();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });



        JPanel rightButtonPanel = new JPanel();
        rightButtonPanel.setLayout(new GridLayout(7, 1));

        _insertLine1Left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controller.modifyPushTile(Direction.WEST, 1);
                try {
                    _plateauWindow.updateMaze(Direction.WEST, 1);
                    disableButtonsInsert();
                    enableButtonsMovement();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        _insertLine2Left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controller.modifyPushTile(Direction.WEST, 3);
                try {
                    _plateauWindow.updateMaze(Direction.WEST, 3);
                    disableButtonsInsert();
                    enableButtonsMovement();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        _insertLine3Left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controller.modifyPushTile(Direction.WEST, 5);
                try {
                    _plateauWindow.updateMaze(Direction.WEST, 5);
                    disableButtonsInsert();
                    enableButtonsMovement();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });



        JPanel leftButtonPanel = new JPanel();
        leftButtonPanel.setLayout(new GridLayout(7, 1));

        _insertLine1Right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controller.modifyPushTile(Direction.EAST, 1);
                try {
                    _plateauWindow.updateMaze(Direction.EAST, 1);
                    disableButtonsInsert();
                    enableButtonsMovement();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        _insertLine2Right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controller.modifyPushTile(Direction.EAST, 3);
                try {
                    _plateauWindow.updateMaze(Direction.EAST, 3);
                    disableButtonsInsert();
                    enableButtonsMovement();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        _insertLine3Right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controller.modifyPushTile(Direction.EAST, 5);
                try {
                    _plateauWindow.updateMaze(Direction.EAST, 5);
                    disableButtonsInsert();
                    enableButtonsMovement();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        // Add to the differents panels
        topButtonPanel.add(new JPanel());
        topButtonPanel.add(_insertColumn1Bottom);
        topButtonPanel.add(new JPanel());
        topButtonPanel.add(_insertColumn2Bottom);
        topButtonPanel.add(new JPanel());
        topButtonPanel.add(_insertColumn3Bottom);
        topButtonPanel.add(new JPanel());
        topButtonPanel.setVisible(true);
        bottomButtonPanel.add(_insertColumn1Top);
        bottomButtonPanel.add(new JPanel());
        bottomButtonPanel.add(_insertColumn2Top);
        bottomButtonPanel.add(new JPanel());
        bottomButtonPanel.add(_insertColumn3Top);
        bottomButtonPanel.setVisible(true);
        rightButtonPanel.add(new JPanel());
        rightButtonPanel.add(_insertLine1Left);
        rightButtonPanel.add(new JPanel());
        rightButtonPanel.add(_insertLine2Left);
        rightButtonPanel.add(new JPanel());
        rightButtonPanel.add(_insertLine3Left);
        rightButtonPanel.add(new JPanel());
        rightButtonPanel.setVisible(true);
        leftButtonPanel.add(new JPanel());
        leftButtonPanel.add(_insertLine1Right);
        leftButtonPanel.add(new JPanel());
        leftButtonPanel.add(_insertLine2Right);
        leftButtonPanel.add(new JPanel());
        leftButtonPanel.add(_insertLine3Right);
        leftButtonPanel.add(new JPanel());
        leftButtonPanel.setVisible(true);


        // What happened when changing turn
        endTurnButton.addActionListener(actionEvent -> {
            _currentTurn.nextTurn();
            goalLabel.setText(_currentTurn.getCurrentPlayer().getCurrentGoal().name());
            try {
                // Changing the Player + associate Objective
                BufferedImage playerImage2 = ImageIO.read(new File("img/Pion" + _currentTurn.getCurrentPlayer().getColor() + ".png"));
                ImagePanel playerImagePanel2 = new ImagePanel(playerImage2);
                goalPanel.remove(playerImagePanel);
                goalPanel.add(playerImagePanel2, BorderLayout.WEST);
                BufferedImage goalImage2 = ImageIO.read(new File("img/Objectives/" + _currentTurn.getCurrentPlayer().getCurrentGoal().name() + ".png"));
                ImagePanel goalImagePanel2 = new ImagePanel(goalImage2);
                goalPanel.remove(goalImagePanel);
                goalPanel.add(goalImagePanel2, BorderLayout.CENTER);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // Re enable the buttons
            enableButtonsInsert();
            disableButtonsMovement();
        });
        // Add all the panels to main one
        _centerPanel.setLayout(new BorderLayout());
        _centerPanel.add(topButtonPanel, BorderLayout.NORTH);
        _centerPanel.add(_plateauWindow, BorderLayout.CENTER);
        _centerPanel.add(bottomButtonPanel, BorderLayout.SOUTH);
        _centerPanel.add(rightButtonPanel, BorderLayout.EAST);
        _centerPanel.add(leftButtonPanel, BorderLayout.WEST);
        _windowPanel.setLayout(new BorderLayout());
        _windowPanel.add(_centerPanel, BorderLayout.CENTER);
        _windowPanel.add(mainButtonPanel, BorderLayout.SOUTH);

        setContentPane(_windowPanel);
        setVisible(true);
    }

    /**
     *
     * @return the board of the game (Class : Board.java)
     */
    public Board getBoard() {
        return _plateauWindow.getBoardModel();
    }

    private void disableButtonsInsert(){
        _insertColumn1Top.setEnabled(false) ;
        _insertColumn2Top.setEnabled(false) ;
        _insertColumn3Top.setEnabled(false) ;
        _insertLine1Left.setEnabled(false) ;
        _insertLine2Left.setEnabled(false) ;
        _insertLine3Left.setEnabled(false) ;
        _insertLine1Right.setEnabled(false) ;
        _insertLine2Right.setEnabled(false) ;
        _insertLine3Right.setEnabled(false) ;
        _insertColumn1Bottom.setEnabled(false) ;
        _insertColumn2Bottom.setEnabled(false) ;
        _insertColumn3Bottom.setEnabled(false) ;
    }

    private void enableButtonsInsert(){
        _insertColumn1Top.setEnabled(true) ;
        _insertColumn2Top.setEnabled(true) ;
        _insertColumn3Top.setEnabled(true) ;
        _insertLine1Left.setEnabled(true) ;
        _insertLine2Left.setEnabled(true) ;
        _insertLine3Left.setEnabled(true) ;
        _insertLine1Right.setEnabled(true) ;
        _insertLine2Right.setEnabled(true) ;
        _insertLine3Right.setEnabled(true) ;
        _insertColumn1Bottom.setEnabled(true) ;
        _insertColumn2Bottom.setEnabled(true) ;
        _insertColumn3Bottom.setEnabled(true) ;
    }

    private void disableButtonsMovement(){
        _upButton.setEnabled(false) ;
        _downButton.setEnabled(false) ;
        _leftButton.setEnabled(false) ;
        _rightButton.setEnabled(false) ;
    }

    private void enableButtonsMovement(){
        _upButton.setEnabled(true) ;
        _downButton.setEnabled(true) ;
        _leftButton.setEnabled(true) ;
        _rightButton.setEnabled(true) ;
    }
}
