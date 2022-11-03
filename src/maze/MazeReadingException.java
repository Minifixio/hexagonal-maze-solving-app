package maze;

public class MazeReadingException extends Exception {

    public MazeReadingException(String fileName, Integer line, String errorType) {
        super("Erreur de lecture du fichier " + fileName
                + (line == null ? " à la ligne " + line : " ") // On ajoute le numéro de la ligne seulement si celle-ci est spécifiée (l'erreur peut seulement provenir de l'ouverture du fichier)
                + "\n Type d'erreur : " + errorType);
    }
}
