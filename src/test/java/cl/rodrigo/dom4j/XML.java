package cl.rodrigo.dom4j;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Namespace;

public class XML extends TestCase{
	
	private static Logger log = Logger.getLogger(XML.class);
	
	public void test(){
		log.info(getDetailString());
	}
	
	public String getDetailString(){
		Namespace con = new Namespace("con", "http://www.bancochile.cl");
		Document document = DocumentHelper.createDocument();
		Element detail = document.addElement("detail");
		
		Element fault = detail.addElement("fault");
		fault.add(con);
		
		Element errorCode = fault.addElement("errorCode");
		errorCode.addText("errorCode_string");
		
		Element reason = fault.addElement("reason");
		reason.addText("reason_string");
		
		Element idTransaccionNegocio = fault.addElement("idTransaccionNegocio");
		idTransaccionNegocio.addText("0303456");
		
		return document.asXML();
	}

}
