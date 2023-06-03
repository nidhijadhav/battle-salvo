package model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Represents tests that check the functionality of the Ship class.
 */
class ShipTest {

  /**
   * Tests the getLocations method of the Ship class.
   */
  @Test
  public void testGetLocations() {
    List<Coord> locations = new ArrayList<>();
    locations.add(new Coord(0, 0));
    locations.add(new Coord(0, 1));
    locations.add(new Coord(0, 2));

    Ship ship = new Ship(ShipType.SUBMARINE, locations, true);

    assertEquals(locations, ship.getLocations());
  }

  /**
   * Tests the isSunk method of the Ship class.
   */
  @Test
  public void testIsSunk() {
    Ship ship = new Ship(ShipType.CARRIER, new ArrayList<>(), true);
    assertFalse(ship.isSunk());

    ship.setSunk();
    assertTrue(ship.isSunk());
  }

}