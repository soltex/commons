package com.vanstone.common.component.queue;

/**
 * 队列高级行为
 * (此行为仅暴露给业务中实际操作入队和出队行为的用户)
 * @author liqiang
 */
public interface AdvanceQueue<E> {

	public void putInStrategy(E obj) throws Exception;

	public void putInStrategy(E obj, long maxlimit) throws Exception;

	public Object getInStrategy() throws Exception;

	public Object getInStrategy(long maxlimit) throws Exception;

}
