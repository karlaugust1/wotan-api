UPDATE historico_pergunta hp 
SET 
	hipe_acertou = ? 
WHERE 
	hp.estu_id = ? 
AND 
	hp.perg_id = ?;
