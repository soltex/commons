/**
 * 
 */
package com.vanstone.elasticsearch.conf;

import java.util.ArrayList;
import java.util.List;

/**
 * Elasticsearch配置中心
 * @author shipeng
 */
public class ESConfig {

	public ESConfig() {
	}

	private String clusterName;
	private String[] addresses;

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public String[] getAddresses() {
		return addresses;
	}

	public void setAddresses(String[] addresses) {
		this.addresses = addresses;
	}
	
	/**
	 * 获取地址包装类
	 * @return
	 */
	public AddressWare[] getAddressDetails() {
		if (this.addresses == null || this.addresses.length <=0) {
			return null;
		}
		List<ESConfig.AddressWare> wares = new ArrayList<ESConfig.AddressWare>();
		for (String str : addresses) {
			String[] ss = str.split(":");
			if (ss.length != 2) {
				throw new IllegalArgumentException("esconf.addressware parse error.");
			}
			AddressWare ware = new AddressWare(ss[0], Integer.parseInt(ss[1]));
			wares.add(ware);
		}
		return wares.toArray(new AddressWare[wares.size()]);
	}
	
	public static class AddressWare{
		private String addr;
		private int port;
		
		public AddressWare(String addr,int port) {
			if (addr == null || addr.equals("") || port <=0) {
				throw new IllegalArgumentException();
			}
			this.addr = addr;
			this.port = port;
		}
		
		public String getAddr() {
			return addr;
		}
		public int getPort() {
			return port;
		}
		
	}
}
