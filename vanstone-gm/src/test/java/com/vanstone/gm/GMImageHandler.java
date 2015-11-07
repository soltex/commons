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

  // @Test
  // public void testScaleImage() {
  // IGMImageHandler handler = GMHandlerFactory.getGMImageHandler();
  // handler.scaleImage(new File("/var/www/aiyutian/tmp/Chrysanthemum.jpg"), new
  // File("/var/www/aiyutian/tmp/Chrysanthemum.jpg"), 100, 100);
  // }

  @Test
  public void testPattern() {
    // Pattern w_pattern = Pattern.compile("\\d+");
    // Pattern wh_pattern = Pattern.compile("(\\d+)x(\\d+)");
    // Pattern whs_pattern = Pattern.compile("(\\d+)x(\\d+)s");
    // Pattern h_pattern = Pattern.compile("x(\\d+)");
    // Matcher matcher = h_pattern.matcher("x100");
    // System.out.println(matcher.matches());

    File source = new File("E:/testimg/1.JPEG");
    File target = new File("E:/testimg/2.JPEG");
    IGMImageHandler handler = GMHandlerFactory.getGMImageHandler();
    handler.scaleImageByPattern(source, target, "x100");
  }
}
