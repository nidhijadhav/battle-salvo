package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SalvoView {
  private final Readable input;
  private final Appendable output;

  public SalvoView(Readable input, Appendable output) {
    this.input = input;
    this.output = output;
  }

  public void displayWelcomeMessage() {
    printLine("Hello! Welcome to the OOD BattleSalvo Game!");
  }

  public int[] promptForDimensions() {
    printLine("Please enter a valid height and width below:");
    printBreakLine();

    try {
      return readIntegers(2);
    } catch (IllegalArgumentException e) {
      return promptForCorrectDimensions();
    }

  }

  public int[] promptForCorrectDimensions() {
    printLine("Uh Oh! You've entered invalid dimensions. Please remember that the height\n" +
              "and width of the game must be in the range (6, 15), inclusive. Try again!");
    printBreakLine();

    try {
      return readIntegers(2);
    } catch (IllegalArgumentException e) {
      return promptForCorrectDimensions();
    }
  }

  public int[] promptForFleet(int expectedSize) {
    printLine("Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].");
    printLine("Remember, your fleet may not exceed size " + expectedSize + ".");
    printBreakLine();

    try {
      return readIntegers(4);
    } catch (IllegalArgumentException e) {
      return promptForCorrectFleet(expectedSize);
    }
  }

  public int[] promptForCorrectFleet(int expectedSize) {
    printLine("Uh Oh! You've entered an invalid fleet. Please try again!");
    return promptForFleet(expectedSize);

  }

  private int[] readIntegers(int expectedCount) {
    Scanner sc = new Scanner(input);
    List<Integer> integers = new ArrayList<>();

    while (integers.size() < expectedCount && sc.hasNextLine()) {
      String line = sc.nextLine();
      Scanner lineScanner = new Scanner(line);
      while (lineScanner.hasNextInt()) {
        integers.add(lineScanner.nextInt());
      }
    }

    if (integers.size() != expectedCount) {
      throw new IllegalArgumentException();
    }

    return integers.stream().mapToInt(Integer::intValue).toArray();
  }

  private void print(String message) {
    try {
      output.append(message);
    } catch (IOException e) {
      throw new RuntimeException();
    }
  }

  private void printLine(String line) {
    print(line + System.lineSeparator());
  }

  private void printLine() {
    print(System.lineSeparator());
  }

  private void printBreakLine() {
    printLine("------------------------------------------------------");
  }

}
