package com.rymm.springboot.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rymm.springboot.model.Runstatus;
import com.rymm.springboot.rowmapper.RunStatusRowMapper;

@Repository
public class RunStatusDao extends BaseDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Transactional(readOnly = true)
	public Runstatus readbyNetworkandRunid(String networkid, String runid) {
		return jdbcTemplate.queryForObject("select * from runstatus where networkid=? and runid=?",
				new Object[] { networkid, runid }, new RunStatusRowMapper());
	}
}
