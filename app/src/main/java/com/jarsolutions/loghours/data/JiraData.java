package com.jarsolutions.loghours.data;

import com.jarsolutions.loghours.exception.JiraValueIsBlankException;

public record JiraData(String url, String email, String apiToken, String issue) {
  public JiraData {
    validateNotBlank(url, "url");
    validateNotBlank(email, "email");
    validateNotBlank(apiToken, "apiToken");
    validateNotBlank(issue, "issue");
  }

  private static void validateNotBlank(String value, String fieldName) {
    if (value == null || value.isBlank()) {
      throw new JiraValueIsBlankException(fieldName + " cannot be null or blank");
    }
  }
}
