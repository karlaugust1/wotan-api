package br.com.wotan.repository;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

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
import br.com.wotan.data.model.Usuario;
import br.com.wotan.exception.DatabaseException;
import br.com.wotan.rowmapper.EstudanteRowMapper;
import br.com.wotan.rowmapper.ProfessorRowMapper;
import br.com.wotan.util.SQLReader;

@Repository
@Qualifier
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class UsuarioRepository extends BaseRepository {

	public Usuario insert(Usuario usuario) {
		try {
			sql = SQLReader.from("sql" + File.separator + "usuario" + File.separator + "insert" + File.separator + "usuario.sql");

			KeyHolder holder = new GeneratedKeyHolder();
			jdbcTemplateMySQL.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					ps.setObject(1, usuario.getUsuaNome());
					ps.setObject(2, usuario.getUsuaCpf());
					ps.setObject(3, usuario.getUsuaRg());
					ps.setObject(4, usuario.getUsuaDataNascimento().toString());
					ps.setObject(5, usuario.getUsuaSenha());
					
					return ps;
				}
			}, holder);

			Long id = holder.getKey().longValue();
			usuario.setUsuaId(id);
			
			return usuario;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}catch (DatabaseException e) {
			throw new DatabaseException(ExceptionType.ERROR, "Erro ocorrido no banco de dados", e.getMessage());
		}
	}

	public Usuario update(Usuario usuario) {
		try {
			sql = SQLReader.from("sql" + File.separator + "pergunta" + File.separator + "update" + File.separator + "pergunta.sql");
			jdbcTemplateMySQL.update(sql,
					new Object[] { usuario.getUsuaNome(), 
							usuario.getUsuaCpf(), 
							usuario.getUsuaRg(), 
							usuario.getUsuaDataNascimento().toString(),
							usuario.getUsuaId()});
			return usuario;
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (DatabaseException e) {
			throw new DatabaseException(ExceptionType.ERROR, "Erro ocorrido no banco de dados", e.getMessage());
		}
	}

}
