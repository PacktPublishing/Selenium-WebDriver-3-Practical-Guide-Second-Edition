package com.example;

import java.io.*;
import java.util.*;

import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class SpreadsheetData {
    public String[][] getCellData(String path) throws InvalidFormatException, IOException {
        System.out.println(path);
        FileInputStream stream = new FileInputStream(path);
        Workbook workbook = WorkbookFactory.create(stream);
        System.out.println(workbook);
        Sheet s = workbook.getSheetAt(0);
        System.out.println(s);
        int rowcount = s.getLastRowNum();
        int cellcount = s.getRow(0).getLastCellNum();
        System.out.println(rowcount);
        String data[][] = new String[rowcount][cellcount];
        for (int rowCnt = 1; rowCnt <= rowcount; rowCnt++) {
            Row row = s.getRow(rowCnt);
            for (int colCnt = 0; colCnt < cellcount; colCnt++) {
                Cell cell = row.getCell(colCnt);
                try {
                    if (cell.getCellType() == cell.CELL_TYPE_STRING) {
                        data[rowCnt - 1][colCnt] = cell.getStringCellValue();
                    } else {
                        data[rowCnt - 1][colCnt] = String.valueOf(cell.getNumericCellValue());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return data;
    }
}
