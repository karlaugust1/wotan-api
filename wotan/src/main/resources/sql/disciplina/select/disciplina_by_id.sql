SELECT * 
FROM disciplina AS d 
INNER JOIN 
	curso AS c 
ON 
	c.curs_id = d.curs_id 
WHERE 
	d.disc_id = ?;
