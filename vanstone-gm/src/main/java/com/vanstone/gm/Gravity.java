/**
 * 
 */
package com.vanstone.gm;

/**
 * 方位
 * 
 * @author shipeng
 */
public enum Gravity {

	east("东"), south("南"), west("西"), north("北"), westnorth("西北"), eastnorth("东北"), westsouth("西南"), southeast("东南");

	private String desc;

	private Gravity(String desc) {
		this.desc = desc;
	}

	public String getCode() {
		return this.toString();
	}

	public String getDesc() {
		return desc;
	}

}
