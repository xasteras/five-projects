package notepad.finals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.io.*;
import java.util.Scanner;


/**
 * Αναπτύξτε ένα πρόγραμμα σε Java που να διαβάζει από ένα αρχείο ακέραιους
 * αριθμούς (το αρχείο πρέπει να περιέχει περισσότερους από 6 αριθμούς και το πολύ
 * 49 αριθμούς) με τιμές από 1 έως 49. Τους αριθμούς αυτούς τους εισάγει σε ένα
 * πίνακα, τον οποίο ταξινομεί (π.χ. με την Arrays.sort()). Στη συνέχεια, το πρόγραμμα
 * παράγει όλες τις δυνατές εξάδες (συνδυασμούς 6 αριθμών). Ταυτόχρονα και αμέσως
 * μετά την παραγωγή κάθε εξάδας ‘φιλτράρει’ κάθε εξάδα ώστε να πληροί τα
 * παρακάτω κριτήρια: 1) Να περιέχει το πολύ 4 άρτιους, 2) να περιέχει το πολύ 4
 * περιττούς, 3) να περιέχει το πολύ 2 συνεχόμενους, 4) να περιέχει το πολύ 3 ίδιους
 * λήγοντες, 5) να περιέχει το πολύ 3 αριθμούς στην ίδια δεκάδα.
 * Τέλος, εκτυπώνει τις τελικές εξάδες σε ένα αρχείο με όνομα της επιλογής σας και
 * κατάληξη.txt.
 */

    public class project1 {

        public static void main(String[] args) {
            // Προσδιορισμός αρχείου εισόδου και εξόδου
            File inFd = new File("/Users/xasteras/Documents/CodingFactory/Java/files/lotto.txt");
            File outFd = new File("/Users/xasteras/Documents/CodingFactory/Java/files/lottoOut.txt");

            try (Scanner in = new Scanner(inFd);
                 PrintStream ps = new PrintStream(outFd)) {
                final int LottoSize = 6;    // Δήλωση συνδυασμών

                int[] lottoNumbers = NumbersFromFile(inFd);  // Τοποθέτηση αριθμών απο αρχείο στον πίνακα
                Arrays.sort(lottoNumbers);  // Ταξινόμηση πίνακα

                int[] result = new int[LottoSize];  // Αρχικοποίηση πίνακα τόσων θέσεων όσο και συνδυασμών
                int window = lottoNumbers.length - LottoSize;   // Οι αριθμοί που μπορούν να μπουν στο παράθυρο που μετακινείται δεξιά
                for (int i = 0; i <= window; i++) {
                    for (int j = i + 1; j <= window + 1; j++) {
                        for (int k = j + 1; k <= window + 2; k++) {
                            for (int l = k + 1; l <= window + 3; l++) {
                                for (int m = l + 1; m <= window + 4; m++) {
                                    for (int n = m + 1; n <= window + 5; n++) {
                                        result[0] = lottoNumbers[i];
                                        result[1] = lottoNumbers[j];
                                        result[2] = lottoNumbers[k];
                                        result[3] = lottoNumbers[l];
                                        result[4] = lottoNumbers[m];
                                        result[5] = lottoNumbers[n];
                                        if (hasMoreThanFourEvens(result) && (hasMoreThanFourOdds(result)) && (!hasMoreThanTwoConsecutives(result)) && (!hasMoreThanThreeSameEnding(result))
                                                && (!hasMoreThanThreeInSameDecade(result))) {
                                            try {
                                                printArray(result, outFd);    // Αποθήκευση σε αρχείο
                                            } catch (FileNotFoundException e) {
                                                System.err.println(e.getMessage());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        }

        /**
         * Διάβασμα αριθμών απο αρχείο και τοποθέτηση σε πίνακα
         *
         * @param file αρχείο εισόδου
         * @return
         * @throws IOException
         */
        public static int[] NumbersFromFile(File file) throws IOException {
            int[] lottoNumbers = new int[49];   // Δημιουργία πίνακα 49 θέσεων
            int end = -1;    // end index

            try (Scanner in = new Scanner(file)) {
                while (in.hasNextInt() && end <= 48) {  // Διάβασμα αρχείου μέχρι να εξαντληθούν οι αριθμοί ή να φτάσουμε τους 49
                    int value = in.nextInt();
                    lottoNumbers[++end] = value;  // Αντιστοίχηση αριθμού στον πίνακα ξεκινώντας απο την θέση 0 ( -1 + 1 = 0)

                }
            } catch (IOException e) {
                throw e;
            }
            return Arrays.copyOfRange(lottoNumbers, 0, end);
        }

        /**
         * 'Έλεγχος για 4 'άρτιους
         *
         * @param arr
         * @return
         */
        public static boolean hasMoreThanFourEvens(int[] arr) {
            if (arr.length == 0) return false;
            int count = 0;

            for (int el : arr) {
                if (el % 2 == 0) {
                    count++;
                }
            }
            return count >= 4;
        }

        /**
         * Έλεγχος για 4 περιττούς
         *
         * @param arr
         * @return
         */
        public static boolean hasMoreThanFourOdds(int[] arr) {
            if (arr.length == 0) return false;  // state-test
            int count = 0;

            for (int el : arr) {
                if (el % 2 == 1) {
                    count++;
                }
            }
            return count >= 4;
        }

        /**
         * Έλεγχος για 2 συνεχόμενους
         *
         * @param arr
         * @return
         */
        public static boolean hasMoreThanTwoConsecutives(int[] arr) {
            if (arr == null) return false;  // state-test
            boolean isConsecutives = false;

            for (int i = 0; i < arr.length - 2; i++) {      //  i < arr.length - 2 για να μην έχουμε ArrayOutOfBounds
                if ((arr[i] == arr[i + 1] - 1) && (arr[i] == arr[i + 2] - 2)) {
                    isConsecutives = true;
                    break;
                }
            }
            return isConsecutives;
        }

        /**
         * Έλεγχος για 3 ίδιους λήγοντες
         *
         * @param arr
         * @return
         */
        public static boolean hasMoreThanThreeSameEnding(int[] arr) {
            if (arr == null) return false;
            int[] endings = new int[10];
            boolean isSameEnding = true;

            for (int num : arr) {
                endings[num % 10]++;
            }
            for (int count : endings) {
                if (count >= 3) {
                    isSameEnding = true;
                    break;
                }
            }
            return isSameEnding;
        }

        /**
         * Έλεγχος για 3 αριθμούς στην ίδια δεκάδα
         *
         * @param arr
         * @return
         */
        public static boolean hasMoreThanThreeInSameDecade(int[] arr) {

            int[] tens = new int[5];
            boolean isTens = false;

            for (int num : arr) {
                tens[num / 10]++;
            }
            for (int count : tens)
                if (count >= 3) {
                    isTens = true;
                    break;
                }
            return isTens;
        }

        /**
         * Αποθήκευση 6άδας αριθμών σε αρχείο
         *
         * @param arr
         * @param file
         * @throws FileNotFoundException
         */
        public static void printArray(int[] arr, File file) throws FileNotFoundException {
            try (PrintStream ps = new PrintStream(new FileOutputStream(file, true))) {  // append = true για να μην γίνει overwrite το αρχείο
                ps.printf("%d\t%d\t%d\t%d\t%d\t%d\n", arr[0], arr[1], arr[2], arr[3], arr[4], arr[5]);
            } catch (FileNotFoundException e) {

                throw e;
            }
        }
    }


