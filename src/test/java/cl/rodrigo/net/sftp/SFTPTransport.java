package cl.rodrigo.net.sftp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import junit.framework.TestCase;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SFTPTransport extends TestCase {

	private static Logger log = Logger.getLogger(SFTPTransport.class);

	private static String priveteKeyPath = "/home/rodrigo/.ssh/id_rsa";
	private static String publicKeypath = "/home/rodrigo/.ssh/id_rsa.pub";
	
	public void test(){
		listFiles("sftpuser", "localhost", "deposito");
	}

	private void setupSftpIdentity(JSch jsch, String username) {
		try {
			byte[] privateKey = IOUtils.toByteArray(new FileInputStream(
					priveteKeyPath));
			byte[] publicKey = IOUtils.toByteArray(new FileInputStream(
					publicKeypath));

			byte[] passphrase = "asdf".getBytes();
			jsch.addIdentity(username, privateKey, publicKey, passphrase);
		} catch (FileNotFoundException e) {
			log.error(e);
		} catch (IOException e) {
			log.error(e);
		} catch (JSchException e) {
			log.error(e);
		}
	}

	private void listFiles(String username, String host, String path) {
		try {
			JSch jsch = new JSch();
//			JSch.setLogger(new JschLogger());
			setupSftpIdentity(jsch, username);

			Session session = jsch.getSession(username, host, 22);
			session.setConfig("StrictHostKeyChecking", "no");
			log.info("Connected to SFTP server");
			session.connect();

			Channel channel = session.openChannel("sftp");
			channel.connect();

			ChannelSftp sftpChannel = (ChannelSftp) channel;

			Vector<LsEntry> directoryEntries = sftpChannel.ls(path);

			for (LsEntry file : directoryEntries) {
				log.info(String.format("File - %s", file.getFilename()));
			}

			sftpChannel.exit();
			session.disconnect();
		} catch (JSchException e) {
			log.error(e);
		} catch (SftpException e) {
			log.error(e);
		}
	}

	class JschLogger implements com.jcraft.jsch.Logger {
		public boolean isEnabled(int arg0) {
			return true;
		}

		public void log(int arg0, String arg1) {
			log.info(String.format("[SFTP/SSH -> %s]", arg1));
		}
	}
}