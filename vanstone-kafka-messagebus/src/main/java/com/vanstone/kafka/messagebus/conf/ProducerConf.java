/**
 * 
 */
package com.vanstone.kafka.messagebus.conf;

/**
 * 生产者配置
 * 
 * @author shipeng
 */
public class ProducerConf {

	/**
	 * meta.broker.list
	 */
	private String metaBrokerList;
	/**
	 * serializer.class
	 */
	private String serializerClass;
	/**
	 * request.required.acks
	 */
	private String requestRequiredAcks;

	public String getMetaBrokerList() {
		return metaBrokerList;
	}

	public String getSerializerClass() {
		return serializerClass;
	}

	public String getRequestRequiredAcks() {
		return requestRequiredAcks;
	}

	protected void setMetaBrokerList(String metaBrokerList) {
		this.metaBrokerList = metaBrokerList;
	}
	
	protected void setSerializerClass(String serializerClass) {
		this.serializerClass = serializerClass;
	}

	protected void setRequestRequiredAcks(String requestRequiredAcks) {
		this.requestRequiredAcks = requestRequiredAcks;
	}
	
}
