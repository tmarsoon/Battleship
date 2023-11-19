package battleship;

	public class Submarine extends Ship {
	    // Constructor
		 private static final int LENGTH = 1;

		    public Submarine() {
		        super(LENGTH);
		    }

		    @Override
		    public String getShipType() {
		        return "submarine";
		    }

			
		}
		/*    public Submarine() {
	        super(1); // Set the length for Submarine
	    }
	   

	    @Override
	    public String getShipType() {
	        return "submarine";
	    }
	    
	}*/

