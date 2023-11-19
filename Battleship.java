package battleship;

	public class Battleship extends Ship {
		 private static final int LENGTH = 4;

		    public Battleship() {
		        super(LENGTH);
		    }

		    @Override
		    public String getShipType() {
		        return "battleship";
		    }

		
		}
		/*  // Constructor
	    public Battleship() {
	        super(4); // Set the length for Battleship
	    }
	  

	    @Override
	    public String getShipType() {
	        return "battleship";
	    }
	 
	}*/


