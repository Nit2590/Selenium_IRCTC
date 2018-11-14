package automationFramework;



import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

//import javafx.scene.control.Alert;
//import testCases.TestCases;

public class GeneralOperations  {
	
	Initialization init;
	public  static WebDriver driver;
	Report repo;
	RepositoryParser parser;
	
	
	public GeneralOperations(Initialization objinit)
	{
		this.init=objinit;
		
		driver=init.getDriver();
		 repo=init.getObjReport();
		 parser=init.getObjConfig();
		
		
	}
	
	
	/**
	   * @Method: waitForElementPresence
	   * @Description: This is wrapper method wait for element presence
	   * @param locator - By identification of element
	   * @param waitInSeconds - wait time 
	   * @author  Creation Date: 27 April 2015   Modified Date: 
	   */
	  public static  void waitForElementPresence(WebElement element ,int waitInSeconds) 
	  {
	    try 
	    {
	      Wait<WebDriver> wait = new WebDriverWait(driver, waitInSeconds).ignoring(StaleElementReferenceException.class);
	      wait.until(ExpectedConditions.elementToBeClickable(element));
	    } 
	    catch(Exception exception)
	    {
	      exception.printStackTrace();
	    }
	  } 
	
	  
	  
	  /**
	   * @Method: KeysOperation
	   * @Description: This is wrapper method to set text for input element
	   * @param locator - By identification of element
	   * @param fieldValue - field value as string 
	   * @return - true if text entered successfully
	   * @author  Creation Date: 27 April 2015   Modified Date: 
	   */
	  public static boolean KeysOperation(WebElement element,String sText) 
	  {
	    waitForElementPresence(element, 10);
	    
	    try
	    {
	      // replace the text
	      element.click();
	      Thread.sleep(500);
	      element.clear();
	      element.sendKeys(sText);
	      
	      return true;
	    } 
	    catch (Exception exception)
	    {
	      exception.printStackTrace();
	      return false;
	    }
	  }
	  
	
	/**
	   * @Method: setEditbox
	   * @Description: This is wrapper method to set text for input element
	   * @param locator - By identification of element
	   * @param fieldValue - field value as string 
	   * @return - true if text entered successfully
	   * @author  Creation Date: 27 April 2015   Modified Date: 
	   */
	  public static boolean setEditbox(WebElement element,String sText) 
	  {
	    waitForElementPresence(element, 10);
	    
	    try
	    {
	      // replace the text
	      JavascriptExecutor executor = (JavascriptExecutor)driver;
	      executor.executeScript("arguments[0].click();", element);
	      element.clear();
	      element.sendKeys(sText);
	      element.sendKeys(Keys.TAB);
	      return true;
	    } 
	    catch (Exception exception)
	    {
	      exception.printStackTrace();
	      return false;
	    }
	  }
	
	  
	  /**
	   * @Method: checkElement_IsSelected
	   * @Description: This is wrapper method to check Element is Selected or not
	   * @param locator - By identification of element
	   * @param waitInSeconds - wait time 
	   * @return - true if element present  
	   * @author  Creation Date: 18-Dec-2013   Modified Date: 
	   */
	  public static boolean checkElement_IsSelected(WebElement element)
	  {
	    
		  waitForElementPresence(element, 20);
		 try {
	      return element.isSelected();
	    }
	    catch(Exception exception)
	    {
	      exception.printStackTrace(); 
	      return false;
	    }
	  }

	  
	
	  /**
	   * @Method: checkElement_Existance
	   * @Description: This is wrapper method to check the existence of any web element on the page
	   * @param locator - By identification of element
	   * @param waitInSeconds - wait time 
	   * @return - true if element present  
	   * @author  Creation Date: 18-Dec-2013   Modified Date: 
	   */
	  public static boolean checkElementExistence(WebElement element, int sTimeInSecond)
	  {
	    
		  waitForElementPresence(element, 20);
		 try {
	      return element.isDisplayed();
	    }
	    catch(Exception exception)
	    {
	      exception.printStackTrace(); 
	      return false;
	    }
	  }

	  
	  /**
	   * @Method: click
	   * @Description: This is wrapper method to click on web element 
	   * @param locator - By identification of element
	   * @return - true if click successful
	   * @author   Creation Date: 27 April 2015   Modified Date: 
	   */
	  public static boolean WebElementclick(WebElement element) 
	  {
	    waitForElementPresence(element, 10);
	   
	    try
	    {
	      JavascriptExecutor executor = (JavascriptExecutor)driver;
	      executor.executeScript("arguments[0].click();", element);
	    	//element.click();
	      return true;
	    } 
	    catch (Exception exception)
	    {
	      exception.printStackTrace();
	      return false;
	    }
	  }

