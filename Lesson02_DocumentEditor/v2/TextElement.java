package Lesson02_DocumentEditor.v2;

public class TextElement implements DocumentElement {
    public String text;

    public TextElement(String text) {
        this.text = text;
    }

    @Override
    public String render() {
        return this.text;
    }
}