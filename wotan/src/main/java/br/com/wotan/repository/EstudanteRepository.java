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
import br.com.wotan.data.model.Estudante;
import br.com.wotan.data.model.Usuario;
import br.com.wotan.exception.DatabaseException;
import br.com.wotan.rowmapper.EstudanteRowMapper;
import br.com.wotan.util.SQLReader;

@Repository
@Qualifier
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class EstudanteRepository extends BaseRepository{

	public Estudante insert(Estudante estudante) {
		try {
			sql = SQLReader.from("sql" + File.separator + "estudante" + File.separator + "insert" + File.separator + "estudante.sql");

			KeyHolder holder = new GeneratedKeyHolder();
			jdbcTemplateMySQL.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					ps.setObject(1, estudante.getEstuMatricula());
					ps.setObject(2, estudante.getEstuDataMatricula().toString());
					ps.setObject(3, estudante.getUsuario().getUsuaId());
					return ps;
				}
			}, holder);

			Long id = holder.getKey().longValue();
			estudante.setEstuId(id);
			
			return estudante;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}catch (DatabaseException e) {
			throw new DatabaseException(ExceptionType.ERROR, "Erro ocorrido no banco de dados", e.getMessage());
		}
	}

	public Estudante update(Estudante estudante) {
		try {
			sql = SQLReader.from("sql" + File.separator + "estudante" + File.separator + "update" + File.separator + "estudante.sql");
			jdbcTemplateMySQL.update(sql,
					new Object[] { estudante.getEstuMatricula(), 
								   estudante.getEstuDataMatricula().toString(), 
								   estudante.getEstuId()});
			return estudante;
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (DatabaseException e) {
			throw new DatabaseException(ExceptionType.ERROR, "Erro ocorrido no banco de dados", e.getMessage());
		}
	}

	public Estudante findById(Long id) {
		try {
			sql = SQLReader.from("sql"+File.separator+"estudante"+File.separator+"select"+File.separator+"estudante_by_id.sql");
			Estudante estudante = jdbcTemplateMySQL.queryForObject(sql, new Object[] {id},new EstudanteRowMapper());
			return estudante;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}catch (DatabaseException e) {
			e.printStackTrace();
			throw new DatabaseException(ExceptionType.ERROR, "Erro ocorrido no banco de dados", e.getMessage());
		}
	}

	public List<Estudante> findAll() {
		try {
			sql = SQLReader.from("sql"+File.separator+"estudante"+File.separator+"select"+File.separator+"estudantes.sql");
			List<Estudante> estudantes = jdbcTemplateMySQL.query(sql, new EstudanteRowMapper());
			return estudantes;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}catch (DatabaseException e) {
			e.printStackTrace();
			throw new DatabaseException(ExceptionType.ERROR, "Erro ocorrido no banco de dados", e.getMessage());
		}
	}

	public Boolean delete(Estudante estudante) {
		try {
			sql = SQLReader.from("sql" + File.separator + "estudante" + File.separator + "delete" + File.separator + "estudante.sql");
			jdbcTemplateMySQL.update(sql, estudante.getEstuId());
			return Boolean.TRUE;
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (DatabaseException e) {
			throw new DatabaseException(ExceptionType.ERROR, "Erro ocorrido no banco de dados", e.getMessage());
		}
	}

	public List<Estudante> findStudentsWithNoLink() {
		try {
			sql = SQLReader.from("sql"+File.separator+"estudante"+File.separator+"select"+File.separator+"estudantes_with_no_link.sql");
			List<Estudante> estudantes = jdbcTemplateMySQL.query(sql, new EstudanteRowMapper());
			return estudantes;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}catch (DatabaseException e) {
			e.printStackTrace();
			throw new DatabaseException(ExceptionType.ERROR, "Erro ocorrido no banco de dados", e.getMessage());
		}
	}
	
	public List<Estudante> findStudentsWithLink(Long id) {
		try {
			sql = SQLReader.from("sql"+File.separator+"estudante"+File.separator+"select"+File.separator+"estudantes_with_link.sql");
			List<Estudante> estudantes = jdbcTemplateMySQL.query(sql, new Object[] {id}, new EstudanteRowMapper());
			return estudantes;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}catch (DatabaseException e) {
			e.printStackTrace();
			throw new DatabaseException(ExceptionType.ERROR, "Erro ocorrido no banco de dados", e.getMessage());
		}
	}

	public Estudante findStudentByRegisterNumber(String matricula) {
		try {
			sql = SQLReader.from("sql"+File.separator+"estudante"+File.separator+"select"+File.separator+"estudante_by_register_number.sql");
			Estudante estudante = jdbcTemplateMySQL.queryForObject(sql, new Object[] { matricula }, new EstudanteRowMapper());
			return estudante;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}catch (DatabaseException e) {
			e.printStackTrace();
			throw new DatabaseException(ExceptionType.ERROR, "Erro ocorrido no banco de dados", e.getMessage());
		}
	}

}