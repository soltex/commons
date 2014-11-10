/**
 * 
 */
package com.vanstone.sms;


/**
 * @author shipeng
 */
public class SMSManagerMainApp {

	public static void main(String[] args) {
		SMSManager smsManager = SMSManagerFactory.getDefaultSMSManager();
//		SMS sms = SMSBuilder.createENSMSEntity("13426173105", "【万爽力产品组】您拜访的吉林大学第二医院张基昌医生通过完成万爽力问卷调研活动的（3份问卷）获得100元充值卡1张，充值卡密码为：013931542152074325，请尽快拨打：13800138000充值电话跟进医生为：13604318480此号码进行充值。 ", null);
//		try {
//			SendState sendState = smsManager.send(sms, true);
//			Gson gson = new Gson();
//			System.out.println(gson.toJson(sendState));
//		} catch (SMSException e) {
//			e.printStackTrace();
//		}
//		EnsmsManager ensmsManager = SMSManagerFactory.getEnsmsManager();
//		try {
//			System.out.println(ensmsManager.getResidualSMSNum());
//		} catch (SMSException e) {
//			e.printStackTrace();
//		}
		SMS sms = SMSBuilder.createTodaynicSMSEntity("13426173105", "我来写第一个帖子-你们都是什么时候知道elasticsearch的？");
		try {
			smsManager.send(sms, false);
		} catch (SMSException e) {
			e.printStackTrace();
		}
		smsManager.close();
	}
}