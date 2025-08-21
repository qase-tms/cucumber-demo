Feature: Organization Management
  As a user
  I want to be able to join organizations
  So that I can collaborate with team members

  @QaseId(20410)
  Scenario: user is able to join to new organization via member portal
    Given I am on the member portal login page
    When I enter valid credentials
    And I click the login button
    Then I should be logged in successfully
    When I navigate to the organization section
    And I click on "Join Organization" button
    And I enter the organization invitation code
    And I click "Join" button
    Then I should be successfully added to the organization
    And I should see the organization dashboard
