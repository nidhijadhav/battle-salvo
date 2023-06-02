package controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import model.AiPlayer;
import model.Coord;
import model.GameBoard;
import model.GameResult;
import model.ManualPlayer;
import model.ManualShots;
import model.Ship;
import model.ShipType;
import view.SalvoView;

public class SalvoController {
  private AiPlayer ai;
  private ManualPlayer manual;
  private ManualShots ms = new ManualShots();
  private GameBoard aiBoard;
  private GameBoard manualBoard;
  private SalvoView view;
  private GameResult result;
  private int height;
  private int width;

  public SalvoController(Readable input, Appendable output) {
    this.view = new SalvoView(input, output);
  }

  public void runGame() {
    view.displayWelcomeMessage();

    int[] dimensions = validateDimensions(view.promptForDimensions());
    height = dimensions[0];
    width = dimensions[1];
    initialize(height, width);

    int maxFleet = Math.min(width, height);
    Map<ShipType, Integer> fleet = validateFleet(view.promptForFleet(maxFleet), maxFleet);

    manual.setup(height, width, fleet);
    ai.setup(height, width, fleet);

    boolean gameOver = false;

    while (!gameOver) {
      gameOver = playRound();
    }

    view.displayEndGame(result);
  }

  private boolean playRound() {
    ms.addShots(validateShots(view.promptForShots(manualBoard,
        manualBoard.getRemainingShipsCount()), height, width));
    List<Coord> manualShots = manual.takeShots();
    List<Coord> aiShots = ai.takeShots();
    List<Coord> successfulManualShots = ai.reportDamage(manualShots);
    List<Coord> successfulAiShots = manual.reportDamage(aiShots);
    manual.successfulHits(successfulManualShots);
    ai.successfulHits(successfulAiShots);

    if (manualBoard.getRemainingShipsCount() == 0 || aiBoard.getRemainingShipsCount() == 0) {
      setGameResult();
      return true;
    }

    ms.clearShots();
    return false;
  }

  private void setGameResult() {
    if (manualBoard.getRemainingShipsCount() == 0 && aiBoard.getRemainingShipsCount() == 0) {
      result = GameResult.DRAW;
    } else if (manualBoard.getRemainingShipsCount() == 0) {
      result = GameResult.LOSE;
    } else {
      result = GameResult.WIN;
    }
  }

  private int[] validateDimensions(int[] dimensions) {
    int h = dimensions[0];
    int w = dimensions[1];

    while (w < 6 || w > 15 || h < 6 || h > 15) {
      dimensions = view.promptForCorrectDimensions();
      h = dimensions[0];
      w = dimensions[1];
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

  private int[] validateShots(int[] shots, int height, int width) {
    for (int i = 0; i < shots.length; i = i + 2) {
      int x = shots[i];
      int y = shots[i + 1];

      if (x < 0 || x >= width || y < 0 || y >= height) {
        return validateShots(view.promptForShots(manualBoard,
            manualBoard.getRemainingShipsCount()), height, width);
      }
    }

    return shots;
  }

  private void initialize(int height, int width) {
    aiBoard = new GameBoard(height, width);
    manualBoard = new GameBoard(height, width);

    ai = new AiPlayer("ai", new Random(), aiBoard);
    manual = new ManualPlayer("manual", new Random(), manualBoard, ms);
  }


}
