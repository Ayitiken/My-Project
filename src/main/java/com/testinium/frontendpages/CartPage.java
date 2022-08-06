package com.testinium.frontendpages;

import com.testinium.utility.TestDataHolder;
import com.testinium.utility.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
    WebDriver driver;
    TestUtility testUtility;

    @FindBy(css = ".m-productPrice__salePrice")
    WebElement salePrice;
    @FindBy(css = ".m-basket__variantion>li:nth-child(2)>div.m-basket__variantionValue")
    WebElement selectedSizeLocation;
    @FindBy(id = "quantitySelect0-key-0")
    WebElement quantityDropDown;
    @FindBy(id = "removeCartItemBtn0-key-0")
    WebElement deleteButton;
    @FindBy(xpath = "//*[@id='emtyCart']/div/strong")
    WebElement emptyMessage;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        testUtility = new TestUtility(driver);
    }

    //compare the actual price in the product detail page with the price of the product in the cart
    public boolean verifyThePriceOfTheProductInTheCart() {
        testUtility.waitForElementPresent(salePrice);
        String priceInTheCart = salePrice.getText();
        if (TestDataHolder.getProductPrice().equals(priceInTheCart))
            return true;
        else
            return false;
    }

    //get the size of the product in the cart--for choose the same size of product again(for make the quantity of the product two )
    public void getTheSelectedSize() {
        String selectedSize = selectedSizeLocation.getText();
        TestDataHolder.setSelectedSize(selectedSize);
    }

    //verify the quantity of product is two
    public boolean verifyTheQuantityOfProductIsTwo() {
        String theQuantityOfProduct = quantityDropDown.getText();
        if (theQuantityOfProduct.contains("2 adet"))
            return true;
        else
            return false;
    }

    //click on delete button
    public void deleteTheProductsFromCart() {
        testUtility.waitForElementPresent(deleteButton);
        deleteButton.click();
    }

    //verify the cart is empty
    public boolean verifyTheCartIsEmpty() {
        testUtility.waitForElementPresent(emptyMessage);
        if (emptyMessage.isDisplayed())
            return true;
        else
            return false;
    }

}
