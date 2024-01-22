--------------------------------------------------------------------------------------
-- USER
--------------------------------------------------------------------------------------

DROP User 'atpsapp'@'%';
flush privileges;
CREATE User 'atpsapp'@'%' IDENTIFIED BY 'atpsapp123';
GRANT SELECT, INSERT, UPDATE, DELETE, EXECUTE, SHOW VIEW ON `ATPS`.* TO 'atpsapp'@'%';
flush privileges;


--------------------------------------------------------------------------------------
-- Tool_States
--------------------------------------------------------------------------------------
INSERT INTO Tool_States VALUES(1, "INITIALIZED"),
                               (2, "EXECUTED"),
                               (3, "CLEANED");
                               
--------------------------------------------------------------------------------------
-- Job_States
--------------------------------------------------------------------------------------
INSERT INTO Job_States VALUES(1, "PREPARED"),
                               (2, "RAN"),
                               (3, "COMPLETED");
