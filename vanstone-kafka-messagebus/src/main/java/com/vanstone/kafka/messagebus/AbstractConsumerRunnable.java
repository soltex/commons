/**
 * 
 */
package com.vanstone.kafka.messagebus;

import kafka.consumer.KafkaStream;

/**
 * @author shipeng
 */
public abstract class AbstractConsumerRunnable implements Runnable {
	
	private KafkaStream<byte[], byte[]> kafkaStream;
	
	public KafkaStream<byte[], byte[]> getKafkaStream() {
		return kafkaStream;
	}

	public void setKafkaStream(KafkaStream<byte[], byte[]> kafkaStream) {
		this.kafkaStream = kafkaStream;
	}
	
}
