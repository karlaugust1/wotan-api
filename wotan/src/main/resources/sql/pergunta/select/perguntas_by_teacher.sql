SELECT * 
FROM pergunta AS p 
INNER JOIN 
	conteudo AS c 
ON 
	c.cont_id = p.cont_id 
WHERE 
	c.prof_id = ? 
AND 
	p.perg_excluida = FALSE;
