package controller;

import java.util.ArrayList;
import java.util.List;
import model.Coord;
import model.ManualShotsInterface;

public class MockManualShots implements ManualShotsInterface {
  private StringBuilder output;

  MockManualShots(StringBuilder output) {
    this.output = output;
  }
  public List<Coord> getShots(){
    output.append("getShots\n");
    return new ArrayList<>();
  }
  public void addShots(int[] shots) {
    output.append("addShots\n");
  }
  public void clearShots() {
    output.append("clearShots\n");
  }
}
