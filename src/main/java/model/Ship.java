package model;

import java.util.List;

/**
 * The Ship class represents a ship in the game.
 * It holds information about the ship's type, locations, sinking status, and orientation.
 */
public class Ship {
  private final ShipType type;
  private final List<Coord> locations;
  private boolean sunk;
  private final boolean vertical;

  /**
   * Instantiates a new Ship object with the specified ship type, locations, and orientation.
   *
   * @param type      the ship type
   * @param locations the list of coordinates representing the ship's locations
   * @param vertical  if the ship is oriented vertically
   */
  public Ship(ShipType type, List<Coord> locations, boolean vertical) {
    this.type = type;
    this.locations = locations;
    this.sunk = false;
    this.vertical = vertical;
  }

  /**
   * Returns the list of coordinates representing the ship's locations.
   *
   * @return the list of ship locations
   */
  public List<Coord> getLocations() {
    return locations;
  }

  /**
   * Checks if the ship is sunk.
   *
   * @return {@code true} if the ship is sunk, {@code false} otherwise
   */
  public boolean isSunk() {
    return sunk;
  }

  /**
   * Sets the ship as sunk.
   */
  public void setSunk() {
    sunk = true;
  }

}
