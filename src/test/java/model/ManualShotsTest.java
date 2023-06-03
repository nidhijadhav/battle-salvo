package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ManualShotsTest {
  private ManualShots manualShots;

  @BeforeEach
  public void setUp() {
    manualShots = new ManualShots();
  }

  @Test
  public void testAddShots() {
    int[] shots = {1, 2, 3, 4, 5, 6};
    manualShots.addShots(shots);

    List<Coord> actualShots = manualShots.getShots();
    assertEquals(3, actualShots.size());
    assertEquals(1, actualShots.get(0).getX());
    assertEquals(2, actualShots.get(0).getY());
    assertEquals(3, actualShots.get(1).getX());
    assertEquals(4, actualShots.get(1).getY());
    assertEquals(5, actualShots.get(2).getX());
    assertEquals(6, actualShots.get(2).getY());
  }

  @Test
  public void testClearShots() {
    int[] shots = {1, 2, 3, 4};
    manualShots.addShots(shots);
    assertEquals(2, manualShots.getShots().size());

    manualShots.clearShots();

    assertEquals(0, manualShots.getShots().size());
  }

}