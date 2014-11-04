/**
 * 
 */
package com.vanstone.kafka.messagebus.conf;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 消费者配置器
 * 
 * @author shipeng
 */
public class ConsumerGroupConf {
	
	/**
	 * zookeeper配置
	 */
	private String zookeeperConnect;
	private String zookeeperSessionTimeoutMs;
	private String zookeeperSyncTimeMs;
	private String autoCommitIntervalMs;
	
	private Collection<ConsumerGroupConf.ConsumerGroup> consumerGroups = new ArrayList<ConsumerGroupConf.ConsumerGroup>();
	
	/**
	 * 获取Zookeeper
	 * @return
	 */
	public String getZookeeperConnect() {
		return zookeeperConnect;
	}
	
	public String getZookeeperSessionTimeoutMs() {
		return zookeeperSessionTimeoutMs;
	}

	public String getZookeeperSyncTimeMs() {
		return zookeeperSyncTimeMs;
	}

	public String getAutoCommitIntervalMs() {
		return autoCommitIntervalMs;
	}

	public void setZookeeperConnect(String zookeeperConnect) {
		this.zookeeperConnect = zookeeperConnect;
	}

	public void setZookeeperSessionTimeoutMs(String zookeeperSessionTimeoutMs) {
		this.zookeeperSessionTimeoutMs = zookeeperSessionTimeoutMs;
	}

	public void setZookeeperSyncTimeMs(String zookeeperSyncTimeMs) {
		this.zookeeperSyncTimeMs = zookeeperSyncTimeMs;
	}

	public void setAutoCommitIntervalMs(String autoCommitIntervalMs) {
		this.autoCommitIntervalMs = autoCommitIntervalMs;
	}

	/**
	 * 获取消费者组配置项
	 * @return
	 */
	public Collection<ConsumerGroup> getConsumerGroups() {
		return consumerGroups;
	}
	
	public void addConsumerGroup(ConsumerGroup consumerGroup) {
		this.consumerGroups.add(consumerGroup);
	}
	
	public static class ConsumerGroup {
		/**
		 * 消费者id
		 */
		private String id;
		/**
		 * 消费者组
		 */
		private String groupid;
		/**
		 * 消费主题
		 */
		private String topic;
		/**
		 * 主题描述
		 */
		private String topicDesc;
		/**
		 * 线程池容量
		 */
		private Integer executeserviceCount;
		/**
		 * 消费者处理类
		 */
		private String consumerclass;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getGroupid() {
			return groupid;
		}

		public void setGroupid(String groupid) {
			this.groupid = groupid;
		}

		public String getTopic() {
			return topic;
		}

		public void setTopic(String topic) {
			this.topic = topic;
		}

		public String getTopicDesc() {
			return topicDesc;
		}

		public void setTopicDesc(String topicDesc) {
			this.topicDesc = topicDesc;
		}

		public Integer getExecuteserviceCount() {
			return executeserviceCount;
		}

		public void setExecuteserviceCount(Integer executeserviceCount) {
			this.executeserviceCount = executeserviceCount;
		}

		public String getConsumerclass() {
			return consumerclass;
		}

		public void setConsumerclass(String consumerclass) {
			this.consumerclass = consumerclass;
		}
	}
}
