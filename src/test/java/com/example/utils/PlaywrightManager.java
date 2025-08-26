package com.example.utils;

import com.microsoft.playwright.*;

public class PlaywrightManager {
    private static ThreadLocal<Playwright> playwright = new ThreadLocal<>();
    private static ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> context = new ThreadLocal<>();
    private static ThreadLocal<Page> page = new ThreadLocal<>();

    public static Page getPage() {
        try {
            if (page.get() == null || page.get().isClosed()) {
                setupPlaywright();
            }
            return page.get();
        } catch (Exception e) {
            System.err.println("Error getting page, recreating: " + e.getMessage());
            // Clean up and retry
            closeAll();
            setupPlaywright();
            return page.get();
        }
    }

    private static void setupPlaywright() {
        try {
            // Create Playwright instance
            playwright.set(Playwright.create());
            
            // Launch browser with stable options
            browser.set(playwright.get().chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(true) // Use headless mode for stability
                    .setArgs(java.util.Arrays.asList(
                            "--no-sandbox",
                            "--disable-dev-shm-usage",
                            "--disable-gpu",
                            "--disable-web-security",
                            "--disable-features=VizDisplayCompositor"
                    ))));
            
            // Create context
            context.set(browser.get().newContext());
            
            // Create page
            page.set(context.get().newPage());
        } catch (Exception e) {
            System.err.println("Failed to setup Playwright: " + e.getMessage());
            throw new RuntimeException("Playwright setup failed", e);
        }
    }

    public static byte[] takeScreenshot() {
        try {
            Page currentPage = getPage();
            if (currentPage != null) {
                return currentPage.screenshot(new Page.ScreenshotOptions()
                        .setFullPage(true));
            }
        } catch (Exception e) {
            System.err.println("Error taking screenshot: " + e.getMessage());
        }
        return null;
    }

    public static void closePage() {
        try {
            if (page.get() != null) {
                page.get().close();
                page.remove();
            }
        } catch (Exception e) {
            System.err.println("Error closing page: " + e.getMessage());
        }
    }

    public static void closeAll() {
        try {
            if (page.get() != null) {
                page.get().close();
                page.remove();
            }
            if (context.get() != null) {
                context.get().close();
                context.remove();
            }
            if (browser.get() != null) {
                browser.get().close();
                browser.remove();
            }
            if (playwright.get() != null) {
                playwright.get().close();
                playwright.remove();
            }
        } catch (Exception e) {
            System.err.println("Error closing Playwright: " + e.getMessage());
        }
    }
}
