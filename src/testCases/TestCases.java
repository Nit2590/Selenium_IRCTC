package testCases;

import static org.testng.Assert.fail;

//import java.io.IOException;

//import org.apache.log4j.Level;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest; 
import org.testng.annotations.Test;

import appModules.*;
import appModules.SelectTrainAndClass;
import appModules.SignIn_Action;
import automationFramework.BrowserFactory;
import automationFramework.Initialization;
import automationFramework.Pojo;
import automationFramework.Report;
import automationFramework.RepositoryParser;

public class TestCases {
  
	WebDriver driver;
	RepositoryParser parser;
	Pojo pojo;
	Initialization init;
	//public static Logger log;

	Report repo=new Report();
	
	@BeforeTest
	public void Initialize() throws Exception
	{
		try {
				init=new Initialization();
				init.initialize();
		    }
		catch(Exception e )
		{
			e.printStackTrace();
		}
	}
	
	
	@Test
  public void Login() throws InterruptedException 
	{
		
	 
		try {
			SignIn_Action.Execute(init);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	     
	}
	
	@Test(dependsOnMethods={ "Login" })
	  public void EnterTrainDetails() throws InterruptedException 
		{
			
		  try {
			  FromToStation_Action.Execute(init);
		  } catch (Exception e)
		     
		  {
			  e.printStackTrace();
		  }
		}
	
	@Test(dependsOnMethods={ "EnterTrainDetails" })
	  public void SelctTrainAndClassType() throws InterruptedException 
		{
			
		  try {
			  SelectTrainAndClass.Execute(init);
		  } catch (Exception e)
		     
		  {
			  e.printStackTrace();
		  }
		}
	
	@Test(dependsOnMethods={ "SelctTrainAndClassType" })
	  public void EnterPassengersDetails() throws InterruptedException 
		{
			
		  try {
			  EnterPassengersDetails.Execute(init);
		  } catch (Exception e)
		     
		  {
			  e.printStackTrace();
		  }
		}
	
	
	
	@AfterTest
	public void ExitTest()
	{
		 BrowserFactory.Closedriver();
		 init.getObjExcel().closeExcel();
	}
	
	
}
