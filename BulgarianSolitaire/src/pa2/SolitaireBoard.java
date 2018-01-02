package pa2;

// Name:Kavish Jadwani
// USC NetID:jadwani
// CSCI455 PA2
// Fall 2017
import java.util.ArrayList;

/*
 class SolitaireBoard
 The board for Bulgarian Solitaire.  You can change what the total number of cards is for the game
 by changing NUM_FINAL_PILES, below.  Don't change CARD_TOTAL directly, because there are only some values
 for CARD_TOTAL that result in a game that terminates.
 (See comments below next to named constant declarations for more details on this.)
 */
import java.util.Random;

public class SolitaireBoard {

    public static final int NUM_FINAL_PILES = 9;
    // number of piles in a final configuration
    // (note: if NUM_FINAL_PILES is 9, then CARD_TOTAL below will be 45)
    public static final int CARD_TOTAL = NUM_FINAL_PILES * (NUM_FINAL_PILES + 1) / 2;
    // bulgarian solitaire only terminates if CARD_TOTAL is a triangular number.
    // see: http://en.wikipedia.org/wiki/Bulgarian_solitaire for more details
    // the above formula is the closed form for 1 + 2 + 3 + . . . + NUM_FINAL_PILES
    // Note to students: you may not use an ArrayList -- see assgt description for details.
    /**
     * Representation invariant: NUM_FINAL_PILES is the total number of piles at
     * the end of the game. NUM_FINAL_PILES >0 CARD_TOTAL is the total number of
     * cards CARD_TOTAL > 0 CARD_TOTAL depends on NUM_FINAL_PILES CARD_TOTAL =
     * NUM_FINAL_PILES * (NUM_FINAL_PILES + 1)/2 -1<= arrangementTracker <
     * CARD_TOTAL size of currentArrangement = CARD_TOTAL The sum of elements in
     * currentArrangement will always be equal to CARD_TOTAL The elements of
     * currentArrangement are always greater than or equal to 0 The elements of
     * currentArrangement until arrangementTracker are always > 0 finalResult
     * will not be empty Sum of Elements of finalResult = CARD_TOTAL Size of
     * finalResult = NUM_FINAL_PILES
     */
    int[] finalResult = new int[NUM_FINAL_PILES];
    private int[] currentArrangement = new int[CARD_TOTAL];
    private int arrangementTracker = -1;
    Random n;

    /**
     * Creates a solitaire board with the configuration specified in piles.
     * piles has the number of cards in the first pile, then the number of cards
     * in the second pile, etc. PRE: piles contains a sequence of positive
     * numbers that sum to SolitaireBoard.CARD_TOTAL
     */
    public SolitaireBoard(ArrayList<Integer> piles) {
        for (int i = 0; i < NUM_FINAL_PILES; i++) {
            finalResult[i] = i + 1;
        }
        for (int i = 0; i < piles.size(); i++) {
            arrangementTracker++;
            currentArrangement[arrangementTracker] = piles.get(i);
        }
        assert isValidSolitaireBoard();
    }

    /**
     * Creates a solitaire board with a random initial configuration.
     */
    public SolitaireBoard() {
        n = new Random();

        for (int i = 0; i < NUM_FINAL_PILES; i++) {
            finalResult[i] = i + 1;
        }
        int sum = 46;
        while (sum > 1) {
            int m = n.nextInt(sum);
            if (m != 0) {
                arrangementTracker++;
                currentArrangement[arrangementTracker] = m;
                sum = sum - currentArrangement[arrangementTracker];
            }
        }
        assert isValidSolitaireBoard();
    }

    /**
     * Plays one round of Bulgarian solitaire. Updates the configuration
     * according to the rules of Bulgarian solitaire: Takes one card from each
     * pile, and puts them all together in a new pile. The old piles that are
     * left will be in the same relative order as before, and the new pile will
     * be at the end.
     */
    public void playRound() {
        int newPileCreatedSize = arrangementTracker + 1;
        for (int i = 0; i <= arrangementTracker; i++) {
            if ((currentArrangement[i] - 1) != 0) {
                currentArrangement[i] = currentArrangement[i] - 1;
            } /*else if((currentArrangement[i] ==1 && currentArrangement[i+1] ==0)||(currentArrangement[i] ==1 &&arrangementTracker ==CARD_TOTAL)){
             currentArrangement[i]= 0;
             arrangementTracker--;
             }*/ else {
                for (int j = i; j < arrangementTracker; j++) {
                    currentArrangement[j] = currentArrangement[j + 1];
                }
                arrangementTracker--;
                i--;
            }

        }
        arrangementTracker++;
        currentArrangement[arrangementTracker] = newPileCreatedSize;
        assert isValidSolitaireBoard();
    }

    /**
     * Returns true iff the current board is at the end of the game. That is,
     * there are NUM_FINAL_PILES piles that are of sizes 1, 2, 3, . . . ,
     * NUM_FINAL_PILES, in any order.
     */
    public boolean isDone() {
        assert isValidSolitaireBoard();
        boolean isGameDone = false;
        int count = 0;
        if (arrangementTracker == NUM_FINAL_PILES - 1) {
            for (int j = 0; j < NUM_FINAL_PILES; j++) {
                for (int i = 0; i <= arrangementTracker; i++) {
                    if (currentArrangement[i] == finalResult[j]) {
                        count++;
                        break;
                    }
                }
            }
            if (count == NUM_FINAL_PILES) {
                isGameDone = true;
            }
        }
          assert isValidSolitaireBoard();
        return isGameDone;
    }

    /**
     * Returns current board configuration as a string with the format of a
     * space-separated list of numbers with no leading or trailing spaces. The
     * numbers represent the number of cards in each non-empty pile.
     */
    public String configString() {

        String s = "";
        for (int i = 0; i < arrangementTracker; i++) {
            s = s + currentArrangement[i] + " ";
        }
        s = s + currentArrangement[arrangementTracker];
        assert isValidSolitaireBoard();
        return s;
    }

    /**
     * Returns true iff the solitaire board data is in a valid state (See
     * representation invariant comment for more details.)
     */
    private boolean isValidSolitaireBoard() {
        boolean isTrue = true;
        //check whether NUM_FINAL_PILES is a valid number 
        if (NUM_FINAL_PILES <= 0) {
            isTrue = false;
        }
        // Check whether CARD_TOTAL is a valid number 
        if (CARD_TOTAL <= 0) {
            isTrue = false;
        }
        // check whether CARD_TOTAL is set properly or not 
        if (CARD_TOTAL != NUM_FINAL_PILES * (NUM_FINAL_PILES + 1) / 2) {
            isTrue = false;
        }
        // check is the arrangement tracker is withing the allowed limits 
        if (arrangementTracker < -1 || arrangementTracker >= CARD_TOTAL) {
            isTrue = false;
        }

        int sum = 0;
        //Check whether all elements of our arrangement are positive integers 
        for (int i = 0; i <= arrangementTracker; i++) {
            if (currentArrangement[i] <= 0) {
                isTrue = false;
                break;
            } else {
                sum = sum + currentArrangement[i];
            }
        }
        //check whether the  sum of the elements in our arrangement
        //is equal to CARD_NUMBER, otherwise return false 

        if (sum != CARD_TOTAL) {
            isTrue = false;
        }
        return isTrue;
    }
}
