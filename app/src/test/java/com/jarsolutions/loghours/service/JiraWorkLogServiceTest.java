package com.jarsolutions.loghours.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.jarsolutions.loghours.client.ClientService;
import com.jarsolutions.loghours.client.input.LogHoursInput;
import com.jarsolutions.loghours.data.JiraData;
import org.junit.Before;
import org.junit.Test;

public class JiraWorkLogServiceTest {

  private MockClientService mockClientService;
  private JiraWorkLogService jiraWorklogService;

  @Before
  public void setUp() {
    mockClientService = new MockClientService();
    jiraWorklogService = new JiraWorkLogService(mockClientService);
  }

  @Test
  public void whenLogEightHours_ShouldFormatInputCorrectly() {
    // Arrange
    JiraData data =
        new JiraData("https://mycompany.atlassian.net/", "dev@test.com", "token123", "TASK-99");
    String expectedAuth = "Bearer token123";

    // Act
    boolean result = jiraWorklogService.logEightHours(data);

    // Assert
    assertTrue("Expected service to return true when client succeeds", result);

    LogHoursInput capturedInput = mockClientService.capturedInput;
    assertNotNull("Expected ClientService to be called with an input", capturedInput);

    assertEquals(
        "Expected endpoint to be formatted and trailing slash removed",
        "https://mycompany.atlassian.net/rest/api/2/issue/TASK-99/worklog",
        capturedInput.endpoint());

    assertEquals(
        "Expected auth to be correctly formatted as Bearer token",
        expectedAuth,
        capturedInput.encodedAuth());
    String cleanPayload = capturedInput.jsonPayload().replaceAll("\\s+", "");
    assertTrue(
        "Expected payload to contain 8h timeSpent", cleanPayload.contains("\"timeSpent\":\"8h\""));
  }

  @Test
  public void whenUrlHasNoTrailingSlash_ShouldFormatEndpointCorrectly() {
    // Arrange
    JiraData data =
        new JiraData("https://mycompany.atlassian.net", "dev@test.com", "token123", "TASK-99");

    // Act
    jiraWorklogService.logEightHours(data);

    // Assert
    LogHoursInput capturedInput = mockClientService.capturedInput;
    assertEquals(
        "Expected endpoint to be formatted correctly even without trailing slash in base URL",
        "https://mycompany.atlassian.net/rest/api/2/issue/TASK-99/worklog",
        capturedInput.endpoint());
  }

  @Test
  public void whenClientServiceFails_ShouldReturnFalse() {
    // Arrange
    JiraData data = new JiraData("https://jira.com", "dev@test.com", "token123", "TASK-99");
    mockClientService.mockReturnValue = false;

    // Act
    boolean result = jiraWorklogService.logEightHours(data);

    // Assert
    assertFalse("Expected service to return false when client fails", result);
  }

  private static class MockClientService extends ClientService {
    LogHoursInput capturedInput;
    boolean mockReturnValue = true;

    @Override
    public boolean logHours(LogHoursInput logHoursInput) {
      this.capturedInput = logHoursInput;
      return mockReturnValue;
    }
  }
}
