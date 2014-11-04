/**
 * 
 */
package com.vanstone.kafka.messagebus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import com.vanstone.kafka.messagebus.conf.ConsumerGroupConf;
import com.vanstone.kafka.messagebus.conf.KafkaConf;

/**
 * @author shipeng
 *
 */
public class ConsumerController {
	
	/**
	 * 当前控制器线程池
	 */
	private ExecutorService executorService;
	/**
	 * 消费者连接器
	 */
	private ConsumerConnector consumerConnector;
	/**
	 * 当前控制器的配置项
	 */
	private ConsumerGroupConf.ConsumerGroup consumerGroup;
	
	public ConsumerController(ConsumerGroupConf.ConsumerGroup consumerGroup) {
		this.consumerGroup = consumerGroup;
	}
	
	/**
	 * 启动当前控制器
	 */
	public void start() {
		Properties properties = new Properties();
		properties.put("zookeeper.connect", KafkaConf.getKafkaConf().getConsumerGroupConf().getZookeeperConnect());
		properties.put("group.id", consumerGroup.getGroupid());
		properties.put("zookeeper.session.timeout.ms", KafkaConf.getKafkaConf().getConsumerGroupConf().getZookeeperSessionTimeoutMs());
		properties.put("zookeeper.sync.time.ms", KafkaConf.getKafkaConf().getConsumerGroupConf().getZookeeperSyncTimeMs());
		properties.put("auto.commit.interval.ms", KafkaConf.getKafkaConf().getConsumerGroupConf().getAutoCommitIntervalMs());
		ConsumerConfig consumerConfig = new ConsumerConfig(properties);
		this.consumerConnector = Consumer.createJavaConsumerConnector(consumerConfig);
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(consumerGroup.getTopic(), consumerGroup.getExecuteserviceCount());
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumerConnector.createMessageStreams(topicCountMap);
        List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(consumerGroup.getTopic());
		executorService = Executors.newFixedThreadPool(this.consumerGroup.getExecuteserviceCount());
		for (KafkaStream<byte[], byte[]> kafkaStream : streams) {
			try {
				AbstractConsumerRunnable runnable = (AbstractConsumerRunnable)Class.forName(consumerGroup.getConsumerclass()).newInstance();
				runnable.setKafkaStream(kafkaStream);
				executorService.submit(runnable);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new ExceptionInInitializerError();
			} catch (InstantiationException e) {
				e.printStackTrace();
				throw new ExceptionInInitializerError();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				throw new ExceptionInInitializerError();
			}
		}
	}
	
	/**
	 * 获取当前控制器状态
	 * @return
	 */
	public ControllerState getControllerState() {
		if (this.executorService == null) {
			return ControllerState.Not_Run;
		}else if (this.executorService.isShutdown()) {
			return ControllerState.Shutdown;
		}else if (this.executorService.isTerminated()) {
			return ControllerState.Terminated;
		}else {
			return ControllerState.Started;
		}
	}

	public ConsumerGroupConf.ConsumerGroup getConsumerGroup() {
		return consumerGroup;
	}
	
	/**
	 * 关闭
	 */
	public void shutdown() {
		if (this.executorService != null && !this.executorService.isShutdown() && !this.executorService.isTerminated()) {
			this.executorService.shutdown();
		}
	}
	
	/**
	 * 强制关闭
	 */
	public void shutdownNow() {
		if (this.executorService != null && !this.executorService.isShutdown() && !this.executorService.isTerminated()) {
			this.executorService.shutdownNow();
		}
	}
	
	/**
	 * 强制关闭，等待3秒后，重新启动当前控制器
	 */
	public void reboot() {
		this.shutdownNow();
		try {
	        TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
	        e.printStackTrace();
        }
		this.start();
	}
}