	  /**
	   * @Method: doubleClick
	   * @Description: This is wrapper method used for doubleClick on element
	   * @param locator - By identification of element
	   * @return - true if double click successful
	   * @author Automation Tester (SQS) Creation Date: 27 April 2015   Modified Date: 
	   */
	  public static boolean WebElementdoubleClick(WebElement element)
	  {
	    waitForElementPresence(element, 10);
	    
	    try
	    {
	      Actions actionBuilder = new Actions(driver);
	      actionBuilder.doubleClick(element).build().perform();
	      return true;
	    } 
	    catch (Exception exception)
	    {
	      exception.printStackTrace();
	      return false;
	    }
	  }
	  
	  
	  public static String getStaticText(WebElement element, String textBy) 
	  {
	    waitForElementPresence(element, 10);
	     
	    if (textBy.isEmpty())
		    {
		    	return element.getText();
		    }
	    else
		    {		
			    try
			    {
			      String strText = "";
				    if(textBy.equals("value"))
				      {
				        strText = element.getAttribute("value");
				      
				        return strText;
				      }
			    } 
			    catch (Exception exception)
			    {
			      exception.printStackTrace();
			      return null;
			    }
		    }
		return "";
	  }
	
	
	  /**
	   * @Method: selectCheckBox
	   * @Description: This is wrapper method select/deselect checkbox
	   * @param locator - By identification of element
	   * @param status - select/deselect 
	   * @author   Creation Date: 27 April 2015   Modified Date: 
	   */
	  public static boolean selectCheckBox(List<WebElement> elements, String sValue,boolean status) 
	  {
	   
	   
	    //int icount=elements.size();
		
		for (WebElement objEle : elements)    //               (int i=0 ; i<=icount; i++)
		{
			
			if (sValue.equalsIgnoreCase(objEle.getAttribute("value")))
			{
				try
			    {
			      if(objEle.getAttribute("type").equals("checkbox"))   
			      {
			        if((objEle.isSelected() && !status) || (!objEle.isSelected() && status))
			          objEle.click();
			        return true;
			      }
			      else
			        return false;
			    } 
			    catch (Exception exception)
			    {
			      exception.printStackTrace();
			      return false;
			    }
			}
			
		}
		
		return false;
	    
	    
	    
	    
	  }

	  /**
	   * @Method: isCheckBoxSelected
	   * @Description: This is wrapper checkbox is selected or not
	   * @param locator - By identification of element
	   * @author   Creation Date: 27 April 2015   Modified Date: 
	   */
	  public static boolean isCheckBoxSelected(List<WebElement> elements,String sValue, boolean status) 
	  {
	    
	    
	    boolean state = false;
	    
	    //int icount=elements.size();
		
		for (WebElement objEle : elements)    //               (int i=0 ; i<=icount; i++)
		{
			
			if (sValue.equalsIgnoreCase(objEle.getAttribute("value")))
			{
				try
			    {
					  state = objEle.isSelected();

				      return state;

			      
			    } 
			    catch (Exception exception)
			    {
			      exception.printStackTrace();
			      return false;
			    }
			}
	    
	     }
		return false;
	  }

	 


	  /**
	   * @Method: selectRadio
	   * @Description: This is wrapper method select/deselect radio button
	   * @param locator - By identification of element
	   * @param status - select/deselect 
	   * @author  Creation Date: 27 April 2015   Modified Date: 
	   */
	  public static boolean selectRadioButton(List<WebElement> elements ,String sValue , boolean status)
	  {
		  //int icount=elements.size();
			
			for (WebElement objEle : elements)    //               (int i=0 ; i<=icount; i++)
			{
				
				if (sValue.equalsIgnoreCase(objEle.getAttribute("value")))
				{
				    try
				    {
				      if(objEle.getAttribute("type").equals("radio"))   
				      {
				        if((objEle.isSelected() && !status) || (!objEle.isSelected() && status))
				        	{
				        		objEle.click();
				        	
				        		return true;
				        	}
				      }
				      else
				        return false;
				    } 
					    catch (Exception exception)
					    {
					      exception.printStackTrace();
					      return false;
					    }
				}
			}
			return false;
	  }
	  
		
	  /**
	   * @Method: mouseHover
	   * @Description: This is wrapper method used for Mouse Hovering to the element
	   * @param locator - By identification of element
	   * @author  Creation Date: 27 April 2015   Modified Date: 
	   */
	  public static boolean mouseHover(WebElement element)
	  {
	    waitForElementPresence(element, 10);
	    
	    try
	    {
	      Actions actionBuilder = new Actions(driver);
	      actionBuilder.moveToElement(element).build().perform();
	      return true;
	    } 
	    catch (Exception exception)
	    {
	      exception.printStackTrace();
	      return false;
	    }
	  }
		
