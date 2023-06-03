package model;

import java.util.List;
import java.util.Random;

public class ManualPlayer extends APlayer {

  private final ManualShotsInterface manualShots;

  public ManualPlayer(String name, Random random, GameBoardInterface board, ManualShotsInterface manualShots) {
    super(name, random, board);
    this.manualShots = manualShots;
  }

  @Override
  public List<Coord> takeShots() {
    board.updateOpponentBoard(manualShots.getShots(), Cell.M);
    return manualShots.getShots();
  }
}
