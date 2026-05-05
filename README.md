# log-hours-app

I need to log my hours i Jira, but because i don't have a project until monday 11th i'm doing this app that will log the hours for me.

## Prerequisites

- Java 21 or higher
- An Atlassian account with an active API token

## Configuration

The application reads an `.env` file or just check the following environmet variables

```properties
JIRA_URL= # The url of the jira
JIRA_EMAIL= # Your company or jira email
JIRA_API_TOKEN= # The API token
JIRA_ISSUE_KEY= # The specific issue you want to log time against
```

## Running the Application

```bash
./gradlew :app:run
```

## Running Tests

```bash
./gradlew :app:test
```
