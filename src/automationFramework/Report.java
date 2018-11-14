package automationFramework;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class Report {
	
	public  Logger log;
	
	static{
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        System.setProperty("current.date.time", dateFormat.format(new Date()));
    }
	
	public Report()
	{
		
		
		
		try {
			log=Logger.getLogger(Report.class.getName());
			DOMConfigurator.configure("log4j.xml");
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
	

}
