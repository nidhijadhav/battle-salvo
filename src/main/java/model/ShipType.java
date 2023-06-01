package model;

public enum ShipType {
  CARRIER (6),
  BATTLESHIP (5),
  DESTROYER (4),
  SUBMARINE (3);

  private final int size;

  private ShipType(int size) {
    this.size = size;
  }

  public int getSize() {
    return size;
  }

}
