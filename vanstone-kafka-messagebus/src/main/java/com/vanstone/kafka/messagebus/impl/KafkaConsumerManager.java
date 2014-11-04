/**
 * 
 */
package com.vanstone.kafka.messagebus.impl;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.vanstone.kafka.messagebus.ConsumerController;
import com.vanstone.kafka.messagebus.ConsumerException;
import com.vanstone.kafka.messagebus.ControllerState;
import com.vanstone.kafka.messagebus.IKafkaConsumerManager;
import com.vanstone.kafka.messagebus.conf.ConsumerGroupConf;
import com.vanstone.kafka.messagebus.conf.KafkaConf;

/**
 * @author shipeng
 */
public class KafkaConsumerManager implements IKafkaConsumerManager {
	
	private Map<String, ConsumerController> controllerContainer = new LinkedHashMap<String, ConsumerController>();
	
	private static Log LOG = LogFactory.getLog(KafkaConsumerManager.class);
	
	public static Object lock = new Object();
	
	public static IKafkaConsumerManager kafkaConsumerManager = new KafkaConsumerManager();
	
	private KafkaConsumerManager() {
		for (ConsumerGroupConf.ConsumerGroup group : KafkaConf.getKafkaConf().getConsumerGroupConf().getConsumerGroups()) {
			ConsumerController controller = new ConsumerController(group);
			this.controllerContainer.put(group.getId(),controller);
			if (LOG.isInfoEnabled()) {
				LOG.info("Add Consumer Controller.");
			}
		}
	}
	
	/**
	 * 获取当前IKafkaConsumerManager实例
	 * @return
	 */
	public static IKafkaConsumerManager getInstance() {
		return kafkaConsumerManager;
	}
	
	@Override
    public ConsumerController getController(String consumerid) {
		return controllerContainer.get(consumerid);
    }

	@Override
    public Collection<ConsumerController> getAllControllers() {
	    return this.controllerContainer.values();
    }
	
	@Override
    public void startController(String consumerid) throws ConsumerException {
		ConsumerController controller = this.getController(consumerid);
		if (controller == null) {
			throw new ConsumerException(ConsumerException.ErrorCode.Controller_Not_Found);
		}
		if (controller.getControllerState().equals(ControllerState.Started)) {
			throw new ConsumerException(ConsumerException.ErrorCode.Controller_Hasbean_Started);
		}
		
		if (controller.getControllerState().equals(ControllerState.Not_Run)) {
			controller.start();
		}else {
			controller.reboot();
		}
    }
	
	@Override
    public void startAllControllers() {
		for (ConsumerController controller : this.controllerContainer.values()) {
			try {
	            startController(controller.getConsumerGroup().getId());
            } catch (ConsumerException e) {
	            e.printStackTrace();
            }
		}
    }
	
	@Override
    public void shutdownController(String consumerid) throws ConsumerException{
		ConsumerController controller = this.getController(consumerid);
		if (controller == null) {
			throw new ConsumerException(ConsumerException.ErrorCode.Controller_Not_Found);
		}
		controller.shutdownNow();
    }

	@Override
    public void shutdownAllControllers() {
		for (ConsumerController controller : this.controllerContainer.values()) {
			try {
				shutdownController(controller.getConsumerGroup().getId());
            } catch (ConsumerException e) {
	            e.printStackTrace();
            }
		}
    }

	@Override
    public void deleteController(String consumerid) {
		synchronized (lock) {
	        ConsumerController controller = this.controllerContainer.get(consumerid);
	        if (controller == null) {
	        	return;
	        }
	        controller.shutdownNow();
	        this.controllerContainer.remove(consumerid);
		}
    }
	
}
