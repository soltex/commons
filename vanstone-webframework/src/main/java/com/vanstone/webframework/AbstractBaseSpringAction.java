package com.vanstone.webframework;

import java.util.Date;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.vanstone.webframework.propertyeditor.DateEditor;
import com.vanstone.webframework.propertyeditor.DoubleEditor;
import com.vanstone.webframework.propertyeditor.IntegerEditor;
import com.vanstone.webframework.propertyeditor.LongEditor;

/**
 * @author peng.shi
 */
public class AbstractBaseSpringAction {
	@InitBinder
	protected void initBinder(ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(int.class, new IntegerEditor());
		binder.registerCustomEditor(long.class, new LongEditor());
		binder.registerCustomEditor(double.class, new DoubleEditor());
		binder.registerCustomEditor(Date.class, new DateEditor());
	}
}
