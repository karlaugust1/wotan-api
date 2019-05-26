package br.com.wotan.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import br.com.wotan.data.model.Conteudo;
import br.com.wotan.data.model.Pergunta;

public class PerguntaRowMapper implements RowMapper<Pergunta> {
	
	@Override
	public Pergunta mapRow(ResultSet rs, int rowNum) throws SQLException {
		Pergunta pergunta = (new BeanPropertyRowMapper<>(Pergunta.class)).mapRow(rs, rowNum);
		Conteudo conteudo = (new BeanPropertyRowMapper<>(Conteudo.class)).mapRow(rs, rowNum);
		
		pergunta.setConteudo(conteudo);
		return pergunta;
	}

}
