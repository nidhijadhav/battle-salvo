package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public abstract class APlayer implements Player {
  protected String name;

  protected Random random;
  protected GameBoard board;

  APlayer(String name, Random random, GameBoard board) {
    this.name = name;
    this.random = random;
    this.board = board;
  }

  public String name() {
    return name;
  }

  public List<Ship> setup(int height, int width, Map<ShipType, Integer> specifications) {
    List<Ship> ships = new ArrayList<>();

    for (Map.Entry<ShipType, Integer> entry : specifications.entrySet()) {
      ShipType shipType = entry.getKey();
      int count = entry.getValue();

      for (int i = 0; i < count; i++) {
        Ship ship = generateShip(shipType, height, width, ships);
        ships.add(ship);
      }
    }

    board.placeShips(ships);
    return ships;
  }

  private Ship generateShip(ShipType type, int height, int width, List<Ship> existingShips) {
    List<Coord> locations = new ArrayList<>();
    int size = type.getSize();

    int x = random.nextInt(width);
    int y = random.nextInt(height);
    boolean vertical = random.nextBoolean();

    while (locations.size() < type.getSize()) {
      Coord coord = new Coord(x, y);

      if (outOfBounds(coord, height, width) || overlaps(coord, existingShips)) {
        return generateShip(type, height, width, existingShips);
      }
      locations.add(coord);

      if (vertical) {
        y++;
      } else {
        x++;
      }
    }

    return new Ship(type, locations, vertical);
  }

  private boolean outOfBounds(Coord coord, int length, int width) {
    int x = coord.getX();
    int y = coord.getY();

    return x < 0 || x >= width || y < 0 || y >= length;
  }

  private boolean overlaps(Coord coord, List<Ship> existingShips) {
    for (Ship ship : existingShips) {
      for (Coord location : ship.getLocations()) {
        if (location.getX() == coord.getX() && location.getY() == coord.getY()) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public abstract List<Coord> takeShots();

  @Override
  public List<Coord> reportDamage(List<Coord> opponentShotsOnBoard) {
    List<Coord> hits = new ArrayList<>();

    for (Coord shot : opponentShotsOnBoard) {
      int x = shot.getX();
      int y = shot.getY();

      if (board.getPlayerCell(x, y) == Cell.S) {
        hits.add(shot);
      }
    }
    board.updatePlayerBoard(opponentShotsOnBoard);
    return hits;
  }

  @Override
  public void successfulHits(List<Coord> shotsThatHitOpponentShips) {
    board.updateOpponentBoard(shotsThatHitOpponentShips, Cell.H);
  }

  @Override
  public void endGame(GameResult result, String reason) {

  }
}
