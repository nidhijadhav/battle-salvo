package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AiPlayer extends APlayer {
  private List<Coord> shotsMade;

  public AiPlayer(String name, Random random, GameBoardInterface board) {
    super(name, random, board);
    shotsMade = new ArrayList<>();
  }


  @Override
  public List<Coord> takeShots() {
    List<Coord> shots = new ArrayList<>();

    for (int i = 0; i < board.getRemainingShipsCount(); i++) {
      Coord shot = getRandomShot();
      shots.add(shot);
    }

    board.updateOpponentBoard(shots, Cell.M);
    return shots;
  }

  private Coord getRandomShot() {
    int x = random.nextInt(board.getWidth());
    int y = random.nextInt(board.getHeight());
    Coord shot = new Coord(x, y);

    for (Coord coord : shotsMade) {
      if (coord.getX() == x && coord.getY() == y) {
        return getRandomShot();
      }
    }

    shotsMade.add(shot);
    return shot;
  }
}
