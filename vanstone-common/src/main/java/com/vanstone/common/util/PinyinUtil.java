package com.vanstone.common.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import com.vanstone.common.MyAssert;

/**
 * <strong>PinyinUtil</strong><br>
 * <br>
 * <strong>Create on : 2012-2-14<br>
 * </strong>
 * <p>
 * <strong>Copyright (C) Ecointel Software Co.,Ltd.<br>
 * </strong>
 * <p>
 * @author peng.shi peng.shi@ecointel.com.cn<br>
 * @version <strong>ecointel-epp v1.0.0</strong><br>
 */
public class PinyinUtil {
	
	private static final HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
	static {
		outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		outputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		outputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
	}
	
	/**
	 * 获取字符串第一个字符首字母
	 * @param str
	 * @return
	 */
	public static String firstLetterOfString(String str) {
		MyAssert.hasText(str);
		char first = str.charAt(0);
		String[] strs = null;
		try {
			strs = PinyinHelper.toHanyuPinyinStringArray(first, outputFormat);
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		if (strs == null || strs.length <= 0) {
			return String.valueOf(first);
		}
		return String.valueOf(strs[0].charAt(0));
	}
	
	/**
	 * 获取字符串拼音首字母
	 * @param SourceStr
	 * @return
	 */
	public static String cn2py(String SourceStr) {
		MyAssert.hasText(SourceStr);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < SourceStr.length(); i++) {
			sb.append(firstLetterOfString(String.valueOf(SourceStr.charAt(i))));
		}
		return sb.toString();
	}
	
	/**
	 * String -> 拼音字符串
	 * @param source
	 * @return
	 */
	public static String cnstr2pinyinstr(String cnstr) {
		if (cnstr == null || "".equals(cnstr)) {
			return cnstr;
		}
		StringBuffer sb = new StringBuffer();
		char[] cn4chars = cnstr.toCharArray();
		for (int i=0;i<cn4chars.length;i++) {
			char c = cn4chars[i];
			if (c > 128) {
				try {
					String[] hanyupinyinChars = PinyinHelper.toHanyuPinyinStringArray(c, outputFormat);
					if (hanyupinyinChars != null && hanyupinyinChars.length >0) {
						sb.append(hanyupinyinChars[0]);
					}
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}else{
				sb.append(c);
			}
		}
		return sb.toString();
	}
	
	public static String getFirstLetter(String str) {
		MyAssert.hasText(str);
		String letters = cn2py(str);
		return letters.substring(0, 1);
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(cnstr2pinyinstr("wokaole"));
	}
}