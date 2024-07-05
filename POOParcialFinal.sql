-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema POOParcialFinal
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema POOParcialFinal
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `POOParcialFinal` DEFAULT CHARACTER SET utf8 ;
USE `POOParcialFinal` ;

-- -----------------------------------------------------
-- Table `POOParcialFinal`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `POOParcialFinal`.`cliente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(60) NOT NULL,
  `direccion` VARCHAR(100) NOT NULL,
  `telefono` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `POOParcialFinal`.`facilitador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `POOParcialFinal`.`facilitador` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tipo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `POOParcialFinal`.`tarjeta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `POOParcialFinal`.`tarjeta` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `numero` VARCHAR(18) NOT NULL,
  `fechaVencimiento` DATE NOT NULL,
  `codigo` VARCHAR(4) NOT NULL,
  `facilitador_id` INT NOT NULL,
  `cliente_id` INT NOT NULL,
  PRIMARY KEY (`id`, `facilitador_id`, `cliente_id`),
  INDEX `fk_tarjeta_facilitador_idx` (`facilitador_id` ASC) VISIBLE,
  INDEX `fk_tarjeta_cliente1_idx` (`cliente_id` ASC) VISIBLE,
  CONSTRAINT `fk_tarjeta_facilitador`
    FOREIGN KEY (`facilitador_id`)
    REFERENCES `POOParcialFinal`.`facilitador` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tarjeta_cliente1`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `POOParcialFinal`.`cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `POOParcialFinal`.`transaccion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `POOParcialFinal`.`transaccion` (
  `id` INT NOT NULL,
  `fecha` DATE NOT NULL,
  `tarjeta_id` INT NOT NULL,
  PRIMARY KEY (`id`, `tarjeta_id`),
  INDEX `fk_transaccion_tarjeta1_idx` (`tarjeta_id` ASC) VISIBLE,
  CONSTRAINT `fk_transaccion_tarjeta1`
    FOREIGN KEY (`tarjeta_id`)
    REFERENCES `POOParcialFinal`.`tarjeta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
