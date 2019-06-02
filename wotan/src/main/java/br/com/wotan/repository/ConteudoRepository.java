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
import br.com.wotan.data.model.Conteudo;
import br.com.wotan.exception.DatabaseException;
import br.com.wotan.rowmapper.ConteudoRowMapper;
import br.com.wotan.util.SQLReader;

@Repository
@Qualifier
@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class ConteudoRepository extends BaseRepository{
	
	public List<Conteudo> findAll() {
		try {
			sql = SQLReader.from("sql"+File.separator+"conteudo"+File.separator+"select"+File.separator+"conteudos.sql");
			List<Conteudo> conteudos = jdbcTemplateMySQL.query(sql, new ConteudoRowMapper());
			return conteudos;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}catch (DatabaseException e) {
			e.printStackTrace();
			throw new DatabaseException(ExceptionType.ERROR, "Erro em banco de dados", e.getMessage());
		}
	}
	
	public Conteudo insert(final Conteudo conteudo) {
		try {
			sql = SQLReader.from("sql" + File.separator + "conteudo" + File.separator + "insert" + File.separator + "conteudo.sql");

			KeyHolder holder = new GeneratedKeyHolder();
			jdbcTemplateMySQL.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					ps.setObject(1, conteudo.getContTitulo());
					ps.setObject(2, conteudo.getContDescricao());
					ps.setObject(3, conteudo.getProfessor().getProfId());
					ps.setObject(4, conteudo.getDisciplina().getDiscId());
					ps.setObject(5, conteudo.getContBimestre());
					return ps;
				}
			}, holder);

			Long id = holder.getKey().longValue();
			conteudo.setContId(id);
			
			return conteudo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}catch (DatabaseException e) {
			throw new DatabaseException(ExceptionType.ERROR, "Erro ocorrido no banco de dados", e.getMessage());
		}
	}

	public Conteudo update(Conteudo conteudo) {
		try {
			sql = SQLReader.from("sql" + File.separator + "conteudo" + File.separator + "update" + File.separator + "conteudo.sql");
			jdbcTemplateMySQL.update(sql,
					new Object[] { conteudo.getContTitulo(),
								   conteudo.getContDescricao(),
								   conteudo.getProfessor().getProfId(),
								   conteudo.getDisciplina().getDiscId(),
								   conteudo.getContBimestre(),
								   conteudo.getContId()});
			return conteudo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (DatabaseException e) {
			throw new DatabaseException(ExceptionType.ERROR, "Erro ocorrido no banco de dados", e.getMessage());
		}
	}

	public Conteudo findById(Long id) {
		try {
			sql = SQLReader.from("sql"+File.separator+"conteudo"+File.separator+"select"+File.separator+"conteudo_by_id.sql");
			Conteudo conteudo = jdbcTemplateMySQL.queryForObject(sql, new Object[] {id} , new ConteudoRowMapper());
			return conteudo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}catch (DatabaseException e) {
			e.printStackTrace();
			throw new DatabaseException(ExceptionType.ERROR, "Erro ocorrido no banco de dados", e.getMessage());
		}
	}

	public Boolean delete(Conteudo conteudo) {
		try {
			sql = SQLReader.from("sql" + File.separator + "conteudo" + File.separator + "delete" + File.separator + "conteudo.sql");
			jdbcTemplateMySQL.update(sql, conteudo.getContId());
			return Boolean.TRUE;
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (DatabaseException e) {
			throw new DatabaseException(ExceptionType.ERROR, "Erro ocorrido no banco de dados", e.getMessage());
		}
	}
	
}