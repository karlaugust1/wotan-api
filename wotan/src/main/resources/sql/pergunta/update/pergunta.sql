UPDATE pergunta 
	SET 
		perg_descricao = ?,
		perg_data_limite = ?,
		perg_valor = ?,
		cont_id = ?,
		perg_ativa = ?,
		perg_visivel = ? 
WHERE 
	perg_id = ?;

