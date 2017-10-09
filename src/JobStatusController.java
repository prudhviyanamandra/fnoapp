package com.rymm.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rymm.springboot.model.ErrorMessage;
import com.rymm.springboot.model.JobStatus;
import com.rymm.springboot.service.JobStatusService;

@RestController
public class JobStatusController {

	@Autowired
	private JobStatusService jobStatusService;
	@Autowired
	private ControllerUtil controllerUtil;

	@RequestMapping(value = "/jobstatus", method = RequestMethod.POST)
	public ResponseEntity<?> jobstatus(@RequestParam(value = "jobid") String jobid) {

		JobStatus jobStatus;
		try {
			jobStatus = jobStatusService.getJobStatus(jobid);
			if (jobStatus == null) {

				return controllerUtil.buildResponse(new ErrorMessage("Job not found"), HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return controllerUtil.buildResponse(new ErrorMessage("Unknown error occured"),
					HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return controllerUtil.buildResponse(jobStatus, HttpStatus.OK);
	}
}
