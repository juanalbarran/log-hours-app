package com.jarsolutions.loghours;

import com.jarsolutions.loghours.client.ClientService;
import com.jarsolutions.loghours.data.JiraData;
import com.jarsolutions.loghours.service.JiraWorkLogService;

public class Main {
  public String getGreeting() {
    return "Hello World!";
  }

  public static void main(String[] args) {
    System.out.println("--- Starting Jira Auto-logger ---");
    String url = System.getenv("JIRA_URL");
    String email = System.getenv("JIRA_EMAIL");
    String token = System.getenv("JIRA_API_TOKEN");
    String issue = System.getenv("JIRA_ISSUE_KEY");

    try {
      JiraData jiraData = new JiraData(url, email, token, issue);
      ClientService clientService = new ClientService();
      JiraWorkLogService jiraWorkLogService = new JiraWorkLogService(clientService);

      boolean success = jiraWorkLogService.logEightHours(jiraData);

      if (!success) {
        System.exit(1);
      }

    } catch (RuntimeException e) {
      System.err.println("Configuration error: " + e.getMessage());
      System.err.println(
          "Please ensure JIRA_URL, JIRA_EMAIL, JIRA_API_TOKEN, JIRA_ISSUE_KEY are set.");
      System.exit(1);
    }
  }
}
