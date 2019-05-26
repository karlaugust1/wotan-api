SELECT * 
FROM professor AS p 
INNER JOIN 
	usuario AS u 
ON 
	u.usua_id = p.usua_id 
WHERE 
	p.prof_excluido = FALSE;
