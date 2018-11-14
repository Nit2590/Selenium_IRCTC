package automationFramework;

import java.io.*;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class RepositoryParser {
	
	private FileInputStream stream;
	private String RepositoryFile;
	private Properties property= new Properties();
	private Initialization init;
	
	
	
	public RepositoryParser(Initialization init ,String fileName) throws IOException
	{
		this.init=init;
		this.RepositoryFile=fileName;
		stream=new FileInputStream(this.RepositoryFile);
	
		property.load(stream);
	}
	
	
	public By getOjectLocator(String locatorName)
	{
		String locatorProperty=property.getProperty(locatorName);
		String locatorType=locatorProperty.split(":", 2)[0];
		String locatorValue=locatorProperty.split(":",2)[1];
		
		//System.out.println(locatorType+" : "+locatorValue);
		
		By locator = null;
		switch(locatorType)
		{
		case "Id":
			locator = By.id(locatorValue);
			break;
		case "Name":
			locator = By.name(locatorValue);
			break;
		case "CssSelector":
			locator = By.cssSelector(locatorValue);
			break;
		case "LinkText":
			locator = By.linkText(locatorValue);
			break;
		case "PartialLinkText":
			locator = By.partialLinkText(locatorValue);
			break;
		case "TagName":
			locator = By.tagName(locatorValue);
			break;
		case "Xpath":
			locator = By.xpath("("+locatorValue+")["+Initialization.iObj+"]");
			break;
		case "Xpaths":  //to support webElements
			locator = By.xpath(locatorValue);
			break;
		
		}
		return locator;
	}
	
	//get Element
	public WebElement getElement(String locatorName)
	{
		
		return init.getDriver().findElement(this.getOjectLocator(locatorName));
	}
	
	//get Elements
	public List<WebElement> getElements(String locatorName)
	{
		
		return init.getDriver().findElements(this.getOjectLocator(locatorName));
	}
	
	
	
}
