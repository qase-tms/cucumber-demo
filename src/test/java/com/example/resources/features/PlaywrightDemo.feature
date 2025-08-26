Feature: Playwright Demo with Screenshots and Videos

  @playwright
  Scenario: Multi-step interaction with screenshots
    Given I open Google
    Given I search for "Playwright automation"
    Given I verify the search results contain "Playwright"
    Given I search for "Cucumber BDD"
    Given I verify the search results contain "Cucumber"

  @playwright
  Scenario: Login flow with video recording
    Given I navigate to the login page
    When I enter valid username and password
    Then I should be redirected to the dashboard

  @playwright
  Scenario: Intentional failure for screenshot capture
    Given I open Google
    Given I search for "Test automation"
    Given I fail the test on purpose
