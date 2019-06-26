SELECT * FROM 
	pergunta p 
INNER JOIN 
	conteudo c 
ON 
	c.cont_id = p.cont_id 
INNER JOIN 
	historico_pergunta hp 
ON 
	hp.perg_id = p.perg_id 
WHERE 
	hp.estu_id = ?;		