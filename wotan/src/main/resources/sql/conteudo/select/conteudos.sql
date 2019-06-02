SELECT * 
FROM 
	conteudo c
INNER JOIN 
	disciplina d 
ON 
	d.disc_id = c.disc_id 
WHERE 
	cont_excluido = FALSE;