/**
 * 
 */
package com.vanstone.common.component.task;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vanstone.common.MyAssert;

/**
 * 任务执行器
 * @author shipeng
 */
class DefaultTaskManagerImpl implements TaskManager {
	
	private static Logger LOG = LoggerFactory.getLogger(DefaultTaskManagerImpl.class);
	
	/** 默认线程池 */
	private ExecutorService executorService = null;
	
	public DefaultTaskManagerImpl() {
		this(DEFAULT_SYS_TASK_THREAD_NUM);
	}
	
	public DefaultTaskManagerImpl(int threadNum) {
		if (threadNum <=0) {
			throw new IllegalArgumentException();
		}
		executorService = Executors.newFixedThreadPool(threadNum);
		LOG.info("Task Manager Initial Ok. Default Thread Num [{}]", threadNum);
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.framework.systask.SysTaskManager#executeTask(java.util.concurrent.Callable, boolean)
	 */
	@Override
	public <T> T executeTask(Callable<T> callable, boolean asyn) {
		MyAssert.notNull(callable);
		Future<T> future = executorService.submit(callable);
		if (asyn) {
			LOG.debug("ASYN execute Callable.");
			return null;
		}
		try {
			LOG.debug("SYNC execute Callable.");
			T t = future.get();
			return t;
		} catch (InterruptedException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (ExecutionException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.framework.systask.SysTaskManager#close()
	 */
	@Override
	public void close() {
		this.executorService.shutdown();
	}

}
