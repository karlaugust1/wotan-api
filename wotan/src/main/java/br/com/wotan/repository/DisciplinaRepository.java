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
import br.com.wotan.data.model.Disciplina;
import br.com.wotan.exception.DatabaseException;
import br.com.wotan.rowmapper.DisciplinaRowMapper;
import br.com.wotan.util.SQLReader;

@Repository
@Qualifier
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class DisciplinaRepository extends BaseRepository{

	public Disciplina insert(Disciplina disciplina) {
		try {
			sql = SQLReader.from("sql" + File.separator + "disciplina" + File.separator + "insert" + File.separator + "disciplina.sql");

			KeyHolder holder = new GeneratedKeyHolder();
			jdbcTemplateMySQL.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					ps.setObject(1, disciplina.getDiscDescricao());
					ps.setObject(2, disciplina.getCurso().getCursId());					
					return ps;
				}
			}, holder);

			Long id= holder.getKey().longValue();
			disciplina.setDiscId(id);
			
			return disciplina;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}catch (DatabaseException e) {
			throw new DatabaseException(ExceptionType.ERROR, "Erro ocorrido no banco de dados", e.getMessage());
		}
	}

	public Disciplina update(Disciplina disciplina) {
		try {
			sql = SQLReader.from("sql" + File.separator + "disciplina" + File.separator + "update" + File.separator + "disciplina.sql");
			jdbcTemplateMySQL.update(sql,
					new Object[] { disciplina.getDiscDescricao(), 
								   disciplina.getCurso().getCursId(), 
								   disciplina.getDiscId()});
			return disciplina;
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (DatabaseException e) {
			throw new DatabaseException(ExceptionType.ERROR, "Erro ocorrido no banco de dados", e.getMessage());
		}
	}

	public Disciplina findById(Long id) {
		try {
			sql = SQLReader.from("sql"+File.separator+"disciplina"+File.separator+"select"+File.separator+"disciplina_by_id.sql");
			Disciplina disciplina = jdbcTemplateMySQL.queryForObject(sql, new DisciplinaRowMapper());
			return disciplina;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}catch (DatabaseException e) {
			e.printStackTrace();
			throw new DatabaseException(ExceptionType.ERROR, "Erro ocorrido no banco de dados", e.getMessage());
		}
	}

	public List<Disciplina> findAll() {
		try {
			sql = SQLReader.from("sql"+File.separator+"disciplina"+File.separator+"select"+File.separator+"disciplinas.sql");
			List<Disciplina> disciplinas = jdbcTemplateMySQL.query(sql, new DisciplinaRowMapper());
			return disciplinas;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}catch (DatabaseException e) {
			e.printStackTrace();
			throw new DatabaseException(ExceptionType.ERROR, "Erro ocorrido no banco de dados", e.getMessage());
		}
	}

	public Boolean delete(Disciplina disciplina) {
		try {
			sql = SQLReader.from("sql" + File.separator + "disciplina" + File.separator + "delete" + File.separator + "disciplina.sql");
			jdbcTemplateMySQL.update(sql, disciplina.getDiscId());
			return Boolean.TRUE;
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (DatabaseException e) {
			throw new DatabaseException(ExceptionType.ERROR, "Erro ocorrido no banco de dados", e.getMessage());
		}
	}

}
