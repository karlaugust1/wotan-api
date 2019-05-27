package br.com.wotan.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import br.com.wotan.data.model.Curso;

public class CursoRowMapper implements RowMapper<Curso> {
	
	@Override
	public Curso mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Curso curso = (new BeanPropertyRowMapper<>(Curso.class)).mapRow(rs, rowNum);
		
		return curso;
	}

}