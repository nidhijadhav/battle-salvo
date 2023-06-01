package cs3500.pa03;

import controller.SalvoController;
import java.io.InputStreamReader;

/**
 * This is the main driver of this project.
 */
public class Driver {
  /**
   * Project entry point
   *
   * @param args - no command line args required
   */
  public static void main(String[] args) {
    System.out.println("Hello from Battle Salvo - PA03 Template Repo");
    Readable input = new InputStreamReader(System.in);
    Appendable output = System.out;
    SalvoController salvo = new SalvoController(input, output);
    salvo.runGame();

  }
}