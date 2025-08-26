package com.example.hooks;

import io.cucumber.java.*;
import com.example.utils.PlaywrightManager;
import io.qase.cucumber7.Qase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestHooks {
    private static final Logger logger = LoggerFactory.getLogger(TestHooks.class);
    private int stepCounter = 0;

    @Before("@playwright")
    public void setUp(Scenario scenario) {
        logger.info("Starting test for scenario: {}", scenario.getName());
        stepCounter = 0;
        
        // Create directories for screenshots
        try {
            Files.createDirectories(Paths.get("test-results/screenshots"));
        } catch (Exception e) {
            logger.warn("Could not create test-results directories: {}", e.getMessage());
        }
    }

    @BeforeStep
    public void beforeStep(Scenario scenario) {
        stepCounter++;
        logger.info("Executing step {} for scenario: {}", stepCounter, scenario.getName());
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS"));
        String scenarioName = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_");
        
        try {
            // Take screenshot after every step (not just failures)
            byte[] screenshotBytes = PlaywrightManager.takeScreenshot();
            if (screenshotBytes != null) {
                String screenshotName = String.format("step_%d_%s_%s.png", stepCounter, scenarioName, timestamp);
                
                // Save screenshot to file system
                Path screenshotPath = Paths.get("test-results/screenshots", screenshotName);
                Files.write(screenshotPath, screenshotBytes);
                
                // Attach to Qase
                Qase.attach(screenshotName, screenshotBytes, "image/png");
                
                logger.info("Screenshot captured: {}", screenshotName);
            }
        } catch (Exception e) {
            logger.error("Error capturing screenshot: {}", e.getMessage(), e);
        }
    }

    @After("@playwright")
    public void tearDown(Scenario scenario) {
        try {
            logger.info("Test completed for scenario: {}", scenario.getName());
        } catch (Exception e) {
            logger.error("Error in tearDown: {}", e.getMessage(), e);
        }
    }
}
