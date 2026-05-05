package com.jarsolutions.loghours;

import static org.junit.Assert.*;

import org.junit.Test;

public class MainTest {
  @Test
  public void appHasAGreeting() {
    Main classUnderTest = new Main();
    assertNotNull("app should have a greeting", classUnderTest.getGreeting());
  }
}
