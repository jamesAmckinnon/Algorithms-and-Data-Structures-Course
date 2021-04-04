/* This program returns a string of star characters that corresponds to the 
 * digits that make up the integer that is passed to the visualInt method.
 *
 * AUCSC 112
 *
 * by James McKinnon
 * 1670634
 *
 * Feb 17, 2021.
 *
 */


public class Lab1PartThree {

    /**
     * Testing of visualInt method, using standard out.
     * 
     * @param args the command line arguments (none)
     */
    public static void main(String[] args) {
        System.out.println("123456 --> " + visualInt(123456));
        System.out.println("-5020 --> " + visualInt(-5020));
        System.out.println("0 --> " + visualInt(0));        
        System.out.println("9 --> " + visualInt(9));       
        System.out.println("-1 --> " + visualInt(-1)); 
        System.out.println(Integer.MAX_VALUE + " --> " + visualInt(Integer.MAX_VALUE));
        System.out.println(Integer.MIN_VALUE + " --> " + visualInt(Integer.MIN_VALUE));
    } // main   

    /**
     * Returns a string that is made up of stars that represent the integer that
     * is passed to it. Each digit of the integer is turned into a string of stars
     * add those strings of stars are added to the output string with spaces between them.
     * Zeros are added to the string as underscores. Final string with all digits is returned. 
     */
    public static String visualInt(int num){
        boolean isNegative = num < 0; // true if num is negative
        num = (isNegative) ? num * (-1) : num; // num will always be positive
        long manipulatedNum = num;
        long baseTenCount = 10; // used long because baseTenCount becomes 10 billion for MAX/MIN_VALUE
        String output = (isNegative) ? "-" : ""; // negative sign for negative numbers
        
        // finds the base ten value of num. After "||" handles MIN_VALUE
        while (manipulatedNum > 9 || manipulatedNum < -9){ 
            manipulatedNum /= 10;
            baseTenCount *= 10; 
        }

        // Loop gets remainder of num divided by baseTenCount and stores it as manipulatedNum.
        // baseTenCount is then divided by ten so manipulatedNum will have one less digit at the front
        // after every loop. manipulatedNum is then divided by baseTenCount and the result is the first digit
        // of manipulatedNum. 
        // With every loop iteration the first digit of manipulatedNum is removed and sent to the stars method.
        while (baseTenCount != 1) { 
            manipulatedNum = num%baseTenCount; // 1234%10000 = 1234, 1234%1000 = 234, 234%100 = 34
            baseTenCount /= 10;
            output = output + stars((manipulatedNum/baseTenCount)); // 1234/1000 = 1, 234/100 = 2, 34/10 = 3
            output = (baseTenCount != 1) ? output + " ": output; // only adds space between digits
        }
        return output;
    }//visualInt  
    
    
    /**
     * Returns string with star representation of digit.
     */ 
    public static String stars(long manipulatedNum){
        String output = "";
               
        if (manipulatedNum == 0){
            output = output + "_"; 
        }// if

        while (manipulatedNum != 0) {
            // MIN_VALUE stays negative so this expression will increment manipulatedNum digit for MIN_VALUE 
            manipulatedNum = (manipulatedNum >= 1) ? manipulatedNum - 1 : manipulatedNum + 1;
            output = output + "*";
        }// while
        
        return output;
    }// stars
}//end of file