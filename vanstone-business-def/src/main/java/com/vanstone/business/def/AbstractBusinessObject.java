package com.vanstone.business.def;

import java.beans.IntrospectionException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import com.google.gson.Gson;
import com.vanstone.business.serialize.GsonCreator;
import com.vanstone.business.serialize.SerializationUtil;

/**
 * @author shipeng
 * 
 */
public abstract class AbstractBusinessObject implements Serializable {

	private static final long serialVersionUID = 4390168867220575257L;

	/**
	 * hashCode 计算使用初始值
	 */
	private static final int PRIME = 31;

	/**
	 * 数量相关字段未初始化默认值
	 */
	public static final int COUNT_TYPE_FIELD_NOT_INITIAL_VALUE = -1;

	public AbstractBusinessObject() {
	}

	/**
	 * 获取业务对象唯一标示
	 * 
	 * @return
	 */
	public abstract Serializable getId();

	@Override
	public int hashCode() {
		if (this.getId() == null) {
			return super.hashCode();
		} else {
			int result = 1;
			return PRIME * result + this.getId().hashCode();
		}
	}

	/*
	 * 重载equals，用来判断业务对象是否相同
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		boolean typeable = obj instanceof AbstractBusinessObject;
		if (!typeable) {
			return false;
		}
		AbstractBusinessObject bo = (AbstractBusinessObject) obj;
		if (this.getId().equals(bo.getId())) {
			return true;
		}
		return false;
	}

	/**
	 * 获取CacheKey,格式如下 ： 应用程序名称_对象名称_引用id
	 * qube_com.vanstone.epp.content.Article_1
	 * ,com.vanstone.epp.content.Category_1
	 * 
	 * @return
	 */
	public String getKey() {
		if (this.getId() == null) {
			throw new IllegalArgumentException();
		}
		return BusinessObjectKeyBuilder.class2key(this.getClass(), this.getId());
	}

	/**
	 * @return
	 */
	public String toJson() {
		Gson gson = GsonCreator.create();
		return gson.toJson(this);
	}

	/**
	 * 验证当前业务对象是否合法
	 */
	public boolean validate() {
		return true;
	}

	/**
	 * 验证是否当前业务对象是否初始化
	 * 
	 * @return
	 */
	public boolean initialable() {
		if (this.getId() == null) {
			return false;
		}
		return true;
	}
	
	/**
	 * @return
	 */
	public byte[] getBytes() {
		return SerializationUtil.object2bytes(this);
	}

	/**
	 * 获取Map<String,Object>
	 * 
	 * @return
	 */
	public Map<String, Object> toMap() {
		Map<String, Object> map = null;
		try {
			map = BeanMapUtils.object2Map(this);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IntrospectionException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return map;
	}
}
