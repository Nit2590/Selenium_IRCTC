package automationFramework;

import java.util.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;

public class BrowserFactory {
	
	private static Map<String,WebDriver> drivers=new HashMap<String,WebDriver>();
	
	public static WebDriver getDriver(String BrowserName)
	{
		WebDriver driver=null;
		
		switch(BrowserName)
		{
			case "Chrome":
				driver=drivers.get("Chrome");
				if (driver==null)
				{
					System.setProperty("webdriver.chrome.driver", "D:\\SeleniumFramework\\chromedriver_win32\\chromedriver.exe");
					
					//Creating Chrome instance
					ChromeOptions options=new ChromeOptions();
					options.addArguments("--start-maximized");
					driver=new ChromeDriver(options);
				
					drivers.put("Chrome", driver);
				}
				
		
		}
		
		return driver;
	}
	
	public static  void Closedriver()
	{
		/*for(String key : drivers.keySet())
		{
			drivers.get(key).close();
			drivers.get(key).quit();
		}*/
	}
	
	
	

}
