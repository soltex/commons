/**
 * 
 */
package com.vanstone.framework;

import java.util.List;

import org.springframework.util.CollectionUtils;

import com.vanstone.business.def.AbstractBusinessObject;


/**
 * @author shipeng
 *
 */
public abstract class CommaUtil {
	
	/**
	 * 业务对象转id逗号分隔符
	 * @param tags
	 * @return
	 */
	public static <T extends AbstractBusinessObject> String businessObjectIds2CommaString(List<T> tags) {
		if (!CollectionUtils.isEmpty(tags)) {
			StringBuffer sb = new StringBuffer();
			for (AbstractBusinessObject o : tags) {
				sb.append(o.getId()).append(",");
			}
			return sb.toString();
		}
		return null;
	}
	
}
