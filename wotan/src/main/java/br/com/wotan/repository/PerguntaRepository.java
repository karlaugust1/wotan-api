package br.com.wotan.repository;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.wotan.data.enun.ExceptionType;
import br.com.wotan.data.model.Pergunta;
import br.com.wotan.exception.DatabaseException;
import br.com.wotan.rowmapper.PerguntaRowMapper;
import br.com.wotan.util.SQLReader;

@Repository
@Qualifier
@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class PerguntaRepository extends BaseRepository {

	public Pergunta insert(final Pergunta pergunta) {
		try {
			sql = SQLReader.from("sql" + File.separator + "pergunta" + File.separator + "insert" + File.separator + "pergunta.sql");

			KeyHolder holder = new GeneratedKeyHolder();
			jdbcTemplateMySQL.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					ps.setObject(1, pergunta.getPergDescricao());
					ps.setObject(2, pergunta.getPergDataCriacao().toString());
					ps.setObject(3, pergunta.getPergDataLimite().toString());
					ps.setObject(4, pergunta.getPergValor());
					ps.setObject(5, pergunta.getConteudo().getContId());
					ps.setObject(6, pergunta.getPergAtiva());
					ps.setObject(7, pergunta.getPergVisivel());
					
					return ps;
				}
			}, holder);

			int idCurso = holder.getKey().intValue();
			pergunta.setPergId(new Long(idCurso));
			
			return pergunta;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}catch (DatabaseException e) {
			throw new DatabaseException(ExceptionType.ERROR, "Erro ocorrido no banco de dados", e.getMessage());
		}
	}
	
	public Pergunta update(final Pergunta pergunta) {
		try {
			sql = SQLReader.from("sql" + File.separator + "pergunta" + File.separator + "update" + File.separator + "pergunta.sql");
			jdbcTemplateMySQL.update(sql,
					new Object[] { pergunta.getPergDescricao(), 
								   pergunta.getPergDataLimite(), 
								   pergunta.getPergValor(), 
								   pergunta.getConteudo().getContId(),
								   pergunta.getPergAtiva(),
								   pergunta.getPergVisivel(),
								   pergunta.getPergId()});
			return pergunta;
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (DatabaseException e) {
			throw new DatabaseException(ExceptionType.ERROR, "Erro ocorrido no banco de dados", e.getMessage());
		}
	}

	public Boolean delete(final Long pergunta) {
		try {
			sql = SQLReader.from("sql" + File.separator + "pergunta" + File.separator + "delete" + File.separator + "pergunta.sql");
			jdbcTemplateMySQL.update(sql, pergunta);
			return Boolean.TRUE;
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (DatabaseException e) {
			throw new DatabaseException(ExceptionType.ERROR, "Erro ocorrido no banco de dados", e.getMessage());
		}
		
	}

	public List<Pergunta> findByTeacher(Long id) {
		try {
			sql = SQLReader.from("sql"+File.separator+"pergunta"+File.separator+"select"+File.separator+"perguntas_by_teacher.sql");
			List<Pergunta> perguntas = jdbcTemplateMySQL.query(sql, new Object[] { id }, new PerguntaRowMapper());
			return perguntas;
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (DatabaseException e) {
			throw new DatabaseException(ExceptionType.ERROR, "Erro ocorrido no banco de dados", e.getMessage());
		}
	}

	public Pergunta findById(Long id) {
		try {
			sql = SQLReader.from("sql"+File.separator+"pergunta"+File.separator+"select"+File.separator+"pergunta_by_id.sql");
			Pergunta pergunta = jdbcTemplateMySQL.queryForObject(sql, new Object[] { id }, new PerguntaRowMapper());
			return pergunta;
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (DatabaseException e) {
			throw new DatabaseException(ExceptionType.ERROR, "Erro ocorrido no banco de dados", e.getMessage());
		}
	}

}
