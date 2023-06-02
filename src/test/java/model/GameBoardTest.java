package model;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameBoardTest {
  GameBoard board;
  Ship carrier;
  Ship battleship;
  Ship destroyer;
  Ship submarine;

  @BeforeEach
  public void setUp() {
    board = new GameBoard(6, 6);
    List<Coord> carrierLocations = Arrays.asList(
        new Coord(0, 0),
        new Coord(0, 1),
        new Coord(0, 2),
        new Coord(0, 3),
        new Coord(0, 4),
        new Coord(0, 5)
    );

    List<Coord> battleshipLocations = Arrays.asList(
        new Coord(1, 2),
        new Coord(2, 2),
        new Coord(3, 2),
        new Coord(4, 2),
        new Coord(5, 2)
    );

    List<Coord> destroyerLocations = Arrays.asList(
        new Coord(1, 4),
        new Coord(2, 4),
        new Coord(3, 4),
        new Coord(4, 4)
    );

    List<Coord> submarineLocations = Arrays.asList(
        new Coord(5, 3),
        new Coord(5, 4),
        new Coord(5, 5)
    );

    carrier = new Ship(ShipType.CARRIER, carrierLocations, true);
    battleship = new Ship(ShipType.BATTLESHIP, battleshipLocations, false);
    destroyer = new Ship(ShipType.DESTROYER, destroyerLocations, false);
    submarine = new Ship(ShipType.SUBMARINE, submarineLocations, true);

    board.placeShips(new ArrayList<>(List.of(carrier, battleship, destroyer, submarine)));

  }

  @Test
  public void testGetHeight() {
    assertEquals(6, board.getHeight());
  }

  @Test
  public void testGetWidth() {
    assertEquals(6, board.getWidth());
  }

  @Test
  public void testGetShips() {
    assertArrayEquals(new ArrayList<>(List.of(carrier, battleship, destroyer, submarine)).toArray(),
        board.getShips().toArray());
  }

  @Test
  public void testGetRemainingShipsCount() {
    assertEquals(4, board.getRemainingShipsCount());
    carrier.setSunk();
    assertEquals(3, board.getRemainingShipsCount());
  }


  @Test
  public void testUpdateBoards() {
    board.updatePlayerBoard(submarine.getLocations());
    String playerBoard =
          "\tS _ _ _ _ _ \n"
        + "\tS _ _ _ _ _ \n"
        + "\tS S S S S S \n"
        + "\tS _ _ _ _ H \n"
        + "\tS S S S S H \n"
        + "\tS _ _ _ _ H \n";
    assertEquals(playerBoard, board.playerBoardToString());

    board.updatePlayerBoard(new ArrayList<>(List.of(new Coord(5, 0))));
    playerBoard =
        "\tS _ _ _ _ M \n"
            + "\tS _ _ _ _ _ \n"
            + "\tS S S S S S \n"
            + "\tS _ _ _ _ H \n"
            + "\tS S S S S H \n"
            + "\tS _ _ _ _ H \n";
    assertEquals(playerBoard, board.playerBoardToString());

    board.updateOpponentBoard(submarine.getLocations(), Cell.M);
    String opponentBoard =
              "\t_ _ _ _ _ _ \n"
            + "\t_ _ _ _ _ _ \n"
            + "\t_ _ _ _ _ _ \n"
            + "\t_ _ _ _ _ M \n"
            + "\t_ _ _ _ _ M \n"
            + "\t_ _ _ _ _ M \n";
    assertEquals(opponentBoard, board.opponentBoardToString());

    board.updateShips();
    assertTrue(submarine.isSunk());
    assertEquals(3, board.getRemainingShipsCount());
  }

}