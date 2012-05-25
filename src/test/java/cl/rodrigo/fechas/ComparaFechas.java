package cl.rodrigo.fechas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;

import junit.framework.TestCase;

public class ComparaFechas extends TestCase{
	
	private static Logger log = Logger.getLogger(ComparaFechas.class);
	
	public void test(){
		String date = "20120425";
		String mask = "yyyyMMdd";
		log.info(compareWithToday(date, mask));
	}
	
	// Compara HOY con una fecha
	// 0 Sin son iguales
	// 1 si hoy es mayor
	// -1 si fecha es mayor
	private int compareWithToday(String date, String mask){
		Calendar cal1 = String2Calendar(date, mask);
		Calendar today = getToday(mask);
		log.info("cal1 : " + cal1.getTimeInMillis());
		log.info("today : " + today.getTimeInMillis());
		return today.compareTo(cal1);
	}
	
	// Convierte String a Calendar segun la mascara ingresada
	private Calendar String2Calendar(String date, String format){
		Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat(format);
	    try {
			cal.setTime(sdf.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    return cal;
	}
	
	// Obtiene hoy segun la mascara que se necesita
	private Calendar getToday(String mask){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(mask);
		String date = sdf.format(cal.getTime());
		return String2Calendar(date, mask);
	}
	
	private String getTodayAAAAMMDD(){
		Calendar cal = Calendar.getInstance();
		String YYYYMM = String.valueOf(cal.get(Calendar.YEAR)) + String.valueOf(cal.get(Calendar.MONTH));
		String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
		String DD = (day.length() == 1) ? "0"+day : day;
		return YYYYMM+DD;
	}
	
}