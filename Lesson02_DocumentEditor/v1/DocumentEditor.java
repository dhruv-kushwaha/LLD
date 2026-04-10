package Lesson02_DocumentEditor.v1;

import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class DocumentEditor {
    ArrayList<String> elements = new ArrayList<>();
    String renderedDocument = "";

    void addText(String text) {
        elements.add(text);
    }

    void addImage(String path) {
        elements.add(path);
    }

    String renderDocument() {
        String result = "";
        if (renderedDocument.isEmpty()) {

            for (String element : elements) {
                if (element.length() > 4
                        && (element.substring(element.length() - 4, element.length()).equalsIgnoreCase(".jpg")
                                || element.substring(element.length() - 4, element.length()).equalsIgnoreCase(".png")))
                    result = result.concat("[Image:" + element + "]" + "\n");
                else
                    result = result.concat(element + "\n");
            }
            renderedDocument = result;
        }
        return renderedDocument;
    }

    void saveToFile() {
        Path path = Paths.get("document.txt");

        try {
            if (!Files.exists(path)) {
                Files.createFile(path);
                System.out.println("File created successfully!");
            } else {
                System.out.println("File already exists");
            }
        } catch (IOException ex) {
            System.out.println("An error occured " + ex.getMessage());
        }

        String docString = elements.stream().reduce("", (a, b) -> b + "\n" + a);

        try {
            Files.writeString(path, docString);
            System.out.println("File writing successfull!");
        } catch (IOException e) {
            System.out.println("Unable to write to file " + e.getMessage());
        }
    }
}
