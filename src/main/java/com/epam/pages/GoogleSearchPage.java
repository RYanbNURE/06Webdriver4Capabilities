package com.epam.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleSearchPage {
    @FindBy(name = "q")
    private WebElement searchBox;

    @FindBy(name = "btnK")
    private WebElement searchButton;

    public GoogleSearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void searchFor(String text) {
        searchBox.sendKeys(text);
        searchButton.click();
    }
}
