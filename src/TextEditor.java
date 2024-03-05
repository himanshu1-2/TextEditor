import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
class TextEditor {
    private List<String> lines;

    public TextEditor() {
        this.lines = new ArrayList<>();
    }

    public void display() {
        for (String line : lines) {
            System.out.println(line);
        }
    }

    public void display(int n, int m) {
        for (int i = n - 1; i < m && i < lines.size(); i++) {
            System.out.println(lines.get(i));
        }
    }

    public void insert(int n, String text) {
        lines.add(n - 1, text);
    }

    public void delete(int n) {
        if (n > 0 && n <= lines.size()) {
            lines.remove(n - 1);
        }
    }

    public void delete(int n, int m) {
        if (n > 0 && n <= lines.size() && m > 0 && m <= lines.size()) {
            lines.subList(n - 1, m).clear();
        }
    }

    public List<String> copy(int n, int m) {
        List<String> copiedLines = new ArrayList<>();
        for (int i = n - 1; i < m && i < lines.size(); i++) {
            copiedLines.add(lines.get(i));
        }
        return copiedLines;
    }

    public void paste(int n, List<String> copiedLines) {
        lines.addAll(n - 1, copiedLines);
    }
}
