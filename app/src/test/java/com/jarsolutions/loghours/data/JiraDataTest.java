package com.jarsolutions.loghours.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import com.jarsolutions.loghours.exception.JiraValueIsBlankException;
import org.junit.Test;

public class JiraDataTest {
  @Test
  public void WhenUrlIsNull_ShouldThrowAnException() {
    whenValueIsNull_ShouldThrowAnException(
        "url cannot be null or blank", null, "email@email.com", "token", "issue");
  }

  @Test
  public void WhenUrlIsBlank_ShouldthrowAnException() {
    whenValueIsNull_ShouldThrowAnException(
        "url cannot be null or blank", "", "email@email.com", "token", "issue");
  }

  @Test
  public void WhenEmailIsNull_ShouldThrowAnException() {
    whenValueIsNull_ShouldThrowAnException(
        "email cannot be null or blank", "url", null, "token", "issue");
  }

  @Test
  public void WhenEmailIsBlank_ShouldThrowAnException() {
    whenValueIsNull_ShouldThrowAnException(
        "email cannot be null or blank", "url", "", "token", "issue");
  }

  @Test
  public void WhenTokenIsNull_ShouldThrowAnException() {
    whenValueIsNull_ShouldThrowAnException(
        "apiToken cannot be null or blank", "url", "email@email.com", null, "issue");
  }

  @Test
  public void WhenTokenIsBlank_ShouldThrowAnException() {
    whenValueIsNull_ShouldThrowAnException(
        "apiToken cannot be null or blank", "url", "email@email.com", "", "issue");
  }

  @Test
  public void WhenIssueIsNull_ShouldThrowAnException() {
    whenValueIsNull_ShouldThrowAnException(
        "issue cannot be null or blank", "url", "email@email.com", "token", null);
  }

  @Test
  public void WhenIssueIsBlank_ShouldThrowAnException() {
    whenValueIsNull_ShouldThrowAnException(
        "issue cannot be null or blank", "url", "email@email.com", "token", "");
  }

  private void whenValueIsNull_ShouldThrowAnException(
      String message, String url, String email, String token, String issue) {
    JiraValueIsBlankException e =
        assertThrows(JiraValueIsBlankException.class, () -> new JiraData(url, email, token, issue));
    assertEquals(message, e.getMessage());
  }
}
