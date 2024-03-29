/*
 * Copyright 2002-2012 the original author or authors.
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
package org.springframework.integration.samples.tcpclientserver;

import java.sql.Date;


/**
 * Simple service that receives data in a byte array,
 * converts it to a String and appends it to 'echo:'.
 *
 * @author Gary Russell
 *
 */
public class EchoService {

	public String test(String input) {
		if ("FAIL".equals(input)) {
			throw new RuntimeException("Failure Demonstration");
		}
		return "echo : " + result(input);
	}

	protected String result(String input) {
		if(input.equals("help"))
			return "SYSDATE, hi, hello, who";
			
		else if(input.equals("hi"))
			return "Hello";

		else if(input.equals("hello"))
			return "Hello world";
		
		else if(input.equals("SYSDATE"))
			return new Date(System.currentTimeMillis()).toString();

		else if(input.equals("who"))
			return "I'm your father!!!";
		
		return input;
	}
}