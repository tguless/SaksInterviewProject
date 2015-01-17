package saks.interviewproject.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestPropUtil  {
	
	static Properties mProp  = LoadProps();
	
	public static Properties LoadProps() {
		Properties prop = new Properties();
		InputStream input = null;
	 
		try {
	 
			input = new FileInputStream("target/test-classes/test.properties");
			
			
	 
			// load a properties file
			prop.load(input);
	 
	 
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop;
	}
	
	public static String getProperty(String key) {
        
        String env = System.getProperty("runenv");
        
        if(env==null) env = new String(); else env = env+".";        
        env=env.toLowerCase();
        
        String result = mProp.getProperty(env+key);
        if (result == null || result.equals("")) {
        	result = System.getProperty(env+key);
        }
        
		return result;
	}

}
