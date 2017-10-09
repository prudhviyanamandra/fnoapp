package com.rymm.springboot.controller;

import java.io.File;
import java.util.UUID;

import javax.annotation.Resource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rymm.springboot.model.JobDetails;

@RestController
public class JobController {

	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	@Resource(name = "importUserJob")
	Job job;

	@Autowired
	private ControllerUtil controllerUtil;

	@RequestMapping(value = "/launchjob", method = RequestMethod.POST)
	public ResponseEntity<?> launchjob(@RequestParam(value = "networkid") String networkid,
			@RequestParam(value = "runid") String runid) {

		System.out.println(new File("").getAbsolutePath());
		JobExecution jobex = null;
		try {
			String uniqueID = UUID.randomUUID().toString();

			jobex = jobLauncher.run(job, new JobParametersBuilder().addString("uniqueID", uniqueID).toJobParameters());

		} catch (JobExecutionAlreadyRunningException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobRestartException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobInstanceAlreadyCompleteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobParametersInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JobDetails jobDetails = new JobDetails();
		jobDetails.setJobid("" + jobex.getJobId());
		return controllerUtil.buildResponse(jobDetails, HttpStatus.OK);
	}
}
