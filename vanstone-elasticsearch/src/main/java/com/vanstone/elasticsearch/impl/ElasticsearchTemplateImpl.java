/**
 * 
 */
package com.vanstone.elasticsearch.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.deletebyquery.DeleteByQueryResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetRequestBuilder;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;

import com.vanstone.elasticsearch.ElasticsearchCallback;
import com.vanstone.elasticsearch.ElasticsearchErrorHandler;
import com.vanstone.elasticsearch.ElasticsearchException;
import com.vanstone.elasticsearch.ElasticsearchTemplate;
import com.vanstone.elasticsearch.conf.ESConfig;

/**
 * @author shipeng
 */
public class ElasticsearchTemplateImpl implements ElasticsearchTemplate {
	
	private ElasticsearchErrorHandler errorHandler = new DefaultElasticsearchErrorHanderImpl();
	
	/**
	 * Elasticsearch配置文件名称
	 */
	public static final String ES_CONF = "/ecointel-elasticsearch.properties";
	
	private TransportClient client;
	
	private ESConfig esConfig;
	
	public ElasticsearchTemplateImpl() {
		try {
			_init();
		} catch (IOException e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError("Elasticsearch template initial error.");
		}
	}
	
	private void _init() throws IOException {
		this.esConfig = new ESConfig();
		InputStream is = this.getClass().getResourceAsStream(ES_CONF);
		Properties properties = new Properties();
		properties.load(is);
		String clusterName = properties.getProperty("es.cluster.name");
		if (clusterName == null || clusterName.equals("")) {
			throw new ExceptionInInitializerError("es.cluster.name is NULL");
		}
		this.esConfig.setClusterName(clusterName);
		String strAddresses = properties.getProperty("es.node.addresses");
		if (strAddresses == null || strAddresses.equals("")) {
			strAddresses = "localhost:9300";
		}
		String[] addresses = strAddresses.split(",");
		this.esConfig.setAddresses(addresses);
		if (client != null) {
			client.close();
			client = null;
		}
		Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", this.esConfig.getClusterName()).build();
		client = new TransportClient(settings);
		for (ESConfig.AddressWare address : esConfig.getAddressDetails()) {
			client.addTransportAddress(new InetSocketTransportAddress(address.getAddr(), address.getPort()));
		}
	}
	
	public ElasticsearchErrorHandler getErrorHandler() {
		return errorHandler;
	}
	
	/* (non-Javadoc)
	 * @see cn.com.ecointel.commons.elasticsearch.IElasticsearchTemplate#execute(cn.com.ecointel.commons.elasticsearch.IElasticsearchCallback)
	 */
	public <T> T execute(ElasticsearchCallback<T> callback) {
		if (callback == null) {
			throw new IllegalArgumentException();
		}
		try {
			return callback.doInElasticsearch(this.getClient());
		} catch (Exception e) {
			if (this.errorHandler != null) {
				this.errorHandler.process(client,null);
			}
			e.printStackTrace();
			throw new ElasticsearchException(e);
		} 
	}

	public TransportClient getClient() {
		return this.client;
	}

	@Override
	public ESConfig getEsConfig() {
		return this.esConfig;
	}

	@Override
	public IndexResponse indexDoc(final String index, final String mapping, final String source) {
		if (index == null || index.equals("") || mapping == null || mapping.equals("")) {
			throw new IllegalArgumentException("index or mapping NULL.");
		}
		IndexResponse indexResponse = this.execute(new ElasticsearchCallback<IndexResponse>() {
			@Override
			public IndexResponse doInElasticsearch(Client client) {
				return client.prepareIndex(index, mapping).setSource(source).setRefresh(true).execute().actionGet();
			}
		});
		return indexResponse;
	}
	
	@Override
	public IndexResponse indexDoc(final String index, final String mapping, final String id, final String source) {
		if (index == null || index.equals("") || mapping == null || mapping.equals("")) {
			throw new IllegalArgumentException("index or mapping NULL.");
		}
		IndexResponse indexResponse = this.execute(new ElasticsearchCallback<IndexResponse>() {
			@Override
			public IndexResponse doInElasticsearch(Client client) {
				return client.prepareIndex(index, mapping).setId(id).setSource(source).setRefresh(true).execute().actionGet();
			}
		});
		return indexResponse;
	}

	@Override
	public DeleteResponse deleteDoc(final String index, final String mapping, final String id) {
		if (index == null || index.equals("") || mapping == null || mapping.equals("") || id == null || id.equals("")) {
			throw new IllegalArgumentException("index or mapping NULL.");
		}
		DeleteResponse deleteResponse = this.execute(new ElasticsearchCallback<DeleteResponse>() {
			@Override
			public DeleteResponse doInElasticsearch(Client client) {
				client.prepareDelete(index, mapping, id).setRefresh(true).execute().actionGet();
				return null;
			}
		});
		return deleteResponse;
	}
	
	@Override
	public DeleteByQueryResponse clearDocuments(final String index) {
		if (index == null || index.equals("")) {
			throw new IllegalArgumentException("index or mapping NULL.");
		}
		DeleteByQueryResponse deleteByQueryResponse = this.execute(new ElasticsearchCallback<DeleteByQueryResponse>() {
			@Override
			public DeleteByQueryResponse doInElasticsearch(Client client) {
				return client.prepareDeleteByQuery(index).setQuery(QueryBuilders.matchAllQuery()).execute().actionGet();
			}
		});
		return deleteByQueryResponse;
	}

	@Override
	public DeleteByQueryResponse clearDocuments(final String index, final String... mappings) {
		if (index == null || index.equals("") || mappings == null || mappings.length <=0 ) {
			throw new IllegalArgumentException("index or mapping NULL.");
		}
		DeleteByQueryResponse deleteByQueryResponse = this.execute(new ElasticsearchCallback<DeleteByQueryResponse>() {
			@Override
			public DeleteByQueryResponse doInElasticsearch(Client client) {
				return client.prepareDeleteByQuery(index).setTypes(mappings).setQuery(QueryBuilders.matchAllQuery()).execute().actionGet();
			}
		});
		return deleteByQueryResponse;
	}

	@Override
	public GetResponse getDoc(final String index, final String mapping, final String id) {
		if (index == null || index.equals("") || mapping == null || mapping.equals("") || id == null || id.equals("")) {
			throw new IllegalArgumentException("index or mapping NULL.");
		}
		GetResponse getResponse = this.execute(new ElasticsearchCallback<GetResponse>() {
			@Override
			public GetResponse doInElasticsearch(Client client) {
				return client.prepareGet(index, mapping, id).execute().actionGet();
			}
		});
		return getResponse;
	}

	@Override
	public MultiGetResponse getDocsByIds(final String index, final String mapping, final String[] ids) {
		if (index == null || index.equals("") || mapping == null || mapping.equals("") || ids == null || ids.length <=0) {
			throw new IllegalArgumentException("index or mapping NULL.");
		}
		MultiGetResponse multiGetResponse = this.execute(new ElasticsearchCallback<MultiGetResponse>() {
			@Override
			public MultiGetResponse doInElasticsearch(Client client) {
				MultiGetRequestBuilder builder = client.prepareMultiGet();
				for (String id : ids) {
					builder.add(index, mapping, id);
				}
				return builder.execute().actionGet();
			}
		});
		return multiGetResponse;
	}
	
}
