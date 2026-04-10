package Lesson02_DocumentEditor.v2;

import java.util.ArrayList;

public class Document {
    private ArrayList<DocumentElement> elements;

    public Document() {
        elements = new ArrayList<DocumentElement>();
    }

    public void addText(String text) {
        elements.add(new TextElement(text));
    }

    public void addImage(String imagePath) {
        elements.add(new ImageElement(imagePath));
    }

    public String render() {
        String result = "";
        for (DocumentElement el : elements) {
            result += el.render() + "\n";
        }
        return result;
    }
}
