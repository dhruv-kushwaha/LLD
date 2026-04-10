package Lesson02_DocumentEditor.v2;

public class ClientCode {
    public static void main(String[] args) {
        Document doc = new Document();
        Persistence storage = new SaveToFile();
        DocumentEditor docEditor = new DocumentEditor(doc, storage);

        docEditor.addText("hello my name is Dhruv.");
        docEditor.addImage("/blah/blah/blah");
        docEditor.addText("I am a champion");
        System.out.println(docEditor.renderDocument());
        docEditor.saveToFile();
    }
}