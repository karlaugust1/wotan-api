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
import br.com.wotan.data.model.Alternativa;
import br.com.wotan.exception.DatabaseException;
import br.com.wotan.rowmapper.AlternativaRowMapper;
import br.com.wotan.util.SQLReader;

@Repository
@Qualifier
@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class AlternativaRepository extends BaseRepository {

	public Alternativa insert(final Alternativa alternativa) {
		try {
			sql = SQLReader.from("sql" + File.separator + "alternativa" + File.separator + "insert" + File.separator + "alternativa.sql");

			KeyHolder holder = new GeneratedKeyHolder();
			jdbcTemplateMySQL.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					ps.setObject(1, alternativa.getAlteDescricao());
					ps.setObject(2, alternativa.getAlteCorreta());
					ps.setObject(3, alternativa.getPergunta().getPergId());				
					return ps;
				}
			}, holder);

			Long id = holder.getKey().longValue();
			alternativa.setAlteId(id);
			
			return alternativa;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}catch (DatabaseException e) {
			throw new DatabaseException(ExceptionType.ERROR, "Erro ocorrido no banco de dados", e.getMessage());
		}
	}
	
	public Alternativa update(final Alternativa alternativa) {
		try {
			sql = SQLReader.from("sql" + File.separator + "alternativa" + File.separator + "update" + File.separator + "alternativa.sql");
			jdbcTemplateMySQL.update(sql,
					new Object[] { alternativa.getAlteDescricao(), 
								   alternativa.getAlteCorreta(),
								   alternativa.getAlteId()});
			return alternativa;
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (DatabaseException e) {
			throw new DatabaseException(ExceptionType.ERROR, "Erro ocorrido no banco de dados", e.getMessage());
		}
	}

	public Boolean delete(final Alternativa alternativa) {
		try {
			sql = SQLReader.from("sql" + File.separator + "alternativa" + File.separator + "delete" + File.separator + "alternativa.sql");
			jdbcTemplateMySQL.update(sql, alternativa.getAlteId());
			return Boolean.TRUE;
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (DatabaseException e) {
			throw new DatabaseException(ExceptionType.ERROR, "Erro ocorrido no banco de dados", e.getMessage());
		}
		
	}

	public List<Alternativa> findByQuestion(Long id) {
		try {
			sql = SQLReader.from("sql"+File.separator+"alternativa"+File.separator+"select"+File.separator+"alternativas_by_question.sql");
			List<Alternativa> alternativas = jdbcTemplateMySQL.query(sql, new Object[] { id }, new AlternativaRowMapper());
			return alternativas;
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (DatabaseException e) {
			throw new DatabaseException(ExceptionType.ERROR, "Erro ocorrido no banco de dados", e.getMessage());
		}
	}

	public Alternativa findById(Long id) {
		try {
			sql = SQLReader.from("sql"+File.separator+"alternativa"+File.separator+"select"+File.separator+"alternativa_by_id.sql");
			Alternativa alternativa = jdbcTemplateMySQL.queryForObject(sql, new Object[] { id }, new AlternativaRowMapper());
			return alternativa;
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (DatabaseException e) {
			throw new DatabaseException(ExceptionType.ERROR, "Erro ocorrido no banco de dados", e.getMessage());
		}
	}

}