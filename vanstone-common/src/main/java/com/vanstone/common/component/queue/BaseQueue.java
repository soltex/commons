package com.vanstone.common.component.queue;

/**
 * 队列基本行为
 * (此行为仅暴露给定制策略的用户)
 * @author liqiang
 */
public interface BaseQueue<E> {

	public void put(E obj) throws Exception;

	public boolean put(E obj, long maxlimit) throws Exception;

	public Object get() throws Exception;

	public Object get(long maxlimit) throws Exception;

	public int capacity();

	public int size();

	public void clear();

	public boolean isFull();

	public E[] toArray(E[] objs);

	public boolean remove(Object o);

}
