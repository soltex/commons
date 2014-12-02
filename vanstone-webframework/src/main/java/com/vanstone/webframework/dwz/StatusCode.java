/**
 * 
 */
package com.vanstone.webframework.dwz;

import com.vanstone.business.lang.BaseEnum;

/**
 * View Command 标志码
 * @author shipeng
 */
public enum StatusCode implements BaseEnum<String> {
	Success("操作成功", "200"), Error("操作失败", "300"), Timeout("操作超时", "301"), Redirect("重定向", "302");
	;
	
	private String desc;
	private String code;

	private StatusCode(String desc, String code) {
		this.desc = desc;
		this.code = code;
	}

	@Override
	public String getCode() {
		return this.code;
	}

	@Override
	public String getDesc() {
		return this.desc;
	}
}
