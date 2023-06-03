package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.Coord;
import model.GameResult;
import model.Player;
import model.Ship;
import model.ShipType;

public class MockPlayer implements Player {
  StringBuilder output;

  MockPlayer(StringBuilder output) {
    this.output = output;
  }

  public String name() {
    output.append("name\n");
    return "";
  }
  public List<Ship> setup(int height, int width, Map<ShipType, Integer> specifications) {
    output.append("setup\n");
    return new ArrayList<>();
  }

  public List<Coord> takeShots() {
    output.append("takeShots\n");
    return new ArrayList<>();
  }

  public List<Coord> reportDamage(List<Coord> opponentShotsOnBoard) {
    output.append("reportDamage\n");
    return new ArrayList<>();
  }

  public void successfulHits(List<Coord> shotsThatHitOpponentShips) {
    output.append("successfulHits\n");
  }

  public void endGame(GameResult result, String reason) {
    output.append("endGame\n");
  }
}
