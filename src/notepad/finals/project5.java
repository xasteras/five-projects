package notepad.finals;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Έστω ένα θέατρο που έχει θέσεις όπου η κάθε θέση περιγράφεται με ένα χαρακτήρα
 * που είναι η στήλη και ένα αριθμό που είναι η σειρά. Για παράδειγμα η θέση C2
 * βρίσκεται στην 2η σειρά και 3η στήλη.
 * Αναπτύξτε ένα πρόγραμμα διαχείρισης θεάτρου με 30 σειρές και 12 στήλες. Πιο
 * συγκεκριμένα γράψτε μία μέθοδο void book(char column, int row) που να κάνει book
 * μία θέση αν δεν είναι ήδη booked και μία μέθοδο void cancel(char column, int row)
 * που να ακυρώνει την κράτηση μία θέσης αν είναι ήδη booked.
 * Hint. Υποθέστε ότι ο δυσδιάστατος πίνακας που απεικονίζει το θέατρο είναι ένα
 * πίνακας από boolean, όπου το true σημαίνει ότι η θέση είναι booked και false ότι δεν
 * είναι booked. Αρχικά όλες οι θέσεις πρέπει να είναι non-booked.
 */

public class project5 {
    // Δημιουργία πίνακα με 30 σειρές και 12 στήλες
    static boolean[][] theater = new boolean[30][12];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int choice = 0;

        // Populate θέσεων με false
        for (int i = 0; i < theater.length; i++) {
            for (int j = 0; j < theater[i].length; j++) {
                theater[i][j] = false; // Όλες οι θέσεις αρχικά είναι μη κρατημένες
            }
        }

        do {
            try {
                System.out.println("Παρακαλώ επιλέξτε: 1. Κράτηση 2. Ακύρωση 3. Έξοδος");
                choice = in.nextInt();

                switch (choice) {
                    case 1: // Κράτηση
                        try {
                            System.out.println("Εισάγετε σειρά (1-30): ");
                            int row = in.nextInt();
                            System.out.println("Εισάγετε στήλη (A-L): ");
                            char column = in.next().charAt(0);
                            book(column, row);
                        } catch (InputMismatchException e) {
                            System.out.println("Μη έγκυρη είσοδος. Παρακαλώ εισάγετε έγκυρους αριθμούς και χαρακτήρες.");
                            in.nextLine(); // Καθαρισμός buffer
                        }
                        break;
                    case 2: // Ακύρωση
                        try {
                            System.out.println("Εισάγετε σειρά (1-30): ");
                            int row = in.nextInt();
                            System.out.println("Εισάγετε στήλη (A-L): ");
                            char column = in.next().charAt(0);
                            cancel(column, row);
                        } catch (InputMismatchException e) {
                            System.out.println("Μη έγκυρη είσοδος. Παρακαλώ εισάγετε έγκυρους αριθμούς και χαρακτήρες.");
                            in.nextLine(); // Καθαρισμός buffer
                        }
                        break;
                    case 3: // Έξοδος
                        System.out.println("Έξοδος από το πρόγραμμα.");
                        break;
                    default:
                        System.out.println("Μη έγκυρη επιλογή. Προσπαθήστε ξανά.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Μη έγκυρη είσοδος. Παρακαλώ εισάγετε έναν αριθμό από 1 έως 4.");
            }
        } while (choice != 3);
    }

    // Μέθοδος για κράτηση μιας θέσης
    public static void book(char column, int row) {

        // Μετατροπή χαρακτήρων σε κεφαλαίους
        char bookToUpper = Character.toUpperCase(column);

        int colIndex = bookToUpper - 'A';   // Μετατροπή του χαρακτήρα της στήλης σε αριθμό
        int rowIndex = row - 1;      // Μετατροπή σε index πίνακα

        // Έλεγχος αν οι συντεταγμένες είναι έγκυρες
        if (isValidPosition(colIndex, rowIndex)) {
            if (!theater[rowIndex][colIndex]) {
                theater[rowIndex][colIndex] = true;
                System.out.println("Η θέση " + bookToUpper + row + " κρατήθηκε επιτυχώς.");
            } else {
                System.out.println("Η θέση " + bookToUpper + row + " είναι ήδη κρατημένη.");
            }
        } else {
            System.out.println("Μη έγκυρη θέση: " + bookToUpper + row);
        }
    }

    // Μέθοδος για ακύρωση μιας κράτησης
    public static void cancel(char column, int row) {

        // Μετατροπή χαρακτήρων σε κεφαλαίους
        char cancelToUpper = Character.toUpperCase(column);

        // Μετατροπή του χαρακτήρα της στήλης σε αριθμό
        int colIndex = cancelToUpper - 'A'; // π.χ. A -> 0, B -> 1, C -> 2, κ.λπ.
        int rowIndex = row - 1;      // π.χ. 1 -> 0, 2 -> 1, κ.λπ.

        // Έλεγχος αν οι θέσεις του πίνακα είναι true / false για ακύρωση
        if (isValidPosition(colIndex, rowIndex)) {
            if (theater[rowIndex][colIndex]) {
                theater[rowIndex][colIndex] = false;
                System.out.println("Η κράτηση της θέσης " + cancelToUpper + row + " ακυρώθηκε επιτυχώς.");
            } else {
                System.out.println("Η θέση " + cancelToUpper + row + " δεν ήταν κρατημένη.");
            }
        } else {
            System.out.println("Μη έγκυρη θέση: " + cancelToUpper + row);
        }
    }

    // Μέθοδος για έλεγχο αν η θέση είναι έγκυρη
    private static boolean isValidPosition(int colIndex, int rowIndex) {
        return rowIndex >= 0 && rowIndex < theater.length && colIndex >= 0 && colIndex < theater[0].length;
    }
}
