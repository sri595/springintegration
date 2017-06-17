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

package org.springframework.integration.samples.helloworld;

import java.util.Map;

import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;

/**
 * Simple POJO to be referenced from a Service Activator.
 *
 * @author Mark Fisher
 */


public class HelloService {
	
	MessageChannel errorChannel;
	MessageChannel errorUpdateChannel;
	

	public void sayHello(Message<?>  message)  throws Exception{
		System.out.println("Message ...."+ message.getPayload());
		Message<?> mesg= MessageBuilder.withPayload(message.getPayload()).build();
		boolean isFirstTime=false;
		Map<String,String> m=(Map<String,String> ) message.getPayload();
		int retrycount=Integer.parseInt(m.get("retry_count"));
		
	/*	try{
		if(retrycount==1){
			isFirstTime=true;
	}*/
		
		//throw new Exception("ewqeqeeeeewe");
		getErrorChannel().send(mesg);
		//}
	/*	catch(Exception e)
		{
			System.out.println("Exception case");
			m.put("status", "Error");
			Message<?> mesg1= MessageBuilder.withPayload(m).build();
			System.out.println("Exceptional message:" + mesg1);
			getErrorUpdateChannel().send(mesg1);
		}*/
	}

	public MessageChannel getErrorChannel() {
		return errorChannel;
	}

	public void setErrorChannel(MessageChannel errorChannel) {
		this.errorChannel = errorChannel;
	}

	public MessageChannel getErrorUpdateChannel() {
		return errorUpdateChannel;
	}

	public void setErrorUpdateChannel(MessageChannel errorUpdateChannel) {
		this.errorUpdateChannel = errorUpdateChannel;
	}
	
	

}
