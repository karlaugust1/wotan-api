package br.com.wotan.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import br.com.wotan.data.model.Professor;
import br.com.wotan.data.model.ProfessorDisciplina;
import br.com.wotan.data.model.Usuario;

public class ProfessorRowMapper implements RowMapper<Professor> {
	
	@Override
	public Professor mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Professor professor = (new BeanPropertyRowMapper<>(Professor.class)).mapRow(rs, rowNum);
		Usuario usuario = (new BeanPropertyRowMapper<>(Usuario.class)).mapRow(rs, rowNum);
		professor.setProfessorDisciplina( (ProfessorDisciplina) (new BeanPropertyRowMapper<>(ProfessorDisciplina.class)).mapRow(rs, rowNum));
		
		professor.setUsuario(usuario);
		return professor;
	}

}