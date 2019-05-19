package br.com.wotan.repository;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.wotan.data.enun.ExceptionType;
import br.com.wotan.data.model.Curso;
import br.com.wotan.exception.DatabaseException;
import br.com.wotan.util.SQLReader;

@Repository
@Qualifier
@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class CursoRepository extends BaseRepository{
	
	public List<Curso> findAll() {
		try {
			sql = SQLReader.from("sql"+File.separator+"curso"+File.separator+"select"+File.separator+"cursos.sql");
			List<Curso> cargos = jdbcTemplateMySQL.query(sql, new BeanPropertyRowMapper<>(Curso.class));
			return cargos;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}catch (DatabaseException e) {
			e.printStackTrace();
			throw new DatabaseException(ExceptionType.ERROR, "Erro em banco de dados", e.getMessage());
		}
	}
	
	public Curso insert(final Curso curso) {
		try {
			sql = SQLReader.from("sql" + File.separator + "curso" + File.separator + "insert" + File.separator + "curso.sql");

			KeyHolder holder = new GeneratedKeyHolder();
			jdbcTemplateMySQL.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, curso.getCursNome());
					return ps;
				}
			}, holder);

			Long id = holder.getKey().longValue();
			curso.setCursId(id);
			
			return curso;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}catch (DatabaseException e) {
			throw new DatabaseException(ExceptionType.ERROR, "Erro ocorrido no banco de dados", e.getMessage());
		}
	}

	public Curso update(Curso curso) {
		try {
			sql = SQLReader.from("sql" + File.separator + "curso" + File.separator + "update" + File.separator + "curso.sql");
			jdbcTemplateMySQL.update(sql,
					new Object[] { curso.getCursNome(), 
								   curso.getCursId()});
			return curso;
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (DatabaseException e) {
			throw new DatabaseException(ExceptionType.ERROR, "Erro ocorrido no banco de dados", e.getMessage());
		}
	}

	public Curso findById(Long id) {
		try {
			sql = SQLReader.from("sql"+File.separator+"curso"+File.separator+"curso"+File.separator+"curso_by_id.sql");
			Curso curso = jdbcTemplateMySQL.queryForObject(sql, new BeanPropertyRowMapper<Curso>());
			return curso;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}catch (DatabaseException e) {
			e.printStackTrace();
			throw new DatabaseException(ExceptionType.ERROR, "Erro ocorrido no banco de dados", e.getMessage());
		}
	}

	public Boolean delete(Curso curso) {
		try {
			sql = SQLReader.from("sql" + File.separator + "curso" + File.separator + "delete" + File.separator + "curso.sql");
			jdbcTemplateMySQL.update(sql, curso.getCursId());
			return Boolean.TRUE;
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (DatabaseException e) {
			throw new DatabaseException(ExceptionType.ERROR, "Erro ocorrido no banco de dados", e.getMessage());
		}
	}
	
}