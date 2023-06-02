package model;

import java.util.List;

public class Ship {
  private final ShipType type;
  private final List<Coord> locations;
  private boolean sunk;
  private final boolean vertical;

  public Ship(ShipType type, List<Coord> locations, boolean vertical) {
    this.type = type;
    this.locations = locations;
    this.sunk = false;
    this.vertical = vertical;
  }

  public List<Coord> getLocations() {
    return locations;
  }

  public boolean isSunk() {
    return sunk;
  }

  public void setSunk() {
    sunk = true;
  }

}
