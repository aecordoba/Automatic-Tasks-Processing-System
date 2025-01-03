--------------------------------------------------------------------------------------
-- Application user
--------------------------------------------------------------------------------------
DROP User IF EXISTS 'atpsapp'@'%';
flush privileges;
CREATE User 'atpsapp'@'%' IDENTIFIED BY 'atpsapp123';
GRANT SELECT, INSERT, UPDATE, DELETE, EXECUTE, SHOW VIEW ON `ATPS`.* TO 'atpsapp'@'%';
flush privileges;

--------------------------------------------------------------------------------------
-- Authorities
--------------------------------------------------------------------------------------
INSERT INTO `ATPS`.`Authorities` VALUES (1,'ADMIN'),
                                (2,'USER'),
                                (3,'OBSERVER');

--------------------------------------------------------------------------------------
-- Users
--------------------------------------------------------------------------------------
INSERT INTO Users(id, name, password, first_name, last_name, enabled, locked, account_expired, credentials_expired) VALUES (1,'admin','$2a$10$kEGwwhWZpV2ApEkEsTXUROFKV4vhkKxaMlZCl7XQ5IfG5Z1GFGVcC','Super','Administrator',1,0,0,0),
                        (2,'user','$2a$10$1lCpQKdv7FQ8ieFJwAVu0uv.x6nqhnEl3mfFJiFyLG36wBnPFtp4W','User','User',1,0,0,0),
                        (3,'obs','$2a$10$VR1N3m6r5POy3NZtr056y.y2axn1gmPWzfguHpO3NHKYkqnBY3Irq','Observer','Observer',1,0,0,0);

--------------------------------------------------------------------------------------
-- Users_Authorities
--------------------------------------------------------------------------------------
INSERT INTO Users_Authorities VALUES(1, 1),
                                    (2, 2),
                                    (3, 3);

--------------------------------------------------------------------------------------
-- Job_Status
--------------------------------------------------------------------------------------
INSERT INTO Job_Status(id, name)
    VALUES(1, 'CREATED'),
        (2, 'INITIALIZING'),
        (3, 'INITIALIZED'),
        (4, 'EXECUTING'),
        (5, 'EXECUTED'),
        (6, 'COMPLETING'),
        (7, 'COMPLETED');
