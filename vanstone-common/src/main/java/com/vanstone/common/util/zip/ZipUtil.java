/**
 * 
 */
package com.vanstone.common.util.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import org.apache.commons.io.IOUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vanstone.common.MyAssert;

/**
 * 压缩工具类
 * @author shipeng
 *
 */
public abstract class ZipUtil {
	
	private static Logger LOG = LoggerFactory.getLogger(ZipUtil.class);
	
	/**
	 * 压缩文件到zip文件中
	 * @param files
	 * @param zipFile
	 * @throws IOException 
	 */
	public static void compressFiles(File zipFile, File... files ) throws IOException {
		MyAssert.notNull(files);
		MyAssert.notNull(zipFile);
		if (!zipFile.exists()) {
			zipFile.createNewFile();
		}
		ZipOutputStream zos = new ZipOutputStream(zipFile);
		for (File file : files) {
			ZipEntry zipEntry = new ZipEntry(file.getName());
			zos.putNextEntry(zipEntry);
			InputStream is = new FileInputStream(file);
			IOUtils.copy(is, zos);
			LOG.info("Compress File " + file.getName() + " to " + zipFile.getName());
			IOUtils.closeQuietly(is);
		}
		zos.close();
		IOUtils.closeQuietly(zos);
		LOG.info("Finish compress zip " + zipFile.getName());
	}
	
	/**
	 * 提取文件
	 * @param zipFile
	 * @param name
	 * @param extFile
	 * @throws IOException
	 */
	public static boolean extractFile(File zipFile, String name, File extFile) throws IOException {
		MyAssert.notNull(zipFile);
		MyAssert.hasText(name);
		MyAssert.notNull(extFile);
		
		ZipFile zip = new ZipFile(zipFile);
		ZipEntry zipEntry = zip.getEntry(name);
		if (zipEntry == null) {
			return false;
		}
		InputStream is = zip.getInputStream(zipEntry);
		OutputStream os = new FileOutputStream(extFile);
		IOUtils.copy(is, os);
		IOUtils.closeQuietly(is);
		IOUtils.closeQuietly(os);
		LOG.info("Finish extract file " + name);
		return true;
	}
	
	/**
	 * 解压文件到指定目录
	 * @param zipFile
	 * @param directory
	 * @throws IOException 
	 */
	public static void extractZipFile(File zipFile, File directory) throws IOException {
		MyAssert.notNull(zipFile);
		MyAssert.notNull(directory);
		if (!zipFile.exists()) {
			throw new IllegalArgumentException();
		}
		if (!directory.exists()) {
			directory.mkdirs();
		}
		ZipFile zip = new ZipFile(zipFile);
		Enumeration<ZipEntry> entries = zip.getEntries();
		while (entries.hasMoreElements()) {
			ZipEntry zipEntry = entries.nextElement();
			InputStream is = zip.getInputStream(zipEntry);
			File outFile = new File(directory.getPath() + "/" + zipEntry.getName());
			if (!outFile.exists()) {
				outFile.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(outFile);
			IOUtils.copy(is, fos);
			IOUtils.closeQuietly(is);
			IOUtils.closeQuietly(fos);
			LOG.info("Extract File " + zipEntry.getName());
		}
		LOG.info("Finish extract all files.");
	}
	
}
