/**
 * 
 */
package com.vanstone.kafka.messagebus;

import java.security.SecureRandom;

import org.apache.commons.logging.LogFactory;

import org.apache.commons.logging.Log;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

/**
 * @author shipeng
 *
 */
public class VanstoneDefaultPartition implements Partitioner {
	
	public static final SecureRandom random = new SecureRandom();
	
	private static Log LOG = LogFactory.getLog(VanstoneDefaultPartition.class);
	
    public VanstoneDefaultPartition (VerifiableProperties props) {
    	
    }
    
	@Override
	public int partition(Object key, int numPartitions) {
		int n = random.nextInt(numPartitions);
		if (LOG.isInfoEnabled()) {
			LOG.info("Total Partitions : " + numPartitions + " Partition Num ： " + n);
		}
		return n;
	}
	
}
