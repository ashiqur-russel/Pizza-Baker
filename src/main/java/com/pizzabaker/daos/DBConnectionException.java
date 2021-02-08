package com.pizzabaker.daos;

public class DBConnectionException extends Exception {

	public DBConnectionException(String msg, Throwable e) {
		super(msg, e);
	}
}
