package com.jarsolutions.loghours.exception;

public class JiraValueIsBlankException extends IllegalArgumentException {
  public JiraValueIsBlankException(String message) {
    super(message);
  }
}
