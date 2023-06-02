package model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class ShipTest {
  @Test
  public void testGetLocations() {
    List<Coord> locations = new ArrayList<>();
    locations.add(new Coord(0, 0));
    locations.add(new Coord(0, 1));
    locations.add(new Coord(0, 2));

    Ship ship = new Ship(ShipType.SUBMARINE, locations, true);

    assertEquals(locations, ship.getLocations());
  }

  @Test
  public void testIsSunk() {
    Ship ship = new Ship(ShipType.CARRIER, new ArrayList<>(), true);
    assertFalse(ship.isSunk());

    ship.setSunk();
    assertTrue(ship.isSunk());
  }

}