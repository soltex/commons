/**
 * 
 */
package com.vanstone.quartz;

import java.util.Map;

/**
 * @author shipeng
 */
public interface IJobInfo {
	
	/**
	 * 获取Job唯一名称
	 * @return
	 */
	String getJobname();

	/**
	 * Job Group名称
	 * @return
	 */
	String getJobgroup();

	/**
	 * @return
	 */
	String getJobdetailClass();

	/**
	 * @return
	 */
	Map<String, Object> getDataMap();

	/**
	 * @return
	 */
	String getDescription();
}
