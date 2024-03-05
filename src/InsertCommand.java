 public class InsertCommand implements Command{
    private TextEditor textEditor;
    private int line;
    private String text;

    public InsertCommand(TextEditor textEditor, int line, String text) {
        this.textEditor = textEditor;
        this.line = line;
        this.text = text;
    }

    @Override
    public void execute() {
        textEditor.insert(line, text);
    }

    @Override
    public void undo() {
        textEditor.delete(line);
    }
}

