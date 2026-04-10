package Lesson02_DocumentEditor.v1;

public class ClientCode {
    public static void main(String[] args) {
        DocumentEditor docEditor = new DocumentEditor();

        docEditor.addText("hello my name is Dhruv.");
        docEditor.addImage("/blah/blah/blah");
        docEditor.addText("I am a champion");
        docEditor.renderDocument();
        docEditor.saveToFile();
    }
}
