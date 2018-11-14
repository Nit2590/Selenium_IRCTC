package appModules;


import static org.testng.Assert.assertFalse;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import automationFramework.GeneralOperations;
import automationFramework.Initialization;
import automationFramework.Report;
import automationFramework.RepositoryParser;
import utilities.ExcelUtils;

public class FromToStation_Action {

	
	public static void Execute(Initialization init) throws Exception
	{
		WebDriver driver=init.getDriver();
		Report repo=init.getObjReport();
		RepositoryParser parser=init.getObjConfig();
		ExcelUtils eUtils=init.getObjExcel();
		
		
		eUtils.setExcelFile("TrainAndBerthDetails");	
		
			
			String sFromStation=eUtils.getCellData(1, 3);
			
			GeneralOperations.clickUsingCoordinates(parser.getElement("fromStation"));
						
	
			//Enter From Station
			GeneralOperations.KeysOperation(parser.getElement("fromStation"), sFromStation);
//			WebElement wE=parser.getElement("fromStation");

			GeneralOperations.WebElementclick(parser.getElement("fromList"));
			repo.log.info("Successfully Entered from Station :"+ sFromStation);
			
			
			String sToStation=eUtils.getCellData(1, 4);
			//Enter To Station
			GeneralOperations.KeysOperation(parser.getElement("toStation"),sToStation);
			GeneralOperations.WebElementclick(parser.getElement("toList"));
			repo.log.info("Successfully Entered to Station : "+sToStation);
			
			
			String sDate=eUtils.getCellData(1, 5);
			//Enter Date 
			GeneralOperations.setEditbox(parser.getElement("journeyDate"), sDate);
			
			repo.log.info("Successfully Entered Date : "+sDate);
			
			//Click on Submit
			//Enter Date 
			GeneralOperations.WebElementclick(parser.getElement("findTrains"));
			
			repo.log.info("Successfully Clicked FInd Trains Button");
			
			
			String stext=GeneralOperations.getStaticText(parser.getElement("trainsFound"),"");
			
			assertFalse(stext.startsWith("0 of"), "Failed to Search Trains");
			
			repo.log.info("Successfully Searched the result");
	}
	
	
}
