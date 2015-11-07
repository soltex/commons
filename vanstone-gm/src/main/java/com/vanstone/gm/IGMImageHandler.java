/**
 * 
 */
package com.vanstone.gm;

import java.io.File;

/**
 * 图片处理
 * 
 * @author shipeng
 */
public interface IGMImageHandler {

  /**
   * 默认图片宽度
   */
  public static final int DEFAULT_WIDTH = 200;

  /**
   * 默认图片高度
   */
  public static final int DEFAULT_HEIGHT = 200;

  /**
   * 图片质量
   */
  public static final int DEFAULT_QUALITY = 80;

  /**
   * 缩放
   * 
   * @param source
   * @param target
   * @param width
   * @param height
   * @return
   */
  boolean scaleImage(File source, File target, Integer width, Integer height);

  /**
   * 缩放
   * 
   * @param source
   * @param target
   * @param quality
   * @param width
   * @param height
   * @return
   */
  boolean scaleImage(File source, File target, Integer quality, Integer width, Integer height);

  /**
   * 根据缩放字符串缩放
   * 
   * @param source
   * @param target
   * @param quality
   * @param strScale
   * @return
   */
  boolean scaleImage(File source, File target, Integer quality, String strScale);

  /**
   * 生成指定宽度和高度的图片
   * 
   * @param source
   * @param target
   * @param width
   * @param height
   * @return
   */
  boolean scaleImageExtent(File source, File target, Integer width, Integer height);

  /**
   * @param source
   * @param target
   * @param width
   * @param quality
   * @param height
   * @return
   */
  boolean scaleImageExtent(File source, File target, Integer quality, Integer width, Integer height);

  /**
   * 通过宽度等比缩放
   * 
   * @param source
   * @param target
   * @param width
   */
  boolean scaleImageByWidth(File source, File target, int width);

  /**
   * @param source
   * @param target
   * @param quality
   * @param width
   * @return
   */
  boolean scaleImageByWidth(File source, File target, Integer quality, int width);

  /**
   * 通过高度等比缩放
   * 
   * @param source
   * @param target
   * @param height
   */
  boolean scaleImageByHeight(File source, File target, int height);

  /**
   * @param source
   * @param target
   * @param quality
   * @param height
   * @return
   */
  boolean scaleImageByHeight(File source, File target, Integer quality, int height);

  /**
   * 合并图片
   * 
   * @param sourceFiles
   * @param targetFile
   * @param direction
   * @param xspace
   * @param yspace
   */
  boolean mergeImages(File[] sourceFiles, File targetFile, Direction direction, int xspace, int yspace);

  /**
   * 为图片增加水印 左上角，左下角 右上角，右下角
   * 
   * @param sourceFile
   * @param watermartFile
   * @param targetFile
   * @param gravity 默认为右下角
   * @return
   */
  boolean watermarkImage(File sourceFile, File watermartFile, File targetFile, Gravity gravity, int dissolve);

  /**
   * 图片质量
   * 
   * @param sourceFile
   * @param targetFile
   * @param quality
   * @return
   */
  boolean qualityImage(File sourceFile, File targetFile, int quality);

  boolean scaleImageByPattern(File source, File target, String pattern, int quality);

  boolean scaleImageByPattern(File source, File target, String pattern);

}
