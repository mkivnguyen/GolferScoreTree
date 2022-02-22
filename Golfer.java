/*
Michael Nguyen - Matthew Morgan
Project 4 (Template for the golfer object, which is used to store numerous golfer information)
4/24/2020
Golfer.java
*/

import java.util.InputMismatchException;

public class Golfer implements Comparable<Golfer> {
   
   //Data memebers
   private String lastName;
   private int numberOfRounds;
   private double averageScore;
   private int handicap;
   
 	/**
	 * A constructor to create a new golfer object with a last name, rounds played, handicap, and average score.
	 * @param lastName
	 *   The last name of this golfer.
	 * @param numberOfRounds
	 *   The number of rounds this golfer has played.
	 * @param averageScore
	 *   The average score of this golfer.
	 * @param handicap
	 *   The handicap for this golfer.
	 * @precondition
	 *   The argument passed for numberOfRounds is an integer value greater then zero.
	 *   The argument passed for handicap is an integer value between 0 and 20.
	 *   The argument passed for averageScore is a double value greater than 0.
	 * @postcondition
	 *   A new golfer has been created with the attributes input.
	 * @exception IllegalArgumentException
	 *   Will occur if numberOfRounds is less than 0 or not an integer value.
	 *   Will occur if handicap is not between 0 and 20 or not an integer value.
	 *   Will occur if averageScore is less than 0 or not a double value.
	 **/

   public Golfer(String name, int numRounds, int numHandiCap, double numAve) {
      if((numRounds < 0) || (numHandiCap < 0) || (numHandiCap > 20) || (numAve < 0.0)) 
         throw new InputMismatchException("Enter proper values"); //throwing the same exception if any of the values are bad
      lastName = name;
      numberOfRounds = numRounds; 
      handicap = numHandiCap;
      averageScore = numAve;
   }
   
    /**
	 * A constructor to create a new golfer object with a last name.
	 * @param lastName
	 *   The last name of this golfer.
	 * @postcondition
	 *   A new golfer has been created with the last name inputName. All other golfer attributes will be set to zero.
	 * @note
	 *   The golfer's last name should be entered in the following format if the String's compareTo method will be used to sort golfers alphabetically: 
	 *   The first letter should be upper case, while all other letters should be lower case, with no numbers or symbols.
	 **/
   public Golfer(String name) {
      lastName = name;
      numberOfRounds = 0;
      averageScore = 0;
      handicap = 0;
      
   }
   //getters
    /**
	 * An getter method that returns the golfer's last name.
	 * @return
	 *   A string value containing the golfer's last name.
	 **/
   public String getLastName() {
      return lastName;
   }
    /**
	 * An getter method that returns the number of rounds played.
	 * @return
	 *   A integer value that return the number of rounds played.
	 **/
	public int getNumberOfRounds() {
	   return numberOfRounds;
	}   
    /**
	 * An getter method that returns the golfer's  handicap.
	 * @return
	 *   A int value that return golfer's handicap.
	 **/
	public int getHandicap() {
		return handicap;
	}
    /**
	 * An getter method that returns the golfer's averageScore.
	 * @return
	 *   A double value that return the golfer's average score.
	 **/
	public double getAverageScore() {
		return averageScore;	
	} 
   //setters
    /**
	 * A modifier method that changes the name of a golfer.
	 * @param lastName
	 *   The new last name of this golfer.
	 * @postcondition
	 *   This golfers last name has been changed to the lastName.
	 **/
	public void setLastName(String name) {
		lastName = name;
	}     
  	/**
	 * A modifier method that changes the number of rounds a golfer has played.
	 * @param numRound
	 *   An integer value that the number of rounds should be change to.
	 * @precondition
	 *   The argument passed for numberOfRounds is an integer value greater then zero and less than Int.MAX_VALUE.
	 * @postcondition
	 *   The golfer's number of rounds has been changed to numberOfRounds.
	 * @exception InputMisMatchException
	 *   Will occur if inputRounds is less than 0 or not an integer value.
    **/ 
   
	public void setNumberOfRounds(int numRound) {
		if (numRound < 0){
			throw new InputMismatchException("Number sent in is less than 0");
		}
		numberOfRounds = numRound;
	} 
   
    /**
	 * A modifier method that changes the handicap for a golfer.
	 * @param handicap
	 *   An integer value  that the handicap should be changed to.
	 * @precondition
	 *   The argument passed for handicap is an integer value between 0 and 20.
	 * @postcondition
	 *   The golfer's handicap has been changed to handicap.
	 * @exception InputMismatchException
	 *   Will occur if handicap is not between 0 and 20 or not an integer value.
	 **/ 
	public void setHandicap(int numHandicap) {
		if ((numHandicap < 0) || (numHandicap > 20)) {
         throw new InputMismatchException("The handicap must be between 0-20");
		}
      handicap = numHandicap;
	}   
    /**
	 * A modifier method that changes the average score of a golfer.
	 * @param averageScore
	 *   A double value that the average score should be change to.
	 * @precondition
	 *   The argument passed for averageScore is a double value greater than 0.
	 * @postcondition
	 *   The golfer's average score has been changed to averageScore.
	 * @exception InputMismatchException
	 *   Will occur if averageScore is less than 0 or not a double value.
	 **/
   
	public void setAverageScore(double numAvgScore) {
		if (numAvgScore < 0){
			throw new InputMismatchException("The average score must be >= 0");
		}
		averageScore = numAvgScore;
	}   
   
    /**
	 * A setter method that increments a golfer's number of rounds by one and calculates a new average score based on the newScore.
	 * @param newScore
	 *   An integer score for a single round played.
	 * @precondition
	 *   The argument passed for newScore is an integer value greater than one.
	 * @postcondition
	 *   The golfer's number of rounds has been incremented by one and a new average score has been calculated for this golfer.
	 * @exception IllegalMisMatchException
	 *   Will occur if newScore is less than one or not an integer value.
	 **/
	public void addNewScore(double newScore) {	
		if (newScore < 0.0) {
			throw new InputMismatchException("The average score must be >= 0");
		}
      numberOfRounds++;
	   averageScore = ((averageScore * (numberOfRounds - 1)) + newScore) / ((double) numberOfRounds); //simple average calculation
	}
	 /**
	 * A method that returns an integer comparison based on the gofler's last name.
	 * The comparison is based on the Unicode value of each character in a golfer's last name. 
	 * @param player
	 *   The Golfer object to compare to other Golfer objects.
	 * @return
	 *   Returns a negative integer if inputGolfer's last name precedes this Golfer's lastName lexicographically.
	 *   Returns a positive integer if inputGolfer's last name follows this Golfer's lastName lexicographically.
	 *   Returns zero if inputGolfer's last name is the same as this Golfer's lastName.
	 **/
	public int compareTo(Golfer player) {
		return lastName.compareTo(player.lastName);
	}      
   
   /**
   *This method prints all name and score in the text
   *@param none
   *@precondition
   *  Sequence is not empty
   *@postcondition
   *  elements of the sequence are printed seperated by a comma and space
   **/
	public String toString() {
		return lastName + " " + numberOfRounds + " " + handicap + " " + averageScore;
	}
} //end of class
