package com.vanstone.business;

/**
 * 对象包含子对象异常
 * @author shipeng
 */
public class ObjectHasSubObjectException extends VanstoneException {
	
	private static final long serialVersionUID = 2087539659791955385L;
	
	public ObjectHasSubObjectException() {
		super();
	}
	
	public ObjectHasSubObjectException(String message) {
		super(message);
	}

	public ObjectHasSubObjectException(Throwable cause) {
		super(cause);
	}

	public ObjectHasSubObjectException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
