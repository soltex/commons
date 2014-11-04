/**
 * 
 */
package com.vanstone.kafka.messagebus.context;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.vanstone.kafka.messagebus.IKafkaConsumerManager;
import com.vanstone.kafka.messagebus.impl.KafkaConsumerManager;
import com.vanstone.kafka.messagebus.impl.KafkaProducerManager;

/**
 * 负责ProducerManager以及ConsumerManager初始化,以防止高并发情况下初始化等待
 * @author shipeng
 */
public class MessagebusContextListener implements ServletContextListener {
	
	private static Log LOG = LogFactory.getLog(MessagebusContextListener.class);
	
	/**
	 * 是否立即启动
	 */
	private boolean instanton = false;
	
	@Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
		KafkaProducerManager.getInstance();
		IKafkaConsumerManager consumerManager = KafkaConsumerManager.getInstance();
		ServletContext servletContext = servletContextEvent.getServletContext();
		String strinstanton = servletContext.getInitParameter("instanton");
		if (!StringUtils.isEmpty(strinstanton)) {
			try {
	            this.instanton = Boolean.parseBoolean(strinstanton);
            } catch (Exception e) {
            	throw new ExceptionInInitializerError("instanton parameter error,is must be boolean true / false.");
            }
		}
		if (LOG.isInfoEnabled()) {
			LOG.info("instanton param : " + this.instanton);
		}
		if (this.instanton) {
			consumerManager.startAllControllers();
			LOG.info("All kafka consumer has been started.");
		}
    }
	
	@Override
    public void contextDestroyed(ServletContextEvent sce) {
	    KafkaProducerManager.getInstance().close();
	    KafkaConsumerManager.getInstance().shutdownAllControllers();
    }
	
}
