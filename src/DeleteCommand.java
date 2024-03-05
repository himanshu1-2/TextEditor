public class DeleteCommand implements Command {
    private TextEditor textEditor;
    private int line;
    private String deletedText;

    public DeleteCommand(TextEditor textEditor, int line) {
        this.textEditor = textEditor;
        this.line = line;
    }

    @Override
    public void execute() {
        deletedText = textEditor.copy(line, line).get(0);
        textEditor.delete(line);
    }

    @Override
    public void undo() {
        textEditor.insert(line, deletedText);
    }
}
