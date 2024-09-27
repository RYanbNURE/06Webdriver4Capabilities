package com.epam;

import com.epam.pages.GoogleResultsPage;
import com.epam.pages.GoogleSearchPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoogleSearchTest {
    private WebDriver driver;
    private GoogleSearchPage googleSearchPage;
    private GoogleResultsPage googleResultsPage;

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.google.com");
        googleSearchPage = new GoogleSearchPage(driver);
        googleResultsPage = new GoogleResultsPage(driver);
    }

    @Test
    public void testGoogleSearch() {
        googleSearchPage.searchFor("JUnit 5");
        assertTrue(googleResultsPage.areResultsDisplayed());
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
