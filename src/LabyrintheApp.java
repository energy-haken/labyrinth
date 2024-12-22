import view.LabyrintheMainWindow;

import java.io.IOException;

public class LabyrintheApp {
    public static void main(String[] args) throws IOException {

        //Plateau cUnPlateau = new Background().lancementJeu();

        //Background background = new Background() ;
        /*background.lancementJeu();

            try {
                // Create a PlateauWindow with a 7x7 grid and the plateau object
                new PlateauWindow(7, 7, plateau);
            } catch (IOException e) {
                System.err.println("Error initializing PlateauWindow: " + e.getMessage());
            }


         */
        

        LabyrintheMainWindow mainWindow = new LabyrintheMainWindow();
        mainWindow.getBoard().printBoard();

    }
}
