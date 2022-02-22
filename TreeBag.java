   /*
   Michael Nguyen & Matthew Morgan
   Project 4 (used to store the Golfer objects in a binary search tree which can be easily manipulated)
   4/24/2020
   TreeBag.java
   */
   
	// The implementation of most methods in this file is left as a student
	// exercise from Section 9.5 of "Data Structures and Other Objects Using Java"


	/******************************************************************************
	* This class is a homework assignment;
	* An <CODE>TreeBag</CODE> is a collection of int numbers.
	*
	* <dl><dt><b>Limitations:</b> <dd>
	*   Beyond <CODE>Integer.MAX_VALUE</CODE> elements, <CODE>countOccurrences</CODE>,
	*   and <CODE>size</CODE> are wrong. 
	*
	* <dt><b>Note:</b><dd>
	*   This file contains only blank implementations ("stubs")
	*   because this is a Programming Project for my students.
	*
	* @version
	*   Jan 24, 2016
	******************************************************************************/
	public class TreeBag<E extends Comparable> implements Cloneable
	{
	   // The Term E extends Comparable is letting the compiler know that any type
	   // used to instantiate E must implement Comparable. i. e. that means that whatever
	   // type E is must have a compareTo method so that elements can be compared against one another
	   // This is required becuase we are doing comparisons in our methods


	   // Invariant of the TreeBag class:
	   //   1. The elements in the bag are stored in a binary search tree.
	   //   2. The instance variable root is a reference to the root of the
	   //      binary search tree (or null for an empty tree).
	   private BTNode<E> root;   


	   /**
	   * Insert a new element into this bag.
	   * @param <CODE>element</CODE>
	   *   the new element that is being inserted
	   * <dt><b>Postcondition:</b><dd>
	   *   A new copy of the element has been added to this bag.
	   * @exception OutOfMemoryError
	   *   Indicates insufficient memory a new BTNode.
	   **/
	   public void add(E element) {    
	      BTNode<E> node = new BTNode<E>(element, null, null);
	      boolean done= false;

	      if(root == null) { //creates the first node and it's root
	         root = new BTNode<E>(element, null, null);
	      }
	      else { //not root
            BTNode<E> cursor = root;
	         while(!done) {
	            if(element.compareTo(cursor.getData()) <=0) {
	            //then focus on left
	               if(cursor.getLeft() == null){
	               //if null then creates a node
	                  cursor.setLeft(node);
	                  done = true;
	               }
	               else { //continue going through node until cursor.getLeft == null
	                  cursor = cursor.getLeft();
	               }
	            }
	            else { //focus on right
	               if(cursor.getRight() == null) {
	                  cursor.setRight(node);
	                  done = true;
	               }
	               else {
	                  cursor = cursor.getRight(); //same with the right
	               }
	            }
	         }//end while
	      }
	   }

	   /**
	   * Retrieve location of a specified element from this bag.
	   * @param <CODE>target</CODE>
	   *   the element to locate in the bag
	   * @return 
	   *  the return value is a reference to the found element in the tree
	   * <dt><b>Postcondition:</b><dd>
	   *   If <CODE>target</CODE> was found in the bag, then method returns
	   *   a reference to a comparable element. If the target was not found then
	   *   the method returns null.
	   *   The bag remains unchanged.
	   **/
	   public E retrieve(E target){
	      BTNode<E> cursor = root;
         boolean flag = true;
         //finding cursor 
         while((cursor!=null) && (target.compareTo(cursor.getData()) != 0)) {
            if(target.compareTo(cursor.getData()) < 0) //to the left
               cursor = cursor.getLeft();
            else //to the right
               cursor = cursor.getRight();
         }
         if(cursor != null) 
            return cursor.getData(); //either return the data if it was found
         return null; //if the cursor is null then the data wasn't found
      }  

	   
	   /**
	   * Remove one copy of a specified element from this bag.
	   * @param <CODE>target</CODE>
	   *   the element to remove from the bag
	   * <dt><b>Postcondition:</b><dd>
	   *   If <CODE>target</CODE> was found in the bag, then one copy of
	   *   <CODE>target</CODE> has been removed and the method returns true. 
	   *   Otherwise the bag remains unchanged and the method returns false. 
	   **/
	  
      public boolean remove(E target) {
         BTNode<E> cursor = root;
         BTNode<E> parentOfCursor = null;
         boolean  answer = true;
         
         while((cursor!=null) && (target.compareTo(cursor.getData()) != 0)) {
            parentOfCursor = cursor; //parent one behind
            if(target.compareTo(cursor.getData()) < 0) //left of tree
               cursor = cursor.getLeft();
            else //right of tree
               cursor = cursor.getRight();
         }
         if(cursor == null) //target not found
            answer = false;

         else if(cursor.isLeaf()) { //case 1: target is a leaf
            if(parentOfCursor == null) { //handling the root
               root = null;
            }
            else { //not root
               if(parentOfCursor.getRight() != null) { //not root
                  if(cursor.getData() == parentOfCursor.getRight().getData()) //easily removing right node
                     parentOfCursor.setRight(null);
               }
               if(parentOfCursor.getLeft() != null) { //removing left node
                  if(cursor.getData() == parentOfCursor.getLeft().getData()) 
                     parentOfCursor.setLeft(null);
               }
            }
         }
         else if((cursor.getLeft() == null) || (cursor.getRight() == null)) { //case 2: one child
            if(root.getData().compareTo(target) == 0) { //handling when target is the root
               if(root.getRight() == null) 
                  root = root.getLeft();
               else
                  root = root.getRight();
            }
            else { //not root
               if(parentOfCursor.getRight() != null) { //checking if the right is null
                  if(cursor.getData() == parentOfCursor.getRight().getData()) { //if it is not null, checking if the right data is the same
                     if(cursor.getRight() != null) {
                        parentOfCursor.setRight(cursor.getRight());
                     }
                     else
                        parentOfCursor.setRight(cursor.getLeft());
                  }       
               }
               if(parentOfCursor.getLeft() != null) { //checking if left is null
                  if(cursor.getData() == parentOfCursor.getLeft().getData()) { //checking if left data is the same
                     if(cursor.getRight() != null) 
                        parentOfCursor.setLeft(cursor.getRight());
                     else 
                        parentOfCursor.setLeft(cursor.getLeft());
                  }
               }
            }
         }
         else { //case 3: both left and right children
            cursor.setData(cursor.getLeft().getRightmostData()); //changing the data to the rightmost, then removing the rightmost
			   cursor.setLeft(cursor.getLeft().removeRightmost());
         }
         return answer;
      }
	   
	   /**
	   * Displays the entire tree of in order
	   * by the elements compareTo method
	   * 
	   * @param 
	   *   none
	   * <dt><b>Postcondition:</b><dd>
	   *   Outputs all elements in the tree to Screen.
	   *   Does not change the structure 
      * @exception IllegalArgumentException
      *  Indicates that the tree is empty.
	   **/
	   public void display() {
	      if(root == null){
	         throw new IllegalArgumentException("The tree is empty");
	      }
	      else{
	         root.inorderPrint(); //inorder to ensure correct order of display
	      }
	   } 
      	   
	   /**
	   * Displays the entire tree of node preordered 
	   * by the elements compareTo method
	   * 
	   * @param 
	   *   none
	   * <dt><b>Postcondition:</b><dd>
	   *   Outputs all elements in the tree to Screen.
	   *   Does not change the structure 
      * @exception IllegalArgumentException
      *  Indicates that the tree is empty.
	   **/
	   public void displayPreOrder() {
	      if(root == null){
	         throw new IllegalArgumentException("The tree is empty");
	      }
	      else{
	         root.preorderPrint(); //used for file displaying
	      }
	   } 
	     
	   /**
	   * Displays the entire tree of Node elements using the
	   * built in print method of BTNode
	   * which displays the entire tree in tree format
	   * 
	   * @param 
	   *   none
	   * <dt><b>Postcondition:</b><dd>
	   *   Outputs all elements in the tree to Screen.
	   *   Does not change the structure 
      * @exception IllegalArgumentException
      *  Indicates that the tree is empty.
	   **/   
	   public void displayAsTree() {
	      if (root == null)
	         throw new IllegalArgumentException("The tree is empty");
	      root.print(0);
	   }
	      
	   
	   
	   /**
	   * Generate a copy of this bag.
	   * @param - none
	   * @return
	   *   The return value is a copy of this bag. Subsequent changes to the
	   *   copy will not affect the original, nor vice versa. Note that the return
	   *   value must be type cast to an <CODE>TreeBag</CODE> before it can be used.
	   * @exception OutOfMemoryError
	   *   Indicates insufficient memory for creating the clone.
	   **/ 
	   public TreeBag<E> clone( )//  n/a
	   {  // Clone an IntTreeBag object.
	      // Student will replace this return statement with their own code:
	      return null; 
	   } 

	   /**
	   * Accessor method to count the number of occurrences of a particular element
	   * in this bag.
	   * @param <CODE>target</CODE>
	   *   the element that needs to be counted
	   * @return
	   *   the number of times that <CODE>target</CODE> occurs in this bag
	   **/
	   public int countOccurrences(E target) //n/a
	   {
	      // Student will replace this return statement with their own code:
	      return 0;
	   }
	   
	       
	   /**
	   * Determine the number of elements in this bag.
	   * @param - none
	   * @return
	   *   the number of elements in this bag
	   **/                           
	   public int size( )
	   {
	      return BTNode.treeSize(root);
	   }




	   /**
	   * Add the contents of another bag to this bag.
	   * @param <CODE>addend</CODE>
	   *   a bag whose contents will be added to this bag
	   * <dt><b>Precondition:</b><dd>
	   *   The parameter, <CODE>addend</CODE>, is not null.
	   * <dt><b>Postcondition:</b><dd>
	   *   The elements from <CODE>addend</CODE> have been added to this bag.
	   * @exception IllegalArgumentException
	   *   Indicates that <CODE>addend</CODE> is null.
	   * @exception OutOfMemoryError
	   *   Indicates insufficient memory to increase the size of the bag.
	   **/
	   public void addAll(TreeBag<E> addend) //n/a
	   {
	      // Implemented by student.
	   }
	   
	   /**
	   * Create a new bag that contains all the elements from two other bags.
	   * @param <CODE>b1</CODE>
	   *   the first of two bags
	   * @param <CODE>b2</CODE>
	   *   the second of two bags
	   * <dt><b>Precondition:</b><dd>
	   *   Neither b1 nor b2 is null.
	   * @return
	   *   the union of b1 and b2
	   * @exception IllegalArgumentException
	   *   Indicates that one of the arguments is null.
	   * @exception OutOfMemoryError
	   *   Indicates insufficient memory for the new bag.
	   **/   
	   public static TreeBag union(TreeBag b1, TreeBag b2) // n/a
	   {
	      // Student will replace this return statement with their own code:
	      return null;
	   }
	      
	}
	           
