SELECT * FROM 
	usuario u 
INNER JOIN 
	estudante e 
ON 
	e.usua_id = u.usua_id 
WHERE 
	e.estu_matricula = ?;