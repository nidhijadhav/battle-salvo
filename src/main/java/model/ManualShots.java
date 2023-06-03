package model;

import java.util.ArrayList;
import java.util.List;

public class ManualShots implements ManualShotsInterface {
  private List<Coord> shots = new ArrayList<>();

  public List<Coord> getShots() {
    return shots;
  }

  public void addShots(int[] shots) {
    for (int i = 0; i < shots.length; i += 2) {
      int x = shots[i];
      int y = shots[i + 1];
      this.shots.add(new Coord(x, y));
    }
  }

  public void clearShots() {
    this.shots = new ArrayList<>();
  }


}
