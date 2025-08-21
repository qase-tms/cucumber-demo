# Cucumber 7 + Gradle + Qase TestOps Integration

This project demonstrates how to integrate Cucumber 7 with Gradle and Qase TestOps for automated test reporting.

## Features

- **Cucumber 7**: BDD testing framework with JUnit Platform Engine
- **Spring Boot Test**: Full Spring context support for dependency injection
- **Playwright Integration**: Browser automation for web testing
- **Qase TestOps Integration**: Automatic test result reporting to Qase
- **Multiple Report Formats**: HTML, JSON, JUnit XML reports
- **Gradle Build System**: Modern build configuration with dependency management

## Project Structure

```
c7-gradle/
├── build.gradle                    # Gradle build configuration
├── settings.gradle                 # Project settings
├── gradle.properties              # Gradle properties
├── qase.config.json               # Qase TestOps configuration
├── .gitignore                     # Git ignore patterns
└── src/
    ├── main/java/com/courtreserve/
    │   └── configs/
    │       └── BaseConfig.java    # Spring Boot configuration
    └── test/
        ├── java/com/courtreserve/
        │   ├── DesktopCucumberRunner.java    # Cucumber test runner
        │   └── stepdefinitions/
        │       └── OrganizationSteps.java   # Step definitions
        └── resources/
            ├── application.yml     # Spring configuration
            └── features/
                └── organization.feature    # Feature files
```

## Prerequisites

- Java 17 or higher
- Gradle 8.5 or higher

## Setup

1. **Clone this specific branch**:
   ```bash
   git clone -b alexandr-215470436287500 --single-branch https://github.com/qase-tms/cucumber-demo.git cucumber7-gradle-demo
   cd cucumber7-gradle-demo
   ```

   Or if you want to clone the entire repository:
   ```bash
   git clone https://github.com/qase-tms/cucumber-demo.git
   cd cucumber-demo
   git checkout alexandr-215470436287500
   ```

2. **Configure Qase TestOps** (optional):
   - Update `qase.config.json` with your project code and API token
   - Set environment variables:
     ```bash
     export QASE_API_TOKEN="your-api-token"
     export QASE_PROJECT_CODE="your-project-code"
     ```

3. **Install Playwright browsers**:
   ```bash
   ./gradlew playwrightInstall
   ```

## Running Tests

### Run all Cucumber tests:
```bash
./gradlew runCucumberTests
```

### Run with specific tags:
```bash
CUCUMBER_FILTER_TAG="@smoke" ./gradlew runCucumberTests
```

### Run with specific Spring profile:
```bash
SPRING_PROFILE="dev" ./gradlew runCucumberTests
```

## Qase Integration

This project includes full Qase TestOps integration:

- **Test Case Mapping**: Use `@QaseId(123)` annotations in feature files
- **Automatic Reporting**: Test results are automatically uploaded to Qase
- **Detailed Results**: Includes step-by-step execution details, timings, and failure information
- **Test Run Links**: Direct links to Qase TestOps dashboard in console output

### Qase Configuration

The `qase.config.json` file contains:
- **Mode**: `testops` for live reporting
- **Project**: Your Qase project code
- **API Token**: Authentication for Qase API
- **Run Settings**: Test run title, description, and completion settings

## Reports

After running tests, you can find reports in:
- **HTML Report**: `build/cucumber-report.html`
- **JSON Report**: `build/cucumber.json`
- **JUnit XML**: `build/cucumber.xml`
- **Qase TestOps**: Live dashboard with detailed results

## Example Feature

```gherkin
Feature: Organization Management
  As a user
  I want to be able to join organizations
  So that I can collaborate with team members

  @QaseId(1647)
  Scenario: user is able to join to new organization via member portal
    Given I am on the member portal login page
    When I enter valid credentials
    And I click the login button
    Then I should be logged in successfully
    # ... more steps
```

## Dependencies

Key dependencies used in this project:
- Cucumber 7.21.1
- Spring Boot 3.4.4
- Playwright 1.53.0
- JUnit Platform 1.12.1
- Qase Cucumber Reporter 4.1.16
- AssertJ 3.27.3

## Contributing

1. Fork the repository
2. Create a feature branch
3. Add your tests and features
4. Ensure all tests pass
5. Submit a pull request

## License

This project is provided as an example for integrating Cucumber 7 with Qase TestOps.
