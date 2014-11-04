/**
 * 
 */
package com.vanstone.common.util;

import java.io.UnsupportedEncodingException;

/**
 * @author shipeng
 * 
 */
public class FullCharConverter {

	public static void main(String[] args) throws UnsupportedEncodingException {

		// 全角转半角
		String QJstr = "１ａ ２ｂ３ｃ４ｄ５ｅ";
		String result = full2HalfChange(QJstr);
		System.out.println(QJstr);
		System.out.println(result);
		System.out.println("------------------------------------");
		// 半角转全角
		String str = "java 汽车 召回 2345";
		System.out.println(str);
		System.out.println(half2Fullchange(str));
	}

	/**
	 * 全角转半角的 转换函数
	 * 
	 * @param QJstr
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static final String full2HalfChange(String QJstr) throws UnsupportedEncodingException {
		StringBuffer outStrBuf = new StringBuffer("");
		String Tstr = "";
		byte[] b = null;
		for (int i = 0; i < QJstr.length(); i++) {
			Tstr = QJstr.substring(i, i + 1);
			// 全角空格转换成半角空格
			if (Tstr.equals("　")) {

				outStrBuf.append(" ");

				continue;

			}

			b = Tstr.getBytes("unicode");

			// 得到 unicode 字节数据

			if (b[2] == -1) {

				// 表示全角？

				b[3] = (byte) (b[3] + 32);

				b[2] = 0;

				outStrBuf.append(new String(b, "unicode"));

			} else {

				outStrBuf.append(Tstr);

			}

		} // end for.

		return outStrBuf.toString();

	}

	/**
	 * 半角转全角
	 * @param QJstr
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static final String half2Fullchange(String QJstr) throws UnsupportedEncodingException {

		StringBuffer outStrBuf = new StringBuffer("");

		String Tstr = "";

		byte[] b = null;

		for (int i = 0; i < QJstr.length(); i++) {

			Tstr = QJstr.substring(i, i + 1);

			if (Tstr.equals(" ")) {

				// 半角空格

				outStrBuf.append(Tstr);

				continue;

			}

			b = Tstr.getBytes("unicode");

			if (b[2] == 0) {

				// 半角?

				b[3] = (byte) (b[3] - 32);

				b[2] = -1;

				outStrBuf.append(new String(b, "unicode"));

			} else {

				outStrBuf.append(Tstr);

			}

		}

		return outStrBuf.toString();

	}

}
