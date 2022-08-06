package com.testinium.frontendpages;

import com.testinium.utility.TestDataHolder;
import com.testinium.utility.TestUtility;
import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class ProductDetailPage {
    WebDriver driver;
    TestUtility testUtility;

    @FindBy(css = ".o-productDetail__brandLink")
    WebElement productBrandLink;
    @FindBy(css = ".o-productDetail__description")
    WebElement productDescription;
    @FindBy(id = "priceNew")
    WebElement productPrice;
    @FindBy(xpath = "(//label[starts-with(@class,'m-form')])[1]")
    WebElement productColor;
    @FindAll(@FindBy(xpath = "//span[@class='m-variation__item' or @class='m-variation__item -criticalStock' or @class='m-variation__item -active -criticalStock']"))
    List<WebElement> availableSizes;
    @FindBy(id = "addBasket")
    WebElement addToCartButton;
    @FindBy(css = ".icon.icon-cart.icon-cart-active")
    WebElement myCartLink;

    public ProductDetailPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        testUtility = new TestUtility(driver);
    }

    public boolean verifySelectedProductDisplayed() {
        testUtility.waitForElementPresent(productBrandLink);
        if (productBrandLink.isDisplayed())
            return true;
        else
            return false;
    }

    //write product information to File
    public void writeProductInfoToFile() {
        testUtility.waitForElementPresent(productBrandLink);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Product Brand: " + productBrandLink.getText()).append("\n");
        stringBuilder.append("Product Description: " + productDescription.getText()).append("\n");
        stringBuilder.append(productPrice.getAttribute("id") + ": " + productPrice.getText()).append("\n");
        stringBuilder.append("Product Color: " + productColor.getText()).append("\n");
        stringBuilder.append("Product Available Size: ");
        for (int i = 0; i < availableSizes.size(); i++) {
            stringBuilder.append(availableSizes.get(i).getText()).append(" ");
        }
        DateTime date = new DateTime();
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd-HH-mm-ss-SS");
        String fileName = "product_info" + date.toString(formatter) + ".txt";
        File file = new File("test-result" + File.separator + fileName);
        try {
            FileUtils.writeStringToFile(file, stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //get the product price for compare the two prices in cart page
    public void getTheProductPrice() {
        TestDataHolder.setProductPrice(productPrice.getText());
    }

    //for random click on any available size among the available sizes list
    public void clickOnAnyAvailableSize() {
        Random rand = new Random();
        testUtility.javaScriptClick(availableSizes.get(rand.nextInt(availableSizes.size())));
    }

    //click on addToCartButton for add the product to cart
    public void clickOnAddToCartButton() {
        testUtility.waitForElementPresent(addToCartButton);
        addToCartButton.click();
    }

    //click on myCartLink for go to Cart Page
    public void clickOnMyCartLink() {
        testUtility.waitForElementPresent(myCartLink);
        myCartLink.click();
    }

    public void addProductToCart() {
        getTheProductPrice();
        clickOnAnyAvailableSize();
        clickOnAddToCartButton();
        clickOnMyCartLink();
    }

    //add the same size product to cart for make the quantity of the products two
    public void addTheSameProductToCart() {
        driver.navigate().back();
        WebElement theSameSize = driver.findElement(By.xpath(String.format("//*[@id='sizes']//span[text()='%s']", TestDataHolder.getSelectedSize())));
        testUtility.javaScriptClick(theSameSize);
        clickOnAddToCartButton();
        clickOnMyCartLink();
    }
}
