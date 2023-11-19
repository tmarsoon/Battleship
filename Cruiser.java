package battleship;

	public class Cruiser extends Ship {
	    // Constructor
		
		    private static final int LENGTH = 3;

		    public Cruiser() {
		        super(LENGTH);
		    }

		    @Override
		    public String getShipType() {
		        return "cruiser";
		    }

			
		}
		/*	    public Cruiser() {
	        super(3); // Set the length for Cruiser
	    }
	  

	    @Override
	    public String getShipType() {
	        return "cruiser";
	    }
	   
	}*/

