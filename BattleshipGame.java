package battleship;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BattleshipGame {

	public static void main(String[] args) {
		// Create an Ocean object to represent the game board
        Ocean ocean = new Ocean();

        

        // Create a scanner for user input
        Scanner scanner = new Scanner(System.in);
        
    	// Display the game board with row and column numbers
    	
        // Initialize the game by placing ships in the ocean
        ocean.placeAllShipsRandomly();
        //these instance variables are used to catch errors
        
        
        // Main game loop
        while (!ocean.isGameOver()) {
            ocean.print();
        	int row = -1, column = -1;
              boolean validInput = false;
              
              while (!validInput) {
                  try {
                      System.out.print("Enter row (0-9): ");
                      row = scanner.nextInt();
                      if (row < 0 || row > 9) {
                          throw new IllegalArgumentException("Row must be between 0 and 9.");
                      }

                      System.out.print("Enter column (0-9): ");
                      column = scanner.nextInt();
                      if (column < 0 || column > 9) {
                          throw new IllegalArgumentException("Column must be between 0 and 9.");
                      }

                      validInput = true; // If no exception, input is valid
                  } catch (InputMismatchException e) {
                      System.out.println("Invalid input. Please enter numbers only.");
                      scanner.nextLine(); // Clear the buffer
                  } catch (IllegalArgumentException e) {
                      System.out.println(e.getMessage());
                      // No need to clear the buffer since the input was a valid integer
                  }
              }

        	

        
        

            // Fire a shot at the specified location
            boolean shotResult = ocean.shootAt(row, column);
            
            // Display the result of the shot
           
            if (shotResult) {
            	 
                System.out.println("You hit a ship!");
                
            } else {
                System.out.println("You missed.");
                
            }
            System.out.println("Hit count: " + ocean.getShotsFired());
              
        }

        // Game is over, display the final results
       
        System.out.println("Game over! Your score: " + ocean.getShotsFired());
        ocean.print();
        scanner.close();
    }

 
}

	


