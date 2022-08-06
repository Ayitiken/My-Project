package com.testinium.utility;

public class TestDataHolder {
    private String productName1;
    private String productName2;
    private static String productPrice;
    private static String selectedSize;

    public static String getSelectedSize() {
        return selectedSize;
    }

    public static void setSelectedSize(String selectedSize) {
        TestDataHolder.selectedSize = selectedSize;
    }

    public TestDataHolder() {
    }

    public String getProductName1() {
        return productName1;
    }

    public void setProductName1(String productName1) {
        this.productName1 = productName1;
    }

    public String getProductName2() {
        return productName2;
    }

    public void setProductName2(String productName2) {
        this.productName2 = productName2;
    }

    public static String getProductPrice() {
        return productPrice;
    }

    public static void setProductPrice(String productPrice) {
        TestDataHolder.productPrice = productPrice;
    }
}
