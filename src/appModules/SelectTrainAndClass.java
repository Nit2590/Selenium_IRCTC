package appModules;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import automationFramework.GeneralOperations;
import automationFramework.Initialization;
import automationFramework.Report;
import automationFramework.RepositoryParser;
import utilities.ExcelUtils;

public class SelectTrainAndClass {
	
	static boolean bFlag=false;
	
	public static void Execute(Initialization init) throws Exception
	{
		WebDriver driver=init.getDriver();
		Report repo=init.getObjReport();
		RepositoryParser parser=init.getObjConfig();
		ExcelUtils eUtils=init.getObjExcel();
				
	
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		eUtils.setExcelFile("TrainAndBerthDetails");
		
		//selcting Number of Passengers
		String sNoOfPass=eUtils.getCellData(1,6);
		
		GeneralOperations.WebElementclick(parser.getElement("noOfPassenger"));
		Thread.sleep(500);
	     
		// Get all of the options
	        List<WebElement> opt = driver.findElements(By.xpath("//*[@id='numberOfPassengers']//ul/li"));
	        // Loop through the options and select the one that matches
	        
	        int size=opt.size();
	        
	        for(int i=1; i<=size;i++)
	        {
	        	String svalue=driver.findElement(By.xpath("//*[@id='numberOfPassengers']//ul/li["+i+"]/span")).getText();
	        
	        	if (svalue.contains(sNoOfPass)) 
	        	{
	        	
	        		driver.findElement(By.xpath("//*[@id='numberOfPassengers']//ul/li["+i+"]")).click();
	        		break;
	        	}
	
	        
	        }

	        //'Click on Journey checkbox And Select 3A,2A'
	        
	        
			//boolean bFlag=GeneralOperations.checkElement_IsSelected(parser.getElement("allClass"));
			boolean bFlag=driver.findElement(By.xpath("//*[@id='jcAll']")).isSelected();
			if (bFlag==true)
			{
				GeneralOperations.WebElementclick(parser.getElement("allClass"));
						//*[@for='jcAll']
			}
		     
			GeneralOperations.WebElementclick(parser.getElement("class2A"));
			GeneralOperations.WebElementclick(parser.getElement("class3A"));
			
			
			String stext=GeneralOperations.getStaticText(parser.getElement("trainsFound"),"");
			
			assertFalse(stext.startsWith("0 of"), "Failed to Search Trains");
			
			repo.log.info("Successfully Searched the result");
		
		
			String sTrain=eUtils.getCellData(1,1);
			List<WebElement> objTrains=parser.getElements("trainList");
			//List<WebElement> objTrains=driver.findElements(By.xpath("//*[contains(@class,'fromAndToStn')]//a/span"));
			
			int iRow=objTrains.size();
			int icount;
			for(icount=0;icount<=iRow-1;icount++)
			{
				if (sTrain.equalsIgnoreCase(objTrains.get(icount).getText()))
				{
					//icount=icount+2;
					break;
					
				}
			}
			
			icount=icount+2;
			int iCol=0;
			WebElement WE=driver.findElement(By.xpath("(//*[contains(@class,'fromAndToStn')])["+icount+"]//select"));
			GeneralOperations.SelectFromList(WE,"AC 3 Tier (3A)" ,"ByText");
			
			Thread.sleep(2000);
			icount=icount-1;
			WebElement ele=driver.findElement(By.xpath("(//*[@id='check-availability'])["+icount+"]"));
			
			GeneralOperations.WebElementclick(ele);
			
			//selecting the date on which seat is available
			if (driver.findElement(By.className("table")).isDisplayed())
			{
				iCol=driver.findElements(By.xpath("//*[@class='table']//tr/td")).size();
				
			}
			else
			{
				driver.close();
			}
			
			
			for(icount=1 ;icount<= iCol; icount++)
			{
				if(driver.findElement(By.xpath("(//*[@class='waitingstatus'])["+icount+"]")).getText().contains("AVAILABLE"))
				{
					driver.findElement(By.xpath("(//*[@class='table']//*[text()='Book Now'])["+icount+"]")).click();
					break;
				}
			}
			
			//Click on Confirmation (Ok Button)
			 driver.findElement(By.xpath("(//*[text()='Ok'])[3]")).click();
			 repo.log.info("Successfully Selected the Train with Availablr Seats");
	}
}
