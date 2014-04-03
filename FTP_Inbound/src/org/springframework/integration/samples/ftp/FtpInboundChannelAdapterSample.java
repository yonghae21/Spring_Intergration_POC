/*
 * Copyright 2002-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.integration.samples.ftp;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.Message;
import org.springframework.integration.core.PollableChannel;

/**
 *
 * @author Oleg Zhurakousky
 * @author Gunnar Hillert
 *
 */
public class FtpInboundChannelAdapterSample {

	public static final String LOCAL_FTP_TEMP_DIR = "target" + File.separator + "local-ftp-temp";
	
	private static final Logger LOGGER = Logger.getLogger(FtpInboundChannelAdapterSample.class);

	@Test
	public void runDemo() throws Exception{
		FileUtils.deleteQuietly(new File(LOCAL_FTP_TEMP_DIR));
		
		ConfigurableApplicationContext ctx =
			new ClassPathXmlApplicationContext("META-INF/spring/integration/FtpInboundChannelAdapterSample-context.xml");

		PollableChannel ftpChannel = ctx.getBean("ftpChannel", PollableChannel.class);
		
		Message<?> message;

		while (true) {
			message = ftpChannel.receive(3000);
			
			if(message == null)
				break;

			LOGGER.info(String.format("Received file : %s.", message));
		}
		
		ctx.close();
	}

}
