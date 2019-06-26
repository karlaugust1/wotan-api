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
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.wotan.data.enun.ExceptionType;
import br.com.wotan.data.model.HistoricoPergunta;
import br.com.wotan.exception.DatabaseException;
import br.com.wotan.rowmapper.HistoricoPerguntaRowMapper;
import br.com.wotan.util.SQLReader;

@Repository
@Qualifier
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class HistoricoPerguntaRepository extends BaseRepository {

	public HistoricoPergunta insert(final HistoricoPergunta historicoPergunta) {
		try {
			sql = SQLReader.from(
					"sql" + File.separator + "historico_pergunta" + File.separator + "insert" + File.separator + "historico_pergunta.sql");

			jdbcTemplateMySQL.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					ps.setObject(1, historicoPergunta.getEstudante().getEstuId());
					ps.setObject(2, historicoPergunta.getHipeAcertou());
					ps.setObject(3, historicoPergunta.getHipeResposta());
					ps.setObject(4, historicoPergunta.getPergunta().getPergId());
					ps.setObject(5, historicoPergunta.getHipeDataRegistro().toString());
					ps.setObject(6, historicoPergunta.getAlternativa() != null? historicoPergunta.getAlternativa().getAlteId() : null);
					return ps;
				}
			});

			return historicoPergunta;
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (DatabaseException e) {
			throw new DatabaseException(ExceptionType.ERROR, "Erro ocorrido no banco de dados", e.getMessage());
		}
	}

	public List<HistoricoPergunta> findByQuestion(Long id) {
		try {
			sql = SQLReader.from("sql"+File.separator+"historico_pergunta"+File.separator+"select"+File.separator+"historico_pergunta_by_question.sql");
			List<HistoricoPergunta> historico = jdbcTemplateMySQL.query(sql, new Object[] { id }, new HistoricoPerguntaRowMapper());
			return historico;
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (DatabaseException e) {
			throw new DatabaseException(ExceptionType.ERROR, "Erro ocorrido no banco de dados", e.getMessage());
		}
	}

	public void correct(HistoricoPergunta historicoPergunta) {
		try {
			sql = SQLReader.from("sql" + File.separator + "historico_pergunta" + File.separator + "update" + File.separator + "correct.sql");
			jdbcTemplateMySQL.update(sql,
					new Object[] { historicoPergunta.getHipeAcertou(), 
								   historicoPergunta.getEstudante().getEstuId(), 
								   historicoPergunta.getPergunta().getPergId()});
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		} catch (DatabaseException e) {
			throw new DatabaseException(ExceptionType.ERROR, "Erro ocorrido no banco de dados", e.getMessage());
		}
	}

	public List<HistoricoPergunta> findByAnsweredQuestionByStudent(Long idPergunta, Long idEstudante) {
		try {
			sql = SQLReader.from("sql"+File.separator+"historico_pergunta"+File.separator+"select"+File.separator+"historico_pergunta_by_question_and_student.sql");
			List<HistoricoPergunta> historico = jdbcTemplateMySQL.query(sql, new Object[] { idPergunta, idEstudante }, new HistoricoPerguntaRowMapper());
			return historico;
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (DatabaseException e) {
			throw new DatabaseException(ExceptionType.ERROR, "Erro ocorrido no banco de dados", e.getMessage());
		}
	}
}
