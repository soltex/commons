/**
 * 
 */
package com.vanstone.webframework.dwz;

import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.vanstone.business.serialize.GsonCreator;

/**
 * @author shipeng
 *
 */
public class ViewCommandObject {
	
	
	/** 操作标志位*/
	private String statusCode;
	/** 显示信息*/
	private String message;
	/** 关联显示ID*/
	private String rel;
	/** 回调类型*/
	private String callbackType;
	/** 转向的URL*/
	private String forwardUrl;
	/** 操作中间过程的确认信息*/
	private String confirmMsg;
	/** 是否装载对话框*/
	private boolean dialog = false;
	/** DWZ参数信息*/
	private Map<String, Object> params = new LinkedHashMap<String, Object>();
	
	private String redirectUrl;
	
	protected ViewCommandObject(String  statusCode) {
		this.statusCode = statusCode;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getCallbackType() {
		return callbackType;
	}

	public void setCallbackType(String callbackType) {
		this.callbackType = callbackType;
	}

	public String getForwardUrl() {
		return forwardUrl;
	}

	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}

	public String getConfirmMsg() {
		return confirmMsg;
	}

	public void setConfirmMsg(String confirmMsg) {
		this.confirmMsg = confirmMsg;
	}
	
	public String getStatusCode() {
		return statusCode;
	}

	public Map<String, Object> getParams() {
		return params;
	}
	
	public void addParam(String name, Object value) {
		this.params.put(name, value);
	}
	
	public void addParams(Map<String, Object> params) {
		this.params.putAll(params);
	}
	
	public void clearParams() {
		this.params.clear();
	}
	
	public boolean isDialog() {
		return dialog;
	}

	public void setDialog(boolean dialog) {
		this.dialog = dialog;
	}
	
	public String getRedirectUrl() {
		return redirectUrl;
	}
	
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	
	/**
	 * 生成Json字符串 
	 * @return
	 */
	public String toJsonString() {
		Gson gson = GsonCreator.create();
		return gson.toJson(this);
	}
	
}
