<<<<<<< HEAD
/**
 * James McKinnon
 *
 * This program simulates a game of Duck Duck Goose. The players in the game are 
 * sitting in a "circle" which is actually a linked list representation of a circle.
 * The user of the program first enters in the number of players that will be
 * playing the game and then they will be prompted to enter the name of each player.
 * The number of rounds that the user wants to simulate is then entered and the
 * game starts. The game continues for the certain number of rounds and displays
 * information about the progress of the game as it is happening.
 * 
 * I did not create the DNode class or the DCircLinkList class, the DuckDuckGoose
 * class is the only class that I created for this project. 
 */

import java.util.Scanner;
import java.util.Random;

/**
 * This class is a game of duck duck goose played in the terminal window.
 * The linked list methods used in this project were not created by me. 
 *
 * @author James
 */
public class DuckDuckGoose {
    static DCircLinkList circleList = new DCircLinkList();
    static Random randVal = new Random();
    
    /**
     * This method contains the main game control logic and initializes the
     * game setup when the program is first started.
     * 
     * @param args the command line arguments (none)
     */
    public static void main(String[] args) {
        // initializeGame() returns an object array that includes the
        // initial it person [0] and the number of rounds to be played [1]
        Object[] initialValues = initializeGame();
        Object itPerson = initialValues[0];
        int rounds = (int)initialValues[1];
        int roundCount = 0;
        Object goose;
        
        while(roundCount < rounds){
            roundCount += 1;
            System.out.println("Round: " + roundCount);
            System.out.println("Game Circle: " + circleList.toString());
            System.out.println("It-Person: " + itPerson.toString());
            System.out.println("========================================");
            
            goose = pickGoose(); // counts ducks, returns goose
            
            circleList.removeFirstNode();
            System.out.println("\nUp Jumps: " + goose); 
            System.out.println("Game Circle: " + circleList.toString());  
            
            //new itPerson is decided in playRound()
            itPerson = playRound(goose.toString(), itPerson.toString());   
        }// while
    }// main()
    
    
        /**
         * This method asks the user to enter in information that is needed for 
         * the game including how many players, the names of the players and 
         * the number of rounds to be played.
         * 
         * @return returns the last player of the list as the first "it person"
         */
        public static Object[] initializeGame(){
            Scanner keyboard = new Scanner(System.in); 
            System.out.println("How many players?");
            int players = Integer.valueOf(keyboard.nextLine());
            Object itPerson;

            for (int i = 1; i < players + 1; i++) {
                System.out.printf("Please enter player %d's name: \n", i);
                String name = keyboard.nextLine();

                // adds names to list in CW direction 
                if (circleList.getSize() == 0) circleList.addFirst(name);
                else circleList.addLast(name);
                    
                System.out.println(name + " added to circle");
            }// for
            
            itPerson = circleList.getLastElement(); //last entered is it first
            circleList.rotateCW();
            circleList.removeFirstNode(); //removes it person from circle

            System.out.println("\nHow many rounds?");
            int rounds = Integer.valueOf(keyboard.nextLine());
            
            System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++\n");

            return new Object[]{itPerson, rounds};
        }// initializeGame()
    
        
        /**
         * This method simulates a player walking around the circle counting
         * the ducks before choosing a goose. The number of ducks counted
         * is a randomly generated number between 1 and 20.
         * 
         * @return returns the name of the goose that has been chosen
         */
        public static Object pickGoose(){
            int counter = 0;
            int numberOfDucks = randVal.nextInt(20) + 1;
            
            System.out.println("Random number generated is: " + numberOfDucks);
            
            // rotates circle as it calls out the ducks 
            for(int i = 0 ; i < numberOfDucks ; i++){
                // formats the duck count, max 5 names per line
                if(counter == 5) { System.out.println(); counter = 1; }
                else counter += 1;
                circleList.rotateCCW();
                System.out.print(circleList.getFirstElement().toString() + " duck; ");
            }

            // after last duck the next person is the goose
            circleList.rotateCCW();
            System.out.println(circleList.getFirstElement().toString() + " GOOSE");
            
            return circleList.getFirstElement().toString(); // returns goose
        }// pickGoose()
        
        
        /**
         * This method contains the logic for the overall flow of the round that
         * is in progress.
         * 
         * @param goose the name of the current goose
         * @param itPerson the name of the current it person
         * @return returns the new it person. Whoever lost the round
         */
        public static Object playRound(String goose, String itPerson){   
            DNode firstNode = circleList.getFirstNode(); 
            DNode goosePosition = circleList.getFirstNode();
            DNode itPersonPosition = circleList.getFirstNode().getPrevious();   
            DNode stopSpotGoose = circleList.getFirstNode();
            DNode stopSpotItPerson = circleList.getFirstNode().getPrevious();
            boolean playing = true;
            boolean winner = false; 
            
            while(playing){
                int gooseSpeed = randVal.nextInt(circleList.getSize()) + 1;
                int itPersonSpeed = randVal.nextInt(circleList.getSize()) + 1; 
                if(gooseSpeed == itPersonSpeed) continue;
                
                System.out.printf("   **Speeds: It-Person %d, Goose %d\n", 
                                               itPersonSpeed, gooseSpeed);
                
                // highest speed runs first. run() returns new positions of 
                // goose and itPlayer after they run their speed value.
                if(gooseSpeed > itPersonSpeed){ 
                    goosePosition = run(gooseSpeed, goosePosition, true, stopSpotGoose);
                    if(goosePosition == stopSpotGoose) winner = true; // true == goose wins

                    itPersonPosition = run(itPersonSpeed, itPersonPosition, false, stopSpotItPerson);
                    if(goosePosition == stopSpotGoose && itPersonSpeed > gooseSpeed) winner = false;

                    System.out.println();
                } else if(gooseSpeed < itPersonSpeed){ 
                    itPersonPosition = run(itPersonSpeed, itPersonPosition, false, stopSpotItPerson);
                    if(goosePosition == stopSpotGoose) winner = false; // false == itPlayer wins
                    
                    goosePosition = run(gooseSpeed, goosePosition, true, stopSpotGoose);
                    if(goosePosition == stopSpotGoose && gooseSpeed > itPersonSpeed) winner = true;

                    System.out.println();
                }

                // determines who won the round
                if(goosePosition == stopSpotGoose && winner){
                    circleList.setEntry(firstNode);
                    circleList.addFirst(goose);// winner sits back down in circle
                    System.out.printf("Goose (%s) wins\n", goose);
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++\n");
                    return itPerson; // returns looser
                } else if(itPersonPosition == stopSpotItPerson && winner == false){
                    circleList.setEntry(firstNode);
                    circleList.addFirst(itPerson); // winner sits back down in circle
                    System.out.printf("It-Person (%s) wins\n", itPerson);
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++\n");
                    return goose;// returns looser
                }
            }// while

            return itPerson;
        }// play round
        
        
        /**
         * This method controls the running of the players as they go around
         * the circle.
         * 
         * @param speed speed assigned to player for current round
         * @param position current position of player
         * @param gooseTrue tells method which player needs to run
         * @param stopSpot tells method which position player needs to stop at
         * @return returns the new position of the player after running
         */
        public static DNode run(int speed, DNode position, boolean gooseTrue, DNode stopSpot){
            String whosTurn = (gooseTrue) ? "Goose" : "It-Person";
            System.out.printf("      %s running past: ", whosTurn);
            if(gooseTrue) System.out.print("    ");
            
            for(int i = 0 ; i < speed ; i++){
                circleList.setEntry(position); // sets entry to players current position 
                // goose runs CCW so circle rotates CW
                if(gooseTrue) circleList.rotateCW();
                else circleList.rotateCCW(); // it-person runs CW
                // position equals the name of the person the player is passing
                position = circleList.getFirstNode();
                System.out.print(position.getElement() + " ");
                // stops loop if player has ran around the whole circle
                if(position.getElement() == stopSpot.getElement())  i = speed;
            }// for

            System.out.println();
            
            return position; // returns new position
        }// run()
=======
/**
 * James McKinnon
 *
 * This program simulates a game of Duck Duck Goose. The players in the game are 
 * sitting in a "circle" which is actually a linked list representation of a circle.
 * The user of the program first enters in the number of players that will be
 * playing the game and then they will be prompted to enter the name of each player.
 * The number of rounds that the user wants to simulate is then entered and the
 * game starts. The game continues for the certain number of rounds and displays
 * information about the progress of the game as it is happening.
 * 
 * I did not create the DNode class or the DCircLinkList class, the DuckDuckGoose
 * class is the only class that I created for this project. 
 */

import java.util.Scanner;
import java.util.Random;

/**
 * This class is a game of duck duck goose played in the terminal window.
 * The linked list methods used in this project were not created by me. 
 *
 * @author James
 */
public class DuckDuckGoose {
    static DCircLinkList circleList = new DCircLinkList();
    static Random randVal = new Random();
    
