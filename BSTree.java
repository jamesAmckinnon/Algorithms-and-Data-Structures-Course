/*
 * James McKinnon
 * April 16th, 2021
 * 
 * This file contains 2 methods used in a binary search tree class. It finds the number of ID 
 * numbers in the tree that have a lower numerical value than the parameter that is passed to the method.
*/
    
/**
 * This method calls the numLessThanHelper() method which counts the number
 * of id numbers that are of a value less than the one passed to the method.
 * 
 * @param lessThanThis id number used to see how many id numbers are less than it.
 * @return returns the number of id numbers in the tree are less than the 
 *         number passed to the method.
 */
public int numLessThan(int lessThanThis){
    int lessThanCount = numLessThanHelper(lessThanThis, root);
    return lessThanCount;
}

/**
 * This method finds how many ID numbers are in the tree that are less than
 * the number passed to the method. 
 * 
 * @param lessThanThis the method is looking for ID numbers less than this number.
 * @param currentLocation the node that the method is currently looking at
 * @return the number of id numbers in the tree that are less than the number
 *         that is passed to the method.
 */
public int numLessThanHelper(int lessThanThis, Node currentLocation){
    if(currentLocation == null) return 0; // base case

    // recursion and work
    // will not go to the right descendant if the currentLocation node is 
    // already larger than the lessThanThis number. This saves time and makes
    // this algorithm less than O(n). If the current node is less than the
    // lessThanThis number the algorithm will go right until that is not the 
    // case anymore. At this point it will only look at the left branch of the 
    // node that is greater than the lessThanThis value.
    if(currentLocation.getIdNum() < lessThanThis){ 
        return 1 + numLessThanHelper(lessThanThis, currentLocation.getLeftChild()) +
                   numLessThanHelper(lessThanThis, currentLocation.getRightChild());
    } else 
        return numLessThanHelper(lessThanThis, currentLocation.getLeftChild());    
} //numLessThanHelper()

