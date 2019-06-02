SELECT p.*, u.* 
FROM professor p 
INNER JOIN 
	usuario u 
ON 
	u.usua_id = p.usua_id 
INNER JOIN 
	professor_disciplina pd 
ON 
	pd.prof_id = p.prof_id 
WHERE 
	pd.disc_id = ? 
AND 
	p.prof_excluido = 0;