/**
 * 
 */
package com.vanstone.kafka.messagebus;

/**
 * @author shipeng
 */
public class MessageObject {
	
	private String key;
	private String message;
	
	public MessageObject(String key,String message) {
		this.key = key;
		this.message = message;
	}

	public String getKey() {
		return key;
	}

	public String getMessage() {
		return message;
	}
	
}
