package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ShipTypeTest {

  @Test
  public void testCarrierSize() {
    ShipType carrier = ShipType.CARRIER;
    int expectedSize = 6;
    assertEquals(expectedSize, carrier.getSize());
  }

  @Test
  public void testBattleshipSize() {
    ShipType battleship = ShipType.BATTLESHIP;
    int expectedSize = 5;
    assertEquals(expectedSize, battleship.getSize());
  }

  @Test
  public void testDestroyerSize() {
    ShipType destroyer = ShipType.DESTROYER;
    int expectedSize = 4;
    assertEquals(expectedSize, destroyer.getSize());
  }

  @Test
  public void testSubmarineSize() {
    ShipType submarine = ShipType.SUBMARINE;
    int expectedSize = 3;
    assertEquals(expectedSize, submarine.getSize());
  }
}
