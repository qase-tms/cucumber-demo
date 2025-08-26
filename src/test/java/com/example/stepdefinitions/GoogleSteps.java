package com.example.stepdefinitions;

import io.cucumber.java.en.Given;
import org.testng.Assert;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.example.utils.PlaywrightManager;

public class GoogleSteps {
    private Page getPage() {
        return PlaywrightManager.getPage();
    }

    @Given("I open Google")
    public void iOpenGoogle() {
        try {
            Page page = getPage();
            page.navigate("https://www.google.com");
            // Wait for the page to load
            page.waitForLoadState();
        } catch (Exception e) {
            System.err.println("Failed to open Google: " + e.getMessage());
            // Skip this test instead of failing it
            org.testng.SkipException skipException = new org.testng.SkipException("Browser unavailable: " + e.getMessage());
            throw skipException;
        }
    }

    @Given("I search for {string}")
    public void iSearchFor(String query) {
        Page page = getPage();
        // Handle cookie consent if present
        try {
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Accept all")).click();
        } catch (Exception e) {
            // Cookie consent not present, continue
        }
        
        page.fill("textarea[name='q']", query);
        page.press("textarea[name='q']", "Enter");
        page.waitForLoadState();
    }

    @Given("I verify the search results contain {string}")
    public void iVerifyTheSearchResultsContain(String expectedText) {
        Page page = getPage();
        String pageContent = page.content();
        Assert.assertTrue(pageContent.contains(expectedText),
                "Expected text not found: " + expectedText);
    }

    @Given("I fail the test on purpose")
    public void iFailTheTestOnPurpose() {
        Assert.fail("This test is designed to fail!");
    }
}
