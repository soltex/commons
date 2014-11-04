/**
 * 
 */
package com.vanstone.elasticsearch;

import org.elasticsearch.client.Client;

/**
 * @author shipeng
 */
public interface ElasticsearchErrorHandler {
	
	<T> void process(Client client,Object param);
	
}
