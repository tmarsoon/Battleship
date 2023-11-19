package battleship;

	public class Destroyer extends Ship {
	    // Constructor
		private static final int LENGTH = 2;

	    public Destroyer() {
	        super(LENGTH);
	    }

	    @Override
	    public String getShipType() {
	        return "destroyer";
	    }

		
	    
	    
	}
		/*    public Destroyer() {
	        super(2); // Set the length for Destroyer
	    }

	    @Override
	    public String getShipType() {
	        return "destroyer";
	    }

	    
	
	}*/

