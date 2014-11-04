/**
 * 
 */
package com.vanstone.quartz.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.vanstone.quartz.IJobInfo;

/**
 * @author shipeng
 */
public class JobInfo implements IJobInfo {
	
	private String jobname;
	private String jobgroup;
	private String jobdetailClass;
	private String description;
	private Map<String, Object> dataMap = new LinkedHashMap<String, Object>();
	
	public String getJobname() {
		return jobname;
	}

	public void setJobname(String jobname) {
		this.jobname = jobname;
	}

	public String getJobgroup() {
		return jobgroup;
	}

	public void setJobgroup(String jobgroup) {
		this.jobgroup = jobgroup;
	}

	public String getJobdetailClass() {
		return jobdetailClass;
	}

	public void setJobdetailClass(String jobdetailClass) {
		this.jobdetailClass = jobdetailClass;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void put(String key,Object value) {
		if (StringUtils.isEmpty(key) || StringUtils.isBlank(key)) {
			throw new IllegalArgumentException();
		}
		this.dataMap.put(key, value);
	}
	
	public void putAll(Map<String, Object> maps) {
		if (maps == null) {
			return;
		}
		this.dataMap.putAll(maps);
	}
}