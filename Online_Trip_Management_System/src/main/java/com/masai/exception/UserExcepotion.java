package com.masai.exception;

public class UserExcepotion extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UserExcepotion() {}
	
	public UserExcepotion(String msg) {
		super(msg);
	}

}
