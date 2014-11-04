package com.vanstone.business.lang;

/**
 * <strong>Title : BaseEnum<br>
 * </strong> <strong>Description : </strong> 
 * 基础业务枚举类,K为泛型,为Code字段的类型定义<br>
 */
public interface BaseEnum<K> {
	/**
	 * 得到存入Db/或者代表的值
	 * 
	 * @return
	 */
	K getCode();

	/**
	 * 描述信息
	 * 
	 * @return
	 */
	String getDesc();

	/**
	 * 显示文本名称
	 * 
	 * @return
	 */
	String name();

}
