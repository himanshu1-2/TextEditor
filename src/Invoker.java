import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Invoker {
    private Stack<Command> undoStack;
    private Stack<Command> redoStack;
    private List<String> lastCopiedLines;


    public Invoker() {
        undoStack = new Stack<>();
        redoStack = new Stack<>();
        lastCopiedLines = new ArrayList<>();
    }

    public void executeCommand(Command command) {
        command.execute();
        if (command instanceof CopyCommand) {
            lastCopiedLines = ((CopyCommand) command).getCopiedLines();
        }
        undoStack.push(command);
        redoStack.clear(); // Clear redo stack when a new command is executed
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            Command command = undoStack.pop();
            command.undo();
            if (command instanceof CopyCommand) {
                lastCopiedLines.clear(); // Clear last copied lines when copy command is undone
            }
            redoStack.push(command);
        } else {
            System.out.println("Nothing to undo.");
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            Command command = redoStack.pop();
            command.execute();
            if (command instanceof CopyCommand) {
                lastCopiedLines = ((CopyCommand) command).getCopiedLines(); // Update last copied lines when redoing copy command
            }

            undoStack.push(command);
        } else {
            System.out.println("Nothing to redo.");
        }
    }

    public List<String> getLastCopiedLines() {
        return lastCopiedLines;
    }


}