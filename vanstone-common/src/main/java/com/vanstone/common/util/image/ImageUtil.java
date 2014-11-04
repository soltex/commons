package com.vanstone.common.util.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.mortennobel.imagescaling.AdvancedResizeOp;
import com.mortennobel.imagescaling.ThumpnailRescaleOp;


/**
 * <strong>ImageUtil</strong>
 * 图片处理，支持图片缩放<br>
 * <strong>Create on : 2012-3-3<br></strong>
 * <p><strong>Copyright (C) Ecointel Software Co.,Ltd.<br></strong></p>
 * @author peng.shi peng.shi@ecointel.com.cn<br>
 * @version <strong>ecointel-roomyi v1.0.0</strong><br>
 */
public class ImageUtil {

	public static void resize(File originalFile, File thumnailFile,
			String dimension) {
		if (StringUtils.isNotBlank(dimension)
				&& StringUtils.contains(dimension, "x")) {
			int wdth = NumberUtils.createInteger(StringUtils.substringBefore(
					dimension, "x"));
			int height = NumberUtils.createInteger(StringUtils.substringAfter(
					dimension, "x"));
			resize(originalFile, thumnailFile, wdth, height, "png");
		}
	}

	/**
	 * 图片缩放
	 * @param originalFile
	 * @param thumnailFile
	 * @param newWidth
	 * @param newHeight
	 * @param format
	 */
	public static void resize(File originalFile, File thumnailFile,
			int newWidth, int newHeight, String format) {
		try {
			resize(new FileInputStream(originalFile), new FileOutputStream(
					thumnailFile), newWidth, newHeight, format);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 图片缩放
	 * @param originalStream
	 * @param thumbnailStream
	 * @param newWidth
	 * @param newHeight
	 * @param format
	 */
	public static void resize(InputStream originalStream,
			OutputStream thumbnailStream, int newWidth, int newHeight,
			String format) {
		BufferedImage originalImage;
		try {
			originalImage = ImageIO.read(originalStream);
			// 获得原始图片的宽度及高度
			int width = originalImage.getWidth();
			int height = originalImage.getHeight();
			// 判断是否有必要缩放
			if (width > 0 || height > 0) {
				AdvancedResizeOp resizeOp = new ThumpnailRescaleOp (newWidth, newHeight);
				resizeOp.setUnsharpenMask(AdvancedResizeOp.UnsharpenMask.Normal);
				BufferedImage thumbnailImage = resizeOp.filter(originalImage, null);
				ImageIO.write(thumbnailImage, format, thumbnailStream);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据width等比缩放
	 * @param originalStream
	 * @param thumbnailStream
	 * @param newWidth
	 * @param format
	 */
	public static void resize(InputStream originalStream,
			OutputStream thumbnailStream, int newWidth,String format) {
		BufferedImage originalImage;
		try {
			originalImage = ImageIO.read(originalStream);
			// 获得原始图片的宽度及高度
			int width = originalImage.getWidth();
			int height = originalImage.getHeight();
			int newHeight = new BigDecimal(newWidth).divide(new BigDecimal(width), 3, BigDecimal.ROUND_HALF_EVEN)
					.multiply(new BigDecimal(height)).setScale(0, BigDecimal.ROUND_HALF_EVEN).intValue();
			// 判断是否有必要缩放
			if (width > 0 || height > 0) {
				AdvancedResizeOp resizeOp = new ThumpnailRescaleOp (newWidth, newHeight);
				resizeOp.setUnsharpenMask(AdvancedResizeOp.UnsharpenMask.Normal);
				BufferedImage thumbnailImage = resizeOp.filter(originalImage, null);
				ImageIO.write(thumbnailImage, format, thumbnailStream);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 返回图片属性
	 * @param is
	 * @return
	 */
	public static ImagePropertyVO getProperty(InputStream is)
	{
		BufferedImage bufferedImage = null;
		try
		{
			bufferedImage = ImageIO.read(is);
			ImagePropertyVO pv = new ImagePropertyVO();
			pv.setHeight(bufferedImage.getHeight());
			pv.setWidth(bufferedImage.getWidth());
			return pv;
		}catch(IOException e) 
		{
			throw new RuntimeException();
		}
	}
	
	public static void main(String[] args)
	{
		
	}
}
