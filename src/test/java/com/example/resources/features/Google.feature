Feature: Google Search

  @playwright
  Scenario: Search on Google
    Given I open Google
    Given I search for "Playwright Cucumber"
    Given I verify the search results contain "Playwright"


  @playwright
  Scenario: Failing Google Search Test
    Given I open Google
    Given I search for "Playwright Cucumber"
    Given I fail the test on purpose
