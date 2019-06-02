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
import br.com.wotan.data.model.Professor;
import br.com.wotan.exception.DatabaseException;
import br.com.wotan.rowmapper.ProfessorRowMapper;
import br.com.wotan.util.SQLReader;

@Repository
@Qualifier
@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class ProfessorRepository extends BaseRepository{

	public Professor insert(Professor professor) {
		try {
			sql = SQLReader.from("sql" + File.separator + "professor" + File.separator + "insert" + File.separator + "professor.sql");

			KeyHolder holder = new GeneratedKeyHolder();
			jdbcTemplateMySQL.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					ps.setObject(1, professor.getUsuario().getUsuaId());
					return ps;
				}
			}, holder);

			Long id = holder.getKey().longValue();
			professor.setProfId(id);
			
			return professor;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}catch (DatabaseException e) {
			throw new DatabaseException(ExceptionType.ERROR, "Erro ocorrido no banco de dados", e.getMessage());
		}
	}

	public Professor findById(Long id) {
		try {
			sql = SQLReader.from("sql"+File.separator+"professor"+File.separator+"select"+File.separator+"professor_by_id.sql");
			Professor professor = jdbcTemplateMySQL.queryForObject(sql, new Object[] {id}, new ProfessorRowMapper());
			return professor;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}catch (DatabaseException e) {
			e.printStackTrace();
			throw new DatabaseException(ExceptionType.ERROR, "Erro ocorrido no banco de dados", e.getMessage());
		}
	}

	public List<Professor> findAll() {
		try {
			sql = SQLReader.from("sql"+File.separator+"professor"+File.separator+"select"+File.separator+"professores.sql");
			List<Professor> professores = jdbcTemplateMySQL.query(sql, new ProfessorRowMapper());
			return professores;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}catch (DatabaseException e) {
			e.printStackTrace();
			throw new DatabaseException(ExceptionType.ERROR, "Erro ocorrido no banco de dados", e.getMessage());
		}
	}

	public Boolean delete(Professor professor) {
		try {
			sql = SQLReader.from("sql" + File.separator + "professor" + File.separator + "delete" + File.separator + "professor.sql");
			jdbcTemplateMySQL.update(sql, professor.getProfId());
			return Boolean.TRUE;
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (DatabaseException e) {
			throw new DatabaseException(ExceptionType.ERROR, "Erro ocorrido no banco de dados", e.getMessage());
		}
		
	}

	public List<Professor> findTeachersWithNoLink() {
		try {
			sql = SQLReader.from("sql"+File.separator+"professor"+File.separator+"select"+File.separator+"professores_with_no_link.sql");
			List<Professor> professores = jdbcTemplateMySQL.query(sql, new ProfessorRowMapper());
			return professores;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}catch (DatabaseException e) {
			e.printStackTrace();
			throw new DatabaseException(ExceptionType.ERROR, "Erro ocorrido no banco de dados", e.getMessage());
		}
	}

	public List<Professor> findTeachersWithLink(Long id) {
		try {
			sql = SQLReader.from("sql"+File.separator+"professor"+File.separator+"select"+File.separator+"professores_with_link.sql");
			List<Professor> professores = jdbcTemplateMySQL.query(sql, new Object[] {id}, new ProfessorRowMapper());
			return professores;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}catch (DatabaseException e) {
			e.printStackTrace();
			throw new DatabaseException(ExceptionType.ERROR, "Erro ocorrido no banco de dados", e.getMessage());
		}
	}

}
