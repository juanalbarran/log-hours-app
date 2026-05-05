package com.jarsolutions.loghours.client.input;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import com.jarsolutions.loghours.exception.LogHoursInputIsBlankException;
import org.junit.Test;

public class LogHoursInputTest {

  @Test
  public void WhenEndpointIsNull_ShouldThrowAnException() {
    whenValueIsNullOrBlank_ShouldThrowAnException(
        "endpoint cannot be null or blank", null, "jsonPayload", "encodedAuth");
  }

  @Test
  public void WhenEndpointIsBlank_ShouldThrowAnException() {
    whenValueIsNullOrBlank_ShouldThrowAnException(
        "endpoint cannot be null or blank", "", "jsonPayload", "encodedAuth");
  }

  @Test
  public void WhenJsonPayloadIsNull_ShouldThrowAnException() {
    whenValueIsNullOrBlank_ShouldThrowAnException(
        "jsonPayload cannot be null or blank", "endpoint", null, "encodedAuth");
  }

  @Test
  public void WhenJsonPayloadIsBlank_ShouldThrowAnException() {
    whenValueIsNullOrBlank_ShouldThrowAnException(
        "jsonPayload cannot be null or blank", "endpoint", "", "encodedAuth");
  }

  @Test
  public void WhenEncodedAuthIsNull_ShouldThrowAnException() {
    whenValueIsNullOrBlank_ShouldThrowAnException(
        "encodedAuth cannot be null or blank", "endpoint", "jsonPayload", null);
  }

  @Test
  public void WhenEncodedAuthIsBlank_ShouldThrowAnException() {
    whenValueIsNullOrBlank_ShouldThrowAnException(
        "encodedAuth cannot be null or blank", "endpoint", "jsonPayload", "");
  }

  private void whenValueIsNullOrBlank_ShouldThrowAnException(
      String message, String endpoint, String jsonPayload, String encodedAuth) {
    LogHoursInputIsBlankException e =
        assertThrows(
            LogHoursInputIsBlankException.class,
            () -> new LogHoursInput(endpoint, jsonPayload, encodedAuth));
    assertEquals(message, e.getMessage());
  }
}
