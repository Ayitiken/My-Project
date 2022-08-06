package com.testinium.utility;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelUtility {
    public String readFromExcelCell(String fileName, String sheetName, int rowNumber, int columNumber) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        XSSFWorkbook workbook = null;
        try {
            assert fileInputStream != null;
            workbook = new XSSFWorkbook(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        XSSFSheet sheet = workbook.getSheet(sheetName);
        XSSFRow row = sheet.getRow(rowNumber);
        String cellValue = null;
        if (row == null) {
            System.out.println("Empty row!!!!");
        } else {
            XSSFCell cell = row.getCell(columNumber);
            CellType cellType = cell.getCellType();
            switch (cellType) {
                case NUMERIC:
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    break;
                case STRING:
                    cellValue = cell.getStringCellValue();
                    break;
            }
        }
        return cellValue;
    }

    public TestDataHolder readProductNameFromExcel(String fileName, String sheetName) {
        ExcelUtility excelUtility = new ExcelUtility();
        TestDataHolder testDataHolder = new TestDataHolder();
        testDataHolder.setProductName1(excelUtility.readFromExcelCell(fileName, sheetName, 1, 1));
        testDataHolder.setProductName2(excelUtility.readFromExcelCell(fileName, sheetName, 1, 2));
        return testDataHolder;
    }
}
