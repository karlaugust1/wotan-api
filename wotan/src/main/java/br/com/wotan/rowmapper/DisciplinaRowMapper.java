package br.com.wotan.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import br.com.wotan.data.model.Curso;
import br.com.wotan.data.model.Disciplina;

public class DisciplinaRowMapper implements RowMapper<Disciplina> {
	
	@Override
	public Disciplina mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Disciplina disciplina = (new BeanPropertyRowMapper<>(Disciplina.class)).mapRow(rs, rowNum);
		Curso curso = (new BeanPropertyRowMapper<>(Curso.class)).mapRow(rs, rowNum);
		
		disciplina.setCurso(curso);
		return disciplina;
	}

}