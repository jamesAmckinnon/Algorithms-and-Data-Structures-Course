/*
 * James McKinnon
 * 1670634
 * April 16th, 2021
 * AUCSC 112
 * 
 * This file contains a binary search tree class which includes methods that are
 * used to create, traverse and find information within a binary search tree. 
 * All methods except for the numLessThan() method and its helper method were 
 * created by Rosanna Heise.
 * 
 *  BSTree.java
 * 
 * 
 *  by R. Heise
 *  Apr. 8, 2021
 * 
 */

/** 
 * This is a binary search tree class containing the methods that create,
 * traverse and find information within a binary tree.
 * @author Rosanna Heise/James McKinnon
 */
public class BSTree {
    private Node root; //node at the top level of the binary tree

    /**
     * Default constructor sets the root to null
     */
    public BSTree(){
        root = null;
    }//BSTree

    
    /**
     * A getter that returns the root node of the tree.
     * @return returns the root node of the tree
     */
    public Node getRoot() {
        return root;
    }

    
    /**
     * A setter that sets the root node of the tree.
     * @param root the node the be set as the root node
     */
    public void setRoot(Node root) {
        this.root = root;
    }
    
    
    /**
     * This method creates a node as the root if it is the first node and calls
     * a helper function to insert any nodes that are not the first node.
     * @param idNum the id number of the node to be inserted
     * @param name the name associated with the node to be inserted
     */
    public void insert(int idNum, String name){
        if(root == null){ // empty tree
            root = new Node(idNum, name); // makes first node
        } else { // do recursion to find spot to insert
            insertHelper(idNum,name,root);
        }
    }//insert()
    
    
    /**
     * This method is a helper method for the insert() method. This method inserts
     * all new nodes excepts for the first node. First the method ensures there 
     * are not any nodes that already have the specified id number and then it
     * will insert nodes in the correct order in the tree. The correct order is
     * where the value of the left child of a node is less than the node and the
     * right child of a node is greater than the node.
     * @param idNum idNum of new node to be inserted
     * @param name name of new node to be inserted
     * @param location location of where the method is currently traversing
     */
    private void insertHelper(int idNum, String name, Node location){
        //will not insert a node that already exists
        if(findNameOf(idNum).equals("NOT FOUND")){ 
            if(idNum < location.getIdNum()){// go left
                Node next = location.getLeftChild();
                if (next == null) {// found spot
                    // insert node
                    location.setLeftChild(new Node(idNum, name));
                } else { //recurse with left node as new location
                    insertHelper(idNum, name, next);
                }
            } else { // go right
                Node next = location.getRightChild();
                if (next == null){
                    // insert node
                    location.setRightChild(new Node(idNum, name));
                } else { // recurse with right node as new location
                    insertHelper(idNum, name, next);
                }
            } 
        } else {
            System.out.printf("Id number %d already belongs to: %s\n", 
                               idNum, findNameOf(idNum));
            return;
        }
    }// insertHelper()
    
    
    /**
     * This method calls the preOrderHelper() method to traverse the tree.  
     * @return a list of nodes in the order that they were visited in the tree 
     *         traversal.
     */
    public String preOrderString() {
        return preOrderHelper(root); //always starts at first node in tree
    }
    
    
    /**
     * This method traverses through the tree using a pre order technique. The
     * pre order technique visits the ancestor nodes first before visiting the
     * descendant nodes of each node in the tree. 
     * 
     * @param locInTree node the method is currently visiting
     * @return a list of nodes in the order that they were visited. 
     */
    private String preOrderHelper(Node locInTree){
        // base case
        if(locInTree == null){
            return "";
        }
        //recursion and work
        return locInTree + 
               preOrderHelper(locInTree.getLeftChild()) +
               preOrderHelper(locInTree.getRightChild());
    }// preOrderHelper()

    
    /**
     * This method calls the postOrderHelper() method to traverse the tree.  
     * @return a list of nodes in the order that they were visited in the tree 
     *         traversal.
     */
    public String postOrderString() {
        return postOrderHelper(root); //always starts at first node in tree
    }
    
    
    /**
     * This method traverses through the tree using a post order technique. The
     * post order technique visits the descendants of the nodes in a tree before 
     * visiting the ancestor nodes that the descendants belong to. 
     *    
     * @param locInTree node the method is currently visiting
     * @return a list of nodes in the order that they were visited. 
     */
    private String postOrderHelper(Node locInTree){
    // base case
    if(locInTree == null){
        return "";
    }
    //recursion and work
    return postOrderHelper(locInTree.getLeftChild()) +
           postOrderHelper(locInTree.getRightChild()) +
           locInTree;
    }// postOrderHelper()

    
    /**
     * This method calls the inOrderHelper() method to traverse the tree.  
     * @return a list of nodes in the order that they were visited in the tree 
     *         traversal.
     */
    public String inOrderString() {
        return inOrderHelper(root); //always starts at first node in tree
    }

    
    /**
     * This method traverses through the tree to using a in order technique. The
     * in order technique visits the descendants on the left side of nodes in a 
     * tree before visiting the ancestor node and then last it visits the 
     * descendant node on the right of the ancestor node. 
     * 
     * @param locInTree node the method is currently visiting
     * @return a list of nodes in the order that they were visited.
     */
    private String inOrderHelper(Node locInTree){
    // base case
    if(locInTree == null){
        return "";
    }
    //recursion and work
    return inOrderHelper(locInTree.getLeftChild()) +
           locInTree+
           inOrderHelper(locInTree.getRightChild());  
    }//inOrderHelper()
    
    
    /**
     * This method calls the findIdNumOfHelper() method which retrieves the id 
     * number of a node that has a name that matches the parameter that is passed 
     * to it.
     * @param name name associated with the id number to find in the tree
     * @return id number associated with the name
     */
    public int findIdNumOf(String name){
        return findIdNumOfHelper(name, root);
    }
    
    
    /**
     * This method will return the id that is associated with the name that is 
     * passed to it if that name exists. If it doesn't exist -1 is returned.
     * 
     * @param name name associated with the id number to find in the tree
     * @param location node where the method is currently looking for the id number
     * @return id number associated with the name 
     */
    private int findIdNumOfHelper(String name, Node location){
        //base case
        if(location == null){
            return -1; //name not found at current node
        }
        
        if(name.equals(location.getName())){ // found id number
            return location.getIdNum(); 
        }
        
        // recursion and work to search through tree 
        // looks at left nodes
        int leftId = findIdNumOfHelper(name, location.getLeftChild());
        if(leftId == -1){ //looks at right nodes
            return findIdNumOfHelper(name, location.getRightChild());
        }
        else { // found name
            return leftId;
        }
    }// findIdNumOfHelper()
    
    
    /**
     * This method calls the findNameNumOfHelper() method which retrieves the name 
     * of a node that has an id number that matches the parameter that is passed 
     * to it.
     * @param idNum id number associated with the name to find in the tree
     * @return name associated with the id number
     */
    public String findNameOf(int idNum){
        if(root == null){
            return "NOT FOUND";
        }
        return findNameOfHelper(idNum, root);
    }   
    
    
    /**
     * This method will return the name that is associated with the id number that
     * is passed to it if that id number exists. If it doesn't exist the method
     * will return "Not Found".
     * 
     * @param idNum id number associated with the name to find in the tree
     * @param location node where the method is currently looking for the name
     * @return name associated with the id number
     */
    private String findNameOfHelper(int idNum, Node location){
        //base case
        if(location == null){ //idNum not in tree
            return "NOT FOUND";
        }
        if (idNum == location.getIdNum()){
            return location.getName(); //name found
        }
        
        // recursion and work
        // traverses left if the current node is larger than target id number
        if(idNum < location.getIdNum()){ 
            return findNameOfHelper(idNum, location.getLeftChild());
        }
        else { // right otherwise
            return findNameOfHelper(idNum, location.getRightChild());
        }
    }// findNameOfHelper()
    

