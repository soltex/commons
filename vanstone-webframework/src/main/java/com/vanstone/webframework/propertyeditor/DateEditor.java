package com.vanstone.webframework.propertyeditor;

import java.beans.PropertyEditorSupport;

import org.springframework.util.StringUtils;

import com.vanstone.common.util.CommonDateUtil;


public class DateEditor extends PropertyEditorSupport {
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (!StringUtils.hasText(text)) {
			setValue(null);
		} else {
			setValue(CommonDateUtil.string2Date(text, "yyyy-MM-dd"));
		}
	}
	@Override
	public String getAsText() {
		return getValue().toString();
	}
}
