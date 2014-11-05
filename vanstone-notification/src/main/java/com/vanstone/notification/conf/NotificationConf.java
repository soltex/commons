/**
 * 
 */
package com.vanstone.notification.conf;

import java.io.IOException;
import java.util.Properties;

import com.vanstone.notification.Environment;

/**
 * 配置器
 * @author shipeng
 */
public class NotificationConf {
	
	
	//======================Global======================//
	/**当前环境*/
	private Environment environment;
	
	
	
	//======================IOS======================//
	/**Test环境下证书*/
	private String certificationOfTest;
	/**Test环境下证书密码*/
	private String certificationPasswordOfTest;
	/**Live环境下证书*/
	private String certificationOfLive;
	/**Live环境下证书密码*/
	private String certificationPasswordOfLive;
	
	
	
	
	
	
	/**当前配置系统*/
	public static final String CONF_PATH = "/vanstone-notification.conf";
	
	/**苹果默认声音*/
	public static final String IOS_DEFAULT_SOUND = "default";
	
	/**IOS默认徽章*/
	public static final int IOS_DEFAULT_BADGE = 100;
	
	private NotificationConf(){}
	
	private static class NotificationConfInstance {
		private static final NotificationConf instance = new NotificationConf();
		//配置信息初始化
		static {
			Properties properties = new Properties();
			try {
				properties.load(NotificationConf.class.getResourceAsStream(CONF_PATH));
			} catch (IOException e) {
				e.printStackTrace();
				throw new ExceptionInInitializerError(e);
			}
			String env = properties.getProperty("env");
			Environment environment = Environment.getByStr(env);
			if (environment == null) {
				throw new ExceptionInInitializerError("Environment IS NULL.");
			}
			instance.environment = environment;
			//ios.live.certification
			String certificationOfLive = properties.getProperty("ios.live.certification");
			String certificationPasswordOfLive = properties.getProperty("ios.live.certification.password");
			
			instance.certificationOfLive = certificationOfLive;
			instance.certificationPasswordOfLive = certificationPasswordOfLive;
			//ios.test.certification
			String certificationOfTest = properties.getProperty("ios.test.certification");
			String certificationPasswordOfTest = properties.getProperty("ios.test.certification.password");
			instance.certificationOfTest = certificationOfTest;
			instance.certificationPasswordOfTest = certificationPasswordOfTest;
			
		}
	}
	
	/**
	 * 获取当前实例
	 * @return
	 */
	public static NotificationConf getInstance() {
		return NotificationConfInstance.instance;
	}
	
	/**
	 * 获取当前环境
	 * @return
	 */
	public Environment getEnvironment() {
		return environment;
	}
	
	/**
	 * 获取环境下证书
	 * @return
	 */
	public String getCertification() {
		valdiateEnvironment();
		String certification = null;
		if (this.getEnvironment().equals(Environment.Live)) {
			certification = this.certificationOfLive;
		}else{
			certification = this.certificationOfTest;
		}
		if (certification == null || certification.equals("")) {
			throw new IllegalArgumentException("Certification NULL.");
		}
		return certification;
	}
	
	/**
	 * 获取当前环境下证书
	 * @return
	 */
	public String getCertificationPassword() {
		valdiateEnvironment();
		String certificationPassword = null;
		if (this.getEnvironment().equals(Environment.Live)) {
			certificationPassword = this.certificationPasswordOfLive;
		}else{
			certificationPassword = this.certificationPasswordOfTest;
		}
		if (certificationPassword == null || certificationPassword.equals("")) {
			throw new IllegalArgumentException();
		}
		return certificationPassword;
	}
	
	/**
	 * 切换环境
	 * @param environment
	 * @return
	 */
	public synchronized boolean switchEnvironment(Environment environment) {
		return true;
	}
	
	private void valdiateEnvironment() {
		if (this.getEnvironment() == null) {
			throw new IllegalArgumentException("Current Environment not run,please conf your environment.");
		}
	}
	
}
