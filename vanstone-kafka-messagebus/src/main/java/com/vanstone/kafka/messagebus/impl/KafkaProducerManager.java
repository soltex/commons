/**
 * 
 */
package com.vanstone.kafka.messagebus.impl;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import com.vanstone.kafka.messagebus.IKafkaProducerManager;
import com.vanstone.kafka.messagebus.conf.KafkaConf;

/**
 * @author shipeng
 */
public class KafkaProducerManager implements IKafkaProducerManager {
	
	private Producer<String, String> producer;
	
	private static KafkaProducerManager kafkaProducerManager = new KafkaProducerManager();
	
	private KafkaProducerManager() {
		KafkaConf kafkaConf = KafkaConf.getKafkaConf();
		Properties properties = new Properties();
		properties.put("metadata.broker.list", kafkaConf.getProducerConf().getMetaBrokerList());
		properties.put("serializer.class", kafkaConf.getProducerConf().getSerializerClass());
		properties.put("request.required.acks", kafkaConf.getProducerConf().getRequestRequiredAcks());
		properties.put("partitioner.class", "com.vanstone.commons.kafka.messagebus.VanstoneDefaultPartition");
		ProducerConfig config = new ProducerConfig(properties);
		producer = new Producer<String, String>(config);
	}
	
	public static IKafkaProducerManager getInstance() {
		return kafkaProducerManager;
	}
	
	/* (non-Javadoc)
	 * @see com.vanstone.commons.kafka.messagebus.IKafkaProducerManager#getProducer()
	 */
	@Override
	public Producer<String, String> getProducer() {
		return this.producer;
	}
	
	/* (non-Javadoc)
	 * @see com.vanstone.commons.kafka.messagebus.IKafkaProducerManager#close()
	 */
	@Override
	public void close() {
		this.producer.close();
	}

	/* (non-Javadoc)
	 * @see com.vanstone.commons.kafka.messagebus.IKafkaProducerManager#rebootProducer()
	 */
	@Override
	public Producer<String, String> rebootProducer() {
		return this.producer;
	}
	
	/* (non-Javadoc)
	 * @see com.vanstone.commons.kafka.messagebus.IKafkaProducerManager#send(com.vanstone.commons.kafka.messagebus.AbstractKafkaMessage)
	 */
	@Override
	public void send(KeyedMessage<String, String> message) {
		if (producer != null) {
			producer.send(message);
		}
	}
	
}
