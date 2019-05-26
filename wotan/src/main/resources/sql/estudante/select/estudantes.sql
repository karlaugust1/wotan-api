SELECT * 
FROM estudante AS e 
INNER JOIN 
	usuario AS u 
ON 
	u.usua_id = e.usua_id 
WHERE 
	e.estu_excluido = FALSE;
