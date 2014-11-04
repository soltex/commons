/**
 * 
 */
package com.vanstone.sms;

import java.util.List;

/**
 * @author shipeng
 */
public interface ISMSOf1069009130Manager {
	/**
	 * 106短信下发
	 * @param mtObject
	 * @return
	 */
	List<C106ChannelCode> sendC106Msg(List<String> mobiles,String msg, int port);
	
	/**
	 * 106短信发下
	 * @param mobile
	 * @param msg
	 * @param port
	 * @return
	 */
	C106ChannelCode sendC106Msg(String mobile,String msg,int port);
	
	/**
	 * 获取106短信库存量 
	 * @return
	 */
	int getC106Inventories();
}
