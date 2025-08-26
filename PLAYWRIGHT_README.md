# Playwright Cucumber Demo

This branch contains a Playwright-based implementation of the Cucumber tests, replacing the original Selenium implementation.

## Key Features

- **Playwright Integration**: Uses Microsoft Playwright for browser automation
- **Enhanced Screenshot Capture**: Takes screenshots after every test step (not just failures)
- **Video Recording**: Records videos for all test scenarios
- **Qase Integration**: Maintains integration with Qase test management system

## Changes Made

### Dependencies
- Replaced Selenium WebDriver with Playwright
- Updated `pom.xml` to include Playwright dependency

### Code Changes
- **PlaywrightManager**: New utility class for managing Playwright browser instances
- **Enhanced TestHooks**: 
  - Screenshots captured after every step
  - Video recording for entire test scenarios
  - Automatic attachment to Qase reports
- **Updated Step Definitions**: Migrated from Selenium to Playwright APIs
- **Feature Files**: Updated tags from `@selenium` to `@playwright`

### Test Results Structure
```
test-results/
├── screenshots/     # Step-by-step screenshots
└── videos/         # Full scenario recordings
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

## Screenshot and Video Features

### Screenshots
- Captured automatically after every test step
- Saved locally in `test-results/screenshots/`
- Attached to Qase test results
- Naming convention: `step_{number}_{scenario}_{timestamp}.png`

### Videos
- Recorded for entire test scenarios
- Saved locally in `test-results/videos/`
- Attached to Qase test results
- Format: WebM video files

## Configuration

### Playwright Settings
- Browser: Chromium (configurable)
- Headless: False (visible browser for better video recording)
- Slow motion: 100ms (for clearer video capture)
- Video resolution: 1280x720

### Qase Integration
- Screenshots attached for both passing and failing steps
- Videos attached for complete scenarios
- Maintains all existing Qase annotations and features

## Troubleshooting

### Common Issues
1. **Browser not found**: Run `mvn exec:java -e -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install"`
2. **Permission errors**: Ensure write permissions for `test-results/` directory
3. **Video not generated**: Check that browser context is properly closed after tests

### Debugging
- Enable verbose logging by setting log level to DEBUG
- Check `test-results/` directory for generated artifacts
- Review console output for Playwright-specific errors
