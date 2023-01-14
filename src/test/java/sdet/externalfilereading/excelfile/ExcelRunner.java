package sdet.externalfilereading.excelfile;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelRunner {
    @Test(dataProvider = "getData")
    public void testWithExcel(String fname, String lname, String mobileno, String isboolean) {
        System.out.println(fname);
        System.out.println(lname);
        System.out.println(mobileno);
        System.out.println(isboolean);
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/externalfile/testdata.xlsx");

        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);

        int lastRowNum = sheet.getLastRowNum();
        short lastColNum = sheet.getRow(0).getLastCellNum();

        System.out.println(lastRowNum);
        System.out.println(lastColNum);

        Object[][] data = new Object[lastRowNum][lastColNum];
        for (int i = 1; i <= lastRowNum; i++) {
            for (int j = 0; j < lastColNum; j++) {
                data[i - 1][j] = sheet.getRow(i).getCell(j).getStringCellValue();
            }
        }
        return data;
    }

    @Test(dataProvider = "getDataWithMap")
    public void testWithExcelAndMap(Map<String, String> data) {
        System.out.println(data.get("fname"));
        System.out.println(data.get("lname"));
        System.out.println("mobileno");
        System.out.println("isboolean");
    }

    @DataProvider
    public Object[] getDataWithMap() throws IOException {
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/externalfile/testdata.xlsx");

        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);

        int row = sheet.getLastRowNum();
        short cell = sheet.getRow(0).getLastCellNum();

        System.out.println(row);
        System.out.println(cell);

        Object[] data = new Object[row];
        Map<String, String> map;

        for (int i = 1; i <= row; i++) {
            map = new HashMap<>();
            for (int j = 0; j < cell; j++) {
                String key = sheet.getRow(0).getCell(j).getStringCellValue();
                String value = sheet.getRow(i).getCell(j).getStringCellValue();
                map.put(key, value);
                data[i - 1] = map;
            }
        }
        return data;
    }

    @Test
    public void excelReader() {
        //ExcelReader.getTestDetails("Sheet1");
        System.out.println(ExcelReader.getTestDetails("Sheet1").get(0).get("fname"));
    }
}
