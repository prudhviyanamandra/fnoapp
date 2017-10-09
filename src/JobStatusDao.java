package com.rymm.springboot.dao;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.rymm.springboot.model.JobStatus;
import com.rymm.springboot.rowmapper.JobStatusRowMapper;

@Repository
public class JobStatusDao extends BaseDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	String fileName = "jobstatus.csv";

	@Transactional(readOnly = true)
	public JobStatus getJobStatus(String jobid) throws Exception {

		// return jdbcTemplate.queryForObject("select * from batch_job_execution
		// where JOB_EXECUTION_ID=?",
		// new Object[] { jobid }, new JobStatusRowMapper());
		File inputFile = new File(fileName);

		CSVReader reader = new CSVReader(new FileReader(inputFile), ',');
		List<String[]> csvBody = reader.readAll();
		JobStatus jobStatus = new JobStatus();
		for (String[] strings : csvBody) {
			String id = strings[0];
			if (id.equals(jobid)) {

				jobStatus.setStatus(strings[1]);
				break;
			}
		}

		reader.close();

		return jobStatus;
	}

	public void writeJobStatus(String jobid, String Status) throws Exception {

		File inputFile = new File(fileName);
		if (!inputFile.exists()) {
			new File(fileName).createNewFile();
		}
		CSVReader reader = new CSVReader(new FileReader(inputFile), ',');
		List<String[]> csvBody = reader.readAll();
		boolean foundJob = false;
		for (int i = 0; i < csvBody.size(); i++) {
			String[] data = csvBody.get(i);
			if (data[0].equals(jobid)) {
				csvBody.get(i)[1] = Status;
				foundJob = true;
				break;
			}
		}
		if(!foundJob){
			String[] jobdata = new String[2];
			jobdata[0] = jobid;
			jobdata[1] = Status;
			csvBody.add(jobdata);
			
		}
		// Read existing file

		// get CSV row column and replace with by using row and column

		// Write to CSV file which is open
		CSVWriter writer = new CSVWriter(new FileWriter(inputFile), ',');
		writer.writeAll(csvBody);
		writer.flush();
		writer.close();
	}

}