    /**
     * This method contains the main game control logic and initializes the
     * game setup when the program is first started.
     * 
     * @param args the command line arguments (none)
     */
    public static void main(String[] args) {
        // initializeGame() returns an object array that includes the
        // initial it person [0] and the number of rounds to be played [1]
        Object[] initialValues = initializeGame();
        Object itPerson = initialValues[0];
        int rounds = (int)initialValues[1];
        int roundCount = 0;
        Object goose;
        
        while(roundCount < rounds){
            roundCount += 1;
            System.out.println("Round: " + roundCount);
            System.out.println("Game Circle: " + circleList.toString());
            System.out.println("It-Person: " + itPerson.toString());
            System.out.println("========================================");
            
            goose = pickGoose(); // counts ducks, returns goose
            
            circleList.removeFirstNode();
            System.out.println("\nUp Jumps: " + goose); 
            System.out.println("Game Circle: " + circleList.toString());  
            
            //new itPerson is decided in playRound()
            itPerson = playRound(goose.toString(), itPerson.toString());   
        }// while
    }// main()
    
    
        /**
         * This method asks the user to enter in information that is needed for 
         * the game including how many players, the names of the players and 
         * the number of rounds to be played.
         * 
         * @return returns the last player of the list as the first "it person"
         */
        public static Object[] initializeGame(){
            Scanner keyboard = new Scanner(System.in); 
            System.out.println("How many players?");
            int players = Integer.valueOf(keyboard.nextLine());
            Object itPerson;

            for (int i = 1; i < players + 1; i++) {
                System.out.printf("Please enter player %d's name: \n", i);
                String name = keyboard.nextLine();

                // adds names to list in CW direction 
                if (circleList.getSize() == 0) circleList.addFirst(name);
                else circleList.addLast(name);
                    
                System.out.println(name + " added to circle");
            }// for
            
            itPerson = circleList.getLastElement(); //last entered is it first
            circleList.rotateCW();
            circleList.removeFirstNode(); //removes it person from circle

            System.out.println("\nHow many rounds?");
            int rounds = Integer.valueOf(keyboard.nextLine());
            
            System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++\n");

            return new Object[]{itPerson, rounds};
        }// initializeGame()
    
        
        /**
         * This method simulates a player walking around the circle counting
         * the ducks before choosing a goose. The number of ducks counted
         * is a randomly generated number between 1 and 20.
         * 
         * @return returns the name of the goose that has been chosen
         */
        public static Object pickGoose(){
            int counter = 0;
            int numberOfDucks = randVal.nextInt(20) + 1;
            
            System.out.println("Random number generated is: " + numberOfDucks);
            
            // rotates circle as it calls out the ducks 
            for(int i = 0 ; i < numberOfDucks ; i++){
                // formats the duck count, max 5 names per line
                if(counter == 5) { System.out.println(); counter = 1; }
                else counter += 1;
                circleList.rotateCCW();
                System.out.print(circleList.getFirstElement().toString() + " duck; ");
            }

            // after last duck the next person is the goose
            circleList.rotateCCW();
            System.out.println(circleList.getFirstElement().toString() + " GOOSE");
            
            return circleList.getFirstElement().toString(); // returns goose
        }// pickGoose()
        
        
        /**
         * This method contains the logic for the overall flow of the round that
         * is in progress.
         * 
         * @param goose the name of the current goose
         * @param itPerson the name of the current it person
         * @return returns the new it person. Whoever lost the round
         */
        public static Object playRound(String goose, String itPerson){   
            DNode firstNode = circleList.getFirstNode(); 
            DNode goosePosition = circleList.getFirstNode();
            DNode itPersonPosition = circleList.getFirstNode().getPrevious();   
            DNode stopSpotGoose = circleList.getFirstNode();
            DNode stopSpotItPerson = circleList.getFirstNode().getPrevious();
            boolean playing = true;
            boolean winner = false; 
            
            while(playing){
                int gooseSpeed = randVal.nextInt(circleList.getSize()) + 1;
                int itPersonSpeed = randVal.nextInt(circleList.getSize()) + 1; 
                if(gooseSpeed == itPersonSpeed) continue;
                
                System.out.printf("   **Speeds: It-Person %d, Goose %d\n", 
                                               itPersonSpeed, gooseSpeed);
                
                // highest speed runs first. run() returns new positions of 
                // goose and itPlayer after they run their speed value.
                if(gooseSpeed > itPersonSpeed){ 
                    goosePosition = run(gooseSpeed, goosePosition, true, stopSpotGoose);
                    if(goosePosition == stopSpotGoose) winner = true; // true == goose wins

                    itPersonPosition = run(itPersonSpeed, itPersonPosition, false, stopSpotItPerson);
                    if(goosePosition == stopSpotGoose && itPersonSpeed > gooseSpeed) winner = false;

                    System.out.println();
                } else if(gooseSpeed < itPersonSpeed){ 
                    itPersonPosition = run(itPersonSpeed, itPersonPosition, false, stopSpotItPerson);
                    if(goosePosition == stopSpotGoose) winner = false; // false == itPlayer wins
                    
                    goosePosition = run(gooseSpeed, goosePosition, true, stopSpotGoose);
                    if(goosePosition == stopSpotGoose && gooseSpeed > itPersonSpeed) winner = true;

                    System.out.println();
                }

                // determines who won the round
                if(goosePosition == stopSpotGoose && winner){
                    circleList.setEntry(firstNode);
                    circleList.addFirst(goose);// winner sits back down in circle
                    System.out.printf("Goose (%s) wins\n", goose);
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++\n");
                    return itPerson; // returns looser
                } else if(itPersonPosition == stopSpotItPerson && winner == false){
                    circleList.setEntry(firstNode);
                    circleList.addFirst(itPerson); // winner sits back down in circle
                    System.out.printf("It-Person (%s) wins\n", itPerson);
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++\n");
                    return goose;// returns looser
                }
            }// while

            return itPerson;
        }// play round
        
        
        /**
         * This method controls the running of the players as they go around
         * the circle.
         * 
         * @param speed speed assigned to player for current round
         * @param position current position of player
         * @param gooseTrue tells method which player needs to run
         * @param stopSpot tells method which position player needs to stop at
         * @return returns the new position of the player after running
         */
        public static DNode run(int speed, DNode position, boolean gooseTrue, DNode stopSpot){
            String whosTurn = (gooseTrue) ? "Goose" : "It-Person";
            System.out.printf("      %s running past: ", whosTurn);
            if(gooseTrue) System.out.print("    ");
            
            for(int i = 0 ; i < speed ; i++){
                circleList.setEntry(position); // sets entry to players current position 
                // goose runs CCW so circle rotates CW
                if(gooseTrue) circleList.rotateCW();
                else circleList.rotateCCW(); // it-person runs CW
                // position equals the name of the person the player is passing
                position = circleList.getFirstNode();
                System.out.print(position.getElement() + " ");
                // stops loop if player has ran around the whole circle
                if(position.getElement() == stopSpot.getElement())  i = speed;
            }// for

            System.out.println();
            
            return position; // returns new position
        }// run()
>>>>>>> 2eb5dbf1ad022be51a076340e5b4d8d9745eed63
}// DuckDuckGoose