import java.io.*;
import java.util.Scanner;

public class Notesapp
    {
        private static final String FILE_NAME = "notes.txt";

        public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n===== NOTES APP =====");
            System.out.println("1. Add a note");
            System.out.println("2. View notes");
            System.out.println("3. Clear all notes");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Please enter a valid number!");
                sc.nextLine();
                continue;
            }

            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addNote(sc);
                case 2 -> viewNotes();
                case 3 -> clearNotes();
                case 4 -> {
                    System.out.println("Exiting... Goodbye!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Try again!");
            }
        }
    }

        // Add note using FileWriter (append mode)
        private static void addNote(Scanner sc) {
        System.out.print("Enter your note: ");
        String note = sc.nextLine();

        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            writer.write(note + System.lineSeparator());
            System.out.println("✅ Note added successfully!");
        } catch (IOException e) {
            System.out.println("Error writing note: " + e.getMessage());
        }
    }

        // View notes using BufferedReader
        private static void viewNotes() {
        System.out.println("\nYour Notes:\n----------------");
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean empty = true;
            while ((line = reader.readLine()) != null) {
                System.out.println("- " + line);
                empty = false;
            }
            if (empty) {
                System.out.println("(No notes found)");
            }
        } catch (FileNotFoundException e) {
            System.out.println("(No notes file found. Add some notes first!)");
        } catch (IOException e) {
            System.out.println("Error reading notes: " + e.getMessage());
        }
    }

        // Clear all notes
        private static void clearNotes() {
        try (FileWriter writer = new FileWriter(FILE_NAME, false)) {
            writer.write("");
            System.out.println("🗑️  All notes cleared!");
        } catch (IOException e) {
            System.out.println("Error clearing notes: " + e.getMessage());
        }
    }
    }

