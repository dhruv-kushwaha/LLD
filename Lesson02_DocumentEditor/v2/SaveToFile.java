package Lesson02_DocumentEditor.v2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SaveToFile implements Persistence {

    public void save(String file) {
        Path path = Paths.get("document.txt");

        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
                System.out.println("Created file");
            } catch (IOException ex) {
                System.out.println("Unable to create file");
                return;
            }
        }

        try {
            Files.writeString(path, file);
            System.out.println("Successfully written to file");
        } catch (IOException ex) {
            System.out.println("Unable to write to file");
            return;
        }
    }
}
