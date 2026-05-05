package com.jarsolutions.loghours.client.input;

import com.jarsolutions.loghours.exception.LogHoursInputIsBlankException;

public record LogHoursInput(String endpoint, String jsonPayload, String encodedAuth) {
  public LogHoursInput {
    validateValue(endpoint, "endpoint");
    validateValue(jsonPayload, "jsonPayload");
    validateValue(encodedAuth, "encodedAuth");
  }

  private void validateValue(String value, String fieldName) {
    if (value == null || value.isBlank()) {
      throw new LogHoursInputIsBlankException(fieldName + " cannot be null or blank");
    }
  }
}
