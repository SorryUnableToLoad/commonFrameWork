package sdet.externalfilereading.excelfile;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class ExcelReader {
    private ExcelReader(){}

    public static List<Map<String, String>> getTestDetails(String sheetName) {
        List<Map<String, String>> list = null;
        try (FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/testdata.xlsx")) {

            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheet(sheetName);

            int lastRowNum = sheet.getLastRowNum();
            short lastColNum = sheet.getRow(0).getLastCellNum();

            System.out.println(lastRowNum);
            System.out.println(lastColNum);

            list = new ArrayList<>();
            Map<String, String> map = null;

            for (int i = 1; i <= lastRowNum; i++) {
                map = new HashMap<>();
                for (int j = 0; j < lastColNum; j++) {
                    String key = sheet.getRow(0).getCell(j).getStringCellValue();
                    String value = sheet.getRow(i).getCell(j).getStringCellValue();
                    map.put(key, value);
                }
                list.add(map);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //System.out.println(list);
        return list;
    }
}
