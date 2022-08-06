package test.uitestframework;

import com.testinium.frontendpages.CartPage;
import com.testinium.frontendpages.HomePage;
import com.testinium.frontendpages.ProductDetailPage;
import com.testinium.frontendpages.ProductsPage;
import com.testinium.utility.ExcelUtility;
import com.testinium.utility.TestDataHolder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class TestRunner extends BasePage {
    HomePage homePage;
    TestDataHolder testDataHolder;
    ExcelUtility excelUtility;
    ProductsPage productsPage;
    ProductDetailPage productDetailPage;
    CartPage cartPage;

    @Test
    @Order(1)
    public void verifyHomePageOpened() {
        homePage = new HomePage(driver);
        Assertions.assertTrue(homePage.verifyHomePageOpened1());
        Assertions.assertTrue(homePage.verifyHomePageOpened2());
    }

    @Test
    @Order(2)
    public void searchAProduct() {
        excelUtility = new ExcelUtility();
        testDataHolder = excelUtility.readProductNameFromExcel("test-data/productNameData.xlsx", "Product_Info");
        homePage.acceptCookies();
        homePage.searchProduct(testDataHolder);
        productsPage = new ProductsPage(driver);
        Assertions.assertTrue(productsPage.verifyProductListDisplayed());
    }

    @Test
    @Order(3)
    public void selectAnyProduct() {
        productsPage.chooseAnyProduct();
        productDetailPage = new ProductDetailPage(driver);
        Assertions.assertTrue(productDetailPage.verifySelectedProductDisplayed());
    }

    @Test
    @Order(4)
    public void addProductToCart() {
        productDetailPage.writeProductInfoToFile();
        productDetailPage.addProductToCart();
        cartPage = new CartPage(driver);
        Assertions.assertTrue(cartPage.verifyThePriceOfTheProductInTheCart());
        cartPage.getTheSelectedSize();
        productDetailPage.addTheSameProductToCart();
        Assertions.assertTrue(cartPage.verifyTheQuantityOfProductIsTwo());
    }

    @Test
    @Order(5)
    public void deleteTheProductsFromTheCart() {
        cartPage.deleteTheProductsFromCart();
        Assertions.assertTrue(cartPage.verifyTheCartIsEmpty());
    }

}
