package automationFramework;

import java.util.Hashtable;
//import java.util.Properties;

import org.openqa.selenium.WebDriver;

import utilities.ExcelUtils;

public class Pojo {
	
	private WebDriver webDriver;
	  private RepositoryParser objConfig;
	  private Report objReport;
	  private ExcelUtils objExcel;
	  private Hashtable<String , String> dataPoolHashTable = new Hashtable<String, String>();

	  public void setDriver(WebDriver webDriver)
	  {
	    this.webDriver = webDriver;
	  }
	  
	  public WebDriver getDriver()
	  {
	    return webDriver;
	  }
	  
	  public void setObjConfig(RepositoryParser objConfig)
	  {
	    this.objConfig = objConfig;
	  }
	  
	  
	  public RepositoryParser getObjConfig()
	  {
	    return objConfig;
	  }
	  
	  public void setobjReport(Report repo)
	  {
	    this.objReport = repo;
	  }
	  
	  
	  public Report getObjReport()
	  {
	    return objReport;
	  }
	  
	  
	  public void setDataPoolHashTable(Hashtable<String, String> dataPoolHashTable)
	  {
	    this.dataPoolHashTable = dataPoolHashTable;
	  }
	  
	  public Hashtable<String, String> getDataPoolHashTable()
	  {
	    return dataPoolHashTable;
	  }
	  
	  public void setobjExcel(ExcelUtils excel)
	  {
	    this.objExcel = excel;
	  }
	  
	  
	  public ExcelUtils getObjExcel()
	  {
	    return objExcel;
	  }
}
