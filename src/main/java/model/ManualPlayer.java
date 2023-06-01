package model;

import java.util.List;
import java.util.Random;

public class ManualPlayer extends APlayer {

  public ManualPlayer(String name, Random random, GameBoard board) {
    super(name, random, board);
  }

  @Override
  public List<Coord> takeShots() {
    return null;
  }
}
