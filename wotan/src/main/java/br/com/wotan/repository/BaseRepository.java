package br.com.wotan.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

public class BaseRepository {

	@Qualifier("jdbcTemplateMySQL")
	@Autowired
	JdbcTemplate jdbcTemplateMySQL;

	public String sql;
}
