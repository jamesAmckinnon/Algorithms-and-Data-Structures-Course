/**
 * James McKinnon
 * 
 * This program solves a maze that is imported through a text file. The program
 * solves the maze and leaves a trail of dots behind it to represent the path 
 * leads from the starting position to the end of the maze. If the maze cannot 
 * be solved then the program tells the user that and ends the program.
 * 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

/**
 * This is a maze solving program. A file containing a maze shape is used as
 * input and the program either solves the maze or displays that the maze
 * can't be solved.
 * @author James
 */
public class MazeSolver {
    public static String fileName = "D:\\Documents\\School\\CSC 112\\Assignment1"
                                              + "\\MazeProject\\maze3.txt";
    static Stack<Coord> positions = new Stack<>();
    static Stack<Coord> dotsToDelete = new Stack<>();
    
    
    /**
     * This class is a coordinate data type and it is used in the stack to 
     * represent the locations that are needed to navigate the maze and to
     * place the dots along the path.
     */
    public static class Coord {
        public int row;
        public int col;  
        
        public Coord(int row, int col){
            this.row = row;
            this.col = col;
        }
        
        public String toString(){
            return row + "," + col;
        }
    }// Coord Class
    
    
    /**
     * This method calls the readFile() method and the solveMaze() method 
     * @param args the command line arguments (none)
     */
    public static void main(String[] args) {
        char[][] maze;
        
        maze = readFile(); // makes text file maze into 2d char array
        solveMaze(maze); // solves maze
    }// main
    
    
    /**
     * This method inputs the data from the text file into a 2d char array to
     * create the maze that will be solved.
     *
     * @return returns the 2d char array maze
     */
    public static char[][] readFile() {
        File inFile = new File(fileName);
        Scanner fileData;

        try {
            fileData = new Scanner(inFile);
        } catch (FileNotFoundException error) {
            System.out.println(error + "The file is not found");
            return null;
        }

        int rowCount = 0;
        int columnCount = 0;

        // loops through every line in text file to count the number of lines
        // and number of characters per line.
        while (fileData.hasNext()) {
            columnCount = fileData.nextLine().length();
            rowCount++;
        }

        fileData.close();

        try {
            
            fileData = new Scanner(inFile);
        } catch (FileNotFoundException error) {
            System.out.println(error + "The file is not found");
            return null;
        }

        char[][] maze = new char[rowCount][columnCount];

        // inserts each row of the text file as a character array into the maze array
        for (int i = 0; i < rowCount; i++) {
            maze[i] = fileData.nextLine().toCharArray();
        }
        
        return maze;
    }// readFile()

    
    /**
     * This method solves the maze. It does so by moving through the array
     * trying to find the ending spot. The method places dots on the spots in
     * the maze that it has checked already and then calls the removeDots()
     * function to remove the dots that lead to a dead end in the maze.
     *
     * @param maze this is the 2D array that contains the maze.
     */
    public static void solveMaze(char[][] maze) {

        Coord start = new Coord(0, 0);
        // finds start coordinate along left side of maze
        for (int i = 0; i < maze.length; i++) {
            if (maze[i][0] == ' ') {
                positions.push(new Coord(i, 0));
                start = new Coord(i, 0);
            }
        }

        Coord end = new Coord(0, 0);
        // finds end coordinate along right side of maze
        for (int i = 0; i < maze.length; i++) {
            if (maze[i][maze[0].length - 1] == ' ') {
                end = new Coord(i, maze[0].length - 1);
            }
        }
        
        // moves through maze to find end coordinate. When end coordinate found
        // the game is over.
        while (positions.isEmpty() == false) {
            // current position is the coordinate on the top of the stack
            Coord currentSpot = positions.pop(); 
            int row = currentSpot.row;
            int col = currentSpot.col;
            int adjacentSpots = 0;
                       
            // checks if current position is the end of the maze. Game over if it is.
            if (row == end.row && col == end.col) {
                maze[row][col] = '.';
                for(char[] rows : maze) System.out.println(rows); // prints maze
                System.out.println("You did it! Amazeing.");
                return;
            }
            
            maze[row][col] = '.'; // dot placed at current position
            
            // makes sure it doesn't go out of bounds at
            // start positions then checks left
            if (col != start.col && maze[row][col - 1] == ' ') {
                positions.push(new Coord(row, col - 1));
                // dots leading up to a dead end will be deleted
                dotsToDelete.push(new Coord(row, col - 1));
                adjacentSpots += 1;
            }

            //checks right
            if (maze[row][col + 1] == ' ') {
                positions.push(new Coord(row, col + 1));
                dotsToDelete.push(new Coord(row, col + 1));
                adjacentSpots += 1;
            }

            //checks up
            if (maze[row - 1][col] == ' ') {
                positions.push(new Coord(row - 1, col));
                dotsToDelete.push(new Coord(row - 1, col));
                adjacentSpots += 1;
            }

            //checks down
            if (maze[row + 1][col] == ' ') {
                positions.push(new Coord(row + 1, col));
                dotsToDelete.push(new Coord(row + 1, col));
                adjacentSpots += 1;
            }
            
            // if there is a dead end this removes dots that have led there
            if (adjacentSpots == 0 && !positions.isEmpty())  
                removeDots(maze);
        
        }// while  
        
        // if there was no path that led to the end position
        if(positions.isEmpty())  
            System.out.println("*** Path cannot be found ***");
        
    }// solveMaze()
    
    
    /**
     * This method removes dots that have led to a dead end in the maze.
     */
    public static void removeDots(char[][] maze) {
        Coord delete;

        // removes dots that were placed on the stack leading up to the dead end. stops 
        // deleting when the top of dotsToDelete stack is a spot where the maze branched. 
        // solveMaze() then continues from that coordinate down another branch.
        while (!dotsToDelete.peek().toString().equals(positions.peek().toString())) {
            delete = dotsToDelete.pop();
            maze[delete.row][delete.col] = ' ';
        }// while
    }// removeDots()
}// mazeSolver
