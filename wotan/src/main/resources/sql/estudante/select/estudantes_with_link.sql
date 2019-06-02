SELECT * 
FROM estudante e 
INNER JOIN 
	usuario u 
ON 
	u.usua_id = e.usua_id 
INNER JOIN 
	estudante_disciplina ed 
ON 
	ed.estu_id = e.estu_id 
WHERE 
	ed.disc_id = ? 
AND 
	e.estu_excluido = 0;