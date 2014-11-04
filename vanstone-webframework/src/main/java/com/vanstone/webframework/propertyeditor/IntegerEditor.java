package com.vanstone.webframework.propertyeditor;

import java.beans.PropertyEditorSupport;

import org.springframework.util.StringUtils;

public class IntegerEditor extends PropertyEditorSupport {
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text == null || text.equals(""))
			text = "0";
		if (!StringUtils.hasText(text)) {
			setValue(null);
		} else {
			// 这句话是最重要的，他的目的是通过传入参数的类型来匹配相应的databind
			setValue(Integer.parseInt(text));
		}
	}

	@Override
	public String getAsText() {

		return getValue().toString();
	}
}
