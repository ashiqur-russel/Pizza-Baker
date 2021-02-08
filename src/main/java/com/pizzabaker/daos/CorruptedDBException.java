package com.pizzabaker.daos;

public class CorruptedDBException extends Exception {

	public CorruptedDBException(String msg, Throwable e) {
		super(msg, e);
	}
}
