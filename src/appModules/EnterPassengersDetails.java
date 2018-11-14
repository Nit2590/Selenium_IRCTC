package appModules;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Scanner;
//import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import automationFramework.GeneralOperations;
import automationFramework.Initialization;
import automationFramework.Report;
import automationFramework.RepositoryParser;
import utilities.ExcelUtils;

public class EnterPassengersDetails {

	public static void Execute(Initialization init) throws Exception
	{
		WebDriver driver=init.getDriver();
		Report repo=init.getObjReport();
		RepositoryParser parser=init.getObjConfig();
		ExcelUtils eUtils=init.getObjExcel();
		
		eUtils.setExcelFile("PassengerDetails");
		 int iRow=eUtils.getExcelRowCount();
		
		 
		 for(int icount=1;icount<=iRow;icount++)
		 {
			
			 Initialization.iObj=icount;
			 
			//Getting Details from Excel'
				String sName=eUtils.getCellData(icount,0);
				String sAge=eUtils.getCellData(icount,1);
				String sGender=eUtils.getCellData(icount,2);
				String sBerth=eUtils.getCellData(icount,3);
				String sNationality=eUtils.getCellData(icount,4);
				String sPhoneNo=eUtils.getCellData(icount,5);
			 
			 
			//Enter Passenger Details
			 
			  if (String.valueOf(icount).equals(driver.findElement(By.xpath("(((//*[@class='passengerDiv'])["+icount+"]//span)[2])")).getText()))
			  {
				  
				  if (GeneralOperations.checkElementExistence(parser.getElement("passengerName"), 5))
				  {
					  GeneralOperations.setEditbox(parser.getElement("passengerName"), sName);
					  repo.log.info("Successfully Entered Name As :"+sName);
				  }
				  
				  if (GeneralOperations.checkElementExistence(parser.getElement("passengerAge"), 5))
				  {
					  GeneralOperations.setEditbox(parser.getElement("passengerAge"), sAge);
					  repo.log.info("Successfully Entered Age As :"+sAge);
				  }
				  
				  if (GeneralOperations.checkElementExistence(parser.getElement("passengerGender"), 5))
				  {
					  GeneralOperations.SelectFromList(parser.getElement("passengerGender"), sGender,"ByText");
					  repo.log.info("Successfully Selected Gender As :"+sGender);
				  }
				  
				  if (GeneralOperations.checkElementExistence(parser.getElement("passengerBerth"), 5))
				  {
					  GeneralOperations.SelectFromList(parser.getElement("passengerBerth"), sBerth,"ByText");;
					  repo.log.info("Successfully Entered Berth As :"+sBerth);
				  }
				 

				  
			  }
			 
			  if ((iRow>1) && (icount<iRow) )
				{
					GeneralOperations.WebElementclick(parser.getElement("addPassenger"));
					 repo.log.info("Successfully Clicked on Add Passenger button");
					//driver.findElement(By.xpath("//*[text()='+ Add Passenger']")).click();
				}
			 
		 }
		 
		 
		 
		 Initialization.iObj=1;
		 
		 
		 //Select Auto -Upgradation
		 
		 GeneralOperations.WebElementclick(parser.getElement("autoUpgrade"));
		 repo.log.info("Successfully Selected AutoUpgrade Option");
		 
		//accept the terms and condition
		
		GeneralOperations.WebElementclick(parser.getElement("termsCond"));
		 
		 
		System.out.println("Enter the Captcha");
		
		 //Scanner sc= new Scanner(System.in);
		 //String str=sc.next();
		
		Thread.sleep(9000);
		
		GeneralOperations.WebElementclick(parser.getElement("continueBooking"));
		repo.log.info("Successfully Clicked on Continue Booking");
		
		//Verify the details
		
		WebElement WETrain=driver.findElement(By.xpath("(//*[text()='JHELUM EXPRESS'])[1]"));
		WebElement WEPass1=driver.findElement(By.xpath("(//*[text()='Nitish Sharma'])[2]"));
		WebElement WEPass2=driver.findElement(By.xpath("(//*[text()='Ashish jain'])[2]"));
		
		
		//assertEquals("JHELUM EXPRESS", driver.findElement(By.xpath(xpathExpression)));
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		
		
		if (WETrain.isDisplayed() && WEPass1.isDisplayed() && WEPass2.isDisplayed())
		{
			repo.log.info("Successfully Booked the Details");
		}
		
		
			
	}
}
