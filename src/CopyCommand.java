import java.util.List;

public class CopyCommand implements Command{
    private TextEditor textEditor;
    private int start;
    private int end;
    private List<String> copiedLines ;

    public CopyCommand(TextEditor textEditor, int start,int end) {
        this.textEditor = textEditor;
        this.start = start;
        this.end=end;
    }

    @Override
    public void execute() {
        copiedLines = textEditor.copy(start,end);
    }

    @Override
    public void undo() {
        //copiedLines.clear();
    }

    public List<String> getCopiedLines() {
        return copiedLines;
    }
}
