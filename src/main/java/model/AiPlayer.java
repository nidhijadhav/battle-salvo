package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AiPlayer extends APlayer{

  public AiPlayer(String name, Random random, GameBoard board) {
    super(name, random, board);
  }


  @Override
  public List<Coord> takeShots() {
    List<Coord> shots = new ArrayList<>();

    for (Ship ship : board.getShips()) {
      if (!ship.isSunk()) {
        shots.add(getRandomShot());
      }
    }

    return shots;
  }

  private Coord getRandomShot() {
    int x = random.nextInt(board.getWidth());
    int y = random.nextInt(board.getHeight());
    Coord shot = new Coord(x, y);
    while (board.getOpponentCell(x, y).equals(Cell.M) ||
        board.getOpponentCell(x, y).equals(Cell.H)) {
      x = random.nextInt(board.getWidth());
      y = random.nextInt(board.getHeight());
      shot = new Coord(x, y);
    }

    return shot;
  }
}
