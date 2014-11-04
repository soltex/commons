package com.vanstone.business;


/**
 * 对象重复异常
 * @author shipeng
 */
public class ObjectDuplicateException extends VanstoneException {

	private static final long serialVersionUID = 2446921528880894125L;

	public ObjectDuplicateException() {
		super();
	}

	public ObjectDuplicateException(String message) {
		super(message);
	}

	public ObjectDuplicateException(Throwable cause) {
		super(cause);
	}

	public ObjectDuplicateException(String message, Throwable cause) {
		super(message, cause);
	}

}
