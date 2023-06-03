package model;

import java.util.List;

public interface ManualShotsInterface {
  List<Coord> getShots();
  void addShots(int[] shots);
  void clearShots();
}
