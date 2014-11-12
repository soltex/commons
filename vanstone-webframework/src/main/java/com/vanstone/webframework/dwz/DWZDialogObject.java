/**
 * 
 */
package com.vanstone.webframework.dwz;


/**
 * @author shipeng
 * 
 */
public class DWZDialogObject extends DWZObject {
	
	/**是否关闭当前dialog*/
	private boolean closeDialog = true;
	
	private DWZDialogObject(String statusCode, boolean closedialog) {
		super(statusCode);
		this.closeDialog = closedialog;
		this.setDialog(true);
	}
	
	/**
	 * 创建对象
	 * 
	 * @param dwzStatusCode
	 * @return
	 */
	public static DWZDialogObject createDialog(StatusCode statusCode, boolean closeDialog) {
		return new DWZDialogObject(statusCode.getCode(), closeDialog);
	}
	
	/**
	 * 创建成功对话框对象
	 * @param closeDialog
	 * @return
	 */
	public static DWZDialogObject createSuccessDialog(boolean closeDialog) {
		return new DWZDialogObject(StatusCode.Success.getCode(), closeDialog);
	}
	
	/**
	 * 创建错误对话框
	 * @param closeDialog
	 * @return
	 */
	public static DWZDialogObject createErrorDialog(boolean closeDialog) {
		return new DWZDialogObject(StatusCode.Error.getCode(), closeDialog);
	}
	
	/**
	 * 是否关闭对话框
	 * 
	 * @return
	 */
	public boolean isCloseDialog() {
		return closeDialog;
	}
}
