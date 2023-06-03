package controller;

import java.util.ArrayList;
import java.util.List;
import model.Cell;
import model.Coord;
import model.GameBoardInterface;
import model.Ship;

public class MockGameBoard implements GameBoardInterface {

  private StringBuilder output;
  private int counter;

  MockGameBoard(StringBuilder output, int counter) {
    this.output = output;
    this.counter = counter;
  }

  public void placeShips(List<Ship> ships) {
    output.append("placeShips\n");
  }
  public void updatePlayerBoard(List<Coord> shots) {
    output.append("updatePlayerBoard\n");
  }
  public void updateShips() {
    output.append("updateShips\n");
  }
  public void updateOpponentBoard(List<Coord> shots, Cell cell) {
    output.append("updateOpponentBoard\n");
  }
  public int getRemainingShipsCount() {
    output.append("getRemainingShipsCount\n");
    if (counter != 0) {
      counter--;
      return counter;
    }
    return 0;
  }
  public String playerBoardToString() {
    output.append("playerBoardToString\n");
    return "";
  }
  public String opponentBoardToString() {
    output.append("opponentBoardToString\n");
    return "";
  }
  public int getHeight() {
    output.append("getHeight\n");
    return 1;
  }
  public int getWidth() {
    output.append("getWidth\n");
    return 1;
  }
  public List<Ship> getShips() {
    output.append("getShips\n");
    return new ArrayList<>();
  }

}
