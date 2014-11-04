package com.vanstone.business;

/**
 * 对象不存在异常
 * @author shipeng
 */
public class ObjectNotFoundException extends VanstoneException {
	private static final long serialVersionUID = 2446921528880894125L;

	public ObjectNotFoundException() {
		super();
	}

	public ObjectNotFoundException(String message) {
		super(message);
	}

	public ObjectNotFoundException(Throwable cause) {
		super(cause);
	}

	public ObjectNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
