/**
 * 
 */
package com.vanstone.zk;

import org.junit.Test;

/**
 * @author shipeng
 *
 */
public class ZKManagerTest {
	
	@Test
	public void testZKManager() {
		
		String node = "/temp/user/username";
		String value = "呵呵呵呵呵";
		ZKManager zkManager = ZKManager.getInstance();
//		zkManager.setNodeValue(node, value);
//		
//		zkManager.deleteNode(node);
		zkManager.setNodeValue(node, value);
		System.out.println(zkManager.getNodeValue(node));
		zkManager.close();
	}
	
}
