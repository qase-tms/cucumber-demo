# Cucumber 7 + Gradle + Qase Integration

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
   git clone -b alexandr-215470436287500 --single-branch https://github.com/qase-tms/cucumber-demo.git
   ```

2. **Configure Qase TestOps** (optional):
   - Update `qase.config.json` with your project code and API token
   OR,
   - Set environment variables:
     ```bash
     export QASE_TESTOPS_API_TOKEN="your-api-token"
     export QASE_TESTOPS_PROJECT="your-project-code"
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

## Dependencies

Key dependencies used in this project:
- Cucumber 7.21.1
- Spring Boot 3.4.4
- Playwright 1.53.0
- JUnit Platform 1.12.1
- Qase Cucumber Reporter 4.1.16
- AssertJ 3.27.3

This project is provided as an example for integrating Cucumber 7 with Qase TestOps.
