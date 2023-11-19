package battleship;

public abstract class Ship {
	 private int bowRow;
	    private int bowColumn;
	    private int length;
	    private boolean horizontal;
	    private boolean[] hit;

	    // Default constructor
	    public Ship(int length) {
	        this.length = length;
	        this.hit = new boolean[length];
	       
	        for (int i = 0; i < length; i++) {
	            hit[i] = false;
	        }
	        }
	   
	   
	   
	    // Getters
	    /**
	     * @return The length of the ship.
	     */
	    public int getLength() {
	        return length;
	    }
	  
	    /**
	     * @return The row of the ship's bow.
	     */
	    public int getBowRow() {
	        return bowRow;
	    }
	    
	    /**
	     * @return The column of the ship's bow.
	     */
	    public int getBowColumn() {
	        return bowColumn;
	    }
	    
	    /**
	     * @return An array of boolean values indicating whether each part of the ship has been hit.
	     */
	    
	    public boolean[] getHit() {
	        return hit;
	    }
	    
	    /**
	     * @return True if the ship is placed horizontally, false if not (vertical)).
	     */

	    public boolean isHorizontal() {
	        return horizontal;
	    }

	    // Setters
	    

	    /** 
	     * @param row The row of the ship's bow.
	     */
	    
	    public void setBowRow(int row) {
	        bowRow = row;
	    }
	   
	    /**
	     * @param column The column of the ship's bow.
	     */
	    
	    public void setBowColumn(int column) {
	        bowColumn = column;
	    }
	    
	    /**
	     * @param horizontal True to place the ship horizontally, false otherwise.
	     */
	    public void setHorizontal(boolean horizontal) {
	        this.horizontal = horizontal;
	    }

	    // Abstract method to get ship type
	    public abstract String getShipType();

	    // Other methods
	    /**
	     * Checks if it's okay to place a ship at the specified position in the ocean.
	     *
	     * @param row     The starting row for ship placement.
	     * @param column    The starting column for placing the ship.
	     * @param horizontal True if the ship is to be placed horizontal, false otherwise..
	     * @param ocean    The Ocean object representing the game board.
	     * @return True if it's okay to place the ship at the given position, false otherwise.
	     */
	    public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
	    	

	    	    if (horizontal && (column - length + 1 < 0) ) {
	    	    	return false;
	    	    } else if (!horizontal && (row - length + 1 <0)) {
	    	    	return false;
	    	    }
	    	    
	    	    Ship[][] torpedo = ocean.getShipArray();
	    	    
	    	    int lRow, lCol, hRow, hCol;
	    	    
	    	    if (horizontal) {
	    	    	lRow = (row > 0) ? row-1 : row;
	    	    	lCol = ((column - length) >0) ? column - length : 0;
	    	    	hRow = (row<9) ? row + 1 : row;
	    	    	hCol = (column < 9) ? column + 1 : column;
	    	    } else {
	    	    	lRow = ((row - length) > 0) ? row - length : 0;
	    	    	lCol = (column >0) ? column - 1: column;
	    	    	hRow = (row<9) ? row + 1 : row;
	    	    	hCol = (column < 9) ? column + 1 : column;
	    	    }
	    	    	for (int i = lRow; i <= hRow; i++) {
	    	    		for (int j = lCol; j <= hCol; j++) {
	    	    			
	    	    			if (!(torpedo[i][j] instanceof EmptySea)) {
	    	    				return false;
	    	    			}
	    	    		}
	    	    	}
	    	    		return true;
	    	    }
	    
	    /**
	     * Checks if the given row and column values represent a valid position on the game board.
	     *
	     * @param row The row value to check.
	     * @param col The column value to check.
	     * @return True if the position is valid, false otherwise.
	     */
	    	private boolean isValidPosition(int row, int col) {
	    	    return row >= 0 && row < 10 && col >= 0 && col < 10;
	    	}
	

	    	/**
	    	 * Place the ship at the specified position in the ocean.
	    	 *
	    	 * @param row     The starting row for ship.
	    	 * @param column    The starting column for placing the ship.
	    	 * @param horizontal True if the ship is to be placed horizontally, false otherwise..
	    	 * @param ocean    The Ocean object representing the game board or grid.
	    	 * @throws IllegalArgumentException If position i soput-of-bounds.
	    	 */
	    	public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
	    	// Place the ship on the ocean
	    	 
	    	
	    	  	if (!isValidPosition(row, column)) {
	            throw new IllegalArgumentException("Invalid position: row and column values must be within the bounds of the ocean.");
	        }
	    	
	    	
	      
	    	
	    	
	    	// Check if it's okay to place the ship
	    	  		//if (okToPlaceShipAt(row, column, horizontal, ocean)) {
	    	        this.bowRow = row;
	    	        this.bowColumn = column;
	    	        this.horizontal = horizontal;
	    	        
	    	      Ship[][] cruzer = ocean.getShipArray();
	    	      
	    	      for ( int i=0; i<length; i++) {
	    	    	  int cruzerRow = horizontal ? row : row - i;
	    	    	  int cruzerColumn = horizontal ? column - i: column;
	    	    	  cruzer[cruzerRow][cruzerColumn] = this;
	    	      }
	    
	    	  }
	    	
	    	/**
	    	 * Attempting to shoot at the ship.
	    	 *
	    	 * @param row  The row of the shot.
	    	 * @param column The column of the shot.
	    	 * @return True if the shot is a hit, false otherwise.
	    	 */
	    public boolean shootAt(int row, int column) {
	    	 // Check if the ship is sunk
	   
	    	
	    	
	    	if (this.isSunk()) {
	    	        return false;
	    	    }

	    	    // Check if the coordinates are within the ship's placement
	    	    if (horizontal) {
	    	    	if (row == this.bowRow && column <= this.bowColumn) {
	    	            // Check if the shot has already hit this part of the ship
	    	           this.hit[this.bowColumn-column] = true;
	    	                return true; // It's a hit
	    	            }
	    	        } else {
	    	        // Check if the shot is within the ship's v
	    	        if (column == this.bowColumn && row <= this.bowRow) {
	    	            // Check if the shot has already hit this part of the ship
	    	          this.hit[this.bowRow - row]=true;
	    	                return true; // It's a hit
	    	            }
	    	        }
	    	    
	    	   
	    	    return false;
	    	}
	  
	    /**
	     * Checkin if the ship is sunk.
	     * @return True if all parts of the ship have been hit, false otherwise.
	     */
	  
	    public boolean isSunk() {
	    	
	    	for (boolean partHit : hit) {
	            if (!partHit) {
	                return false;
	            }
	        }
	        return true;
	    }
	
	    /**
	     * @return "s" if the ship is sunk, "x" if it's not.
	     */
	    @Override
	    public String toString() {
	    	if (isSunk()) {
	    	   return "s";
	       } else {
	    	   return "x";
	       }
	    }
}
	       


		
	    
	      

	
	

