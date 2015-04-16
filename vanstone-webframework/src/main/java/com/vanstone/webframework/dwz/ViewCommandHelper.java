/**
 * 
 */
package com.vanstone.webframework.dwz;

import com.google.gson.Gson;
import com.vanstone.business.serialize.GsonCreator;

/**
 * View Command Helper类
 * @author shipeng
 *
 */
public class ViewCommandHelper {
	
	/**
	 * 创建对象
	 * @param dwzStatusCode
	 * @return
	 */
	public static ViewCommandObject create(StatusCode statusCode) {
		return new ViewCommandObject(statusCode.getCode());
	}
	
	/**
	 * 创建对象
	 * @param dwzStatusCode
	 * @param message
	 * @return
	 */
	public static ViewCommandObject create(StatusCode statusCode, String message) {
		ViewCommandObject object = new ViewCommandObject(statusCode.getCode());
		object.setMessage(message);
		return object;
	}
	
	/**
	 * 创建成功对象
	 * @param message
	 * @return
	 */
	public static ViewCommandObject createSuccessObject(String message) {
		return create(StatusCode.Success, message);
	}
	
	/**
	 * 创建成功对象
	 * @return
	 */
	public static ViewCommandObject createSuccessObject() {
		return create(StatusCode.Success);
	}
	
	/**
	 * 创建错误对象
	 * @param message
	 * @return
	 */
	public static ViewCommandObject createErrorObject(String message) {
		return create(StatusCode.Error, message);
	}
	
	/**
	 * 创建redirect object
	 * @param redirectUrl
	 * @return
	 */
	public static ViewCommandObject createRedirectObject(String redirectUrl) {
		ViewCommandObject object = create(StatusCode.Redirect);
		object.setRedirectUrl(redirectUrl);
		return object;
	}
	
	/**
	 * 创建错误对象
	 * @return
	 */
	public static ViewCommandObject createErrorObject() {
		return create(StatusCode.Error);
	}
	
	/**
	 * 创建对象
	 * 
	 * @param dwzStatusCode
	 * @return
	 */
	public static DialogViewCommandObject createDialog(StatusCode statusCode, boolean closeDialog) {
		return new DialogViewCommandObject(statusCode.getCode(), closeDialog);
	}
	
	/**
	 * 创建对象
	 * @param statusCode
	 * @param closeDialog
	 * @param dialog
	 * @return
	 */
	public static DialogViewCommandObject createDialog(StatusCode statusCode, boolean closeDialog, boolean dialog) {
		return new DialogViewCommandObject(statusCode.getCode(), closeDialog, dialog);
	}
	
	/**
	 * @param closeDialog
	 * @param dialog
	 * @return
	 */
	public static DialogViewCommandObject createSuccessObject(boolean closeDialog, boolean dialog) {
		return new DialogViewCommandObject(StatusCode.Success.getCode(), closeDialog, dialog);
	}
	
	/**
	 * 创建成功对话框对象
	 * @param closeDialog
	 * @return
	 */
	public static DialogViewCommandObject createSuccessDialog(boolean closeDialog) {
		return new DialogViewCommandObject(StatusCode.Success.getCode(), closeDialog);
	}
	
	/**
	 * 创建错误对话框
	 * @param closeDialog
	 * @return
	 */
	public static DialogViewCommandObject createErrorDialog(boolean closeDialog) {
		return new DialogViewCommandObject(StatusCode.Error.getCode(), closeDialog);
	}
	
	/**
	 * 创建错误对话框
	 * @param closeDialog
	 * @param dialog
	 * @return
	 */
	public static DialogViewCommandObject createErrorDialog(boolean closeDialog, boolean dialog) {
		return new DialogViewCommandObject(StatusCode.Error.getCode(), closeDialog, dialog);
	}
	
	public static ViewCommandObject parse(String json) {
		Gson gson = GsonCreator.create();
		return gson.fromJson(json, ViewCommandObject.class);
	}
	
}
