/*
 * James McKinnon
 * 
 * This file contains the TwoDice class. The twodice class has the ability to 
 * print two dice for a user, randomly generate two rolled dice, and display 
 * these dice in a visual way and as a string message.
 * 
 */

import java.util.Random;

public class TwoDice {

    //Data 
    private int dice1Value;
    private int dice2Value;
    private Random rand;

    //Methods
    /**
     * This is the constructor for the TwoDice class. It creates an object with
     * two dice values.
     *
     * @param dice1 //the user enters the values that they want for dice 1
     * @param dice2 //the user enters the values that they want for dice 2
     */
    public TwoDice(int dice1, int dice2) {
        dice1Value = dice1;
        dice2Value = dice2;
        rand = new Random();
    }//TwoDice

    
    
    /**
     * This is the default constructor for the TwoDice class. Default dice
     * values have been set within it.
     */
    public TwoDice() {
        this(1, 1); // 1 and 1 will be the default values for the two dice
    }//TwoDice

    /**
     * This method will generate two random values for dice 1 and dice 2 which
     * simulates rolling the dice.
     *
     * @return random values for dice 1 and dice 2 are returned
     */
    public int[] roll() {
        dice1Value = rand.nextInt(6) + 1;
        dice2Value = rand.nextInt(6) + 1;
        return new int[]{dice1Value, dice2Value};
    }//roll

    
    
    /**
     * This method will return a string containing the dice values when the
     * TwoDice constructor is called to create an object.
     *
     * @return The integer values for the dice are implicitly converted to a
     * string and returned
     */
    public String toString() {
        return "Dice: " + dice1Value + ", " + dice2Value;
    }//toString

    
    
    /**
     * this method visually represents the dice values that have been rolled.
     *
     * @return a 2D character array is returned which represents the dice that
     * were rolled
     */
    public char[][] displayDice() {
        int whichDie = 1; //will change after each loop iteration
        char[][] dice = {
            {' ', '-', '-', '-', '-', '-', '-', '-', ' ', ' ', ' ', '-', '-', '-', '-', '-', '-', '-', ' '},
            {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {' ', '-', '-', '-', '-', '-', '-', '-', ' ', ' ', ' ', '-', '-', '-', '-', '-', '-', '-', ' '}
        };

        while (whichDie >= 0) {
            int value = (whichDie == 1) ? dice1Value : dice2Value; //puts dice in 2D dice array one at a time
            int dice2Index = (whichDie == 1) ? 0 : 10; // adds index values for second dice
            whichDie -= 1; // while loop moves on to second dice after switch
            switch (value) {
                case 1:
                    dice[2][4 + dice2Index] = 'o';
                    break;
                case 2:
                    dice[1][2 + dice2Index] = 'o';
                    dice[3][6 + dice2Index] = 'o';
                    break;
                case 3:
                    dice[1][2 + dice2Index] = 'o';
                    dice[2][4 + dice2Index] = 'o';
                    dice[3][6 + dice2Index] = 'o';
                    break;
                case 4:
                    dice[1][2 + dice2Index] = 'o';
                    dice[1][6 + dice2Index] = 'o';
                    dice[3][2 + dice2Index] = 'o';
                    dice[3][6 + dice2Index] = 'o';
                    break;
                case 5:
                    dice[1][2 + dice2Index] = 'o';
                    dice[1][6 + dice2Index] = 'o';
                    dice[2][4 + dice2Index] = 'o';
                    dice[3][2 + dice2Index] = 'o';
                    dice[3][6 + dice2Index] = 'o';
                    break;

                case 6:
                    dice[1][2 + dice2Index] = 'o';
                    dice[1][6 + dice2Index] = 'o';
                    dice[2][2 + dice2Index] = 'o';
                    dice[2][6 + dice2Index] = 'o';
                    dice[3][2 + dice2Index] = 'o';
                    dice[3][6 + dice2Index] = 'o';
                    break;
            }//switch
        }//while

        for (int row = 0; row < dice.length; row++) { //iterates through rows
            for (int col = 0; col < dice[0].length; col++) { //iterates through columns in row
                System.out.print(dice[row][col]);
            }//for
            System.out.println();
        }//for
        return dice;
    }//displayDice
}//TwoDice
