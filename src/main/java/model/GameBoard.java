package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameBoard {

  private final int height;
  private final int width;
  private final Cell[][] playerBoard;
  private final Cell[][] opponentBoard;
  private List<Ship> ships;

  public GameBoard(int height, int width) {
    this.height = height;
    this.width = width;
    this.playerBoard = new Cell[height][width];
    this.opponentBoard = new Cell[height][width];

    for (Cell[] row : playerBoard) {
      Arrays.fill(row, Cell.O);
    }

    for (Cell[] row : opponentBoard) {
      Arrays.fill(row, Cell.O);
    }

  }

  public void placeShips(List<Ship> ships) {
    for (Ship ship : ships) {
      List<Coord> locations = ship.getLocations();
      for (Coord coord : locations) {
        int x = coord.getX();
        int y = coord.getY();
        playerBoard[y][x] = Cell.S;
      }
    }

    this.ships = ships;
  }

  public void updatePlayerBoard(List<Coord> shots) {
    for (Coord shot : shots) {
      int x = shot.getX();
      int y = shot.getY();

      if (playerBoard[y][x] == Cell.S) {
        playerBoard[y][x] = Cell.H;
      } else {
        playerBoard[y][x] = Cell.M;
      }
    }

  }

  public void updateShips() {
    for (Ship ship : ships) {
      boolean allLocationsHit = true;

      for (Coord location : ship.getLocations()) {
        int x = location.getX();
        int y = location.getY();

        if (playerBoard[y][x] != Cell.H) {
          allLocationsHit = false;
          break;
        }
      }

      if (allLocationsHit) {
        ship.setSunk();
      }
    }
  }

  public void updateOpponentBoard(List<Coord> shots, Cell cell) {
    for (Coord shot : shots) {
      int x = shot.getX();
      int y = shot.getY();
      opponentBoard[y][x] = cell;
    }
  }

  public int getRemainingShipsCount() {
    int count = 0;
    for (Ship ship : ships) {
      if (!ship.isSunk()) {
        count++;
      }
    }
    return count;
  }

  public String playerBoardToString() {
    return boardToString(playerBoard);
  }
  public String opponentBoardToString() {
    return boardToString(opponentBoard);
  }
  private String boardToString(Cell[][] cells) {
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < height; i++) {
      sb.append("\t");
      for (int j = 0; j < width; j++) {
        if (cells[i][j] == Cell.O) {
          sb.append("_").append(" ");
        } else {
          sb.append(cells[i][j].toString()).append(" ");
        }
      }
      sb.append("\n");
    }

    return sb.toString();
  }

  public int getHeight() {
    return height;
  }

  public int getWidth() {
    return width;
  }

  public List<Ship> getShips() {
    return ships;
  }

}
