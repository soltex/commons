package com.vanstone.common.util.security;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;

public class Uploads {
	public static final DateFormat DEF_FORMAT = new SimpleDateFormat(
			"/yyyyMM/ddHHmmss");

	public static String filename(String extension) {
		StringBuilder filename = new StringBuilder();
		filename.append(DEF_FORMAT.format(new Date()));
		filename.append(RandomStringUtils.random(3, Hex36.HEX36_CHARS_LOWER));
		if (StringUtils.isNotBlank(extension)) {
			filename.append(".").append(extension.toLowerCase());
		}
		return filename.toString();
	}

	/**
	 * Iterates over a base name and returns the first non-existent file.<br />
	 * This method extracts a file's base name, iterates over it until the first
	 * non-existent appearance with <code>basename(n).ext</code>. Where n is a
	 * positive integer starting from one.
	 * 
	 * @param file
	 *            base file
	 * @return first non-existent file
	 */
	public static File getUniqueFile(final File file) {
		if (!file.exists())
			return file;

		File tmpFile = new File(file.getAbsolutePath());
		File parentDir = tmpFile.getParentFile();
		int count = 1;
		String extension = FilenameUtils.getExtension(tmpFile.getName());
		String baseName = FilenameUtils.getBaseName(tmpFile.getName());
		do {
			tmpFile = new File(parentDir, baseName + "_" + count++ + "."
					+ extension);
		} while (tmpFile.exists());
		return tmpFile;
	}
}
