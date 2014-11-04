/**
 * 
 */
package com.vanstone.kafka.messagebus;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;

/**
 * Kafka消息总线生产者管理器
 * @author shipeng
 */
public interface IKafkaProducerManager {
	
	/**
	 * 获取当前生产者
	 * @return
	 */
	Producer<String, String> getProducer();
	
	/**
	 * 关闭当前生产者
	 */
	void close();
	
	/**
	 * 重新启动当前Producer
	 * @return
	 */
	Producer<String, String> rebootProducer();
	
	/**
	 * 发送消息
	 * @param message
	 */
	void send(KeyedMessage<String, String> message);
	
}
