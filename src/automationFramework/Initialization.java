package automationFramework;

import java.io.IOException;

import org.apache.log4j.Level;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;

import utilities.ExcelUtils;

public class Initialization extends Pojo {
	
	WebDriver driver;
	RepositoryParser parser;
	Pojo pojo;
	GeneralOperations objGenOpr;
	ExcelUtils objUtils;
	
	public static  int iObj=1;
	
	
	public void  initialize()
	{
		//setting the driver object
		driver=BrowserFactory.getDriver("Chrome");
		this.setDriver(driver);
		
		//setting Report Object
		Report repo=new Report();
		this.setobjReport(repo);
		
		
		//Repository Parser
		
		
	
			//DOMConfigurator.configure("log4j.xml");
		try {
			parser=new RepositoryParser(this,"D:\\SeleniumFramework\\IRCTC\\src\\pageObjects\\ObjectRepo.properties");
			this.setObjConfig(parser);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			repo.log.setLevel(Level.ERROR );
			repo.log.error("Unable to Configure Chrome");
			e.printStackTrace();
		}
	
			objGenOpr=new GeneralOperations(this);
	
		try {
			objUtils=new ExcelUtils("D:\\SeleniumFramework\\IRCTC\\src\\testData\\TestData.xlsx");
			this.setobjExcel(objUtils);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			repo.log.setLevel(Level.ERROR );
			repo.log.error("Unable to Configure Excel Object");
			e.printStackTrace();
		}
	
	
	}
}
