package battleship;

import java.util.Random;

/**
 * The Ocean class represents agrid for Battleship.
 * It contains ships in an ocean.
 */
public class Ocean {
//creating the general properties of the war ocean.
	//instance variables. 
	
	private Ship[][]ships = new Ship[10][10];
	private int shotsFired;
	private int hitCount;
	private int shipsSunk;
	
	//this is the constructor, the SPECIFIC ocean we are using. 
	public Ocean() {
		//create an empty ocean object
		
		for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                ships[row][col] = new EmptySea();
                ships[row][col].setBowRow(row);   // Set bow row
                ships[row][col].setBowColumn(col); // Set bow column
            }
        }
        //here I am  initializing the variables.
		shotsFired = 0;
        hitCount = 0;
        shipsSunk = 0;
    }
	 public void placeAllShipsRandomly() {
		  /**
		     * Randomly places all ships on the ocean grid.
		     */
		  
		        Random random = new Random();

		        // Define an array of ship lengths to iterate through
		        int[] shipLengths = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1};

		        for (int length : shipLengths) {
		            boolean placed = false;
		            while (!placed) {
		                int row = random.nextInt(10);
		                int column = random.nextInt(10);
		                boolean horizontal = random.nextBoolean();

		                Ship newShip;

		                // Choose the ship type based on its length
		                switch (length) {
		                    case 4:
		                        newShip = new Battleship();
		                        break;
		                    case 3:
		                        newShip = new Cruiser();
		                        break;
		                    case 2:
		                        newShip = new Destroyer();
		                        break;
		                    case 1:
		                    default:
		                        newShip = new Submarine();
		                        break;
		                }
		                //Checking if it's okay to place the ship at the randomly generated position
		                if (newShip.okToPlaceShipAt(row, column, horizontal, this)) {
		                    newShip.placeShipAt(row, column, horizontal, this);
		                    placed = true;
		                }
		            }
		        }
		    }
		
	 /**
	     * Seeing if the spot on the grid on the grid is occupied by a ship.
	     * @param row   The row coordinate.
	     * @param column The column coordinate.
	     * @return True if the location is occupied by a ship, false otherwise.
	     */
   public boolean isOccupied(int row, int column) {
        // Check if the given location contains a ship
    	  
    	if (ships[row][column] instanceof EmptySea) {
    		return false;
    	}
    	return true;
    }
 
   /**
    * Checks if a  position is in the bounds of the game.
    * @param row The row coordinate.
    * @param col The column coordinate.
    * @return True if the position is valid, false otherwise.
    */
   
   boolean isValidPosition(int row, int col) {
	    return row >= 0 && row < 10 && col >= 0 && col < 10;
	}

	 
   /**
    * shooting at a specific location on the ocean grid.
    * @param row   The row coordinate.
    * @param column The column coordinate.
    * @return True if the shot resulted in a hit, false otherwise.
    */
    public boolean shootAt(int row, int column) {
    	//for testing purposes
    	if (row < 0 || row >= 10 || column < 0 || column >= 10) {
    	        System.out.println("Invalid shot. Row and column must be between 0 and 9.");
    	        return false;
    	    }  
    	
    	shotsFired++;
    	  Ship ship = ships[row][column];
    	   
    	        if (!(ship instanceof EmptySea)) {
    	        	if (ship.shootAt(row, column)) {    	        	  
    	      	        	        	 
    	            hitCount++;
    	            if (ship.isSunk()) {
    	                shipsSunk++;
    	                
    	            }
    	        
    	            return true;
    	           
    	        }
    	        	
    	        	}
    	    
    	        return false;
    	       
    }
    	  
    	
    	
    	
    	
    /**
     * @return The number of shots fired.
     */
    
    public int getShotsFired() {
    
    	return shotsFired;
    
    }
    /**
     * @return The number of hits.
     */
    public int getHitCount() {
        return hitCount;
    }
    
    /**
     * @return The number of sunk ships.
     */
    
    public int getShipsSunk() {
        return shipsSunk;
    }

    /**
     * @return True if the game is over, false otherwise if not 10 ships getting sunk.
     */
    
    public boolean isGameOver() {
        // Check if the game is over (all ships sunk)
        return shipsSunk == 10;
    }
    
    /**
     * @return The array (grid) of ships.
     */
    public Ship[][] getShipArray() {
        return ships;
    }

    
       
      
    /**
     * Prints the current state of the ocean grid and shows the positions of ships and shots fired..
     * '.' represents an empty sea, 'x' represents a hit on a ship, 'x' represents a sunken ship, '-' represents a miss for fired shots.
     */
   
 // Helper method to check if any element in the boolean array is true
    private boolean hasHit(boolean[] hitArray) {
        for (boolean hit : hitArray) {
            if (hit) {
                return true;
            }
        }
        return false;
    }
    
    public void print() {
    	 
    	System.out.println("  0 1 2 3 4 5 6 7 8 9");

        for (int i = 0; i < ships.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < ships[i].length; j++) {
                Ship ship = ships[i][j];
                String shipRepresentation;

                if (ship instanceof EmptySea) {
                    if (!hasHit(ship.getHit())) {
                        shipRepresentation = "- ";
                    } else {
                        shipRepresentation = ship.toString();
                    }
                } else {
                    if (hasHit(ship.getHit())) {
                        shipRepresentation = ship.toString();
                    } else {
                        shipRepresentation = "- ";
                    }
                }

                if (ship.isHorizontal()) {
                    // Check if the current position is part of the ship
                    int hitIndex = j - ship.getBowColumn();
                    if (hitIndex >= 0 && hitIndex < ship.getLength() && ship.getHit()[hitIndex]) {
                        System.out.print(shipRepresentation + " ");
                    } else {
                        System.out.print(". ");
                    }
                } else {
                    // Check if the current position is part of the ship
                    int hitIndex = i - ship.getBowRow();
                    if (hitIndex >= 0 && hitIndex < ship.getLength() && ship.getHit()[hitIndex]) {
                        System.out.print(shipRepresentation + " ");
                    } else {
                        System.out.print(". ");
                    }
                }
            }
           System.out.println();
           
        }
    }
        // Print column numbers
    	 // Print column numbers
  /*      System.out.println("  0 1 2 3 4 5 6 7 8 9");
     
        for (int i = 0; i < ships.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < ships[i].length; j++) {
                Ship ship = ships[i][j];
                
                if (ship instanceof EmptySea) {
                    if (ship.getHit()[0]) {
                        System.out.print(ship + ""); // Display "-" for hit EmptySea
                    } else {
                        System.out.print(". "); // Display "." for missed EmptySea
                    }
                } else if (ship.getHit()[0]) {
                    // If the location contains a ship (hit or sunk), use the ship's toString method
                        int hitIndex = j - ship.getBowColumn();
                        if (hitIndex >= 0 && hitIndex < ship.getLength() && ship.getHit()[hitIndex]) {
                            System.out.print(ship + "");
                        } 	else {
                            System.out.print(". ");
                        } else {
                        int hitIndex = i - ship.getBowRow();
                        if (hitIndex >= 0 && hitIndex < ship.getLength() && ship.getHit()[hitIndex]) {
                            System.out.print(ship + " ");
                        } else {
                            System.out.print(". ");
                        }
                        }
                    
                }
            System.out.println();
        }
        }
       // firstShotTaken = true;
  //  }*/

 
    /*   if (ship.isHorizontal()) {
    if (ship.getHit()[j - ship.getBowColumn()]) {
        System.out.print(ship.toString() + " ");
    	
    } else {
        System.out.print(". ");
    }
} else {
    if (ship.getHit()[i - ship.getBowRow()]) {
        System.out.print(ship.toString() + " ");
    	
    } else {
        System.out.print(". ");
    }
}*/
    
    /**
     * Checks if it's valid to place a ship at correct location.
     * 
     * @param row    The starting row for ship.
     * @param column    The starting column for the ship.
     * @param horizontal True if the ship should be placed horizontally, false otherwise.
     * @param ship     The ship to be placed.
     * @return True if it's valid to place the ship, false otherwise.
     */
    public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ship ship) {
        // Check if it's okay to place a ship at the given location
        int length = ship.getLength();
    	if (horizontal) {
            if (column + length > 10) {
                return false; // Ship would go out of bounds
            }
            for (int col = column; col < column + length; col++) {
                if (!ships[row][col].getShipType().equals("empty")) {
                    return false; // Ship overlaps with another ship
                }
            }
        } else {
            if (row + length > 10) {
                return false; // Ship would go out of bounds
            }
            for (int r = row; r < row + length; r++) {
                if (!ships[r][column].getShipType().equals("empty")) {
                    return false; // Ship overlaps with another ship
                }
            }
        }
        return true;
    }
   
    /**
     * Places a ship on the ocean at the specified location.
     * @param row  The starting row for ship.
     * @param column    The starting column for the ship.
     * @param horizontal True if the ship should be placed horizontally, false otherwise.
     * @param ship     	The ship to be placed.
     */
    public void placeShipAt(int row, int column, boolean horizontal, Ship ship) {
        // Place the ship on the ocean
        ship.setHorizontal(horizontal);
        ship.setBowRow(row);
        ship.setBowColumn(column);
        int length = ship.getLength();
        if (horizontal) {
        	for (int col = column; col < column + length; col++) {
                ships[row][col] = ship;
            }
        } else {
            for (int r = row; r < row + length; r++) {
                ships[r][column] = ship;
            }
        }
    }

  
}
		
