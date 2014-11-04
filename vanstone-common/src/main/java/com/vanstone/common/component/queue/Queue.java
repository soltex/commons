package com.vanstone.common.component.queue;

import java.util.Collection;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 策略型线程安全的队列(基于JDK1.5)
 * @author liqiang
 */
public class Queue<E> implements BaseQueue<E>, AdvanceQueue<E>, DiagnoseQueue<E> {

	private BlockingQueue<E> queue = null;

	private Strategy strategy = null;

	private int capacity;

	private boolean priority;

	private final ReentrantLock lock = new ReentrantLock(false);

	/**
	 * 构造函数
	 */
	public Queue() {
		this(1024, false, null);
	}

	/**
	 * 构造函数
	 * @param capacity
	 */
	public Queue(int capacity) {
		this(capacity, false, null);
	}

	/**
	 * 构造函数
	 * @param capacity
	 * @param priority
	 */
	public Queue(int capacity, boolean priority) {
		this(capacity, priority, null);
	}

	/**
	 * 构造函数
	 * 为无界的，范围是int的最大值，所以offer,put时并不需要阻塞，一旦超出范围，将抛出OutOfMemoryError异常
	 * @param capacity 优先级队列，此参数无效
	 * @param priority
	 */
	public Queue(int capacity, boolean priority, Strategy strategy) {
		if (capacity <= 0) {
			throw new IllegalArgumentException("capacity <= 0");
		}
		this.capacity = capacity;
		this.priority = priority;
		if(priority){
			queue = new PriorityBlockingQueue<E>();
		}else{
			queue = new ArrayBlockingQueue<E>(capacity);
		}
		if(strategy == null){
			this.strategy = new DefaultStrategy();
		}else{
			this.strategy = strategy;
		}
	}

	/**
	 * 构造函数
	 * 为无界的，范围是int的最大值，所以offer,put时并不需要阻塞，一旦超出范围，将抛出OutOfMemoryError异常
	 * @param capacity 优先级队列，此参数无效
	 * @param priority
	 * @param c 可以传入已存在的集合作为队列的初始值
	 */
	public Queue(int capacity, boolean priority, Strategy strategy, Collection<E> c) {
		if (capacity <= 0) {
			throw new IllegalArgumentException("capacity <= 0");
		}
		this.capacity = capacity;
		this.priority = priority;
		if(priority){
			queue = new PriorityBlockingQueue<E>(c);
		}else{
			queue = new ArrayBlockingQueue<E>(capacity, false, c);
		}
		if(strategy == null){
			this.strategy = new DefaultStrategy();
		}else{
			this.strategy = strategy;
		}
	}

	/**
	 * 存入对象, 一直等待直到对象存入.
	 * @param obj 要存入的对象,且不能为空
	 * @throws Exception 如果当前线程被中断.
	 */
	public void put(E obj) throws Exception  {
		if (obj == null) {
			throw new Exception("null disallow");
		}
		queue.put(obj);
	}
	/**
	 * 在指定的时间内存储对象, 如果存入返回true,否则返回false.
	 * @param obj 要存入的对象,且不能为空
	 * @param maxlimit 最多等待的毫秒数, 如果<=0则立即返回.
	 * @return
	 * @throws Exception 如果当前线程被中断.
	 */
	//NANOSECONDS(0), MICROSECONDS(1), MILLISECONDS(2), SECONDS(3);
	public boolean put(E obj, long maxlimit) throws Exception {
		if (obj == null) {
			throw new Exception("null disallow");
		}
		if (maxlimit > 0) {
			return queue.offer(obj, maxlimit, TimeUnit.MILLISECONDS);
		} else {
			queue.put(obj);
			return true;
		}
	}

	/**
	 * 一直等待，直到对象返回.
	 * @return
	 * @throws InterruptedException 如果当前线程被中断.
	 */
	public Object get() throws Exception {
		return queue.take();
	}

	/**
	 * 在指定时间内等待对象返回.
	 * @param maxlimit 最多等待时间, 如果<=0, 则立即返回null.
	 * @return 返回对象, 如果超时返回null.
	 * @throws Exception 如果当前线程被中断.
	 */
	public Object get(long maxlimit) throws Exception {
		if (maxlimit > 0) {
			return queue.poll(maxlimit, TimeUnit.MILLISECONDS);
		} else {
			return queue.take();
		}
	}

	/**
	 * 队列中最多可以容纳的对象数量
	 * @return
	 */
	public int capacity() {
		return capacity;
	}

	/**
	 * 队列中已经存在的对象数量
	 * @return
	 */
	public int size() {
		return queue.size();
	}

	/**
	 * 清空当前的队列
	 */
	public void clear() {
		queue.clear();
	}

	/**
	 * 将队列中的对象列表导出到数组
	 */
	public E[] toArray(E[] objs) {
		return queue.toArray(objs);
	}

	/**
	 * 删除队列中对象
	 */
	public boolean remove(Object o) {
		return queue.remove(o);
	}

	/**
	 * 判断队列是否已满
	 * @return
	 */
	public boolean isFull() {
		return (capacity - queue.size()) <= 0;
	}

	public boolean isPriorityQueue() {
		return priority;
	}

	@SuppressWarnings("unchecked")
	public void putInStrategy(E obj) throws Exception {
		lock.lockInterruptibly();
		try{
			if(strategy.allowPut((BaseQueue<Object>) this, obj)){
				put(obj);
			}
		}finally{
			lock.unlock();
		}
	}

	@SuppressWarnings("unchecked")
	public void putInStrategy(E obj, long maxlimit) throws Exception {
		lock.lockInterruptibly();
		try{
			if(strategy.allowPut((BaseQueue<Object>) this, obj)){
				put(obj, maxlimit);
			}
		}finally{
			lock.unlock();
		}
	}

	public Object getInStrategy() throws Exception {
		return get();
	}

	public Object getInStrategy(long maxlimit) throws Exception {
		return get(maxlimit);
	}

	/**
	 * 默认策略对象
	 * @author liqiang
	 */
	private class DefaultStrategy extends Strategy {
		/* （非 Javadoc）
		 * @see com.Ecointel.bsm.queue.PutWay#allowPut(com.Ecointel.bsm.queue.IQueue)
		 * 默认队列满时丢弃
		 */
		public boolean allowPut(BaseQueue<Object> queue, Object o) {
			return true;
		}
	}

}
