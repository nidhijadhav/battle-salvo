package cs3500.pa03;

import controller.SalvoController;
import java.io.InputStreamReader;
import view.SalvoView;

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
    Readable input = new InputStreamReader(System.in);
    Appendable output = System.out;
    SalvoView view = new SalvoView(input, output);
    SalvoController salvo = new SalvoController(view);
    salvo.runGame(false);

  }
}