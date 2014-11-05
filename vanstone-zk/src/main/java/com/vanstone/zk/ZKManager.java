/**
 * 
 */
package com.vanstone.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.imps.CuratorFrameworkState;

/**
 * ZK管理器 
 * @author shipeng
 */
public class ZKManager {
	
	private static class ZKManagerInstance {
		private static final ZKManager instance = new ZKManager();
		static {
			instance.init();
		}
	}
	
	private CuratorFramework curatorFramework;
	
	/**
	 * 初始化ZK
	 */
	private void init() {
		
	}
	
	public CuratorFramework getCuratorFramework() {
		return curatorFramework;
	}
	
	public void close() {
		if (curatorFramework != null && (curatorFramework.getState().equals(CuratorFrameworkState.STARTED) || curatorFramework.getState().equals(CuratorFrameworkState.LATENT))) {
			curatorFramework.close();
		}
		curatorFramework = null;
	}
	
	/**
	 * 获取当前实例
	 * @return
	 */
	public static ZKManager getZKManager() {
		return ZKManagerInstance.instance;
	}
}
