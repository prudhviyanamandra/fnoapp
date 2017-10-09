package com.rymm.springboot.model;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class ApiResponse<T> {

	private HttpStatus responseType;
	private Date timestamp;
	private T payload;

	public void setResponseType(HttpStatus responseType) {
		this.responseType = responseType;
	}

	public HttpStatus getResponseType() {
		return responseType;
	}

	

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Object getPayload() {
		return payload;
	}

	public void setPayload(T payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		return "ImlResponse{" + "responseType=" + responseType + " , timestamp="
				+ timestamp + ", payload=" + payload + '}';
	}
}
