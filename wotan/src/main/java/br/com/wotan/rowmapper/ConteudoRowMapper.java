package br.com.wotan.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import br.com.wotan.data.model.Conteudo;
import br.com.wotan.data.model.Disciplina;

public class ConteudoRowMapper implements RowMapper<Conteudo> {
	
	@Override
	public Conteudo mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Conteudo conteudo = (new BeanPropertyRowMapper<>(Conteudo.class)).mapRow(rs, rowNum);
		Disciplina disciplina = (new BeanPropertyRowMapper<>(Disciplina.class)).mapRow(rs, rowNum);
		
		conteudo.setDisciplina(disciplina);
		return conteudo;
	}

}