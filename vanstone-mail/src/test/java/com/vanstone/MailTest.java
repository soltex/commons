package com.vanstone;

import java.io.File;
import java.io.FileFilter;
import java.io.UnsupportedEncodingException;

import javax.mail.internet.MimeUtility;

import org.apache.commons.mail.EmailException;
import org.junit.Test;

import com.vanstone.mail.HostConfBuilder;
import com.vanstone.mail.IHostConf;
import com.vanstone.mail.MailObject;

/**
 * Unit test for simple App.
 */
public class MailTest {
	
	@Test
	public void testSendMail() throws EmailException {
		
		HostConfBuilder hostConfBuilder = new HostConfBuilder();
		hostConfBuilder.smtp("smtp.sagacityidea.com").emailUsername("shipeng@sagacityidea.com").emailUserPwd("sci12345").fromEmailAddress("shipeng@sagacityidea.com");
		hostConfBuilder.debugable(true);
		IHostConf hostConf = hostConfBuilder.build();
		MailObject mailObject = new MailObject(hostConf);
		mailObject.addTo("shipengpipi@126.com");
		mailObject.setSubject("测试邮件发送带有附件的，瞅瞅");
		mailObject.setHtmlMsg("<html><body>收到没  ？ 嗯 ？？？</body></html>");
		
		File file = new File("C:\\Users\\Public\\Pictures\\Sample Pictures");
		File[] files = file.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				if (pathname.isDirectory()) {
					return false;
				}
				return true;
			}
		});
		
		for (File f : files) {
			try {
				mailObject.attach(f.getPath(), MimeUtility.encodeText(f.getName()), null);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		mailObject.send();
	}
	
}
