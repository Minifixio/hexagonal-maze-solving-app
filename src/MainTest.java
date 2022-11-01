import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public class MainTest {
    public static void main(String[] args) throws FileNotFoundException {
        Reader reader = new FileReader("/data/maze.txt");
    }
}
