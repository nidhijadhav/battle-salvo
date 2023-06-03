package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests that check the functionality of the AiPlayer class.
 */
class AiPlayerTest {
  private GameBoard board;
  private AiPlayer aiPlayer;
  private Ship carrier;
  private Ship battleship;
  private Ship destroyer;
  private Ship submarine;
  private final List<Coord> carrierLocations = Arrays.asList(new Coord(0, 0),
      new Coord(0, 1), new Coord(0, 2), new Coord(0, 3), new Coord(0, 4),
      new Coord(0, 5));

  private final List<Coord> battleshipLocations = Arrays.asList(new Coord(1, 2),
      new Coord(2, 2), new Coord(3, 2), new Coord(4, 2), new Coord(5, 2));

  private final List<Coord> destroyerLocations = Arrays.asList(new Coord(1, 4),
      new Coord(2, 4), new Coord(3, 4), new Coord(4, 4));

  private final List<Coord> submarineLocations = Arrays.asList(new Coord(5, 3),
      new Coord(5, 4), new Coord(5, 5));

  /**
   * Sets up the test environment by creating ships and a game board.
   */
  @BeforeEach
  public void setUp() {
    carrier = new Ship(ShipType.CARRIER, carrierLocations, true);
    battleship = new Ship(ShipType.BATTLESHIP, battleshipLocations, false);
    destroyer = new Ship(ShipType.DESTROYER, destroyerLocations, false);
    submarine = new Ship(ShipType.SUBMARINE, submarineLocations, true);

    board = new GameBoard(6, 6);
    aiPlayer = new AiPlayer("AI Player", new Random(123), board);

    board.placeShips(new ArrayList<>(List.of(carrier, battleship, destroyer, submarine)));

  }

  /**
   * Tests the takeShots method of the AiPlayer class.
   */
  @Test
  public void testTakeShots() {
    List<Coord> shots = aiPlayer.takeShots();

    assertEquals(board.getRemainingShipsCount(), shots.size());

    for (Coord shot : shots) {
      assertFalse(shot.getX() < 0 || shot.getX() >= board.getWidth()
          || shot.getY() < 0 || shot.getY() >= board.getHeight());
    }

    String opponentBoard =
              "\t_ _ _ _ _ _ \n"
            + "\tM _ _ _ _ _ \n"
            + "\t_ _ M _ _ _ \n"
            + "\t_ _ _ _ _ _ \n"
            + "\t_ _ _ _ _ _ \n"
            + "\t_ _ M M _ _ \n";

    assertEquals(opponentBoard, board.opponentBoardToString());

  }

}