/**
 * 
 */
package com.vanstone.elasticsearch.impl;

import org.elasticsearch.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vanstone.elasticsearch.ElasticsearchErrorHandler;

/**
 * @author shipeng
 *
 */
public class DefaultElasticsearchErrorHanderImpl implements ElasticsearchErrorHandler {

	private static Logger _LOG = LoggerFactory.getLogger(DefaultElasticsearchErrorHanderImpl.class);
	
	@Override
	public <T extends Object> void process(Client client, Object param) {
		_LOG.error("Elasticsearc Client -->> " + client + ",param -->> " + (param != null? param.getClass().getName() : null));
	}
	
}
