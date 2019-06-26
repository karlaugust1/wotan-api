SELECT hp.*, e.*, u.*, a.alte_id, a.alte_descricao FROM 
	historico_pergunta hp 
INNER JOIN 
	estudante e 
ON 
	e.estu_id = hp.estu_id 
INNER JOIN 
	usuario u 
ON 
	u.usua_id = e.usua_id 
LEFT JOIN 
	alternativa a 
ON 
	a.alte_id = hp.alte_id
WHERE 
	hp.perg_id = ? 
AND 
	hp.estu_id = ?;