    /**
     * This method prints the nodes in the tree in the order that the inOrder 
     * traversal technique traverses the nodes. 
     * @return a list of the nodes in the tree
     */
    public String toString(){
        return inOrderString();
    }

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
     * This method finds how many id numbers are in the tree that are less than
     * the number passed to the method. 
     * 
     * @param lessThanThis id number used to see how many id numbers are less than it.
     * @param currentLocation the node that the method is currently using
     * @return the number of id numbers in the tree that are less than the number
     *         that is passed to the method.
     */
    public int numLessThanHelper(int lessThanThis, Node currentLocation){
        if(currentLocation == null) return 0; // base case
        
        // recursion and work
        // will not go to the right descendant if the currentLocation node is 
        // already larger than the lessThanThis number. This saves time and makes
        // this algorithm less than O(n). If the current node is lesss than the
        // lessThanThis number the algorithm will go right until that is not the 
        // case anymore. At this point it will only look at the left branch of the 
        // node that is greater than the lessThanThis value.
        if(currentLocation.getIdNum() < lessThanThis){ 
            return 1 + numLessThanHelper(lessThanThis, currentLocation.getLeftChild()) +
                       numLessThanHelper(lessThanThis, currentLocation.getRightChild());
        } else 
            return numLessThanHelper(lessThanThis, currentLocation.getLeftChild());    
    } //numLessThanHelper()
}//class
