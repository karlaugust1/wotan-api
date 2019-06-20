SELECT p.*, c.*, hp.estu_id FROM 
	pergunta p 
INNER JOIN 
	conteudo c 
ON 
	c.cont_id = p.cont_id 
INNER JOIN 
	disciplina d 
ON 
	d.disc_id = c.disc_id 
LEFT JOIN 
	estudante_disciplina ed 
ON 
	ed.disc_id = d.disc_id 
LEFT JOIN 
	historico_pergunta hp 
ON 
	hp.perg_id = p.perg_id 
WHERE 
	((hp.estu_id = ?) IS NULL) <> ? 
AND 
	ed.estu_id = ? 
AND 
	p.perg_excluida = FALSE;