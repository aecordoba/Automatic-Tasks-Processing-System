-------------------------------------------------------------
-- SCHEMA
-------------------------------------------------------------
DROP SCHEMA IF EXISTS `ATPS` ;
CREATE SCHEMA IF NOT EXISTS `ATPS` DEFAULT CHARACTER SET utf8 ;
USE `ATPS` ;

-- -----------------------------------------------------
-- Table `ATPS`.`Tools`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ATPS`.`Tools` ;

CREATE TABLE IF NOT EXISTS `ATPS`.`Tools` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `jar_name` VARCHAR(60) NOT NULL,
  `full_class_name` VARCHAR(256) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `jar_name_UNIQUE` (`jar_name` ASC))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `ATPS`.`Tool_States`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ATPS`.`Tool_States` ;

CREATE TABLE IF NOT EXISTS `ATPS`.`Tool_States` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `ATPS`.`Tasks`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ATPS`.`Tasks` ;

CREATE TABLE IF NOT EXISTS `ATPS`.`Tasks` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `jar_name` VARCHAR(60) NOT NULL,
  `full_class_name` VARCHAR(256) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `jar_name_UNIQUE` (`jar_name` ASC))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `ATPS`.`Tasks_Tools`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ATPS`.`Tasks_Tools` ;

CREATE TABLE IF NOT EXISTS `ATPS`.`Tasks_Tools` (
  `task` INT NOT NULL,
  `tool` INT NOT NULL,
  `order` TINYINT NOT NULL,
  `state` INT NULL,
  INDEX `fk_Tasks_Tools_Tasks_idx` (`task` ASC),
  INDEX `fk_Tasks_Tools_Tools_idx` (`tool` ASC),
  UNIQUE INDEX `task_order_UNIQUE` (`task` ASC, `order` ASC),
  CONSTRAINT `fk_Tasks_Tools_Tasks`
    FOREIGN KEY (`task`)
    REFERENCES `ATPS`.`Tasks` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Tasks_Tools_Tools`
    FOREIGN KEY (`tool`)
    REFERENCES `ATPS`.`Tools` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Tasks_Tools_Tools_Tool_States`
    FOREIGN KEY (`state`)
    REFERENCES `ATPS`.`Tool_States` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `ATPS`.`Job_States`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ATPS`.`Job_States` ;

CREATE TABLE IF NOT EXISTS `ATPS`.`Job_States` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `ATPS`.`Jobs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ATPS`.`Jobs` ;

CREATE TABLE IF NOT EXISTS `ATPS`.`Jobs` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(60) NOT NULL,
  `task` INT NOT NULL,
  `state` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC),
  CONSTRAINT `fk_Jobs_Tasks`
    FOREIGN KEY (`task`)
    REFERENCES `ATPS`.`Tasks` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Jobs_Job_States`
    FOREIGN KEY (`state`)
    REFERENCES `ATPS`.`Job_States` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)

ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `ATPS`.`Arguments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ATPS`.`Arguments` ;

CREATE TABLE IF NOT EXISTS `ATPS`.`Arguments` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `job` INT NOT NULL,
  `argument` BLOB NOT NULL,
  `order` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `job_order_UNIQUE` (`job` ASC, `order` ASC))
ENGINE = InnoDB;
