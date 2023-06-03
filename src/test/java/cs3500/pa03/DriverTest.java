package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Represents tests that check the functionality of the Driver class.
 */
class DriverTest {

  /**
   * Tests the main method of the Driver class.
   */
  @Test
  public void mainTest() {
    assertThrows(StackOverflowError.class, () -> Driver.main(new String[0]));
  }

}