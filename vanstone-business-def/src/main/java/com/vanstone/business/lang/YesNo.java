package com.vanstone.business.lang;

/**
 * <strong>YesNo</strong><br>
 * Yes/No 通用<br>
 */
public enum YesNo implements BaseEnum<Integer> {
	Yes("是", 1), No("否", 0);

	private String desc;
	private Integer code;

	private YesNo(String desc, Integer code) {
		this.desc = desc;
		this.code = code;
	}
	
	public Integer getCode() {
		return this.code;
	}

	public String getDesc() {
		return this.desc;
	}

	public static YesNo bool2YesNo(boolean flag) {
		if (flag) {
			return YesNo.Yes;
		}
		return YesNo.No;
	}

	public boolean getBoolValue() {
		if (this.equals(YesNo.Yes)) {
			return true;
		}
		return false;
	}
}
