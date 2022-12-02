package com.apiproj.utils;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.util.Calendar;

/**
 * @author nishanth.t
 */

public class XlsReader {
    public String path;
    public FileInputStream fis = null;
    public FileOutputStream fileOut = null;
    private XSSFWorkbook workbook = null;
    private XSSFSheet sheet = null;
    private XSSFRow row = null;
    private XSSFCell cell = null;

    // -------------------------------------------------------------------------

    /**
     * @param path
     */
    public XlsReader(String path, String sheetName) {

        this.path = path;
        try {

            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);
            fis.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    // -------------------------------------------------------------------------
    // returns the row count in a sheet

    /**
     * To get the Row count and returns the same integer.
     *
     * @param sheetName
     * @return rowcount no.
     */
    public int getRowCount(String sheetName) {
        int index = workbook.getSheetIndex(sheetName);
        if (index == -1) return 0;
        else {
            sheet = workbook.getSheetAt(index);
            int number = sheet.getLastRowNum() + 1;
            return number;
        }

    }

    // -------------------------------------------------------------------------
    // returns the data from a cell

    /**
     * To read a data from a cell based on column name and returns the same
     * value.
     *
     * @param sheetName
     * @param colName
     * @param rowNum
     * @return cellText - data value from a excel cell.
     * @throws Exception
     */
    public String getCellData(String sheetName, String colName, int rowNum) {
        /*try {
            if (rowNum <= 0)
                return "";

            int index = workbook.getSheetIndex(sheetName);
            int col_Num = -1;
            if (index == -1)
                return "";

            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(0);
            for (int i = 0; i < row.getLastCellNum(); i++) {
                // System.out.println(row.getCell(i).getStringCellValue().trim());
                if (row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
                    col_Num = i;
            }
            if (col_Num == -1)
                return "";

            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(rowNum - 1);
            if (row == null)
                return "";
            cell = row.getCell(col_Num);

            if (cell == null)
                return "";
            // System.out.println(cell.getCellType());
            if (cell.getCellType() == Cell.CELL_TYPE_STRING)
                return cell.getStringCellValue();
            else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_FORMULA) {

                String cellText = String.valueOf(cell.getNumericCellValue());
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    // format in form of M/D/YY
                    double d = cell.getNumericCellValue();

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(HSSFDateUtil.getJavaDate(d));
                    cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
                    cellText = cal.get(Calendar.MONTH) + 1 + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + cellText;
                    // System.out.println(cellText);
                }

                return cellText;
            } else if (cell.getCellType() == Cell.CELL_TYPE_BLANK)
                return "";
            else
                return String.valueOf(cell.getBooleanCellValue());

        } catch (Exception e) {

            e.printStackTrace();
            return "row " + rowNum + " or column " + colName + " does not exist in xls";
        }*/
        try {
            if (rowNum <= 0) {
                return "";
            } else {
                int index = this.workbook.getSheetIndex(sheetName);
                int col_Num = -1;
                if (index == -1) {
                    return "";
                } else {
                    this.sheet = this.workbook.getSheetAt(index);
                    this.row = this.sheet.getRow(0);

                    for (int i = 0; i < this.row.getLastCellNum(); ++i) {
                        if (this.row.getCell(i).getStringCellValue().trim().equals(colName.trim())) {
                            col_Num = i;
                        }
                    }

                    if (col_Num == -1) {
                        return "";
                    } else {
                        this.sheet = this.workbook.getSheetAt(index);
                        this.row = this.sheet.getRow(rowNum - 1);
                        if (this.row == null) {
                            return "";
                        } else {
                            this.cell = this.row.getCell(col_Num);
                            if (this.cell == null) {
                                return "";
                            } else if (this.cell.getCellType() == 1) {
                                return this.cell.getStringCellValue();
                            } else if (this.cell.getCellType() != 0 && this.cell.getCellType() != 2) {
                                if (this.cell.getCellType() == 3) {
                                    return "";
                                } else {
                                    return String.valueOf(this.cell.getBooleanCellValue());
                                }
                            } else {
                                String cellText = String.valueOf(this.cell.getNumericCellValue());
                                if (HSSFDateUtil.isCellDateFormatted(this.cell)) {
                                    double d = this.cell.getNumericCellValue();
                                    Calendar cal = Calendar.getInstance();
                                    cal.setTime(HSSFDateUtil.getJavaDate(d));
                                    cellText = String.valueOf(cal.get(1)).substring(2);
                                    cellText = cal.get(2) + 1 + "/" + cal.get(5) + "/" + cellText;
                                }

                                return cellText;
                            }
                        }
                    }
                }
            }
        } catch (Exception var10) {
            var10.printStackTrace();
            return "row " + rowNum + " or column " + colName + " does not exist in xls";
        }
    }

    // -------------------------------------------------------------------------

    /**
     * Overloaded method. To read a data from a cell based column No. Returns
     * the data from a cell
     *
     * @param sheetName
     * @param colNum
     * @param rowNum
     * @return cellText - the text value in the specific cell.
     */
    // returns the data from a cell
    public String getCellData(String sheetName, int colNum, int rowNum) {
        /*try {
            if (rowNum <= 0)
                return "";

            int index = workbook.getSheetIndex(sheetName);

            // System.out.println("Index:" +index);

            if (index == -1)
                return "";

            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(rowNum - 1);
            if (row == null)
                return "";
            cell = row.getCell(colNum);
            if (cell == null)
                return "";

            if (cell.getCellType() == Cell.CELL_TYPE_STRING)
                return cell.getStringCellValue();
            else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_FORMULA) {

                String cellText = String.valueOf(cell.getNumericCellValue());
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    // format in form of M/D/YY
                    double d = cell.getNumericCellValue();

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(HSSFDateUtil.getJavaDate(d));
                    cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
                    cellText = cal.get(Calendar.MONTH) + 1 + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + cellText;

                    // System.out.println(cellText);

                }

                return cellText;
            } else if (cell.getCellType() == Cell.CELL_TYPE_BLANK)
                return "";
            else
                return String.valueOf(cell.getBooleanCellValue());
        } catch (Exception e) {

            e.printStackTrace();
            return "row " + rowNum + " or column " + colNum + " does not exist  in xls";
        }*/
        try {
            if (rowNum <= 0) {
                return "";
            } else {
                int index = this.workbook.getSheetIndex(sheetName);
                if (index == -1) {
                    return "";
                } else {
                    this.sheet = this.workbook.getSheetAt(index);
                    this.row = this.sheet.getRow(rowNum - 1);
                    if (this.row == null) {
                        return "";
                    } else {
                        this.cell = this.row.getCell(colNum);
                        if (this.cell == null) {
                            return "";
                        } else if (this.cell.getCellType() == 1) {
                            return this.cell.getStringCellValue();
                        } else if (this.cell.getCellType() != 0 && this.cell.getCellType() != 2) {
                            return this.cell.getCellType() == 3 ? "" : String.valueOf(this.cell.getBooleanCellValue());
                        } else {
                            String cellText = String.valueOf(this.cell.getNumericCellValue());
                            if (HSSFDateUtil.isCellDateFormatted(this.cell)) {
                                double d = this.cell.getNumericCellValue();
                                Calendar cal = Calendar.getInstance();
                                cal.setTime(HSSFDateUtil.getJavaDate(d));
                                cellText = String.valueOf(cal.get(1)).substring(2);
                                cellText = cal.get(2) + 1 + "/" + cal.get(5) + "/" + cellText;
                            }

                            return cellText;
                        }
                    }
                }
            }
        } catch (Exception var9) {
            var9.printStackTrace();
            return "row " + rowNum + " or column " + colNum + " does not exist  in xls";
        }
    }

    // -------------------------------------------------------------------------
    // returns true if data is set successfully else false

    /**
     * To write the data in a excel result sheet cell, using ColumnName. Return
     * true/False.
     *
     * @param sheetName
     * @param colName
     * @param rowNum
     * @param data
     * @return True/False
     */
    public boolean setCellData(String sheetName, String colName, int rowNum, String data) {
        try {
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);

            if (rowNum <= 0) return false;

            int index = workbook.getSheetIndex(sheetName);
            int colNum = -1;
            if (index == -1) return false;

            sheet = workbook.getSheetAt(index);

            row = sheet.getRow(0);
            for (int i = 0; i < row.getLastCellNum(); i++) {
                // System.out.println(row.getCell(i).getStringCellValue().trim());
                if (row.getCell(i).getStringCellValue().trim().equals(colName)) colNum = i;
            }
            if (colNum == -1) return false;

            sheet.autoSizeColumn(colNum);
            row = sheet.getRow(rowNum - 1);
            if (row == null) row = sheet.createRow(rowNum - 1);

            cell = row.getCell(colNum);
            if (cell == null) cell = row.createCell(colNum);

            // cell style
            // CellStyle cs = workbook.createCellStyle();
            // cs.setWrapText(true);
            // cell.setCellStyle(cs);
            cell.setCellValue(data);

            fileOut = new FileOutputStream(path);

            workbook.write(fileOut);

            fileOut.close();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // -------------------------------------------------------------------------

    /**
     * To write the data in a excel result file. Returns true or false.
     *
     * @param sheetName
     * @param colName
     * @param rowNum
     * @param data
     * @param url
     * @return True/false.
     */
    // returns true if data is set successfully else false
    public boolean setCellData(String sheetName, String colName, int rowNum, String data, String url) {
        /*// System.out.println("setCellData setCellData******************");
        try {
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);

            if (rowNum <= 0)
                return false;

            int index = workbook.getSheetIndex(sheetName);
            int colNum = -1;
            if (index == -1)
                return false;

            sheet = workbook.getSheetAt(index);
            // System.out.println("A");
            row = sheet.getRow(0);
            for (int i = 0; i < row.getLastCellNum(); i++) {
                // System.out.println(row.getCell(i).getStringCellValue().trim());
                if (row.getCell(i).getStringCellValue().trim().equalsIgnoreCase(colName))
                    colNum = i;
            }

            if (colNum == -1)
                return false;
            sheet.autoSizeColumn(colNum); // ashish
            row = sheet.getRow(rowNum - 1);
            if (row == null)
                row = sheet.createRow(rowNum - 1);

            cell = row.getCell(colNum);
            if (cell == null)
                cell = row.createCell(colNum);

            cell.setCellValue(data);
            XSSFCreationHelper createHelper = workbook.getCreationHelper();

            // cell style for hyperlinks
            // by default hypelrinks are blue and underlined
            CellStyle hlink_style = workbook.createCellStyle();
            XSSFFont hlink_font = workbook.createFont();
            hlink_font.setUnderline(XSSFFont.U_SINGLE);
            hlink_font.setColor(IndexedColors.BLUE.getIndex());
            hlink_style.setFont(hlink_font);
            // hlink_style.setWrapText(true);

            XSSFHyperlink link = createHelper.createHyperlink(XSSFHyperlink.LINK_FILE);
            link.setAddress(url);
            cell.setHyperlink(link);
            cell.setCellStyle(hlink_style);

            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);

            fileOut.close();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;*/
        try {
            this.fis = new FileInputStream(this.path);
            this.workbook = new XSSFWorkbook(this.fis);
            if (rowNum <= 0) {
                return false;
            } else {
                int index = this.workbook.getSheetIndex(sheetName);
                int colNum = -1;
                if (index == -1) {
                    return false;
                } else {
                    this.sheet = this.workbook.getSheetAt(index);
                    this.row = this.sheet.getRow(0);

                    for (int i = 0; i < this.row.getLastCellNum(); ++i) {
                        if (this.row.getCell(i).getStringCellValue().trim().equalsIgnoreCase(colName)) {
                            colNum = i;
                        }
                    }

                    if (colNum == -1) {
                        return false;
                    } else {
                        this.sheet.autoSizeColumn(colNum);
                        this.row = this.sheet.getRow(rowNum - 1);
                        if (this.row == null) {
                            this.row = this.sheet.createRow(rowNum - 1);
                        }

                        this.cell = this.row.getCell(colNum);
                        if (this.cell == null) {
                            this.cell = this.row.createCell(colNum);
                        }

                        this.cell.setCellValue(data);
                        XSSFCreationHelper createHelper = this.workbook.getCreationHelper();
                        CellStyle hlink_style = this.workbook.createCellStyle();
                        XSSFFont hlink_font = this.workbook.createFont();
                        hlink_font.setUnderline((byte) 1);
                        hlink_font.setColor(IndexedColors.BLUE.getIndex());
                        hlink_style.setFont(hlink_font);
                        XSSFHyperlink link = createHelper.createHyperlink(4);
                        link.setAddress(url);
                        this.cell.setHyperlink(link);
                        this.cell.setCellStyle(hlink_style);
                        this.fileOut = new FileOutputStream(this.path);
                        this.workbook.write(this.fileOut);
                        this.fileOut.close();
                        return true;
                    }
                }
            }
        } catch (Exception var12) {
            var12.printStackTrace();
            return false;
        }
    }

    // -------------------------------------------------------------------------

    /**
     * To create a excel sheet for the provided sheetname. Returns true/faluse
     * value.
     *
     * @param sheetname
     * @return boolean - true for pass, false for fail.
     */
    // returns true if sheet is created successfully else false
    public boolean addSheet(String sheetname) {

        FileOutputStream fileOut;
        try {
            workbook.createSheet(sheetname);
            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // -------------------------------------------------------------------------
    // returns true if sheet is removed successfully else false if sheet does
    // not exist

    /**
     * To Remove a excel sheet for the provided sheetname. Returns true/faluse
     * value.
     *
     * @param sheetName
     * @return boolean - true for pass, false for fail.
     */
    public boolean removeSheet(String sheetName) {
        int index = workbook.getSheetIndex(sheetName);
        if (index == -1) return false;

        FileOutputStream fileOut;
        try {
            workbook.removeSheetAt(index);
            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // -------------------------------------------------------------------------
    // returns true if column is created successfully

    /**
     * To add a column into the excelsheet.
     *
     * @param sheetName
     * @param colName
     * @return boolean - true/faluse.
     */
    public boolean addColumn(String sheetName, String colName) {
        // System.out.println("**************addColumn*********************");

        /*try {
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);
            int index = workbook.getSheetIndex(sheetName);
            if (index == -1)
                return false;

            XSSFCellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
            style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

            sheet = workbook.getSheetAt(index);

            row = sheet.getRow(0);
            if (row == null)
                row = sheet.createRow(0);

            if (row.getLastCellNum() == -1)
                cell = row.createCell(0);
            else
                cell = row.createCell(row.getLastCellNum());

            cell.setCellValue(colName);
            cell.setCellStyle(style);

            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
*/
        try {
            this.fis = new FileInputStream(this.path);
            this.workbook = new XSSFWorkbook(this.fis);
            int index = this.workbook.getSheetIndex(sheetName);
            if (index == -1) {
                return false;
            } else {
                XSSFCellStyle style = this.workbook.createCellStyle();
                style.setFillForegroundColor((short) 55);
                style.setFillPattern((short) 1);
                this.sheet = this.workbook.getSheetAt(index);
                this.row = this.sheet.getRow(0);
                if (this.row == null) {
                    this.row = this.sheet.createRow(0);
                }

                if (this.row.getLastCellNum() == -1) {
                    this.cell = this.row.createCell(0);
                } else {
                    this.cell = this.row.createCell(this.row.getLastCellNum());
                }

                this.cell.setCellValue(colName);
                this.cell.setCellStyle(style);
                this.fileOut = new FileOutputStream(this.path);
                this.workbook.write(this.fileOut);
                this.fileOut.close();
                return true;
            }
        } catch (Exception var5) {
            var5.printStackTrace();
            return false;
        }
    }

    // -------------------------------------------------------------------------

    /**
     * To remove a column after deleting the column data.
     *
     * @param sheetName
     * @param colNum
     * @return boolean - true/faluse.
     */
    // removes a column and all the contents
    public boolean removeColumn(String sheetName, int colNum) {
        /*try {
            if (!isSheetExist(sheetName))
                return false;
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);
            XSSFCellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
            XSSFCreationHelper createHelper = workbook.getCreationHelper();
            style.setFillPattern(HSSFCellStyle.NO_FILL);

            for (int i = 0; i < getRowCount(sheetName); i++) {
                row = sheet.getRow(i);
                if (row != null) {
                    cell = row.getCell(colNum);
                    if (cell != null) {
                        cell.setCellStyle(style);
                        row.removeCell(cell);
                    }
                }
            }
            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;*/

        try {
            if (!this.isSheetExist(sheetName)) {
                return false;
            } else {
                this.fis = new FileInputStream(this.path);
                this.workbook = new XSSFWorkbook(this.fis);
                this.sheet = this.workbook.getSheet(sheetName);
                XSSFCellStyle style = this.workbook.createCellStyle();
                style.setFillForegroundColor((short) 55);
                XSSFCreationHelper createHelper = this.workbook.getCreationHelper();
                style.setFillPattern((short) 0);

                for (int i = 0; i < this.getRowCount(sheetName); ++i) {
                    this.row = this.sheet.getRow(i);
                    if (this.row != null) {
                        this.cell = this.row.getCell(colNum);
                        if (this.cell != null) {
                            this.cell.setCellStyle(style);
                            this.row.removeCell(this.cell);
                        }
                    }
                }
                this.fileOut = new FileOutputStream(this.path);
                this.workbook.write(this.fileOut);
                this.fileOut.close();
                return true;
            }
        } catch (Exception var6) {
            var6.printStackTrace();
            return false;
        }

    }

    // -------------------------------------------------------------------------
    // find whether sheets exists

    /**
     * To Verify the sheet exists or not.
     *
     * @param sheetName
     * @return boolean - true/faluse.
     */
    public boolean isSheetExist(String sheetName) {
        int index = workbook.getSheetIndex(sheetName);
        if (index == -1) {
            index = workbook.getSheetIndex(sheetName.toUpperCase());
            if (index == -1) return false;
            else return true;
        } else return true;
    }

    // -------------------------------------------------------------------------
    // returns number of columns in a sheet

    /**
     * Returns the No of Coulums in a sheet.
     *
     * @param sheetName
     * @return NoOfrows count.
     */
    public int getColumnCount(String sheetName) {
        // check if sheet exists
        if (!isSheetExist(sheetName)) return -1;

        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(0);

        if (row == null) return -1;

        return row.getLastCellNum();
    }

    // -------------------------------------------------------------------------

    /**
     * To add a hyperlink in the sheet.
     *
     * @param sheetName
     * @param screenShotColName
     * @param testCaseName
     * @param index
     * @param url
     * @param message
     * @return boolean - true/faluse.
     */
    // String sheetName, String testCaseName,String keyword ,String URL,String
    // message
    public boolean addHyperLink(String sheetName, String screenShotColName, String testCaseName, int index, String url, String message) {
        // System.out.println("ADDING addHyperLink******************");

        url = url.replace('\\', '/');
        if (!isSheetExist(sheetName)) return false;

        sheet = workbook.getSheet(sheetName);

        for (int i = 2; i <= getRowCount(sheetName); i++) {
            if (getCellData(sheetName, 0, i).equalsIgnoreCase(testCaseName)) {
                // System.out.println("**caught "+(i+index));
                setCellData(sheetName, screenShotColName, i + index, message, url);
                break;
            }
        }

        return true;
    }

    // -------------------------------------------------------------------------

    /**
     * To get Cell row number in the specific sheet and in a column.
     *
     * @param sheetName
     * @param colName
     * @param cellValue
     * @return boolean - true/faluse.
     */
    public int getCellRowNum(String sheetName, String colName, String cellValue) {

        for (int i = 2; i <= getRowCount(sheetName); i++) {
            if (getCellData(sheetName, colName, i).equalsIgnoreCase(cellValue)) {
                return i;
            }
        }
        return -1;

    }

    // -------------------------------------------------------------------------
    // copy xls file

    /**
     * Copy a file from one location to another location.
     *
     * @param Destination
     * @throws IOException
     */
    public void copy_file(String Destination) throws IOException {
        File f1 = new File(path);
        File f2 = new File(Destination);
        FileInputStream fis = new FileInputStream(f1);
        OutputStream out = new FileOutputStream(f2);

        byte[] buf = new byte[1024];
        int len;
        while ((len = fis.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        fis.close();
        out.close();
    }

    // -----------------------------------DataReader----------------------------------

    public String[][] getExcelData(String sheetName) {
        String[][] arrayExcelData = null;
        int totalNoOfCols = getColumnCount(sheetName);
        int totalNoOfRows = getRowCount(sheetName);
        // System.out.println("Row count:" +totalNoOfRows);
        // System.out.println("Column count:" +totalNoOfCols);

        arrayExcelData = new String[totalNoOfRows - 1][totalNoOfCols];

        for (int i = 1; i < totalNoOfRows; i++) {

            for (int j = 0; j < totalNoOfCols; j++) {
                arrayExcelData[i - 1][j] = getCellData(sheetName, j, i + 1);
            }

        }
        return arrayExcelData;
    }
}