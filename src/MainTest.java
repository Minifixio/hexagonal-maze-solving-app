import maze.Maze;
import maze.MazeReadingException;

import java.io.*;

public class MainTest {
    public static void main(String[] args) throws MazeReadingException {
        String fileName = "data/" + args[0] + ".txt";
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            try {
                // Possible IOException si la lecture de la première ligne pose problème
                int mazeWidth = br.readLine().length();
                int mazeLength = 1;
                while (br.readLine() != null) mazeLength++;

                Maze maze = new Maze(mazeWidth, mazeLength);
                System.out.println("Création d'un labyrinthe de longueur : " + mazeLength + " et de largeur " + mazeWidth);

                // On reset le Buffer car on a fait descendre le curseur jusqu'en bas pour avoir mazeLength
                br = new BufferedReader(new FileReader(fileName));

                String line = null;
                int lineCount = 0;
                while((line = br.readLine())!= null) {

                    // Si l'une des lignes n'est pas de la même taille que la première (avec la convention qu'un labyrinthe est rectangulaire)
                    if (line.length() != mazeWidth) {
                        throw new MazeReadingException(fileName, lineCount, "Ligne de mauvaise taille");
                    }

                    for (int i=0; i<line.length(); i++) {
                        boolean boxValid = maze.addBoxByCoords(i, lineCount, line.charAt(i));

                        // Si le caractère n'est pas 'E', 'W', 'A' ou 'D'
                        if (!boxValid) {
                            throw new MazeReadingException(fileName, lineCount, "Mauvais caractère (" + line.charAt(i) + ") à la position " + i);
                        }
                    }
                    lineCount += 1;
                }
                br.close();
                maze.printMaze();

            } catch (IOException e) {
                throw new MazeReadingException(fileName, 0, "Initialisation de la taille du labyrinthe");
            }


        } catch(FileNotFoundException e) {
            throw new MazeReadingException(fileName, null, "Ouverture du fichier");
        }



    }
}
