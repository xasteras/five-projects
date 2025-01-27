package notepad.finals;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Αναπτύξτε μία εφαρμογή που διαβάζει έναν-έναν τους χαρακτήρες ενός αρχείου και
 * τους εισάγει σε ένα πίνακα 128x2. Υποθέστε ότι οι χαρακτήρες είναι λατινικοί. Κάθε
 * θέση του πίνακα είναι επομένως ένας πίνακας δύο θέσεων, όπου στην 1η θέση
 * αποθηκεύεται ο χαρακτήρας που έχει διαβαστεί (αν δεν υπάρχει ήδη στον πίνακα)
 * και στην 2η θέση αποθηκεύεται το πλήθος των φορών που έχει διαβαστεί (βρεθεί)
 * κάθε χαρακτήρας. Αγνοήστε τα κενά και τις αλλαγές γραμμής και γενικά τα
 * whitespaces.
 * Στο τέλος η main() παρουσιάζει στατιστικά στοιχεία για κάθε χαρακτήρα όπως η
 * συχνότητα εμφάνισής του στο κείμενο ταξινομημένα ανά χαρακτήρα και ανά
 */

public class project3 {
    public static void main(String[] args) {

        // Δημιουργία πίνακα 128x2
        int[][] countChar = new int[128][2];
        for (int i = 0; i < 128; i++) {
            countChar[i][0] = i; // Αποθηκεύουμε τον ASCII αριθμό στην 1η θέση
            countChar[i][1] = 0; // Αρχικοποιούμε με 0 εμφανίσεις ανά χαρακτήρα
        }

        // Εισαγωγή αρχείου σε μεταβλητή
        String inFd = "/Users/xasteras/Documents/CodingFactory/Java/files/characters.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(inFd))) {
            // Ανάγνωση του περιεχομένου του αρχείου γραμμή-γραμμή
            String line;
            while ((line = reader.readLine()) != null) {
                // Επεξεργασία κάθε γραμμής
                for (char ch : line.toCharArray()) {
                    if (!Character.isWhitespace(ch) && ch < countChar.length) {
                        countChar[ch][1]++; // Αυξάνουμε τον μετρητή εμφάνισης για τον χαρακτήρα
                    }
                }
            }

            // Μέτρημα των χαρακτήρων με εμφανίσεις > 0
            int count = 0;
            for (int i = 0; i < countChar.length; i++) {
                if (countChar[i][1] > 0) {
                    count++;
                }
            }

            // Δημιουργία νέου πίνακα με μόνο τους χαρακτήρες που εμφανίστηκαν
            int[][] characters = new int[count][2];
            int pivot = -1; // Ξεκινάμε με -1 γιατι κάνουμε ++pivor για να μην δημιουργηθεί κενή θέση στο τέλος, array.length + 1

            for (int i = 0; i < countChar.length; i++) {
                if (countChar[i][1] > 0) {
                    ++pivot;
                    characters[pivot][0] = countChar[i][0];
                    characters[pivot][1] = countChar[i][1];
                }
            }

            // Ταξινόμηση και εκτύπωση
            System.out.println("Ταξινόμηση ανά χαρακτήρα:");
            selectionSortByCharacter(characters); // Ταξινόμηση ανά χαρακτήρα (ASCII)
            printStats(characters);

            System.out.println("Ταξινόμηση ανά συχνότητα εμφάνισης:");
            selectionSortByFrequency(characters); // Ταξινόμηση ανά συχνότητα
            printStats(characters);

        } catch (FileNotFoundException e) {
            System.err.println("Το αρχείο δεν βρέθηκε: " + inFd);
        } catch (IOException e) {
            System.err.println("Προέκυψε σφάλμα κατά την ανάγνωση του αρχείου: " + e.getMessage());
        }
    }

    // Ταξινόμηση κατά χαρακτήρα (πρώτη στήλη)
    public static void selectionSortByCharacter(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minPos = i;

            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j][0] < arr[minPos][0]) {
                    minPos = j;
                }
            }

            // Ανταλλαγή στοιχείων
            swap(arr, i, minPos);
        }
    }

    // Ταξινόμηση κατά συχνότητα (δεύτερη στήλη)
    public static void selectionSortByFrequency(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            int maxPos = i;

            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j][1] > arr[maxPos][1]) {
                    maxPos = j;
                }
            }

            // Ανταλλαγή στοιχείων
            swap(arr, i, maxPos);
        }
    }

    public static void swap(int[][] arr, int i, int j) {
        int[] tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // Εκτύπωση στατιστικών
    public static void printStats(int[][] charData) {
        for (int[] entry : charData) {
            System.out.printf("Χαρακτήρας: '%c' (ASCII: %d) -> Εμφανίσεις: %d%n", (char) entry[0], entry[0], entry[1]);
        }
        System.out.println();
    }
}
