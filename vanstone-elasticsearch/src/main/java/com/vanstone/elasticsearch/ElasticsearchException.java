/**
 * 
 */
package com.vanstone.elasticsearch;

/**
 * @author shipeng
 *
 */
public class ElasticsearchException extends Error {

	private static final long serialVersionUID = -805524186667852119L;
	
	public ElasticsearchException() {
		super();
	}
	
	public ElasticsearchException(Exception e) {
		super(e);
	}
	
}
