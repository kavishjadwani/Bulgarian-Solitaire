package pa2;

// Name:Kavish Jadwani
// USC NetID:jadwani
// CSCI455 PA2
// Fall 2017
import java.util.ArrayList;
import java.util.Scanner;

/**
 * class BulgarianSolitaireSimulator The class simulates the Bulgarian Solitaire
 * Game allowing the user to toggle two modes, Single step and User
 * Configuration
 */
public class BulgarianSolitaireSimulator {

    /**
     * This method reads the command line for the mode selection and runs the
     * simulation accordingly to completion
     */
    public static void main(String[] args) {
        boolean singleStep = false;
        boolean userConfig = false;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-u")) {
                userConfig = true;
            } else if (args[i].equals("-s")) {
                singleStep = true;
            }
        }
        // Accept user input and play game step by step 
        if (userConfig && singleStep) {
            playUserConfigSingleStep();
        }
        //Take user input and play complete game 
        if (userConfig && !singleStep) {
            playUserConfigCompleteGame();
        }
// Plays a random game completely  
        if (!userConfig && !singleStep) {
            playCompleteRandomGame();
        }
        //Plays a random game Step By Step 
        if (!userConfig && singleStep) {
            playRandomGameSingleStep();
        }
    }

    /**
     * This method asks user for the input and checks whether the input is valid
     * or not. If input is not in valid format,it prompts the user to enter the
     * correct input
     *
     * @return the array list of numbers provided by the user
     */
    public static ArrayList<Integer> getUserInputArray() {
        ArrayList<Integer> pile1 = new ArrayList<Integer>();;
        boolean isCorrectInput = false;
        System.out.println("Number of Total Cards is " + SolitaireBoard.CARD_TOTAL + "\nYou will be entering the initial configuration of the cards (i.e., how many in each pile).\nPlease enter a space-separated list of positive integers followed by newline:");
        while (!isCorrectInput) {
            Scanner in2 = new Scanner(System.in);
            String inputLine = in2.nextLine();
            Scanner in = new Scanner(inputLine);
            pile1 = new ArrayList<Integer>();
            int sum = 0, num = 0, i = 0;
            while (in.hasNext()) {
                try {
                    num = Integer.parseInt(in.next());
                } catch (NumberFormatException e) {
                }
                pile1.add(num);
                if (pile1.get(i) <= 0) {
                    isCorrectInput = false;
                    break;
                }
                sum += pile1.get(i);
                i++;
            }
            if (sum != SolitaireBoard.CARD_TOTAL) {
                System.out.println("ERROR: Each pile must have at least one card and the total number of cards must be " + SolitaireBoard.CARD_TOTAL + "\nPlease enter a space-separated list of positive integers followed by newline:");
            } else {
                isCorrectInput = true;
            }
        }
        return pile1;
    }

    /**
     * This method takes the Solitaire Board as the parameter and plays a single
     * step of Bulgarian Solitaire. After each step, the method prompts the user
     * for an input to continue playing the round of game.
     *
     * @param board1
     */
    public static void playOneStep(SolitaireBoard board1) {
        int j = 0;

        Scanner in3 = new Scanner(System.in);
        System.out.print("Initial Configuration: ");
        System.out.println(board1.configString());
        while (!board1.isDone()) {
            j++;
            board1.playRound();
            System.out.print("[" + j + "] Current configuration: ");
            System.out.println(board1.configString());
            System.out.print("<Type return to Continue>");
            if (in3.hasNextLine()) {
                String a = in3.nextLine();
            }
        }
        System.out.println("Done!");
    }

    /**
     * This method plays the Bulgarian Solitaire game step by step. The intial
     * configuration of the game is provided by the user. The game pauses after
     * each round and ask for user to provide an input to continue playing the
     * next round.
     */
    private static void playUserConfigSingleStep() {
        SolitaireBoard board1 = new SolitaireBoard(getUserInputArray());
        playOneStep(board1);
    }

    /**
     * This method plays the Bulgarian Solitaire complete game without pause.
     * The initial configuration of the game is provided by the user.
     */
    private static void playUserConfigCompleteGame() {
        SolitaireBoard board1 = new SolitaireBoard(getUserInputArray());
        playCompleteGame(board1);
    }

    /**
     * This method plays a Random Bulgarian Solitaire complete game without
     * pause. The initial configuration of the game is random.
     */
    private static void playCompleteRandomGame() {
        SolitaireBoard board1 = new SolitaireBoard();
        playCompleteGame(board1);
    }

    /**
     * This method plays a Random Bulgarian Solitaire game step by step. The
     * initial configuration of the game is random. The game pauses after each
     * round and ask for user to provide an input to continue playing the next
     * round.
     */
    private static void playRandomGameSingleStep() {
        SolitaireBoard board1 = new SolitaireBoard();
        playOneStep(board1);
    }

    /**
     * This method takes Solitaire Board as the parameter and plays a complete
     * Bulgarian Solitaire game without pause on it
     *
     * @param board1
     */
    private static void playCompleteGame(SolitaireBoard board1) {
        int j = 0;
        System.out.print("Initial Configuration: ");
        System.out.println(board1.configString());
        while (!board1.isDone()) {
            j++;
            board1.playRound();
            System.out.print("[" + j + "] Current configuration: ");
            System.out.println(board1.configString());
        }
        System.out.println("Done!");
    }
}