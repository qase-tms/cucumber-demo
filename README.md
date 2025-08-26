# Playwright Cucumber Demo

This branch contains a Playwright-based implementation of the Cucumber tests, replacing the original Selenium implementation.

### Dependencies
- Replaced Selenium WebDriver with Playwright
- Updated `pom.xml` to include Playwright dependency

### Test Results Structure
```
test-results/
├── screenshots/     # Step-by-step screenshots
```

## Running Tests

### Prerequisites
1. Java 8 or higher
2. Maven 3.6+
3. Playwright browsers (will be installed automatically on first run)

### Installation
```bash
# Install Playwright browsers
mvn exec:java -e -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install"
```

### Execution
```bash
# Run all Playwright tests
mvn test

# Run specific feature
mvn test -Dcucumber.filter.tags="@playwright"

# Run with specific browser
mvn test -Dplaywright.browser=chromium  # or firefox, webkit
```

## Test Scenarios

1. **Google Search Tests**: Basic search functionality with result verification
2. **Login Flow**: Complete login process using demo site
3. **Multi-step Interactions**: Complex scenarios with multiple steps for screenshot demonstration
4. **Failure Scenarios**: Intentional failures to demonstrate error capture

### Screenshots
- Captured automatically after every test step
- Saved locally in `test-results/screenshots/`
- Attached to Qase test results
- Naming convention: `step_{number}_{scenario}_{timestamp}.png`
