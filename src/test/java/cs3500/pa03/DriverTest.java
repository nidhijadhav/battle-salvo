package cs3500.pa03;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class DriverTest {

  @Test
  public void mainTest() {
    assertThrows(StackOverflowError.class, () -> Driver.main(new String[0]));
  }

}