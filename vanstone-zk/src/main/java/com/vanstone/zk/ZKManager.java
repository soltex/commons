/**
 * 
 */
package com.vanstone.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ZK管理器 
 * @author shipeng
 */
public class ZKManager {
	
	private static Logger LOG = LoggerFactory.getLogger(ZKManager.class);
	
	private static class ZKManagerInstance {
		private static final ZKManager instance = new ZKManager();
		static {
			instance.init();
		}
	}
	
	/**当前单例CuratorFramework Client*/
	private CuratorFramework curatorFramework;
	
	/**
	 * 初始化ZK
	 */
	private void init() {
		//创建CuratorFramework
		this.curatorFramework = createCuratorFramework();
		//启动CuratorFramework
		this.curatorFramework.start();
	}
	
	/**
	 * 创建ZK的CuratorFramework
	 * @return
	 */
	private CuratorFramework createCuratorFramework() {
		CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().connectString(ZKConf.getInstance().getZk())
				.retryPolicy(new RetryNTimes(Integer.MAX_VALUE, 1000)).connectionTimeoutMs(ZKConf.getInstance().getConnectionTimeoutMS()).build();
		return curatorFramework;
	}
	
	/**
	 * 获取当前CuratorFramework
	 * @return
	 */
	public CuratorFramework getCuratorFramework() {
		return curatorFramework;
	}
	
	/**
	 * 设定节点值，如Parent节点不存在，则直接创建
	 * @param node
	 * @param value
	 * @param createMode
	 */
	public void setNodeValue(String node, String value, CreateMode createMode) {
		this.validateNode(node);
		if (createMode == null) {
			createMode = CreateMode.PERSISTENT;
		}
		try {
			Stat nodeStat = this.curatorFramework.checkExists().forPath(node);
			if (nodeStat == null) {
				this.curatorFramework.create().creatingParentsIfNeeded().withMode(createMode).inBackground().forPath(node, value != null ? value.getBytes(ZKConf.SYS_CHARSET) : null);
				LOG.debug("Create Node for path {} , value {} ", node, value);
				return;
			}
			this.curatorFramework.setData().inBackground().forPath(node, value != null ? value.getBytes(ZKConf.SYS_CHARSET) : null);
			LOG.debug("Set Exist Node {}, value {}", node, value);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ZKError(e);
		}
	}
	
	/**
	 * 设定节点值
	 * @param node
	 * @param value
	 */
	public void setNodeValue(String node, String value) {
		this.setNodeValue(node, value, null);
	}
	
	/**
	 * 删除节点值
	 * @param node
	 */
	public void deleteNode(String node) {
		this.validateNode(node);
		try {
			Stat nodeStat = this.curatorFramework.checkExists().forPath(node);
			if (nodeStat == null) {
				LOG.error("Delete Node {} not exists.", node);
				return;
			}
			this.curatorFramework.delete().deletingChildrenIfNeeded().inBackground().forPath(node);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ZKError(e);
		}
	}
	
	/**
	 * 获取节点值
	 * @param node
	 * @return
	 */
	public String getNodeValue(String node) {
		this.validateNode(node);
		Stat nodeStat;
		try {
			nodeStat = this.curatorFramework.checkExists().forPath(node);
			if (nodeStat == null) {
				return null;
			}
			byte[] bytesValue = this.curatorFramework.getData().forPath(node);
			if (bytesValue == null) {
				return null;
			}
			return new String(bytesValue, ZKConf.SYS_CHARSET);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ZKError(e);
		}
	}
	
	/**
	 * 关闭当前ZKManager
	 */
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
	public static ZKManager getInstance() {
		return ZKManagerInstance.instance;
	}
	
	/**
	 * 验证节点
	 * @param node
	 */
	private void validateNode(String node) {
		if (node == null || node.equals("") || !node.startsWith("/")) {
			throw new IllegalArgumentException("node is null or node not start with '/'");
		}
	}
}
