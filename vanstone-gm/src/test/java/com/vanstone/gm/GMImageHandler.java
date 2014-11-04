/**
 * 
 */
package com.vanstone.gm;

import java.io.File;

import org.junit.Test;

/**
 * @author shipeng
 */
public class GMImageHandler {
	
	@Test
	public void testScaleImage() {
		IGMImageHandler handler = GMHandlerFactory.getGMImageHandler();
		handler.scaleImage(new File("/var/www/aiyutian/tmp/Chrysanthemum.jpg"), new File("/var/www/aiyutian/tmp/Chrysanthemum.jpg"), 100, 100);
	}
	
}
