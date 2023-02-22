package com.ems.EmployeeManage.helper;

import com.ems.EmployeeManage.controller.EmployeeController;
import com.ems.EmployeeManage.entity.Employee;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String SHEET = "Worksheet";
    public static boolean hasExcelFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public static List<Employee> excelToEmployees(InputStream is) {
        Logger logger = LoggerFactory.getLogger(EmployeeController.class);
        List<Employee> employees = new ArrayList<Employee>();
        try {

            XSSFWorkbook workbook = new XSSFWorkbook(is);
            XSSFSheet sheet = workbook.getSheet(SHEET);

            //get error when we not set to null
            if(sheet==null)
            {
                logger.info("Sheet is empty ~|~ file error");
                return employees;
            }

            Iterator<Row> rows = sheet.iterator();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // we not need the title of the columns
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Employee employee = new Employee();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0 -> {
                                employee.setEmail(String.valueOf(currentCell.getStringCellValue()));
                                break;
                            }

                        case 1 -> {
                            employee.setEmpCode((int) currentCell.getNumericCellValue());
                            break;
                        }
                        case 2 -> {
                            employee.setFirstName(String.valueOf(currentCell.getStringCellValue()));
                            break;
                        }
                        case 3 -> {
                            employee.setLastName(String.valueOf(currentCell.getStringCellValue()));
                            break;
                        }
                        case 4 -> {
                            employee.setPhone((int) currentCell.getNumericCellValue());
                            break;
                        }
                        default -> {
                        }
                    }
                    cellIdx++;
                }
                employees.add(employee);
            }
            workbook.close();

            return employees;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
