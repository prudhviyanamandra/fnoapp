package com.rymm.springboot.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rymm.springboot.model.Runstatus;

public class RunStatusRowMapper implements RowMapper<Runstatus> {
	@Override
	public Runstatus mapRow(ResultSet rs, int rowNum) throws SQLException {
		Runstatus user = new Runstatus();
		user.setStatus(rs.getString("runstatus"));
		return user;
	}
}