/**
 * 
 */
package com.vanstone.webframework.dwz;

/**
 * @author shipeng
 *
 */
public class DialogViewCommandObject extends ViewCommandObject {
	
	/**是否关闭当前dialog*/
	private boolean closeDialog = true;
	
	protected DialogViewCommandObject(String statusCode, boolean closedialog) {
		super(statusCode);
		this.closeDialog = closedialog;
		this.setDialog(true);
	}
	
	protected DialogViewCommandObject(String statusCode, boolean closedialog, boolean dialog) {
		super(statusCode);
		this.closeDialog = closedialog;
		this.setDialog(dialog);
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
