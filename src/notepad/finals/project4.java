package notepad.finals;

import java.util.Scanner;

/**
 * Αναπτύξτε ένα παιχνίδι Τρίλιζα, όπου δύο παίκτες παίζουν Χ και Ο (ή 1 και 2 αν θέλετε
 * να υλοποιήσετε με πίνακα ακεραίων και όχι με πίνακα char) και κερδίζει ο παίκτης
 * που έχει συμπληρώσει τρία ίδια σύμβολα ή αριθμούς σε οποιαδήποτε διάσταση του
 * πίνακα, οριζόντια, κάθετα ή διαγώνια.
 */

public class project4 {

    public static void main(String[] args) {
        // Δημιουργία πίνακα
        char[][] board = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };
        char currentPlayer = 'X';
        Scanner scanner = new Scanner(System.in);
        int moves = 0;
        boolean gameWon = false;

        while (moves < 9 && !gameWon) {     // Έλεγχος κινήσεων και αν υπάρχει νικητής
            printBoard(board);
            System.out.println("Παίκτης " + currentPlayer + ", εισάγετε σειρά (1-3) και στήλη (1-3):");
            try {
                int row = scanner.nextInt() - 1;    // Εισαγωγή στοιχείου για index πίνακα
                int col = scanner.nextInt() - 1;

                if (row < 0 || row > 2 || col < 0 || col > 2) {     // Έλεγχος ορίων πίνακα
                    System.out.println("Μη έγκυρη είσοδος! Δοκιμάστε ξανά.");
                    continue;
                }

                if (board[row][col] != ' ') {   // Έλεγχος κατειλημμένης θέσης
                    System.out.println("Η θέση είναι ήδη κατειλημμένη! Δοκιμάστε ξανά.");
                    continue;
                }

                board[row][col] = currentPlayer;    // Εισαγωγή συμβόλου παίκτη
                moves++;    // Αλλαγή στον counter των κινήσεων +1

                if (checkWin(board, row, col, currentPlayer)) {     // Έλεγχος παίκτη αν κέρδισε
                    gameWon = true;
                    printBoard(board);
                    System.out.println("Ο παίκτης " + currentPlayer + " κέρδισε!");
                    break;
                }

                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';     // Εναλλαγή παικτών
            } catch (Exception e) {
                System.out.println("Λανθασμένη είσοδος! Παρακαλώ εισάγετε δύο αριθμούς μεταξύ 1 και 3.");
            }
        }

        if (!gameWon) {     // Τελος παιχνιδιού
            printBoard(board);
            System.out.println("Ισοπαλία!");
        }
    }

    public static void printBoard(char[][] board) {     // Εμφάνιση του πίνακα
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    public static boolean checkWin(char[][] board, int row, int col, char currentPlayer) {  // Έλεγχος συνθηκών παιχνιδιού
        // Έλεγχος γραμμής
        if (board[row][0] == currentPlayer && board[row][1] == currentPlayer && board[row][2] == currentPlayer)
            return true;

        // Έλεγχος στήλης
        if (board[0][col] == currentPlayer && board[1][col] == currentPlayer && board[2][col] == currentPlayer)
            return true;

        // Έλεγχος διαγωνίου
        if (row == col && board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer)
            return true;

        // Έλεγχος διαγωνίου
        if (row + col == 2 && board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer)
            return true;
        return false;
    }
}

