package com.testinium.frontendpages;

import com.testinium.utility.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;

public class ProductsPage {
    WebDriver driver;
    TestUtility testUtility;

    @FindBy(id = "productListTitle")
    WebElement productListTitle;

    @FindAll(@FindBy(xpath = "//*[@id='productList']/div"))
    List<WebElement> products;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        testUtility = new TestUtility(driver);
    }

    //for verify ProductList Displayed
    public boolean verifyProductListDisplayed() {
        testUtility.waitForElementPresent(productListTitle);
        if (productListTitle.getText().contains("bulundu"))
            return true;
        else
            return false;
    }

    //Random click on one of the products
    public void chooseAnyProduct() {
        testUtility.sleep(3);
        Random rand = new Random();
        products.get(rand.nextInt(products.size())).click();
    }

}
