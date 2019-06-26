SELECT * FROM 
	usuario u 
INNER JOIN 
	professor p 
ON 
	p.usua_id = u.usua_id 
WHERE 
	u.usua_cpf = ?;