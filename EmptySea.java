package battleship;

	public class EmptySea extends Ship {
	    // Constructor
	    public EmptySea() {
	        super(1); // Set the length for EmptySea to 1
	     
	    }

	    @Override
	    public boolean shootAt(int row, int column) {
	        // Override shootAt method to always return false
	    	
	    	
	    	 if (!this.isSunk()) {
	    	      this.getHit()[0] = true;
	    	    }
	    	    return false;
	    }
	    
	    @Override
	    public boolean isSunk() {
	        // Override isSunk method to always return false
	        return false;
	    }

	    @Override
	    public String toString() {
	    	  return "-"; 
	    }

	    @Override
	    public String getShipType() {
	        // Return "empty" for the ship type
	        return "empty";
	    }

		}

		

