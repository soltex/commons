package com.vanstone.dal.object;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * <strong>AbstractDO</strong>
 */
public abstract class AbstractDO implements Serializable {

	private static final long serialVersionUID = -3942149913171834745L;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
