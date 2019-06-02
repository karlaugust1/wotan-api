package br.com.wotan.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import br.com.wotan.data.model.Alternativa;
import br.com.wotan.data.model.Pergunta;

public class AlternativaRowMapper implements RowMapper<Alternativa> {
	
	@Override
	public Alternativa mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Alternativa alternativa = (new BeanPropertyRowMapper<>(Alternativa.class)).mapRow(rs, rowNum);
		alternativa.setPergunta( (Pergunta) (new BeanPropertyRowMapper<>(Pergunta.class)).mapRow(rs, rowNum));
		
		return alternativa;
	}

}