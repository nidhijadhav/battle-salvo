package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.MockReadable;
import view.SalvoView;

class SalvoControllerTest {
  private MockReadable input;
  private StringBuilder viewOutput;
  private StringBuilder output;
  private MockManualShots mockManualShots;
  private MockPlayer mockAi;
  private MockPlayer mockManual;
  private MockGameBoard mockManualBoard;
  private MockGameBoard mockAiBoard;
  private SalvoView view;
  private SalvoController controller;

  @BeforeEach
  public void setup() {
    input = new MockReadable(new ArrayList<>());
    input.setInput(new ArrayList<>(List.of("6 20\n", "3 6\n", "6 3\n", "20 6\n", "6 6\n",
        "5 5 1 1\n", "0 5 1 1\n", "3 1 1 1\n", "-1 3\n", "10 3\n", "6 3", "3 -1\n", "3 10\n",
        "3 6\n", "1 1\n", "1 1\n", "1 1\n", "1 1\n", "1 1\n", "1 1\n", "1 1\n", "1 1\n", "1 1\n")));
    viewOutput = new StringBuilder();
    view = new SalvoView(input, viewOutput);
    output = new StringBuilder();
    mockManualShots = new MockManualShots(output);
    mockAi = new MockPlayer(output);
    mockManual = new MockPlayer(output);

  }

  @Test
  public void testRunGameWin() {
    mockManualBoard = new MockGameBoard(output, 6);
    mockAiBoard = new MockGameBoard(output, 0);
    controller = new SalvoController(view, mockManualShots, mockAi, mockManual, mockAiBoard,
        mockManualBoard);
    controller.runGame(true);
    System.out.println(output);
    String log = "setup\n" +
        "setup\n" +
        "clearShots\n" +
        "getRemainingShipsCount\n" +
        "opponentBoardToString\n" +
        "playerBoardToString\n" +
        "opponentBoardToString\n" +
        "playerBoardToString\n" +
        "addShots\n" +
        "takeShots\n" +
        "takeShots\n" +
        "reportDamage\n" +
        "reportDamage\n" +
        "successfulHits\n" +
        "successfulHits\n" +
        "getRemainingShipsCount\n" +
        "getRemainingShipsCount\n" +
        "getRemainingShipsCount\n" +
        "getRemainingShipsCount\n" +
        "getRemainingShipsCount\n";
    assertEquals(log, output.toString());
  }

  @Test
  public void testRunGameLose() {
    mockManualBoard = new MockGameBoard(output, 4);
    mockAiBoard = new MockGameBoard(output, 6);
    controller = new SalvoController(view, mockManualShots, mockAi, mockManual, mockAiBoard,
        mockManualBoard);
    controller.runGame(true);
    System.out.println(output);
    String log = "setup\n"
        + "setup\n"
        + "clearShots\n"
        + "getRemainingShipsCount\n"
        + "opponentBoardToString\n"
        + "playerBoardToString\n"
        + "opponentBoardToString\n"
        + "playerBoardToString\n"
        + "getRemainingShipsCount\n"
        + "opponentBoardToString\n"
        + "playerBoardToString\n"
        + "addShots\n"
        + "takeShots\n"
        + "takeShots\n"
        + "reportDamage\n"
        + "reportDamage\n"
        + "successfulHits\n"
        + "successfulHits\n"
        + "getRemainingShipsCount\n"
        + "getRemainingShipsCount\n"
        + "clearShots\n"
        + "getRemainingShipsCount\n"
        + "opponentBoardToString\n"
        + "playerBoardToString\n"
        + "addShots\n"
        + "takeShots\n"
        + "takeShots\n"
        + "reportDamage\n"
        + "reportDamage\n"
        + "successfulHits\n"
        + "successfulHits\n"
        + "getRemainingShipsCount\n"
        + "getRemainingShipsCount\n"
        + "getRemainingShipsCount\n"
        + "getRemainingShipsCount\n";
    assertEquals(log, output.toString());
  }

  @Test
  public void testRunGameDraw() {
    mockManualBoard = new MockGameBoard(output, 3);
    mockAiBoard = new MockGameBoard(output, 1);
    controller = new SalvoController(view, mockManualShots, mockAi, mockManual, mockAiBoard,
        mockManualBoard);
    controller.runGame(true);
    System.out.println(output);
    String log = "setup\n"
        + "setup\n"
        + "clearShots\n"
        + "getRemainingShipsCount\n"
        + "opponentBoardToString\n"
        + "playerBoardToString\n"
        + "getRemainingShipsCount\n"
        + "opponentBoardToString\n"
        + "playerBoardToString\n"
        + "opponentBoardToString\n"
        + "playerBoardToString\n"
        + "getRemainingShipsCount\n"
        + "opponentBoardToString\n"
        + "playerBoardToString\n"
        + "addShots\n"
        + "takeShots\n"
        + "takeShots\n"
        + "reportDamage\n"
        + "reportDamage\n"
        + "successfulHits\n"
        + "successfulHits\n"
        + "getRemainingShipsCount\n"
        + "getRemainingShipsCount\n"
        + "getRemainingShipsCount\n";
    assertEquals(log, output.toString());
  }



}