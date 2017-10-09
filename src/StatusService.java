package com.rymm.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rymm.springboot.dao.RunStatusDao;
import com.rymm.springboot.model.Runstatus;

@Service
public class StatusService {

	@Autowired
	private RunStatusDao runStatusDao;

	public Runstatus readbyNetworkandRunid(String networkid, String runid) {
		return runStatusDao.readbyNetworkandRunid(networkid, runid);
	}

}
