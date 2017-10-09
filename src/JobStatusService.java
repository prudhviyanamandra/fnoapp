package com.rymm.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rymm.springboot.dao.JobStatusDao;
import com.rymm.springboot.dao.RunStatusDao;
import com.rymm.springboot.model.JobStatus;
import com.rymm.springboot.model.Runstatus;

@Service
public class JobStatusService {

	@Autowired
	private JobStatusDao jobStatusDao;

	public JobStatus getJobStatus(String jobid) {
		try {
			return jobStatusDao.getJobStatus(jobid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
