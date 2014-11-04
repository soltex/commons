package com.vanstone.common.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import com.vanstone.common.MyAssert;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author shipeng
 */
public class TemplateUtil {

	@SuppressWarnings("rawtypes")
	public static String template2String(String templateContent, Map map) {
		MyAssert.hasText(templateContent);
		if (map == null) {
			map = new HashMap();
		}
		
		Template t = null;
		try {
			Configuration config = new Configuration();
			t = new Template("", new StringReader(templateContent), config);
			StringWriter writer = new StringWriter();
			t.process(map, writer);
			return writer.toString();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (TemplateException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
