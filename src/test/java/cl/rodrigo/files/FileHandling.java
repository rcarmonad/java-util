package cl.rodrigo.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

public class FileHandling extends TestCase{
	
	private static Logger log = Logger.getLogger(FileHandling.class);
	
	public void testFile2String(){
		InputStream resource = this.getClass().getResourceAsStream("/log4j.properties");
		String str = "";
		try {
			str = inputStream2String(resource);
		} catch (IOException e) {
			log.error(e);
		}	
		log.info(str.toString());
	}
	
	private String file2String(File file) throws IOException{
		return inputStream2String(new FileInputStream(file));
	}
	
	private String inputStream2String(InputStream is) throws IOException{
		if(is != null){
			Writer writer = new StringWriter();
			char[] buffer = new char[1024];
			try{
				Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				int n;
				while((n = reader.read(buffer)) != -1){
					writer.write(buffer,0,n);
				}
			}finally{
				is.close();
			}
			return writer.toString();
		}
		return "";
	}
}
