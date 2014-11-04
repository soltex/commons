/**
 * 
 */
package com.vanstone.kafka.messagebus;

import java.util.Collection;

/**
 * 消费者管理器
 * 根据当前环境下，topic数量以及topic定义线程数量来批量处理消息，并初始化到当前环境中
 * @author shipeng
 */
public interface IKafkaConsumerManager {
	
	/**
	 * 获取消费者控制器
	 * @param id
	 * @return
	 */
	ConsumerController getController(String consumerid);
	
	/**
	 * 获取全部消费者控制器
	 * @return
	 */
	Collection<ConsumerController> getAllControllers();
	
	/**
	 * 启动消费者任务控制器
	 * @param consumerid
	 */
	void startController(String consumerid) throws ConsumerException ;
	
	/**
	 * 启动全部消费者任务控制器
	 */
	void startAllControllers();
	
	/**
	 * 停止消费者任务控制
	 * @param consumerid
	 */
	void shutdownController(String consumerid) throws ConsumerException;
	
	/**
	 * 停止全部控制器
	 */
	void shutdownAllControllers();
	
	/**
	 * 删除控制器
	 * @param consumerid
	 */
	void deleteController(String consumerid);
	
}
