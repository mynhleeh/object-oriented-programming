package ptit;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.io.IOException;

public class Hellofile {
    public static void main(String[] args) throws IOException {
        // List<String> lines = Files.readAllLines(Paths.get("Hello.txt"));
        // for (String l : lines)
        // System.out.println(l);

        Files.lines(Paths.get("Hello.txt")).forEach(line -> System.out.println(line));
    }
}
