package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CoordTest {

  @Test
  public void testGetters() {
    Coord coord = new Coord(3, 5);

    assertEquals(3, coord.getX());
    assertEquals(5, coord.getY());
  }

}