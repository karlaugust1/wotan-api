package br.com.wotan.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import br.com.wotan.data.model.Estudante;
import br.com.wotan.data.model.Usuario;

public class EstudanteRowMapper implements RowMapper<Estudante> {
	
	@Override
	public Estudante mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Estudante estudante = (new BeanPropertyRowMapper<>(Estudante.class)).mapRow(rs, rowNum);
		Usuario usuario = (new BeanPropertyRowMapper<>(Usuario.class)).mapRow(rs, rowNum);
		
		estudante.setUsuario(usuario);
		return estudante;
	}

}