package com.pizzabaker.daos;

public class NoDataException extends Exception {

	public NoDataException(String msg, Throwable e) {
		super(msg, e);
	}
}
