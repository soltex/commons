/**
 * 
 */
package com.vanstone.redis.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author shipeng
 */
public class DefaultRedisErrorHandler implements ErrorHandler {
	
	private static Logger _LOG = LoggerFactory.getLogger(DefaultRedisErrorHandler.class);
	
	@Override
	public void process(Exception e) {
		if (_LOG.isErrorEnabled()) {
			_LOG.error(e.toString());
		}
	}
	
}
