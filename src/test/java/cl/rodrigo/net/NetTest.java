package cl.rodrigo.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

public class NetTest extends TestCase{
	
	private static Logger log = Logger.getLogger(NetTest.class);
	
	public void test(){
		ping("bchqaservers");
	}
	
	private boolean ping(String serverName){
		try {
			InetAddress result = InetAddress.getByName(serverName);
			log.info(result.getHostAddress() + " - " + result.getHostName() + " found");
			return true;
		} catch (UnknownHostException e) {
			log.error(e);
			return false;
		}
	}

}
