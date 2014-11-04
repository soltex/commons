/**
 * 
 */
package com.vanstone.webframework;

import java.util.LinkedHashMap;
import java.util.Map;

import com.vanstone.common.MyAssert;

/**
 * @author shipeng
 */
public class CommandObject {
	
	/**
	 * 业务操作成功|失败
	 */
	private boolean businessState = true;
	/**
	 * 显示提示信息
	 */
	private String message;
	/**
	 * 转移URL
	 */
	private String redirectUrl;
	/**
	 * 传递扩展参数
	 */
	private Map<String, Object> params = new LinkedHashMap<String, Object>();
	
	public CommandObject() {}
	
	public static CommandObject createSuccessCommand(String message,String redirectUrl) {
		CommandObject command = new CommandObject();
		command.businessState = true;
		command.message = message;
		command.redirectUrl = redirectUrl;
		return command;
	}
	
	public static CommandObject createFailCommand(String message,String redirectUrl) {
		CommandObject command = new CommandObject();
		command.businessState = false;
		command.message = message;
		command.redirectUrl = redirectUrl;
		return command;
	}
	
	public boolean isBusinessState() {
		return businessState;
	}

	public String getMessage() {
		return message;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public Map<String, Object> getParams() {
		return params;
	}
	
	public void putParam(String key,Object value) {
		MyAssert.hasText(key);
		MyAssert.notNull(value);
		this.params.put(key, value);
	}
	
	public void putParams(Map<String, Object> map) {
		MyAssert.notEmpty(map);
		this.params.putAll(map);
	}
	
	public void clear() {
		this.params.clear();
	}
	
}
