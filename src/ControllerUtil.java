package com.rymm.springboot.controller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.rymm.springboot.model.ApiResponse;

@Component

public class ControllerUtil {

	public ResponseEntity<ApiResponse> buildResponse(Object payload, HttpStatus status) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setTimestamp(new Date());
		apiResponse.setPayload(payload);
		apiResponse.setResponseType(status);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}
}
