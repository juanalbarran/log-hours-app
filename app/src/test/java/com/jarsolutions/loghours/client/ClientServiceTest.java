package com.jarsolutions.loghours.client;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.jarsolutions.loghours.client.input.LogHoursInput;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ClientServiceTest {
  private HttpServer mockServer;
  private ClientService clientService;
  private String serverUrl;

  @Before
  public void setUp() throws Exception {
    clientService = new ClientService();

    mockServer = HttpServer.create(new InetSocketAddress(0), 0);
    serverUrl = "http://localhost:" + mockServer.getAddress().getPort();
    mockServer.start();
  }

  @After
  public void shutDown() {
    mockServer.stop(0);
  }

  @Test
  public void whenServerReturns2xx_ShouldReturnTrue() {
    mockServer.createContext(
        "/success-endpoint",
        exchange -> {
          exchange.sendResponseHeaders(201, -1);
          exchange.close();
        });

    LogHoursInput input = new LogHoursInput(serverUrl + "/success-endpoint", "{}", "dummyAuth");
    boolean result = clientService.logHours(input);

    assertTrue("Expected logHours to return an 2xx response.", result);
  }

  @Test
  public void whenServerReturns4xx_ShouldReturnFalse() {
    mockServer.createContext(
        "/fail-endpoint",
        exchange -> {
          String errorBody = "Invalid input data";
          exchange.sendResponseHeaders(400, errorBody.length());
          exchange.getResponseBody().write(errorBody.getBytes());
          exchange.close();
        });

    LogHoursInput input = new LogHoursInput(serverUrl + "/fail-endpoint", "{}", "dummyAuth");
    boolean result = clientService.logHours(input);

    assertFalse("Expected logHours to be false on a 4xx response", result);
  }

  @Test
  public void whenServerIsUnreachable_ShouldReturnFalseAndNotCrash() {
    LogHoursInput input = new LogHoursInput("some-endpoint", "{}", "dummyAuth");
    boolean result = clientService.logHours(input);
    assertFalse("Expected logHours to catch the Exception and return false", result);
  }
}
