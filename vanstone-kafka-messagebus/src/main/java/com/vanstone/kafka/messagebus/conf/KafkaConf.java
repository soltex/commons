/**
 * 
 */
package com.vanstone.kafka.messagebus.conf;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.vanstone.kafka.messagebus.conf.ConsumerGroupConf.ConsumerGroup;

/**
 * @author shipeng
 *
 */
public class KafkaConf {
	
	private static final String PRODUCER_CONF = "/kafka-producer.properties";
	
	private static final String CONSUMER_CONF = "/kafka-consumer.xml";
	
	/**
	 * 生产者配置
	 */
	private ProducerConf producerConf;
	/**
	 * 消费者配置
	 */
	private ConsumerGroupConf consumerGroupConf;
	
	private static KafkaConf kafkaConf = new KafkaConf() ;
	
    @SuppressWarnings("unchecked")
    private KafkaConf() {
		Properties producerProperties = new Properties();
		try {
			producerProperties.load(KafkaConf.class.getResourceAsStream(PRODUCER_CONF));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Properties consumerProperties = new Properties();
		try {
			consumerProperties.load(KafkaConf.class.getResourceAsStream(CONSUMER_CONF));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (producerProperties.isEmpty() && consumerProperties.isEmpty()) {
			throw new ExceptionInInitializerError("please config kafka-producer.properties or kafka-consumer.properties");
		}
		
		if (!producerProperties.isEmpty()) {
			//初始化生产者配置信息
			//metadata.broker.list
			String strMetadataBrokerList = producerProperties.getProperty("metadata.broker.list");
			if (StringUtils.isEmpty(strMetadataBrokerList) || StringUtils.isEmpty(strMetadataBrokerList)) {
				throw new ExceptionInInitializerError("metadata.broker.list is empty.");
			}
			//serializer.class
			String strSerializerClass = producerProperties.getProperty("serializer.class");
			if (StringUtils.isEmpty(strSerializerClass) || StringUtils.isBlank(strSerializerClass)) {
				throw new ExceptionInInitializerError("serializer.class is empty.");
			}
			//request.required.acks
			String strRequestRequiredAcks = producerProperties.getProperty("request.required.acks");
			if (StringUtils.isEmpty(strRequestRequiredAcks)) {
				throw new ExceptionInInitializerError("request.required.acks is empty.");
			}
			try {
				Integer.parseInt(strRequestRequiredAcks);
			} catch (Exception e) {
				throw new ExceptionInInitializerError("request.required.acks is not number.");
			}
			producerConf = new ProducerConf();
			producerConf.setMetaBrokerList(strMetadataBrokerList);
			producerConf.setRequestRequiredAcks(strRequestRequiredAcks);
			producerConf.setSerializerClass(strSerializerClass);
		}
		if (!consumerProperties.isEmpty()) {
			//初始化消费者配置信息
			SAXReader saxReader = new SAXReader();
			Document document = null;
			
			consumerGroupConf = new ConsumerGroupConf();
			try {
                document = saxReader.read(KafkaConf.class.getResourceAsStream(CONSUMER_CONF));
            } catch (DocumentException e) {
                e.printStackTrace();
                throw new ExceptionInInitializerError(e);
            }
			Element rootElement = document.getRootElement();
			
			//zookeeper.connect
			Element zookeeperconnectElement = rootElement.element("zookeeper.connect");
			if (zookeeperconnectElement == null || StringUtils.isEmpty(zookeeperconnectElement.getTextTrim())) {
				throw new ExceptionInInitializerError("zookeeper.connect empty.");
			}
			consumerGroupConf.setZookeeperConnect(zookeeperconnectElement.getTextTrim());
			
			//zookeeper.session.timeout.ms
			Element zookeepersessiontimeoutmsElement = rootElement.element("zookeeper.session.timeout.ms");
			if (zookeepersessiontimeoutmsElement == null || StringUtils.isEmpty(zookeepersessiontimeoutmsElement.getTextTrim())) {
				throw new ExceptionInInitializerError("zookeeper.session.timeout.ms empty.");
			}
			consumerGroupConf.setZookeeperSessionTimeoutMs(zookeepersessiontimeoutmsElement.getTextTrim());
			
			//zookeeper.sync.time.ms
			Element zookeepersynctimemsElement = rootElement.element("zookeeper.sync.time.ms");
			if (zookeepersynctimemsElement == null || StringUtils.isEmpty(zookeepersynctimemsElement.getTextTrim())) {
				throw new ExceptionInInitializerError("zookeeper.sync.time.ms empty.");
			}
			consumerGroupConf.setZookeeperSyncTimeMs(zookeepersynctimemsElement.getTextTrim());
			
			//auto.commit.interval.ms
			Element autocommitintervalmsElement = rootElement.element("auto.commit.interval.ms");
			if (autocommitintervalmsElement == null || StringUtils.isEmpty(autocommitintervalmsElement.getTextTrim())) {
				throw new ExceptionInInitializerError("auto.commit.interval.ms empty.");
			}
			consumerGroupConf.setAutoCommitIntervalMs(autocommitintervalmsElement.getTextTrim());
			
			//consumer
			List<Element> consumerElements = rootElement.elements("consumer");
			if (consumerElements == null || consumerElements.size() <=0) {
				throw new ExceptionInInitializerError("consumer collection empty.");
			}
			for (Element consumerElement : consumerElements) {
				ConsumerGroup group = new ConsumerGroup();
				
				String id = consumerElement.attributeValue("id");
				if (id == null) {
					id = UUID.randomUUID().toString();
				}
				group.setId(id);
				//groupid
				Element groupidElement = consumerElement.element("groupid");
				if (groupidElement == null || StringUtils.isEmpty(groupidElement.getTextTrim())) {
					throw new ExceptionInInitializerError("consumer groupid empty.");
				}
				group.setGroupid(groupidElement.getTextTrim());
				
				//topic
				Element topicElement = consumerElement.element("topic");
				if (topicElement == null || StringUtils.isEmpty(topicElement.getTextTrim())) {
					throw new ExceptionInInitializerError("topic empty.");
				}
				group.setTopic(topicElement.getTextTrim());
				
				//topic-desc
				Element topicDescElement = consumerElement.element("topic-desc");
				if (topicDescElement == null || StringUtils.isEmpty(topicDescElement.getTextTrim())) {
					throw new ExceptionInInitializerError("topic desc empty.");
				}
				group.setTopicDesc(topicDescElement.getTextTrim());
				
				//executeservice-count
				Element executeservicecountElement = consumerElement.element("executeservice-count");
				if (executeservicecountElement == null || StringUtils.isEmpty(executeservicecountElement.getTextTrim())) {
					throw new ExceptionInInitializerError("executeservice-count empty.");
				}
				try {
					group.setExecuteserviceCount(Integer.parseInt(executeservicecountElement.getTextTrim()));
				} catch (Exception e) {
					throw new ExceptionInInitializerError("executeservice-count not integer.");
				}
				//consumerclass
				Element consumerclassElement = consumerElement.element("consumerclass");
				if (consumerclassElement == null || StringUtils.isEmpty(consumerclassElement.getTextTrim())) {
					throw new ExceptionInInitializerError("consumerclass empty.");
				}
				group.setConsumerclass(consumerclassElement.getTextTrim());
				consumerGroupConf.addConsumerGroup(group);
			}
		}
	}
	
    public static KafkaConf getKafkaConf() {
		return kafkaConf;
	}
	
	/**
	 * 获取ProducerConf
	 * @return
	 */
	public ProducerConf getProducerConf() {
		return producerConf;
	}
	
	/**
	 * 获取ConsumerGroupConf
	 * @return
	 */
	public ConsumerGroupConf getConsumerGroupConf() {
		return consumerGroupConf;
	}
	
}
