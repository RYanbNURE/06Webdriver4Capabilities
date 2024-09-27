package com.epam.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleResultsPage {
    @FindBy(id = "search")
    private WebElement searchResults;

    public GoogleResultsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public boolean areResultsDisplayed() {

        // In WebDriver 3, finding an element to the right of another typically required complex XPath expressions.
//        WebElement dateLabel = driver.findElement(By.id("date-label"));
//        WebElement dateInput = driver.findElement(By.xpath("//input[@id='date-input'][following-sibling::label[@id='date-label']]"));

// With WebDriver 4, you can achieve this more intuitively.
//        WebElement dateLabel = driver.findElement(By.id("date-label"));
//        WebElement dateInput = driver.findElement(RelativeLocator.with(By.tagName("input")).toRightOf(dateLabel));


//        // In WebDriver 3, switching between windows/tabs was less direct.
//        String originalWindow = driver.getWindowHandle();
//        for (String windowHandle : driver.getWindowHandles()) {
//            if (!originalWindow.contentEquals(windowHandle)) {
//                driver.switchTo().window(windowHandle);
//                break;
//            }
//        }

        // WebDriver 4 introduces a more straightforward way to switch to a new window or tab.
//        driver.switchTo().newWindow(WindowType.TAB);

        // Example of using CDP Commands (part of the BiDi APIs) to fetch browser console logs.
//        ((ChromeDriver) driver).getDevTools().createSession();
//        List<LogEntry> consoleLogs = ((ChromeDriver) driver).manage().logs().get(LogType.BROWSER).getAll();
//        consoleLogs.forEach(log -> System.out.println(log.getMessage()));

        // Using WebDriver 4, you can use Edge like you use Chrome.
//        WebDriver edgeDriver = new EdgeDriver();

        return searchResults.isDisplayed();
    }
}
