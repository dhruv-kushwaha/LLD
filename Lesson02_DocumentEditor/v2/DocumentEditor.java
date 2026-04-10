package Lesson02_DocumentEditor.v2;

public class DocumentEditor {
    Persistence persistence;
    Document document;
    String renderedDocumentString = "";

    public DocumentEditor(Document doc, Persistence storage) {
        persistence = storage;
        document = doc;
    }

    void addText(String text) {
        document.addText(text);
    }

    void addImage(String path) {
        document.addImage(path);
    }

    String renderDocument() {
        renderedDocumentString = document.render();
        return renderedDocumentString;
    }

    void saveToFile() {
        persistence.save(renderedDocumentString);
    }
}