		//handle Alert
		
		public static void handleAlert(WebDriver driver)
		{
			org.openqa.selenium.Alert switchAlert=driver.switchTo().alert();
			
			switchAlert.accept();
			
		}
		
		
		//Selcting From List
		public static void SelectFromList(WebElement element,String sValue, String SelectingType)
		{
			
			Select objslct=new Select(element);
			
			switch (SelectingType)
			{
				
				case "ByValue"  :
					objslct.selectByValue(sValue);
					break;
				
				case "ByText"  :
					objslct.selectByVisibleText(sValue);
					break;
					
			}
			
			
		}
		
		
		/**
		   * @Method: switchToWindowUsingTitle
		   * @Description: This is wrapper method used switch to window using the given title
		   * @param locator - Window title
		   * @author  Creation Date: 27 April 2015   Modified Date: 
		   */
		  public static boolean switchToWindowUsingTitle(String windowTitle)
		  {
		    try
		    {
		      String mainWindowHandle = driver.getWindowHandle();
		      Set<String> openWindows = driver.getWindowHandles();

		      if (!openWindows.isEmpty()) 
		      {
		        for (String windows : openWindows) 
		        {
		          String window = driver.switchTo().window(windows).getTitle();
		          if (windowTitle.equals(window)) 
		            return true;
		          else 
		            driver.switchTo().window(mainWindowHandle);
		        }
		      }
		      return false;
		    } 
		    catch (Exception exception)
		    {
		      exception.printStackTrace();
		      return false;
		    }
		  }
		
		
		  /**
		   * @Method: selectCheckBox
		   * @Description: This is wrapper method select drop down element
		   * @param locator - By identification of element
		   * @param option - drop down element (user may specify text/value/index)
		   * @param selectType - select dorp down element by Text/Value/Index
		   * @author   Creation Date: 27 April 2015   Modified Date: 
		   */
		  public static boolean selectDropDownOption(WebElement element, String option, String... selectType) 
		  {
		    try
		    {
		     
		      Select sltDropDown = new Select(element);

		      if(selectType.length > 0 && !selectType[0].equals(""))
		      {
		        if(selectType[0].equals("Value"))
		          sltDropDown.selectByValue(option);
		        else if(selectType[0].equals("Text"))
		          sltDropDown.selectByVisibleText(option);
		        else if(selectType[0].equals("Index"))
		          sltDropDown.selectByIndex(Integer.parseInt(option));

		        return true;
		      }
		      else
		      {
		        // Web elements from dropdown list 
		        List<WebElement> options = sltDropDown.getOptions();
		        boolean blnOptionAvailable = false;
		        int iIndex = 0;
		        for(WebElement weOptions : options)  
		        {  
		          if (weOptions.getText().trim().equals(option))
		          {
		            sltDropDown.selectByIndex(iIndex);
		            blnOptionAvailable = true;
		          }
		          else
		            iIndex++;
		          if(blnOptionAvailable)
		            break;
		        }
		        if(blnOptionAvailable)
		          return true;
		        else
		          return false;
		      }
		    } 
		    catch (Exception exception)
		    {
		      exception.printStackTrace();
		      return false;
		    }
		  }

		  /**
		   * @Method: getSelectedValueFormDropDown
		   * @Description: This is wrapper method select drop down element
		   * @param locator - By identification of element
		   * @author   Creation Date: 27 April 2015   Modified Date: 
		   */
		  public String getSelectedValueFormDropDown(WebElement element) 
		  {
		    try
		    {
		      waitForElementPresence(element, 10);
		      Select selectDorpDown = new Select(element);
		      String selectedDorpDownValue = selectDorpDown.getFirstSelectedOption().getText();
		      return selectedDorpDownValue;
		    }
		    catch (Exception exception)
		    {
		      exception.printStackTrace();
		      return null;
		    }

		  }

		  /**
		   * @Method:clickOnElementUsingCoordinates
		   * @Description: This is wrapper method which clicks on element on coordinate basis
		   * @param locator - By identification of element
		   * @author   Creation Date: 27 April 2015   Modified Date: 
		   */
		  public static void clickUsingCoordinates(WebElement element)
		  {
			  waitForElementPresence(element, 10);
			  
			  	Point point = element.getLocation();

				// Store value of elements as pixels in integers x and y

				int x = point.getX();

				//System.out.println("Horizontal Position: " + x + " pixels");

				int y = point.getY();

				//System.out.println("Vertical Position " + y + " pixels");

				Actions action = new Actions(driver);

				action.moveToElement(element, x, y).click().build().perform(); 
			  
				
		  }
}


