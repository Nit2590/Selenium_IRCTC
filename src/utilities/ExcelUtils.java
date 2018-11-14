package utilities;

import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
//import java.io.InputStream;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
//import org.apache.poi.hssf.model.InternalWorkbook;
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.openxml4j.opc.OPCPackage;
//import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtils {
	 
	private static XSSFSheet ExcelWSheet;

	private static XSSFWorkbook ExcelWBook;

	private static XSSFCell Cell;

	private static XSSFRow Row;
	
	public static FileOutputStream fileOut ;

//This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method
public ExcelUtils(String Path) throws InvalidFormatException, IOException
{
	//FileInputStream ExcelFile=new FileInputStream(Path);
	File file = new File(Path);
	OPCPackage opcPackage = OPCPackage.open(file);
	///XSSFWorkbook workbook = new XSSFWorkbook(opcPackage);
	

	//ExcelWBook = new XSSFWorkbook(ExcelFile);
	 ExcelWBook = new XSSFWorkbook(opcPackage);
}
public  void setExcelFile(String SheetName) throws Exception {

					
		
		try {
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		//fileOut=new FileOutputStream(Path);
}

//This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num

public  String getCellData(int RowNum, int ColNum) throws Exception{

	try{

		Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
		Cell.setCellType(org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING);

		String CellData = Cell.getStringCellValue();

			return CellData;

		}catch (Exception e){

			return"";

		}
			

}


//this method is Used to get row count
public  int getExcelRowCount()
{
	int RowCount=ExcelWSheet.getLastRowNum()-ExcelWSheet.getFirstRowNum();
	return RowCount;
}

//this method is Used to get  column Count
public  int getExcelColCount()
{
	int ColCount=ExcelWSheet.getRow(0).getLastCellNum();
	return ColCount;
}


//This method is to write in the Excel cell, Row num and Col num are the parameters

public  void setCellData(String Result,  int RowNum, int ColNum) throws Exception	{

		try{

			Row  = ExcelWSheet.getRow(RowNum);

		Cell = Row.getCell(ColNum);

		if (Cell == null) {

			Cell = Row.createCell(ColNum);

			Cell.setCellValue(Result);

			} else {

				Cell.setCellValue(Result);

			}

				//Updating Excel  	

				ExcelWBook.write(fileOut);

				fileOut.flush();

				fileOut.close();

			}catch(Exception e){

				throw (e);

		}
 }



public  void closeExcel()
{
	try {
		ExcelWBook.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}



}


	
	

