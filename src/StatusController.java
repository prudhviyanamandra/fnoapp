package com.rymm.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rymm.springboot.model.ErrorMessage;
import com.rymm.springboot.model.Runstatus;
import com.rymm.springboot.service.StatusService;

@RestController
public class StatusController {

	@Autowired
	private StatusService statusService;

	@Autowired
	private ControllerUtil controllerUtil;

	@RequestMapping(value = "/runstatus", method = RequestMethod.POST)
	public ResponseEntity<?> runstatus(@RequestParam(value = "networkid") String networkid,
			@RequestParam(value = "runid") String runid) {

		System.out.println(networkid);
		Runstatus runstatus;
		try {
			runstatus = statusService.readbyNetworkandRunid(networkid, runid);
			if (runstatus == null) {

				return controllerUtil.buildResponse(new ErrorMessage("Runstatus not found"), HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return controllerUtil.buildResponse(new ErrorMessage("Unknown error occured"),
					HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return controllerUtil.buildResponse(runstatus, HttpStatus.OK);
	}
}
