package com.jarsolutions.loghours.client;

import com.jarsolutions.loghours.client.input.LogHoursInput;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class ClientService {

  public ClientService() {}

  public void logHours(LogHoursInput logHoursInput) {
    try (HttpClient client =
        HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build()) {
      HttpRequest request =
          HttpRequest.newBuilder()
              .uri(URI.create(logHoursInput.endpoint()))
              .header("Authorization", "Basic " + logHoursInput.encodedAuth())
              .header("Accept", "application/json")
              .header("Content-Type", "application/json")
              .POST(HttpRequest.BodyPublishers.ofString(logHoursInput.jsonPayload()))
              .build();

      System.out.println("Sending my 8 hours of hard work to Jira...");

      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

      if (response.statusCode() >= 200 && response.statusCode() < 300) {
        System.out.println("Succesfully logged 8 hours");
      } else {
        System.out.println("Something went wrong when logging the 8 hours");
        System.out.println("Response: " + response.body());
      }
    } catch (Exception e) {
      System.err.println("An error ocurred: " + e.getMessage());
    }
  }
}
