/*
 * This class controls the logic for the current players turn and enforces
 * the rules for each round that is played.
 */

import java.util.Scanner;
import java.util.Random;


public class PlayerTurn {

    //Data 
    private TwoDice dice = new TwoDice();
    private int[] score = {0, 0};
    private int[] diceValues; //recieves dice values after dice are rolled
    private int scoreThisRound;
    private int playersTurn; // 0 for human, 1 for computer
    private boolean playing;



    /**
     * This method sets the score after the dice have been rolled. If
     * doubles are rolled the doubled score is set within that method
     * instead.
     */
    public void score() {
        if (playing) {
            scoreThisRound += diceValues[0] + diceValues[1];
            if (score[playersTurn] + scoreThisRound > 60) { //checks if the current player won
                playing = false;
            }//if
        }//if
    }//score



    /**
     * This method controls the flow of the current players turn. The dice
     * are rolled and displayed when this method makes that happen and the
     * methods for the rules are also called from this method.
     *
     * @param whosTurn this tells the method which player is currently
     * rolling.
     * @param humanName this tells the method the name of the human playing
     * the game.
     * @return the method returns the updated total score for the current
     * player after each round.
     */
    public int startTurn(int whosTurn, String humanName) {
        playing = true;
        playersTurn = whosTurn;

        while (playing) {
            System.out.println("\nRolling...");
            diceValues = dice.roll(); //rolls dice
            dice.displayDice(); //dice are always displayed after a roll
            boolean doubles = ifDoubles();
            if (!doubles) { //rolls again if doubles
                singleOneCheck(); //ends round/subtracts points if a single one is rolled
                score(); //updates score for this round
                System.out.printf("Score for this round: %d\n", scoreThisRound);
                keepPlaying(); //asks user if they would like to roll again
            }//if
        }//while
        score[playersTurn] += scoreThisRound; // overall score is updated for current player
        System.out.printf("Total Score: %s %d; Computer %d", humanName, score[0], score[1]);
        scoreThisRound = 0; // gets ready for the next player's turn
        return score[playersTurn];
    }//startTurn



    /**
     * Checks to see if doubles were rolled. Multiplies the dice values by
     * two if doubles were rolled and adds that score to the score for the
     * round.
     *
     * @return returns true if doubles have been rolled.
     */
    public boolean ifDoubles() {

        if (diceValues[0] == diceValues[1]) {
            scoreThisRound += (diceValues[0] + diceValues[1]) * 2;
            System.out.println("Doubles! Roll again!");
        }//if
        return diceValues[0] == diceValues[1]; // if true the loop will roll dice again
    }//ifDoubles



    /**
     * This method checks to see if there was a one that has been rolled. it
     * only executes if doubles have not been rolled. This rules out snake
     * eyes.
     */
    public void singleOneCheck() {
        if (diceValues[0] == 1 || diceValues[1] == 1) {
            playing = false;
            System.out.printf("A single one! %d points lost.\n\n", scoreThisRound);
            scoreThisRound = 0;
        }//if
    }//singleOneCheck



    /**
     * This method asks the user if they would like to keep playing. They
     * can enter and string that starts with 'y', 'Y', 'n' or 'N' and the
     * corresponding yes or no will be assumed. If they press enter they
     * will also get a message.
     */
    public void keepPlaying() {
        if (playersTurn == 0 && playing) { //only runs if its the human player's turn
            boolean loop = true; // control variable for while loop
            while (loop) {
                System.out.println("\nWould You Like to Keep Playing? \nEnter \'y\' or \'n\': ");
                Scanner askToPlay = new Scanner(System.in);
                String answer = askToPlay.nextLine();

                if (answer.equals("")) {
                    System.out.println("*** Enter 'y' for yes or 'n' for no ***");
                } else if (answer.charAt(0) == 'n' || answer.charAt(0) == 'N') {
                    System.out.println("\nStaying");
                    playing = false;
                    loop = false;
                } else if (answer.charAt(0) == 'y' || answer.charAt(0) == 'Y') {
                    loop = false;
                } else {
                    System.out.println("\n*** Enter 'y' for yes or 'n' for no ***");
                }
            }//while
        }//if

        if (playersTurn == 1 && playing) { //runs when it's computers turn

            Random randomNum = new Random();
            playing = (randomNum.nextInt(3) + 1) != 1; //   2/3 chance that expression will be true
            if (!playing) {
                System.out.println("\nStaying");
            }//if
        }//if
    }//keepPlaying
}//PlayerTurn class
