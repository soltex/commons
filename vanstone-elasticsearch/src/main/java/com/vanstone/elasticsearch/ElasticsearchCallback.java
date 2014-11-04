/**
 * 
 */
package com.vanstone.elasticsearch;

import org.elasticsearch.client.Client;

/**
 * @author shipeng
 *
 */
public interface ElasticsearchCallback<T> {
	
	public T doInElasticsearch(Client client) ;
	
}
