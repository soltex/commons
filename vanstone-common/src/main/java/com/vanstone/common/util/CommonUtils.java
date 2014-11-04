package com.vanstone.common.util;

/**
 *
 */
public class CommonUtils {
	
	public static String parseHexStringFromBytes(final byte[] text) {
		StringBuffer buff = new StringBuffer(0);
		for (int i = 0; i < text.length; i++) {
			byte _byte = text[i];
			byte _bytel = (byte) (_byte & 0x000f);
			byte _byteh = (byte) (_byte & 0x00f0);
			getHexString(buff, (byte) ((_byteh >> 4) & 0x000f));
			getHexString(buff, _bytel);
		}
		return buff.toString();
	}
	
	private static void getHexString(final StringBuffer buffer, final byte value) {
		if (value - 9 > 0) {
			int index = value - 9;
			switch (index) {
			case 1:
				buffer.append("A");
				break;
			case 2:
				buffer.append("B");
				break;
			case 3:
				buffer.append("C");
				break;
			case 4:
				buffer.append("D");
				break;
			case 5:
				buffer.append("E");
				break;
			case 6:
				buffer.append("F");
				break;
			default:
				break;
			}
		} else {
			buffer.append(String.valueOf(value));
		}
	}
	
	public static byte[] parseBytesByHexString(final String hexString) {
		if (hexString == null || hexString.length() == 0
				|| hexString.equals("")) {
			return new byte[0];
		}
		if (hexString.length() % 2 != 0) {
			throw new IllegalArgumentException(
					"hexString length must be an even number!");
		}
		StringBuffer sb = new StringBuffer(hexString);
		StringBuffer sb1 = new StringBuffer(2);
		int n = hexString.length() / 2;
		byte[] bytes = new byte[n];
		for (int i = 0; i < n; i++) {
			if (sb1.length() > 1) {
				sb1.deleteCharAt(0);
				sb1.deleteCharAt(0);
			}
			sb1.append(sb.charAt(0));
			sb1.append(sb.charAt(1));
			sb.deleteCharAt(0);
			sb.deleteCharAt(0);
			bytes[i] = (byte) Integer.parseInt(sb1.toString(), 16);
		}
		return bytes;
	}
	
}
