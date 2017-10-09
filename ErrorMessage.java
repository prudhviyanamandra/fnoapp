package com.rymm.springboot.model;

import java.io.Serializable;

public class ErrorMessage implements Serializable{

	private String message;

	public ErrorMessage(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
