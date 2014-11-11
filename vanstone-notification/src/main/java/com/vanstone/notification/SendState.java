/**
 * 
 */
package com.vanstone.notification;

/**
 * 通知状态
 * 
 * @author shipeng
 */
public class SendState {

	private int successNum = 0;
	private int failNum = 0;

	/**
	 * 创建单体发送结果状态
	 * @param isSuccess
	 * @return
	 */
	public static SendState createSingleNotificationState(boolean isSuccess) {
		SendState state = new SendState();
		if (isSuccess) {
			state.incSuccessNum();
		}else {
			state.incFailNum();
		}
		return state;
	}
	
	/**
	 * 成功数量
	 * @return
	 */
	public int getSuccessNum() {
		return successNum;
	}
	
	/**
	 * 失败数量
	 * @return
	 */
	public int getFailNum() {
		return failNum;
	}
	
	/**
	 * 增加成功数量
	 */
	public void incSuccessNum() {
		this.successNum++;
	}
	
	/**
	 * 增加成功数量
	 * @param num
	 */
	public void incSuccessNum(int num) {
		this.successNum = this.successNum + num;
	}
	
	/**
	 * 增加失败数量
	 */
	public void incFailNum() {
		this.failNum ++;
	}
	
	/**
	 * 增加失败数量
	 * @param num
	 */
	public void incFailNum(int num) {
		this.failNum = this.failNum + num;
	}
	
	/**
	 * 是否完全成功
	 * @return
	 */
	public boolean isFullySuccess() {
		if (this.failNum == 0) {
			return true;
		}
		return false;
	}
	
}
