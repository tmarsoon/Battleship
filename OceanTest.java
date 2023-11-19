package battleship;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OceanTest {

	Ocean ocean;
	
	static int NUM_BATTLESHIPS = 1;
	static int NUM_CRUISERS = 2;
	static int NUM_DESTROYERS = 3;
	static int NUM_SUBMARINES = 4;
	static int OCEAN_SIZE = 10;
	
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}
	
	@Test
	void testEmptyOcean() {
		
		//tests that all locations in the ocean are "empty"
		
		Ship[][] ships = ocean.getShipArray();
		
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];
				
				assertEquals("empty", ship.getShipType());
			}
		}
		
		assertEquals(0, ships[0][0].getBowRow());
		assertEquals(0, ships[0][0].getBowColumn());
		
		assertEquals(5, ships[5][5].getBowRow());
		assertEquals(5, ships[5][5].getBowColumn());
		
		assertEquals(9, ships[9][0].getBowRow());
		assertEquals(0, ships[9][0].getBowColumn());
	}
	
	@Test
	void testPlaceAllShipsRandomly() {
		
		//tests that the correct number of each ship type is placed in the ocean
		
		ocean.placeAllShipsRandomly();

		Ship[][] ships = ocean.getShipArray();
		ArrayList<Ship> shipsFound = new ArrayList<Ship>();
		
		int numBattlehips = 0;
		int numCruisers = 0;
		int numDestroyers = 0;
		int numSubmarines = 0;
		int numEmptySeas = 0;
		
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];
				if (!shipsFound.contains(ship)) {
					shipsFound.add(ship);
				}
			}
		}
		
		for (Ship ship : shipsFound) {
			if ("battleship".equals(ship.getShipType())) {		
				numBattlehips++;
			} else if ("cruiser".equals(ship.getShipType())) {
				numCruisers++;
			} else if ("destroyer".equals(ship.getShipType())) {
				numDestroyers++;
			} else if ("submarine".equals(ship.getShipType())) {
				numSubmarines++;
			} else if ("empty".equals(ship.getShipType())) {
				numEmptySeas++;
			}
		}
		
		assertEquals(NUM_BATTLESHIPS, numBattlehips);
		assertEquals(NUM_CRUISERS, numCruisers);
		assertEquals(NUM_DESTROYERS, numDestroyers);
		assertEquals(NUM_SUBMARINES, numSubmarines);
		
		//calculate total number of available spaces and occupied spaces
		int totalSpaces = OCEAN_SIZE * OCEAN_SIZE; 
		int occupiedSpaces = (NUM_BATTLESHIPS * 4)
				+ (NUM_CRUISERS * 3)
				+ (NUM_DESTROYERS * 2)
				+ (NUM_SUBMARINES * 1);
		
		//test number of empty seas, each with length of 1
		assertEquals(totalSpaces - occupiedSpaces, numEmptySeas);
	}

	@Test
	void testIsOccupied() {

		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.isOccupied(1, 5));
		
		//TODO
		//More tests
		//scenario 1
		// Place a Cruiser at coordinates (6, 2) vertically
	    Cruiser cruiser = new Cruiser();
	    cruiser.placeShipAt(6, 2, false, ocean);
	    // Check if the cell (7, 2) is unoccupied (it should be empty sea)
	    assertFalse(ocean.isOccupied(7, 2));
	    
	    //scenario 2
	    // placing a Submarine at coordinates (8, 8) horizontally
	    submarine.placeShipAt(8, 8, true, ocean);
	    // Check if the edge the bounds of the ocean (9, 8) is unoccupied
	    assertFalse(ocean.isOccupied(9, 8));
	    
	    //scenario 3
	    Battleship battleship = new Battleship();
	    battleship.placeShipAt(3, 3, true, ocean);
	    // Check if the cell (3, 3) is occupied
	    assertTrue(ocean.isOccupied(3, 3));
	
	}

	@Test
	void testShootAt() {
	
		assertFalse(ocean.shootAt(0, 1));
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertTrue(ocean.shootAt(0, 5));
		
		//TODO
		//More tests
		//Scenario 1
		//shooting at the same location should return true because your hit count increase even though you're not making progress
		Ship destroyer2 = new Destroyer();
		destroyer2.placeShipAt(2, 3, true, ocean); 
		assertTrue(ocean.shootAt(2, 3)); 
		assertTrue(ocean.shootAt(2, 3));
		//the reason the above is true is because you can hit multiple spots.
		assertTrue(ocean.shootAt(2, 2)); 
		//the reason this fails is because in the shootAt method, if the last spot is hit on a ship we expect it to sink
		//essentially your firing at an already sunken ship so it's false.
		assertFalse(ocean.shootAt(2, 2)); 
		
		//Scenario 2
		//testing the sunk function in the ShootAt method
		Ship battleship = new Battleship();
		battleship.placeShipAt(0, 5, true, ocean); 
		assertTrue(ocean.shootAt(0, 5)); 
		assertTrue(ocean.shootAt(0, 4)); 
		//sunk should be false because we only hit 2 out of 4
		assertFalse(battleship.isSunk()); 
		assertTrue(ocean.shootAt(0, 3)); 
		assertTrue(ocean.shootAt(0, 2)); 
		assertTrue(battleship.isSunk()); 
		
		//Scenario 3
		//testing sub shots
		Ship submarine = new Submarine();
	    submarine.placeShipAt(3, 3, true, ocean); 
	    //missing sub
	    assertFalse(ocean.shootAt(4, 3));
	    //missing sub
	    assertFalse(ocean.shootAt(4, 4));
	  //missing sub
	    assertFalse(ocean.shootAt(4, 2));
	    //hitting sub
	    assertTrue(ocean.shootAt(3, 3));
	    //sinking sub
	    assertTrue(submarine.isSunk()); 
	    //one sub hit/sunk so this is the 15th all together
	    assertEquals(15, ocean.getShotsFired()); 
	
	}

	@Test
	void testGetShotsFired() {
		
		//should be all false - no ships added yet
		assertFalse(ocean.shootAt(0, 1));
		assertFalse(ocean.shootAt(1, 0));
		assertFalse(ocean.shootAt(3, 3));
		assertFalse(ocean.shootAt(9, 9));
		assertEquals(4, ocean.getShotsFired());
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());
		assertEquals(6, ocean.getShotsFired());
		
		//TODO
		//More tests
		//scenario 1
		// Ensure that shooting at empty sea cells does not increase the shots fired count
	    assertFalse(ocean.shootAt(0, 1));
	    assertFalse(ocean.shootAt(1, 0));
	    assertFalse(ocean.shootAt(3, 3));
	    assertFalse(ocean.shootAt(9, 9));
	    // Check if the total shots fired count is 10
	    assertEquals(10, ocean.getShotsFired());
	    
	    //scenario 2
	    //Placing a Cruiser horizontally
	    Cruiser cruiser = new Cruiser();
	    cruiser.placeShipAt(3, 3, true, ocean);
	    //Firing multiple shots at the same spot
	    assertTrue(ocean.shootAt(3, 3));
	    assertTrue(ocean.shootAt(3, 3));
	    assertTrue(ocean.shootAt(3, 3));
	    //Checking if the total shots fired count is 3, even though multiple shots were at the same spot .total should therefore be 3 + 10(previous) shots = 13
	    assertEquals(13, ocean.getShotsFired());
	    //scenario 3
	    // Ensure that shooting out-of-bounds does not increase the shots fired count
	    assertFalse(ocean.shootAt(-1, 5));
	    assertFalse(ocean.shootAt(10, 10));
	    assertFalse(ocean.shootAt(11, -2));
	    assertFalse(ocean.shootAt(5, 11));
	    //Checking if the total shots fired count is 0 since these shots were invalid and out of bounds. So total should still be 13.
	    assertEquals(13, ocean.getShotsFired());
	}
	@Test
	void testGetHitCount() {
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		
		//TODO
		//More tests
		 //Scenario 1 
		//missing multiple missed shots
        Destroyer destroyer2 = new Destroyer();
        int row2 = 1;
        int column2 = 5;
        boolean horizontal2 = false;
        destroyer2.placeShipAt(row2, column2, horizontal2, ocean);
        // Perform multiple missed shots
        assertFalse(ocean.shootAt(2, 3));
        assertFalse(ocean.shootAt(0, 9));
        assertFalse(ocean.shootAt(4, 7));
        // Verify that hit count remains 1 for missed shots (since there is one above)
        assertEquals(1, ocean.getHitCount());
        
        
        //Scenario 2
        //Hitting different types of ships
        Destroyer destroyer3 = new Destroyer();
        int row3 = 1;
        int column3 = 5;
        boolean horizontal3 = false;
        destroyer3.placeShipAt(row3, column3, horizontal3, ocean);
        Submarine submarine = new Submarine();
        int row4 = 0;
        int column4 = 0;
        boolean horizontal4 = true;
        submarine.placeShipAt(row4, column4, horizontal4, ocean);
        //hitting different types of ships
        assertTrue(ocean.shootAt(1, 5)); // Hit Destroyer
        assertTrue(ocean.shootAt(0, 0)); // Hit Submarine
        //Verifying that hit count reflects the total number of hits, expecting 2 hits + 1 from above
        assertEquals(3, ocean.getHitCount());
        
        //Scenario 3
        //multiple shots at the same ship
        Destroyer destroyer4 = new Destroyer();
        int row5 = 1;
        int column5 = 5;
        boolean horizontal5 = false;
        destroyer4.placeShipAt(row5, column5, horizontal5, ocean);
        //Performing multiple shots at the same ship
        assertTrue(ocean.shootAt(1, 5));
        assertTrue(ocean.shootAt(1, 5));
        assertTrue(ocean.shootAt(1, 5));
        //Verify that hit count reflects the number of hits. Even the same spot counts as a hit so we expect 6 here.
        assertEquals(6, ocean.getHitCount());
		
	}
	
	
	@Test
	void testGetShipsSunk() {
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		assertEquals(0, ocean.getShipsSunk());
	
		
		//TODO
		//More tests
		//Scenario 1
		
		//Multiple ships getting sunk
        Destroyer destroyer2 = new Destroyer();
        int row1 = 1;
        int column1 = 5;
        boolean horizontal1 = false;
        destroyer2.placeShipAt(row1, column1, horizontal1, ocean);
        Submarine submarine = new Submarine();
        int row2 = 0;
        int column2 = 0;
        boolean horizontal2 = true;
        submarine.placeShipAt(row2, column2, horizontal2, ocean);
        //Sinking both the destroyer and the submarine
        assertTrue(ocean.shootAt(1, 5)); 
        assertTrue(ocean.shootAt(0, 5));
        assertTrue(ocean.shootAt(0, 0)); 
        //Verifying that two ships are sunk
        assertEquals(2, ocean.getShipsSunk());
        
        // Scenario2
        //No ships are sunk
        Destroyer destroyer3 = new Destroyer();
        int row3 = 3;
        int column3 = 8;
        boolean horizontal3 = false;
        destroyer3.placeShipAt(row3, column3, horizontal3, ocean);
        //Hitting the destroyer but not sinking it
        assertTrue(ocean.shootAt(3, 8));
        //Verifying that ship isn't sunk, but still 2 from above
        assertEquals(2, ocean.getShipsSunk());
        
        //Scenario 3
        //One sunk the other not.
        Destroyer destroyer4 = new Destroyer();
        int row4 = 1;
        int column4 = 5;
        boolean horizontal4 = false;
        destroyer4.placeShipAt(row4, column4, horizontal4, ocean);
        Submarine submarine2 = new Submarine();
        int row5 = 0;
        int column5 = 0;
        boolean horizontal5 = true;
        submarine2.placeShipAt(row5, column5, horizontal5, ocean);
        // Hitting both ships 
        assertTrue(ocean.shootAt(1, 5)); // Hit destroyer
        assertTrue(ocean.shootAt(0, 0)); // Hit submarine
        //Verify only the sub gets sunk plus still having 2 from above
        assertEquals(3, ocean.getShipsSunk());
    
	}
	

	@Test
	void testGetShipArray() {
		
		Ship[][] shipArray = ocean.getShipArray();
		assertEquals(OCEAN_SIZE, shipArray.length);
		assertEquals(OCEAN_SIZE, shipArray[0].length);
		
		assertEquals("empty", shipArray[0][0].getShipType());
		
		//TODO
		//More tests
	    //Scenario 1
		//Checking the dimensions of the ship array 
	    assertEquals(OCEAN_SIZE, shipArray.length);
	    assertEquals(OCEAN_SIZE, shipArray[0].length);
	    
	    //Scenario 2
	    //Checking the ship type of the last spot of array
	    assertEquals("empty", shipArray[9][9].getShipType());
	    
	    //Scenario 3
	    //Checking the ship type of the first spot of array
	    assertEquals("empty", shipArray[0][0].getShipType());
	
		
	}

}
