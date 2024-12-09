-- -----------------------------------------------------
-- Schema ATPS
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS ATPS ;
CREATE SCHEMA ATPS ;
USE ATPS;

-- -----------------------------------------------------
-- Table Users
-- -----------------------------------------------------
CREATE OR REPLACE TABLE Users (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) UNIQUE NOT NULL,
  password VARCHAR(128) NOT NULL,
  enabled TINYINT NULL,
  first_name VARCHAR(15) NOT NULL,
  middle_name VARCHAR(15) NULL,
  last_name VARCHAR(20) NOT NULL,
  PRIMARY KEY (id))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table Authorities
-- -----------------------------------------------------
CREATE OR REPLACE TABLE Authorities (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(20) UNIQUE NOT NULL,
  PRIMARY KEY (id))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table Users_Authorities
-- -----------------------------------------------------
CREATE OR REPLACE TABLE Users_Authorities (
  user INT NOT NULL,
  authority INT NOT NULL,
  INDEX fk_Users_Authorities_Users_idx (user ASC),
  INDEX fk_Users_Authorities_Authorities_idx (authority ASC),
  CONSTRAINT fk_Users_Authorities_Users
    FOREIGN KEY (user)
    REFERENCES Users (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Users_Authorities_Authorities
    FOREIGN KEY (authority)
    REFERENCES Authorities (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table Job_Status
-- -----------------------------------------------------
CREATE OR REPLACE TABLE Job_Status(
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(15) NOT NULL
);

-- -----------------------------------------------------
-- Table Tsks
-- -----------------------------------------------------
CREATE OR REPLACE TABLE Tasks(
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(30) UNIQUE NOT NULL,
  jar_path VARCHAR(100) NOT NULL,
  full_class_name VARCHAR(100) NOT NULL
);

-- -----------------------------------------------------
-- Table Jobs
-- -----------------------------------------------------
CREATE OR REPLACE TABLE Jobs(
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(30) UNIQUE NOT NULL,
  task INT NOT NULL,
  CONSTRAINT fk_tasks FOREIGN KEY (task) REFERENCES Tasks(id)
);

-- -----------------------------------------------------
-- Table Data
-- -----------------------------------------------------
CREATE OR REPLACE TABLE Data(
  id INT PRIMARY KEY AUTO_INCREMENT,
  job INT NOT NULL,
  arg_order INT NOT NULL,
  data BLOB NOT NULL,
  CHECK (arg_order > 0),
  UNIQUE uq_data (job, arg_order),
  CONSTRAINT fk_jobs1 FOREIGN KEY (job) REFERENCES Jobs(id)
    ON DELETE CASCADE ON UPDATE CASCADE
);

-- -----------------------------------------------------
-- Table Jobs_History
-- -----------------------------------------------------
CREATE OR REPLACE TABLE Jobs_History(
  id INT PRIMARY KEY AUTO_INCREMENT,
  job INT NOT NULL,
  status INT NOT NULL,
  time TIMESTAMP NOT NULL,
  CONSTRAINT fk_jobs2 FOREIGN KEY (job) REFERENCES Jobs(id)
    ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_job_status FOREIGN KEY (status) REFERENCES Job_Status(id)
    ON DELETE CASCADE ON UPDATE CASCADE
);
