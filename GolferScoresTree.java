/*
Michael Nguyen - Matthew Morgan
Project 4 (Main Driver class for our Golfer, TreeBag and BTNode classes, and used to display various helpful information)
4/24/2020
GolferScoresTree.java
*/

import java.util.*; //used for scanner
import java.io.*; //exceptions

class GolferScoresTree { 
   public static void main(String args[]) throws IOException {
   /*
   Initializing variables, creating new golfer object, setting up file reader and 
   input scanners.
   */
      TreeBag<Golfer> golferTree = new TreeBag<Golfer>();
      File temp = new File("golferinfo.txt");
      Scanner keyboard = new Scanner(System.in), numbers = new Scanner(System.in); //one used for numbers, other for strings (preventing a buffer problem)
      String response, name;
      Golfer oneGolfer;
      int rounds = 0, handicap = 0, choice = 0;
      double average = 0.0;
      boolean test, excep = true;
      /*
      Checking to make sure our txt file exist if it doesn't 
      we will create a file. User will have to enter Players 
      name and stats. 
     */
      try{
         if(temp.exists()) {
            System.out.println("File found!");
         }
         else {
            System.out.println("No file found");
            temp.createNewFile();
            System.out.println("File has been created");
         }
         Scanner readFile = new Scanner(temp);
         while(readFile.hasNext()){
            name = readFile.next();
            rounds = readFile.nextInt();
            handicap = readFile.nextInt();
            average = readFile.nextDouble();
            golferTree.add(new Golfer(name, rounds, handicap, average));
         }
      }   
      catch(Exception e){
         System.out.println("No such file");
      }
      /*
      Entering a While loop to display menu
      as long user does not enter 7 (exit) the menu will 
      reDisplay each time. 
      */
      while(choice != 7) {
         choice = menu();
         switch(choice) {
            case 1://display inorder
               try {
                  golferTree.display();
               }
               catch(IllegalArgumentException e) {
                  System.out.println("Tree is empty");
               }
               System.out.println();
            break;
            case 2://display as tree
               try {
                  golferTree.displayAsTree();
               }
               catch(IllegalArgumentException e) {
                  System.out.println("Tree is empty");
               }
               System.out.println();
            break;
            case 3:// display a certain golfer stats
               System.out.println("Which golfer's stats would you like to be displayed?");
               response = keyboard.nextLine();
               oneGolfer = golferTree.retrieve(new Golfer(response));
               if(oneGolfer == null)
                  System.out.println(response + "'s stats not found");
               else {
                  System.out.println(oneGolfer);
               }
               System.out.println();
            break;
            case 4:// update a golfer
               System.out.println("Which golfer's stats would you like to update? ");
               response = keyboard.nextLine();
               oneGolfer = golferTree.retrieve(new Golfer(response));
               if(oneGolfer == null)
                  System.out.println("Couldn't find " + response);
               else {
                  System.out.println(response + " found! What did they score on the new round? ");
                  while(excep) {
                     try {
                        double newScore = numbers.nextDouble();
                        oneGolfer.addNewScore(newScore);
                        excep = false;
                     }
                     catch(InputMismatchException e) {
                        System.out.println("Please enter a valid double");
                        numbers.nextLine();
                     }
                  }
                  System.out.println("Stats have been updated"); 
               }
               System.out.println();      
               excep = true;    
            break;
            case 5://delete a golfer
               System.out.println("Which golfer would you like to remove from the Database? ");
               response = keyboard.nextLine();
               test = golferTree.remove(new Golfer(response));
               if(test) 
                  System.out.println(response + " has been removed from the Database");
               else 
                  System.out.println("Couldn't find " + response);
               System.out.println();
            break;
            case 6://add a new golfer
               System.out.println("What is the name of the golfer that you would like to add to the Database? ");
               response = keyboard.nextLine();
               System.out.println("How many rounds have they played? ");
               while(excep) {
                  try {
                     rounds = numbers.nextInt();
                     excep = false;
                  }
                  catch(InputMismatchException e) {
                     System.out.println("Please enter a valid integer");
                     numbers.nextLine();
                  }
               }
               excep = true;
               System.out.println("What is their handicap? ");
               while(excep) {
                  try {                   
                     handicap = numbers.nextInt();
                     excep = false;
                  }
                  catch(InputMismatchException e) {
                     System.out.println("Please enter a valid integer");
                     numbers.nextLine();
                  }
               }
               excep = true;
               System.out.println("What is their average score? ");
               while(excep) {
                  try {
                     average = numbers.nextDouble();
                     excep = false;
                  }
                  catch(InputMismatchException e) {
                     System.out.println("Please enter a valid double");
                     numbers.nextLine();
                  }
               }
               excep = true;
               try {
                  golferTree.add(new Golfer(response, rounds, handicap, average));
                  System.out.println(response + " successfully added to the Database");
               }
               catch(InputMismatchException e) {
                  System.out.println("Couldn't add the Golfer to the Database, at least one of the numbers entered is invalid");
               }
               System.out.println();
            break;
            case 7://update txt file 
               PrintStream out = System.out;
               try{
                  PrintStream outFile = new PrintStream(new File("golferinfo.txt"));
                  System.setOut(outFile);
                  golferTree.displayPreOrder();
                  outFile.close();
                  System.setOut(out);
               }
               catch(Exception e){
                  System.out.println("A problem occurred");
               }
               System.out.println("File successfully updated");
               System.out.println("Thank you for using this program!");
         }
      }
      
   } //end of main
   
   public static int menu() { //created to display the menu every time the while loop in the main is repeated
      int choice = 0;
      Scanner input = new Scanner(System.in);
      while(choice < 1 || choice > 7) {
         System.out.println("\n**************NOTE**************\nPlease start with a capital \nLetter when spelling last names\n********************************\nPlease choose from the following: \n");
         System.out.println("\t#1 Display listing to screen of all golfers stats(ordered by lastname)");
         System.out.println("\t#2 Display golfers in current tree format");
         System.out.println("\t#3 Find and display one individual golfers stats");
         System.out.println("\t#4 Update an individual golfers stats (by adding a new score)");
         System.out.println("\t#5 Remove a golfer from the Database");
         System.out.println("\t#6 Add a new golfer to the Database");
         System.out.println("\t#7 Quit and update datafile");
         System.out.print("Choice: ");
         try {
            choice = input.nextInt();
         }
         catch(InputMismatchException e) {
            System.out.println("Please enter a number!");
         }
         input.nextLine(); //clear buffer
         System.out.println();
      }
      return choice;
   }
   
   
} //end of class