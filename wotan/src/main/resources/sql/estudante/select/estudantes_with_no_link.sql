SELECT e.*, u.* 
FROM estudante e 
INNER JOIN 
	usuario u 
ON 
	u.usua_id = e.usua_id 
LEFT JOIN 
	estudante_disciplina ed 
ON 
	ed.estu_id = e.estu_id 
WHERE 
	ed.estu_id IS NULL 
AND 
	e.estu_excluido = 0;