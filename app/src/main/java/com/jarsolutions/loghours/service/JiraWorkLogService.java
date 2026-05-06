package com.jarsolutions.loghours.service;

import com.jarsolutions.loghours.client.ClientService;
import com.jarsolutions.loghours.client.input.LogHoursInput;
import com.jarsolutions.loghours.data.JiraData;

public class JiraWorkLogService {
  private final ClientService clientService;

  public JiraWorkLogService(ClientService clientService) {
    this.clientService = clientService;
  }

  public boolean logEightHours(JiraData jiraData) {
    String endpoint = formatEndpoint(jiraData);
    String encodedAuth = "Bearer " + jiraData.apiToken();
    String jsonPayload =
        """
        {
          "timeSpent": "8h",
          "comment": ""
        }
        """;
    LogHoursInput input = new LogHoursInput(endpoint, jsonPayload, encodedAuth);

    return clientService.logHours(input);
  }

  private String formatEndpoint(JiraData data) {
    String url =
        data.url().endsWith("/") ? data.url().substring(0, data.url().length() - 1) : data.url();
    return String.format("%s/rest/api/2/issue/%s/worklog", url, data.issue());
  }
}
