package model;

import java.util.List;

public interface GameBoardInterface {
  void placeShips(List<Ship> ships);
  void updatePlayerBoard(List<Coord> shots);
  void updateShips();
  void updateOpponentBoard(List<Coord> shots, Cell cell);
  int getRemainingShipsCount();
  String playerBoardToString();
  String opponentBoardToString();
  int getHeight();
  int getWidth();
  List<Ship> getShips();
}
