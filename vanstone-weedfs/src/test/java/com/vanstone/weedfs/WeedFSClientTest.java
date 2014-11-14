/**
 * 
 */
package com.vanstone.weedfs;

import java.io.File;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vanstone.weedfs.client.RequestResult;
import com.vanstone.weedfs.client.impl.WeedFSClient;

/**
 * @author shipeng
 */
public class WeedFSClientTest {
	
	@Test
	public void testUploadWeed() {
		String path = "/var";
		File[] files = new File(path).listFiles();
		WeedFSClient weedFSClient = new WeedFSClient();
		for (File file : files) {
			if (file.isFile()){
				RequestResult requestResult = weedFSClient.upload(file);
				System.out.println(requestResult.getFid());
			}
		}
	}
	
	@Test
	public void testUploadFile() {
		String path = "/var/hudson-3.0.1.war";
		WeedFSClient weedFSClient = new WeedFSClient();
		RequestResult requestResult = weedFSClient.upload(new File(path));
		System.out.println(requestResult);
	}
	
	@Test
	public void testDeleteFSFile() {
		String fileid = "7,0e8ce54c44065c";
		WeedFSClient weedFSClient = new WeedFSClient();
		RequestResult requestResult = weedFSClient.delete(fileid);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		System.out.println(gson.toJson(requestResult));
	}
	
}
