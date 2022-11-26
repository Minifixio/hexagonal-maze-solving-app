package maze;

public class MazeWritingException extends Exception {
    public MazeWritingException(String fileName, String errorType) {
        super("Erreur de l'écriture du fichier " + fileName
                + "\n Type d'erreur : " + errorType);
    }
}