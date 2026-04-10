package Lesson02_DocumentEditor.v2;

public class ImageElement implements DocumentElement {
    public String path;

    public ImageElement(String path) {
        this.path = path;
    }

    @Override
    public String render() {
        return "[Image: " + this.path + "]\n";
    }
}
