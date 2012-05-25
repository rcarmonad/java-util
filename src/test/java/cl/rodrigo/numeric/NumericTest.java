package cl.rodrigo.numeric;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

public class NumericTest extends TestCase{
	
	private static Logger log = Logger.getLogger(NumericTest.class);
	
	public void test(){
		String number = "999999999999999999";
		log.info("isInterger("+number+") : " + isInterger(number));
		log.info("isLong("+number+") : " + isLong(number));
	}
	
	public boolean isInterger(String number){
		try{
			Integer.parseInt(number);
			return true;
		}catch(NumberFormatException e){
			return false;
		}
	}
	
	public boolean isLong(String number){
		try{
			Long.parseLong(number);
			return true;
		}catch(NumberFormatException e){
			return false;
		}
	}

}
