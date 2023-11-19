package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShipTest {

	Ocean ocean;
	Ship ship;
	
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}

	@Test
	void testGetLength() {
		ship = new Battleship();
		assertEquals(4, ship.getLength());
		
		//TODO
		//More tests
		//Scenario 1
		//Checking the length of Destroyer ship
		 Ship destroyer = new Destroyer();
	    assertEquals(2, destroyer.getLength());
	    
	    //Scenario 2
	    //Check the length of cruiser ship
	    Ship cruiser = new Cruiser();
	    assertEquals(3, cruiser.getLength());
	    
	    //Scenario 3
	    Ship submarine = new Submarine();
	    //Check the length of Sub ship
	    assertEquals(1, submarine.getLength());
	}

	@Test
	void testGetBowRow() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		
		//TODO
		//More tests
		//Scenario 1
		 Ship cruiser = new Cruiser();
		 int row2 = 3;
		 int column2 = 5;
		 boolean horizontal2 = false;
		 cruiser.placeShipAt(row2, column2, horizontal2, ocean);

		//Checking if the row where the bow of the cruiser would be located
		  assertEquals(row2, cruiser.getBowRow());
		  
		//Scenario 2
		  Ship submarine = new Submarine();
		  int row3 = 0;
		  int column3 = 7;
		  boolean horizontal3 = true;
		  submarine.placeShipAt(row3, column3, horizontal3, ocean);

		 //Checking the row where the bow of the sub is located, which should be the top
		  assertEquals(row3, submarine.getBowRow());
		  
		  //Scenario 3
		  Ship destroyer = new Destroyer();
		  int row4 = 2;
		  int column4 = 2;
		  boolean horizontal4 = true;
		  destroyer.placeShipAt(row4, column4, horizontal4, ocean);

		  //Checking the row where the bow of the destroyer is 
		  assertEquals(row4, destroyer.getBowRow());
		  
	}

	@Test
	void testGetBowColumn() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());	
		
		//TODO
		//More tests
		//Scenario 1
		Ship destroyer = new Destroyer();
	    int row2 = 3;
	    int column2 = 1;
	    boolean horizontal2 = true;
	    destroyer.placeShipAt(row2, column2, horizontal2, ocean);
	    //Checking the column where the bow of the destroyer is most left.
	    assertEquals(column2, destroyer.getBowColumn());
		
	    //Scenario 2
	    Ship cruiser = new Cruiser();
	    int row3 = 4;
	    int column3 = 9;
	    boolean horizontal3 = true;
	    cruiser.placeShipAt(row3, column3, horizontal3, ocean);
	    //Checking the column where the bow of the Cruiser is most right.
	    assertEquals(column3, cruiser.getBowColumn());
	    
	    //Scenario 3
	    Ship submarine = new Submarine();
	    int row4 = 1;
	    int column4 = 5;
	    boolean horizontal4 = true;
	    submarine.placeShipAt(row4, column4, horizontal4, ocean);
	    //Checking the column where the bow of the Sub is in the middle.
	    assertEquals(column4, submarine.getBowColumn());
	    
	}

	@Test
	void testGetHit() {
		ship = new Battleship();
		boolean[] hits = new boolean[4];
		assertArrayEquals(hits, ship.getHit());
		assertFalse(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);
		
		//TODO
		//More tests
		//Scenario 1
		Ship battleship2 = new Battleship();
	    //Getting hit status of Battleship
	    boolean[] hits2 = battleship2.getHit();
	    //The ship should have no hits 
	    boolean[] expectedHits = {false, false, false, false};
	    assertArrayEquals(expectedHits, hits2);
	    //Checking hit statuses
	    assertFalse(hits2[0]);
	    assertFalse(hits2[1]);
	    assertFalse(hits2[2]);
	    assertFalse(hits2[3]);
	    
	    //Scenario 2
	    Ship cruiser = new Cruiser();
	    cruiser.shootAt(1, 2); // Hit one section of the Cruiser

	    // Get the hit status of the Cruiser
	    boolean[] hits3 = cruiser.getHit();

	    // The ship should have one hit and three undamaged sections
	    boolean[] expectedHits2 = {false, false, false};
	    assertArrayEquals(expectedHits2, hits3);

	    // Check individual hit statuses
	    assertFalse(hits3[0]);
	    assertFalse(hits3[1]);
	    assertFalse(hits3[2]);
	    
	 
	  
	    
	}
	@Test
	void testGetShipType() {
		ship = new Battleship();
		assertEquals("battleship", ship.getShipType());
		
		//TODO
		//More tests
		//Scenario 1
		//sub
		ship = new Submarine();
		assertEquals("submarine", ship.getShipType());
		//Scenario 2
		//cruiser
		ship = new Cruiser();
		assertEquals("cruiser", ship.getShipType());
		//Scenario 3
		//destroyer
		ship = new Destroyer();
		assertEquals("destroyer", ship.getShipType());
		
	}
	
	@Test
	void testIsHorizontal() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertTrue(battleship.isHorizontal());
		
		//TODO
		//More tests
		
		//Scenario 1
        //Testing if the battleship is not horizontal after placed vertically
        Ship battleship2 = new Battleship();
        int row2 = 5;
        int column2 = 5;
        boolean horizontal2 = false;
        battleship2.placeShipAt(row2, column2, horizontal2, ocean);
        assertFalse(battleship2.isHorizontal());
        
        //Scenario 2
        //Testing if Destroyer is horizontal
        Ship destroyer = new Destroyer();
        int row3 = 2;
        int column3 = 3;
        boolean horizontal3 = true;
        destroyer.placeShipAt(row3, column3, horizontal3, ocean);
        assertTrue(destroyer.isHorizontal());
    
        //Scenario 3
        //Testing if Cruiser is horizontal
        Ship cruiser = new Cruiser();
        int row4 = 9;
        int column4 = 8;
        boolean horizontal4 = true;
        cruiser.placeShipAt(row4, column4, horizontal4, ocean);
        assertTrue(cruiser.isHorizontal());
	}
	
	@Test
	void testSetBowRow() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowRow(row);
		assertEquals(row, battleship.getBowRow());
		
		//TODO
		//More tests
		//Scenario 1
		//testing edge of grid for bow row
		Ship battleship2 = new Battleship();
        int row2 = 5;
        int column2 = 9;
        boolean horizontal2 = false;
        battleship2.placeShipAt(row2, column2, horizontal2, ocean);
        battleship.setBowRow(row2);
        assertEquals(row2, battleship2.getBowRow());

        //Scenario 2
        //Setting bow row to a spot on grid after placing the ship
        Ship battleship4 = new Battleship();
        int row4 = 4;
        int column4 = 4;
        boolean horizontal4 = true;
        battleship4.placeShipAt(row4, column4, horizontal4, ocean);
        // Attempting to move the bow row to a new spot
        int newRow4 = 6;
        battleship4.setBowRow(newRow4);
        //Verifying that the bow row has been updated
        assertEquals(newRow4, battleship4.getBowRow());
        
        //Scenario 3
        //Setting bow row to a spot on grid after placing the ship
        Ship cruiser = new Cruiser();
        int row3 = 8;
        int column3 = 8;
        boolean horizontal3 = true;
        cruiser.placeShipAt(row3, column3, horizontal3, ocean);
        //Attempting to move the bow row to a new spot
        int newRow3 = 2;
        cruiser.setBowRow(newRow3);
        //Verifying that the bow row has been updated
        assertEquals(newRow3, cruiser.getBowRow());
        
	    
	}

	@Test
	void testSetBowColumn() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());
		
		//TODO
		//More tests
		//Scenario 1
		Ship destroyer = new Destroyer();
	    //maximum column value allowed for Bow
		int column2 = 9; 
	    destroyer.setBowColumn(column2);
	    assertEquals(column2, destroyer.getBowColumn());
	    
	    //Scenario 2
	    Ship submarine = new Submarine();
	    //Minimum valid value for Bow
	    int column3 = 0; 
	    submarine.setBowColumn(column3);
	    assertEquals(column3, submarine.getBowColumn());
	    
	    //Scenario 3
	    //middle column
	    Ship battleship2 = new Battleship();
	    int column4 = 4;
	    battleship2.setBowColumn(column4);
	    assertEquals(column4, battleship2.getBowColumn());

	}

	@Test
	void testSetHorizontal() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setHorizontal(horizontal);
		assertTrue(battleship.isHorizontal());
		
		//TODO
		//More tests
		//Scenario 1
		Ship cruiser = new Cruiser();
	    boolean horizontal2 = true;
	    cruiser.setHorizontal(horizontal2);
	    //Changing the orientation to vertical
	    cruiser.setHorizontal(false);
	    assertFalse(cruiser.isHorizontal());
	    
	    //Scenario 2
	    Ship destroyer = new Destroyer();
	    boolean horizontal3 = false;
	    //making sure orientation is false
	    destroyer.setHorizontal(horizontal3);
	    assertFalse(destroyer.isHorizontal());
	    
	    //Scenario 3
	    Ship battleship2 = new Battleship();
	    boolean horizontal4 = false;
	    battleship2.setHorizontal(horizontal4);
	    //changing orientation to horizontal
	    battleship2.setHorizontal(true);
	    assertTrue(battleship2.isHorizontal());
	
	}

	@Test
	void testOkToPlaceShipAt() {
		
		//test when other ships are not in the ocean
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok = battleship.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "OK to place ship here.");
	
		//TODO
		//More tests
		  // Scenario 2: Placing a Cruiser in an ocean with a Battleship
        Ship cruiser = new Cruiser();
        row = 0;
        column = 5;
        horizontal = false;
        battleship.placeShipAt(0, 4, true, ocean); // Place Battleship first
        boolean ok2 = cruiser.okToPlaceShipAt(row, column, horizontal, ocean);
        assertFalse(ok2, "Not OK to place ship vertically adjacent to the right.");

        // Scenario 3: Placing a Submarine near the edge of the ocean
        Ship submarine = new Submarine();
        row = 9;
        column = 9;
        horizontal = true;
        boolean ok3 = submarine.okToPlaceShipAt(row, column, horizontal, ocean);
        assertTrue(ok3, "OK to place ship near the edge.");
    }
		
    
    
	
	
	@Test
	void testOkToPlaceShipAtAgainstOtherShipsOneBattleship() {
		
		//test when other ships are in the ocean
		
		//place first ship
		Battleship battleship1 = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok1 = battleship1.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok1, "OK to place ship here.");
		battleship1.placeShipAt(row, column, horizontal, ocean);

		//test second ship
		Battleship battleship2 = new Battleship();
		row = 1;
		column = 4;
		horizontal = true;
		boolean ok2 = battleship2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2, "Not OK to place ship vertically adjacent below.");
		
		//TODO
		//More tests
		//Scenario1: Placing Battleship, Cruiser, and Submarine, then try to place another Submarine nearby.
        Battleship battleship = new Battleship();
        Cruiser cruiser = new Cruiser();
        Submarine submarine1 = new Submarine();
        Submarine submarine2 = new Submarine();
        //Placing the ships in different locations
        battleship.placeShipAt(6, 1, false, ocean);
        cruiser.placeShipAt(2, 2, false, ocean);
        submarine1.placeShipAt(5, 5, true, ocean);
        //attempting to place another Submarine near the other ships
        boolean ok = submarine2.okToPlaceShipAt(5, 6, true, ocean);
        assertFalse(ok, "Not OK to place another Submarine nearby.");
        
        //Scenario 2:
     //	Placing the first Battleship in the ocean
        Battleship battleship3 = new Battleship();
        int row1 = 9;
        int column1 = 3;
        boolean horizontal1 = true;
        boolean ok3 = battleship3.okToPlaceShipAt(row1, column1, horizontal1, ocean);
        assertTrue(ok3, "OK the first ship here.");
        battleship3.placeShipAt(9, 3, true, ocean);
        //Attempting to place the second Battleship horizontally adjacent 
        Battleship battleship4 = new Battleship();
        boolean ok4 = battleship4.okToPlaceShipAt(8, 3, true, ocean);
        //Checking if it's not OK to place the second ship horizontally adjacent to the first one
        assertFalse(ok4, "Not OK to place ship horizontally adjacent to the first Battleship.");
        
        //Scenario 3
        //placing sub in ocean
        Submarine submarine = new Submarine();
        int row7 = 4;
        int column7 = 7;
        boolean horizontal7 = true;
        //Checking if it's okay to place down
        boolean ok7 = submarine.okToPlaceShipAt(row7, column7, horizontal7, ocean);
        //Should be okay to place the Sub here
        assertTrue(ok7, "OK to place ship at the specified location.");
        
	}

	@Test
	void testPlaceShipAt() {
		
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		assertEquals(column, battleship.getBowColumn());
		assertTrue(battleship.isHorizontal());
		
		assertEquals("empty", ocean.getShipArray()[0][0].getShipType());
		assertEquals(battleship, ocean.getShipArray()[0][1]);
		

		//TODO
		//More tests
		//Scenario 1
		Ship battleship2 = new Battleship();
		int row2 = 2;
		int column2 = 3;
		boolean horizontal2 = true;
		//Placing the Battleship horizontally
		battleship2.placeShipAt(row2, column2, horizontal2, ocean);
		//setting the ships attributes
		assertEquals(row2, battleship2.getBowRow());
		assertEquals(column2, battleship2.getBowColumn());
		assertTrue(battleship2.isHorizontal());
		//Checking that the ship occupies the correct spots on grid
		assertEquals("battleship", ocean.getShipArray()[2][3].getShipType());
		assertEquals(battleship2, ocean.getShipArray()[2][3]);
		
		//Scenario 2
		Ship cruiser = new Cruiser();
	    int row3 = 4;
	    int column3 = 5;
	    boolean horizontal3 = false;
	    //Placing the Cruiser vertically 
	    cruiser.placeShipAt(row3, column3, horizontal3, ocean);
	    //setting the ships attributes
	    assertEquals(row3, cruiser.getBowRow());
	    assertEquals(column3, cruiser.getBowColumn());
	    assertFalse(cruiser.isHorizontal());
	    //Checking that the ship occupies the correct spots on the grid
	    assertEquals("cruiser", ocean.getShipArray()[4][5].getShipType());
	    assertEquals(cruiser, ocean.getShipArray()[4][5]);
	    
	    //Scenario 3
	    Ship submarine = new Submarine();
	    int row4 = 9;
	    int column4 = 0;
	    boolean horizontal4 = true;
	    //Placing the Sub at the edge 
	    submarine.placeShipAt(row4, column4, horizontal4, ocean);
	    //setting the ships attributes
	    assertEquals(row4, submarine.getBowRow());
	    assertEquals(column4, submarine.getBowColumn());
	    assertTrue(submarine.isHorizontal());
	    //Checking that the ship occupies the correct spots on grid
	    assertEquals("submarine", ocean.getShipArray()[9][0].getShipType());
	    assertEquals(submarine, ocean.getShipArray()[9][0]);
	}

	@Test
	void testShootAt() {
		
		Ship battleship = new Battleship();
		int row = 0;
		int column = 9;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(battleship.shootAt(1, 9));
		boolean[] hitArray0 = {false, false, false, false};
		assertArrayEquals(hitArray0, battleship.getHit());
		
		//TODO
		//More tests
		//Scenario 1
	    battleship.placeShipAt(row, column, horizontal, ocean);
	    //Attempting to shoot at a location to miss
	    boolean result = battleship.shootAt(1, 9);
	    // Check if the shot result is false (missed)
	    assertFalse(result, "Missed shot.");
	    
	    //Scenario 2 
	    Ship cruiser = new Cruiser();
	    int row2 = 2;
	    int column2 = 3;
	    boolean horizontal2 = true;
	    cruiser.placeShipAt(row2, column2, horizontal2, ocean);
	    //Shooting at the Cruiser ship but miss
	    boolean result2 = cruiser.shootAt(2, 4);
	    //Checking if the shot result is false (missed) and no hits get counted
	    assertFalse(result2, "Missed shot.");
	    boolean[] hitArray = cruiser.getHit();
	    assertFalse(hitArray[0]);
	    assertFalse(hitArray[1]);
	    assertFalse(hitArray[2]);
	    
	    //Scenario 3
	    Ship destroyer = new Destroyer();
	    int row3 = 4;
	    int column3 = 6;
	    boolean horizontal3 = true;
	    destroyer.placeShipAt(row3, column3, horizontal3, ocean);
	    //Shooting at Destroyer for all hits 
	    destroyer.shootAt(4, 6);
	    destroyer.shootAt(4, 7);
	    //Attempting to shoot at the same location again
	    boolean result3 = destroyer.shootAt(4, 6);
	    //Checking if the shot result is false (already hit)
	    assertTrue(result3, "Already hit the same location.");
	    
	    //Scenario 4
	    //Testing shooting at a Submarine
        Submarine submarine = new Submarine();
        int row4 = 5;
        int column4 = 5;
        boolean horizontal4 = true;
        submarine.placeShipAt(row4, column4, horizontal4, ocean);
        //missing submarine shot
        assertFalse(submarine.shootAt(7, 6)); 
        boolean[] hitArray4 = submarine.getHit();
        boolean[] expectedHits4 = {false}; 
        assertArrayEquals(expectedHits4, hitArray4);
        //now hitting the sub
        assertTrue(submarine.shootAt(5, 5)); 
        hitArray4 = submarine.getHit();
        boolean[] expectedHitsAfterHit = {true};
        assertArrayEquals(expectedHitsAfterHit, hitArray4);
	}
	
	@Test
	void testIsSunk() {
		
		Ship submarine = new Submarine();
		int row = 3;
		int column = 3;
		boolean horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(submarine.isSunk());
		assertFalse(submarine.shootAt(5, 2));
		assertFalse(submarine.isSunk());
		
		//TODO
		//More tests
		//Scenario 1
		Ship cruiser = new Cruiser();
		int row2 = 2;
		int column2 = 1;
		boolean horizontal2 = false;
		cruiser.placeShipAt(row2, column2, horizontal2, ocean);
		//Checking if the Cruiser ship wont sink with no hits
		assertFalse(cruiser.isSunk());
		
		//Scenario 2 
		Ship battleship = new Battleship();
		int row3 = 3;
		int column3 = 4;
		boolean horizontal3 = true;
		battleship.placeShipAt(row3, column3, horizontal3, ocean);
		//shooting some shots at battleship
		battleship.shootAt(3, 4);
		battleship.shootAt(3, 5);
		//Checking if the Battleship ship is not sunk with some hits
		assertFalse(battleship.isSunk());
		
		//Scenario 3
		Ship destroyer = new Destroyer();
		int row4 = 6;
		int column4 = 2;
		boolean horizontal4 = true;
		destroyer.placeShipAt(row4, column4, horizontal4, ocean);
		//shooting at Destroyer until it sinks
		destroyer.shootAt(6, 2);
		destroyer.shootAt(6, 1);
		//checking if it actually sinks
		assertTrue(destroyer.isSunk());
		
	
	}

	@Test
	void testToString() {
		
		Ship battleship = new Battleship();
		assertEquals("x", battleship.toString());
		
		int row = 9;
		int column = 1;
		boolean horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.shootAt(9, 1);
		assertEquals("x", battleship.toString());
	
		//TODO
		//More tests
		//Scenario 1
		//testing sunk battleship
		Ship battleship2 = new Battleship();
		battleship2.placeShipAt(3, 3, true, ocean);
	    battleship2.shootAt(3, 3);
	    battleship2.shootAt(3, 2);
	    battleship2.shootAt(3, 1);
	    battleship2.shootAt(3, 0);
	    assertEquals("s", battleship2.toString());
	    
	    //Scenario 2
	    //shoot at submarine until it's sunk
	    Ship submarine = new Submarine();
	    submarine.placeShipAt(8, 8, true, ocean);
	    submarine.shootAt(8, 8);
	    assertEquals("s", submarine.toString());
	    
	    //Scenario 3
	    //making sure empty sea returns "-" for shot that hits empty sea
	    Ship emptySea = new EmptySea();
	    emptySea.shootAt(4,2);
	    assertEquals("-", emptySea.toString());
	}
	
	}
		
	


