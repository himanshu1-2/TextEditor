import java.util.List;

public class PasteCommand implements Command{

    private TextEditor textEditor;
    private int line;
    private List<String> copiedLines;

    public PasteCommand(TextEditor textEditor, int line, List<String> copiedLines) {
        this.textEditor = textEditor;
        this.line = line;
        this.copiedLines = copiedLines;
    }

    @Override
    public void execute() {
       textEditor.paste(line,copiedLines);
    }

    @Override
    public void undo() {
        textEditor.delete(line, line +copiedLines.size() - 1);
    }
}
