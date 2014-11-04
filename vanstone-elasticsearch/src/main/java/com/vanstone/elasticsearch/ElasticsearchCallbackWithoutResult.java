/**
 * 
 */
package com.vanstone.elasticsearch;

import org.elasticsearch.client.Client;

/**
 * @author shipeng
 *
 */
public abstract class ElasticsearchCallbackWithoutResult implements ElasticsearchCallback<Object> {
	
	public final Object doInElasticsearch(Client client) {
		this.doInElasticSearchWithoutResult(client);
		return null;
	}
	
	public abstract void doInElasticSearchWithoutResult(Client client);
	
}
