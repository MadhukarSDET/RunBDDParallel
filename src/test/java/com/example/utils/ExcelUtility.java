package com.example.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	public static List<Map<String, String>> getTestData(String filePath , String sheetName) {
		List<Map<String, String>> dataList = new ArrayList<>();

		XSSFWorkbook workbook = null;
		XSSFRow headerRow;

		try {
			workbook = new XSSFWorkbook(filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		XSSFSheet sheet = workbook.getSheet(sheetName);
		headerRow = sheet.getRow(0);
		int rowsCount = sheet.getPhysicalNumberOfRows();
		int columnCount = sheet.getRow(0).getPhysicalNumberOfCells();

		for (int i = 1; i < rowsCount; i++) {

			HashMap<String, String> data = new HashMap<>();
			for (int j = 0; j < columnCount; j++) {
				String key = headerRow.getCell(j).getStringCellValue();
				data.put(key,sheet.getRow(i).getCell(j).getStringCellValue());
						
			}
			dataList.add(data);
		}
		return dataList;
	}

}
