//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
        Invoker invoker = new Invoker();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Display");
            System.out.println("2. Insert");
            System.out.println("3. Delete");
            System.out.println("4. Undo");
            System.out.println("5. Redo");
            System.out.println("6. Display section");
            System.out.println("7. Copy Lines");
            System.out.println("8. Paste Lines");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");

            // Consume newline character
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        textEditor.display();
                        break;
                    case 2:
                        System.out.print("Enter line number: ");
                        int insertLine = scanner.nextInt();
                        scanner.nextLine(); // Consume newline character
                        System.out.print("Enter text to insert: ");
                        String insertText = scanner.nextLine();
                        invoker.executeCommand(new InsertCommand(textEditor, insertLine, insertText));
                        break;
                    case 3:
                        System.out.print("Enter line number to delete: ");
                        int deleteLine = scanner.nextInt();
                        scanner.nextLine(); // Consume newline character
                        invoker.executeCommand(new DeleteCommand(textEditor, deleteLine));
                        break;
                    case 4:
                        invoker.undo();
                        break;
                    case 5:
                        invoker.redo();
                        break;
                    case 6:
                        System.out.println("Enter start line");
                        int start = scanner.nextInt();
                        System.out.println("Enter end line");
                        int end = scanner.nextInt();
                        textEditor.display(start, end);
                        break;
                    case 7:
                        System.out.print("Enter start line number: ");
                        int startCopyLine = scanner.nextInt();
                        scanner.nextLine(); // Consume newline character
                        System.out.print("Enter end line number: ");
                        int endCopyLine = scanner.nextInt();
                        scanner.nextLine(); // Consume newline character
                        invoker.executeCommand(new CopyCommand(textEditor, startCopyLine, endCopyLine));
                        break;

                    case 8:
                        System.out.print("Enter paste line number: ");
                        int pasteLine = scanner.nextInt();
                        scanner.nextLine(); // Consume newline character
                        List<String> copiedLines = invoker.getLastCopiedLines();
                        if (copiedLines != null) {
                            invoker.executeCommand(new PasteCommand(textEditor, pasteLine, copiedLines));
                        } else {
                            System.out.println("Nothing copied.");
                        }
                      break;
                    case 10:
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice.");
                        break;
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Error" + e.getMessage());
                 System.exit(500);
            }
            catch (InputMismatchException e) {
                System.out.println("Error invalid line number" );
                System.exit(400);
            }
        }
    }
}