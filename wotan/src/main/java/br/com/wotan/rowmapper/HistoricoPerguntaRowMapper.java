package br.com.wotan.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import br.com.wotan.data.model.Alternativa;
import br.com.wotan.data.model.Estudante;
import br.com.wotan.data.model.HistoricoPergunta;
import br.com.wotan.data.model.Pergunta;
import br.com.wotan.data.model.Usuario;

public class HistoricoPerguntaRowMapper implements RowMapper<HistoricoPergunta> {

	@Override
	public HistoricoPergunta mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		HistoricoPergunta historicoPergunta = (new BeanPropertyRowMapper<>(HistoricoPergunta.class)).mapRow(rs, rowNum);
		Estudante estudante = (new BeanPropertyRowMapper<>(Estudante.class)).mapRow(rs, rowNum);
		estudante.setUsuario((Usuario) (new BeanPropertyRowMapper<>(Usuario.class)).mapRow(rs, rowNum));
		historicoPergunta.setEstudante(estudante);
		historicoPergunta.setAlternativa((Alternativa) (new BeanPropertyRowMapper<>(Alternativa.class)).mapRow(rs, rowNum));
		historicoPergunta.setPergunta((Pergunta) (new BeanPropertyRowMapper<>(Pergunta.class)).mapRow(rs, rowNum));
		return historicoPergunta;
		
	}

}
