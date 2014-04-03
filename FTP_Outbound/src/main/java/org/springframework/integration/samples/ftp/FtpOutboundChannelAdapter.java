
package org.springframework.integration.samples.ftp;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.support.MessageBuilder;

public class FtpOutboundChannelAdapter {

	private final File baseFolder = new File(File.separator + "toSend");

	@Test
	public void runDemo() throws Exception{
		ConfigurableApplicationContext ctx =
			new ClassPathXmlApplicationContext("META-INF/spring/integration/dynamic-ftp-outbound-adapter-context.xml");
		
		MessageChannel ftpChannel = ctx.getBean("ftpChannel", MessageChannel.class);

		final File fileToSendA = new File(baseFolder, "a_" + System.currentTimeMillis() + ".txt");
		final File fileToSendB = new File(baseFolder, "b_" + System.currentTimeMillis() + ".txt");
		
		final InputStream inputStreamA = FtpOutboundChannelAdapter.class.getResourceAsStream("/test-files/a.txt");
		final InputStream inputStreamB = FtpOutboundChannelAdapter.class.getResourceAsStream("/test-files/b.txt");

		FileUtils.copyInputStreamToFile(inputStreamA, fileToSendA);
		FileUtils.copyInputStreamToFile(inputStreamB, fileToSendB);
		
		assertTrue(fileToSendA.exists());
		assertTrue(fileToSendB.exists());
		
		final Message<File> messageA = MessageBuilder.withPayload(fileToSendA).build();
		final Message<File> messageB = MessageBuilder.withPayload(fileToSendB).build();
		
		ftpChannel.send(messageA);
		ftpChannel.send(messageB);
		
		ctx.close();

		System.out.println("COMPLETE");
	}
	
}
