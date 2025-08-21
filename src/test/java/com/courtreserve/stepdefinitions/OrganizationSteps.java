package com.courtreserve.stepdefinitions;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class OrganizationSteps {

    private Playwright playwright;
    private Browser browser;
    private Page page;

    @Before
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
    }

    @After
    public void tearDown() {
        if (page != null) {
            page.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }

    @Given("I am on the member portal login page")
    public void iAmOnTheMemberPortalLoginPage() {
        // Navigate to the login page
        page.navigate("https://example.com/login");
        assertThat(page.title()).contains("Login");
    }

    @When("I enter valid credentials")
    public void iEnterValidCredentials() {
        // Enter username and password
        page.fill("input[name='username']", "testuser@example.com");
        page.fill("input[name='password']", "password123");
    }

    @And("I click the login button")
    public void iClickTheLoginButton() {
        page.click("button[type='submit']");
    }

    @Then("I should be logged in successfully")
    public void iShouldBeLoggedInSuccessfully() {
        // Wait for navigation and verify login success
        page.waitForURL("**/dashboard");
        assertThat(page.url()).contains("dashboard");
    }

    @When("I navigate to the organization section")
    public void iNavigateToTheOrganizationSection() {
        page.click("a[href*='organization']");
    }

    @And("I click on {string} button")
    public void iClickOnButton(String buttonText) {
        page.click("button:has-text('" + buttonText + "')");
    }

    @And("I enter the organization invitation code")
    public void iEnterTheOrganizationInvitationCode() {
        page.fill("input[name='invitationCode']", "ORG-12345");
    }

    @Then("I should be successfully added to the organization")
    public void iShouldBeSuccessfullyAddedToTheOrganization() {
        // Wait for success message or redirect
        page.waitForSelector(".success-message");
        assertThat(page.locator(".success-message").textContent()).contains("Successfully joined");
    }

    @And("I should see the organization dashboard")
    public void iShouldSeeTheOrganizationDashboard() {
        assertThat(page.url()).contains("organization/dashboard");
        assertThat(page.locator("h1").textContent()).contains("Organization Dashboard");
    }
}
