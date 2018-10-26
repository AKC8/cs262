--I used SQL Server to write this code. TOP does not work with postgre. It was annoying so I switched dbms. 

--8.1

USE monopoly
GO

--a)
SELECT * FROM Game
ORDER BY time DESC


--b)
SELECT * FROM Game
WHERE time > DATEADD(day, -7, GETDATE())

--c)
SELECT name FROM Player
WHERE name is not NULL

--d)
SELECT playerID FROM PlayerGame
WHERE score > 2000

--e)
SELECT name, emailAddress
FROM Player
WHERE emailAddress like '%gmail%'

--8.2

--a)
SELECT score
FROM PlayerGame
WHERE PlayerGame.playerID = (SELECT ID
							 FROM Player
							 WHERE name = 'The King'
							 )
ORDER BY score DESC

--b)
--used subquery to avoid confusing join statements
SELECT name
FROM Player
--TOP works the same as LIMIT in postgre. 
WHERE ID = (SELECT TOP 1 playerID 
			FROM PlayerGame PG
			JOIN Game Ga
			ON PG.gameID = Ga.ID
			WHERE time = '2006-06-28 13:20:00'
			)

--c)
-- compares two rows with different IDs within the same table. 

--d)
--http://www1.udel.edu/evelyn/SQL-Class3/SQL3_self.html
--self joins are useful because it can be used to see which people live in the same dorm at calvin
--from a list of calvin students, their dorms, and their majors.