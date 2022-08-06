package com.testinium.frontendpages;

import com.testinium.utility.TestDataHolder;
import com.testinium.utility.TestUtility;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;
    TestUtility testUtility;

    @FindBy(id = "onetrust-accept-btn-handler")
    WebElement acceptCookiesButton;
    @FindBy(css = "input.default-input.o-header__search--input")
    WebElement searchBox;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        testUtility = new TestUtility(driver);
    }

    //verify Home Page is Opened successfully---Method One
    public boolean verifyHomePageOpened1() {
        testUtility.waitForElementPresent(searchBox);
        if (searchBox.isDisplayed())
            return true;
        else
            return false;
    }

    //verify Home Page is Opened successfully---Method Two
    public boolean verifyHomePageOpened2() {
        if (driver.getTitle().contains("Beymen.com"))
            return true;
        else
            return false;
    }

    //For accept Cookies
    public void acceptCookies() {
        testUtility.waitForElementPresent(acceptCookiesButton);
        acceptCookiesButton.click();
    }

    //For search product
    public void searchProduct(TestDataHolder testDataHolder) {
        testUtility.waitForElementPresent(searchBox);
        searchBox.sendKeys(testDataHolder.getProductName1());
        searchBox.sendKeys(Keys.chord(Keys.CONTROL, "A", Keys.DELETE));
        searchBox.sendKeys(testDataHolder.getProductName2() + Keys.ENTER);
    }

}
