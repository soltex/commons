package com.vanstone;

import org.elasticsearch.action.get.GetResponse;
import org.junit.Test;

import com.vanstone.elasticsearch.ElasticsearchTemplate;
import com.vanstone.elasticsearch.impl.ElasticsearchTemplateImpl;

/**
 */
public class ElasticsearchTemplateTest {

	@Test
	public void testElasticsearchTemplate() {
		ElasticsearchTemplate template = new ElasticsearchTemplateImpl();
		System.out.println(template.getClient());
		GetResponse getResponse = template.getDoc("original_data_index", "data_mapping", "1cc61580-2731-4ffc-bd54-c3b29c4d2fd3");
		if (getResponse != null) {
			System.out.println(getResponse.getSourceAsString());
		}
	}
	
}
