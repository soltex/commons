/**
 * 
 */
package com.vanstone.elasticsearch;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.deletebyquery.DeleteByQueryResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;

import com.vanstone.elasticsearch.conf.ESConfig;

/**
 * @author shipeng
 *
 */
public interface ElasticsearchTemplate {

	/**
	 * 获取Elasticsearch Client
	 * @return
	 */
	TransportClient getClient();
	
	/**
	 * 处理带有返回值的Elasticsearch处理
	 * @param callback
	 * @return
	 */
	<T extends Object> T execute(ElasticsearchCallback<T> callback);
	
	/**
	 * 索引单个文档
	 * @param index
	 * @param mapping
	 * @param source
	 * @return
	 */
	IndexResponse indexDoc(String index,String mapping,String source);
	
	/**
	 * 索引单个文件
	 * @param index
	 * @param mapping
	 * @param id
	 * @param source
	 * @return
	 */
	IndexResponse indexDoc(String index,String mapping,String id,String source);
	
	/**
	 * 通过id删除文档
	 * @param index
	 * @param mapping
	 * @param id
	 * @return
	 */
	DeleteResponse deleteDoc(String index,String mapping,String id);
	
	/**
	 * 清理索引下所有文档
	 * @param index
	 * @return
	 */
	DeleteByQueryResponse clearDocuments(String index);
	
	/**
	 * 清理索引下mapping的所有文档
	 * @param index
	 * @param mapping
	 * @return
	 */
	DeleteByQueryResponse clearDocuments(String index,String... mappings);
	
	/**
	 * 获取文档
	 * @param index
	 * @param mapping
	 * @param id
	 * @return
	 */
	GetResponse getDoc(String index,String mapping,String id);
	
	/**
	 * 获取一组文档
	 * @param index
	 * @param mapping
	 * @param ids
	 * @return
	 */
	MultiGetResponse getDocsByIds(String index,String mapping,String[] ids);
	
	/**
	 * 获取Elasticsearch应用配置信息
	 * @return
	 */
	ESConfig getEsConfig();
	
}
