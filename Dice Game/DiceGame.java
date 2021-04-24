/*
 * James McKinnon
 *
 * This program is a game of dice played played with one human player and one computer
 * player. The object of the game is to reach 60 points before the other player does.
 * To gain points each player virtually rolls a set of two dice and the count of the
 * dice for each roll is added to their total score. If a single one is rolled before they
 * stop rolling though they lose all of the points that they have accumulated for
 * that turn. If a player rolls doubles then double the value of those dice are added
 * to their score and the dice are rolled again.
 *
 * This file is used to initiate and control the flow of the game. All of the rules of the
 * game are also implemented using this file.
 *
 */

import java.util.Random;
import java.util.Scanner;

public class DiceGame {

    /**
     * This method contains the main game control logic and also keeps track of
     * the total score of the game.
     *
     * @param args the command line arguments (none)
     */
    public static void main(String[] args) {
        PlayerTurn player = new PlayerTurn();
        int[] score = new int[2];
        int whosTurn; // human turn == 0
        String humanName = startGame(); //prints intro message and gets users name

        while (score[0] < 60 && score[1] < 60) {
            //humans turn
            System.out.println("\n\n--------------------");
            System.out.printf(" Your Turn %s\n", humanName);
            System.out.println("--------------------");
            whosTurn = 0;
            // updated total score is returned after each turn
            score[0] = player.startTurn(whosTurn, humanName);

            //computers turn
            if (score[0] < 60) {
                System.out.println("\n\n---------------------");
                System.out.println("   Computer's Turn");
                System.out.println("---------------------");
                whosTurn = 1;
                // updated total score is returned after each turn
                score[1] = player.startTurn(whosTurn, humanName);
            }//if
        }//while
        decideWinner(score, humanName); //when loop ends one player has reached 60 points
    }// main

    
    
    /**
     * This method prints an intro message that explains the rules of the game
     * and tells the user the game is starting. It also asks the user to enter
     * their name which is used in the UI.
     *
     * @return returns the name of the human player.
     */
    public static String startGame() {
        String name = "";

        while (name == "") { // handles any input including pressing enter
            System.out.println("\nPlease enter your name to start the game: ");
            Scanner enterName = new Scanner(System.in);
            name = enterName.nextLine();
        }//while

        System.out.print("\n====================================\n"
                + "            The Dice Game           \n"
                + "  How Much Can You Afford to Lose?  \n"
                + "====================================\n"
                + "Roll the dice, accumulating the total to add to your score.\n"
                + "Hit 60 before the computer and you win!\n"
                + "If you roll doubles, you get double the value "
                + "and you must roll again.\n"
                + "If you roll a one - you are done,\n"
                + "          unless it's snake eyes!\n"
                + " -------      ------- \n"
                + "|       |    |       |\n"
                + "|   o   |    |   o   |\n"
                + "|       |    |       |\n"
                + " -------      ------- \n\n"
                + "Here we go...\n"
                + "------------------------------------\n\n\n");
        return name;
    }// startGame

    
    
    /**
     * This method prints which player won the game after checking to see which
     * player has a score of 60 or above.
     *
     * @param score the total scores at the time the loop stopped running
     * @param humanName if human wins their name will be displayed in message
     */
    public static void decideWinner(int[] score, String humanName) {
        String winner = (score[0] > 60) ? humanName : "Computer"; //Who reached 60 points?
        System.out.printf("\n\n%s Wins!", winner);
    }//decideWinner
}// DiceGame

