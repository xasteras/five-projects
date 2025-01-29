package notepad.finals;

import java.util.Arrays;

public class project2 {

    public static void main(String[] args) {
        // Αρχικοποίηση πίνακα δεδομένων
        int[] array = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        // Κλήση της μεθόδου για εύρεση του μέγιστου υποπίνακα
        int[] maxSubArray = findMaxSubArray(array);

        // Εκτύπωση του μέγιστου υποπίνακα
        System.out.println("Μέγιστος υποπίνακας:");
        for (int num : maxSubArray) {
            System.out.print(num + " ");
        }
    }

    public static int[] findMaxSubArray(int[] array) {
        int n = array.length;

        // Τρέχον μέγιστο άθροισμα (μεγιστοποιείται καθώς προχωράμε)
        int currentMaxSum = array[0];

        // Συνολικό μέγιστο άθροισμα που έχουμε βρει μέχρι τώρα
        int globalMaxSum = array[0];

        // Δείκτες αρχής και τέλους του καλύτερου υποπίνακα
        int bestStartIndex = 0, bestEndIndex = 0;

        // Προσωρινός δείκτης αρχής για ενδεχόμενη νέα ακολουθία
        int tempStartIndex = 0;

        // Διατρέχουμε τον πίνακα από το δεύτερο στοιχείο
        for (int i = 1; i < n; i++) {

            // Αν το τρέχον στοιχείο είναι καλύτερο από το άθροισμα που έχουμε έως τώρα,
            // ξεκινάμε νέο υποπίνακα από εδώ.
            if (array[i] > currentMaxSum + array[i]) {
                currentMaxSum = array[i];
                tempStartIndex = i; // Νέο σημείο έναρξης του υποπίνακα
            } else {
                currentMaxSum += array[i]; // Προσθέτουμε στο υπάρχον άθροισμα
            }

            // Αν βρήκαμε νέο μεγαλύτερο άθροισμα, ενημερώνουμε τις μεταβλητές
            if (currentMaxSum > globalMaxSum) {
                globalMaxSum = currentMaxSum;
                bestStartIndex = tempStartIndex;
                bestEndIndex = i;
            }
        }

        // Δημιουργούμε τον υποπίνακα με το μέγιστο άθροισμα
        int[] result = new int[bestEndIndex - bestStartIndex + 1];
        System.arraycopy(array, bestStartIndex, result, 0, result.length);

        return result;
    }
}