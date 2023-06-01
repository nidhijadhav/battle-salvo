package controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import model.AiPlayer;
import model.GameBoard;
import model.ManualPlayer;
import model.Ship;
import model.ShipType;
import view.SalvoView;

public class SalvoController {
  private AiPlayer ai;
  private ManualPlayer manual;
  private GameBoard aiBoard;
  private GameBoard manualBoard;
  private SalvoView view;
  private final Readable input;
  private final Appendable output;

  public SalvoController(Readable input, Appendable output) {
    this.input = input;
    this.output = output;
    this.view = new SalvoView(input, output);
  }

  public void runGame() {
    view.displayWelcomeMessage();

    int[] dimensions = validateDimensions(view.promptForDimensions());
    int width = dimensions[0];
    int height = dimensions[1];
    initialize(height, width);

    int maxFleet = Math.min(width, height);
    Map<ShipType, Integer> fleet = validateFleet(view.promptForFleet(maxFleet), maxFleet);

    List<Ship> ships = manual.setup(height, width, fleet);
    ships = ai.setup(height, width, fleet);

    System.out.println(manualBoard.playerBoardToString());
    System.out.println(aiBoard.playerBoardToString());

  }

  private int[] validateDimensions(int[] dimensions) {
    int width = dimensions[0];
    int height = dimensions[1];

    while (width < 6 || width > 15 || height < 6 || height > 15) {
      dimensions = view.promptForCorrectDimensions();
      width = dimensions[0];
      height = dimensions[1];
    }

    return dimensions;
  }

  private Map<ShipType, Integer> validateFleet(int[] sizes, int fleetSize) {
    int sum = 0;
    for (int size : sizes) {
      if (size < 1) {
        return validateFleet(view.promptForCorrectFleet(fleetSize), fleetSize);
      }
      sum += size;
    }

    if (sum > fleetSize) {
      return validateFleet(view.promptForCorrectFleet(fleetSize), fleetSize);
    }

    Map<ShipType, Integer> fleet = new HashMap<>();
    fleet.put(ShipType.CARRIER, sizes[0]);
    fleet.put(ShipType.BATTLESHIP, sizes[1]);
    fleet.put(ShipType.DESTROYER, sizes[2]);
    fleet.put(ShipType.SUBMARINE, sizes[3]);

    return fleet;
  }

  private void initialize(int height, int width) {
    aiBoard = new GameBoard(height, width);
    manualBoard = new GameBoard(height, width);

    ai = new AiPlayer("ai", new Random(), aiBoard);
    manual = new ManualPlayer("manual", new Random(), manualBoard);
  }


}
