/*
 * James McKinnon
 * April 7th, 2021
 * 
 * This file contains two methods that preform tasks by calling other methods
 * recursively. The first recursive method is the printInvertTriangle() method.
 * This method calls other methods which recursively create a rectangle box of
 * pound symbols with a triangle of spaces inside of it. The second method is
 * the multsOf7First() method. This method calls other methods which recursively
 * sort an array so that the resulting array has all multiples of 7 at the front.
 * 
*/


public class recursion {

    /** This method calls the recursive methods.
     * @param args the command line arguments (none)
     */
    public static void main(String[] args) {
        int[] anArray = {1, 2, 7, 49, 87, 96, 25, 21, 14, 50, 56};
        
        printInvertTriangle(10);
        multsOf7First(anArray);
    } //main()
    
    
    /** This method prints the top and bottom border of the rectangle and calls
     * the recursive method that prints the triangle of spaces if size > 0.
     * @param size user defined size for how many rows the triangle has
     */
    public static void printInvertTriangle(int size){
        if(size > 0){
            printPoundsOrBlanks((size + 1) * 2 + 1, '#'); // top border 
            System.out.println();
            printPoundsOrBlanks((size + 1) * 2 + 1, '#'); // top border
            System.out.println();  
            invertTriangle(size, 1); // section containing triangle
            printPoundsOrBlanks((size + 1) * 2 + 1, '#'); // bottom border
            System.out.println();
            printPoundsOrBlanks((size + 1) * 2 + 1, '#'); // bottom border
            System.out.println();
        } 
        else System.out.printf("\r\nA triangle of size %d is too small", size);
    } // printInvertTriangle()
    
    /** This method prints the triangle section of the picture.
     * @param size user defined size for how many rows the triangle has 
     * @param counter counts the number of spaces used in each row. Starts with 1
     */
    
    public static void invertTriangle(int size, int counter){
        if(size == 0) return; // base case. Stops when all rows have printed
        else {
            printPoundsOrBlanks(size + 1, '#');
            printPoundsOrBlanks(counter, ' ');
            printPoundsOrBlanks(size + 1, '#');
            System.out.println();
        } // else
       invertTriangle(size - 1, counter + 2); // recursive step
    } // invertTriangle()
    
    /** This method prints either pound symbols or spaces.
     * @param amount tells the method how many characters to print
     * @param type tells the method what type of characters to print
     */
    public static void printPoundsOrBlanks(int amount, char type){
        if(amount != 0) {
            System.out.printf("%s", type);
            printPoundsOrBlanks(amount - 1, type);
        }
    } // printPoundsOrBlanks()

    
    
    /** Initializes the indexes for the array and calls the recursive method. 
     * @param anArray the initial array that will be sorted 
     */
    public static void multsOf7First(int[] anArray){
        int leftIndex = 0,  rightIndex = anArray.length - 1;
        sort(anArray, leftIndex, rightIndex); 
    } // multsOf7First()
    
    /** sorts array by placing numbers that are multiples of 7 at front of list.
     * @param theArray the array that will be changed in-place using recursion
     * @param leftIndex the left array index that the algorithm is looking at
     * @param rightIndex the right array index that the algorithm is looking at
     */
    public static void sort(int[] theArray, int leftIndex, int rightIndex){
        if (leftIndex == rightIndex) return; // when indexes cross, the list is sorted. Base case.
        
        if (theArray[leftIndex] % 7 == 0) // shifts left index right if it is a multiple of 7
            sort(theArray, leftIndex + 1, rightIndex); // recursive step
        else if (theArray[rightIndex] % 7 == 0){ // swaps left/right index if both are multiples of 7
            int tempValue = theArray[leftIndex]; // ensures value of left index isn't overwritten
            theArray[leftIndex] = theArray[rightIndex];
            theArray[rightIndex] = tempValue;
            sort(theArray, leftIndex + 1, rightIndex - 1);} // recursive step 
        else sort(theArray, leftIndex, rightIndex - 1); // recursive step
    }// sort()
}// recursion Class
