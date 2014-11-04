/**
 * 
 */
package com.vanstone.kafka.messagebus;

import kafka.producer.KeyedMessage;

/**
 * @author shipeng
 */
public abstract class AbstractKafkaMessage extends KeyedMessage<String, String> {

	private static final long serialVersionUID = 1L;
	
	public AbstractKafkaMessage(String topic,String msg) {
		super(topic, msg);
	}
	
}
