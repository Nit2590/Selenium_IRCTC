package appModules;

import static org.testng.Assert.assertTrue;

import java.util.Scanner;
//import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Level;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import automationFramework.GeneralOperations;
import automationFramework.Initialization;
import automationFramework.Report;
import automationFramework.RepositoryParser;
import utilities.ExcelUtils;

public class SignIn_Action {
	
	//Getting Objects
	
	
	public static void Execute( Initialization init) throws Exception
	{
		WebDriver driver=init.getDriver();
		Report repo=init.getObjReport();
		RepositoryParser parser=init.getObjConfig();
		ExcelUtils eUtils=init.getObjExcel();
			repo.log.setLevel(Level.INFO);
			driver.get("https://www.irctc.co.in/eticketing/loginHome.jsf");
			repo.log.info("Successfully navigated to IRCTC");
		
			eUtils.setExcelFile("LoginDetails");
		
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			
			GeneralOperations.WebElementclick(parser.getElement("sidePanel"));
			GeneralOperations.WebElementclick((parser.getElement("login")));
			
			
			assertTrue(parser.getElement("userName").isDisplayed());
		
			repo.log.info("Login Page is Displayed");
			
			String userName=eUtils.getCellData(1, 0);
			GeneralOperations.setEditbox(parser.getElement("userName"), userName);
			repo.log.info("Successfully Entered UserName as : "+userName);
			
			
			String sPassword=eUtils.getCellData(1, 1);
			GeneralOperations.setEditbox(parser.getElement("password"), sPassword);
			repo.log.info("Successfully Entered Password ");
			
			
			
			
			// enter Captcha
			
			System.out.println("enter Captcha");
			
			
			//Scanner sc= new Scanner(System.in);
			
			//String sCaptcha=sc.next();
			Thread.sleep(9000);
			/*try {
			GeneralOperations.setEditbox(parser.getElement("captcha"), sCaptcha);
			}
			catch(Exception e)
			{
				GeneralOperations.setEditbox(parser.getElement("captchaImage"), sCaptcha);
		
			
			repo.log.info("Successfully Entered Captcha as  "+ sCaptcha);
			*/
			
			GeneralOperations.WebElementclick(parser.getElement("signIn"));
			
			Thread.sleep(1000);
			repo.log.info("Successfully Clicked SignIn Button");
			
			//checking user is successfully logged in or not
			GeneralOperations.WebElementclick(parser.getElement("sidePanel"));
			
			Assert.assertTrue(GeneralOperations.getStaticText(parser.getElement("loginUserName"),"").contains("Hello, Nitish Sharma"));
			repo.log.info("Successfully logged in : Nitish Sharma ");
			
		
	}

}

