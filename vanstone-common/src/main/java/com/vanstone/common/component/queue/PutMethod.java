package com.vanstone.common.component.queue;

/**
 * 入队方式
 * @author liqiang
 */
public interface PutMethod {

	public boolean allowPut(BaseQueue<Object> queue, Object o);

}
