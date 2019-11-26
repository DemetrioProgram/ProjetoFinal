
-- -----------------------------------------------------
-- Schema oficina
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `oficina` DEFAULT CHARACTER SET latin1 ;
USE `oficina` ;
SET SQL_SAFE_UPDATES = 0;

-- -----------------------------------------------------
-- Table `mydb`.`situacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oficina`.`situacao` (
  `IDSITUACAO` INT NOT NULL,
  `DESITUACAO` VARCHAR(255) NULL,
  PRIMARY KEY (`IDSITUACAO`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`servico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oficina`.`servico` (
  `IDSERVICO` INT NOT NULL,
  `DESERVICO` VARCHAR(255) NULL,
  PRIMARY KEY (`IDSERVICO`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oficina`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oficina`.`cliente` (
  `IDCLIENTE` INT(11) NOT NULL AUTO_INCREMENT,
  `NOME` VARCHAR(255) NULL DEFAULT NULL,
  `CPF` CHAR(11) NULL DEFAULT NULL,
  `TELEFONE` VARCHAR(13) NULL DEFAULT NULL,
  PRIMARY KEY (`IDCLIENTE`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `mydb`.`carro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oficina`.`carro` (
  `IDCARRO` INT NOT NULL AUTO_INCREMENT,
  `MARCA` VARCHAR(45) NULL,
  `MODELO` VARCHAR(45) NULL,
  `PLACA` CHAR(7) NULL,
  `COR` VARCHAR(255) NULL,
  `ANO` VARCHAR(4) NULL,
  `IDCLIENTE` INT(11) NOT NULL,
  PRIMARY KEY (`IDCARRO`),
  CONSTRAINT `fk_carro_cliente`
    FOREIGN KEY (`IDCLIENTE`)
    REFERENCES `oficina`.`cliente` (`IDCLIENTE`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `oficina`.`mecanico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oficina`.`mecanico` (
  `IDMECANICO` INT(11) NOT NULL AUTO_INCREMENT,
  `NOME` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`IDMECANICO`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `oficina`.`peca`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oficina`.`peca` (
  `IDPECA` INT(11) NOT NULL AUTO_INCREMENT,
  `NOMEPECA` VARCHAR(255) NULL DEFAULT NULL,
  `CODIGO` VARCHAR(13) NULL,
  PRIMARY KEY (`IDPECA`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `oficina`.`item_peca`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oficina`.`item_peca` (
  `IDITEMPECA` INT(11) NOT NULL AUTO_INCREMENT,
  `VALORVENDA` DOUBLE(4,2) NULL,
  `VALORCOMPRA` DOUBLE(4,2) NULL,
  `DATAENTRADA` DATE NULL,
  `DATASAIDA` DATE NULL,
  `QUANTIDADE` INT NULL,
  `IDPECA` INT(11) NOT NULL,
  PRIMARY KEY (`IDITEMPECA`),
  CONSTRAINT `fk_item_peca_peca1`
    FOREIGN KEY (`IDPECA`)
    REFERENCES `oficina`.`peca` (`IDPECA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `oficina`.`orcamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oficina`.`orcamento` (
  `IDORCAMENTO` INT(11) NOT NULL AUTO_INCREMENT,
  `NUORCAMENTO` VARCHAR(45) NULL,
  `DESCRICAO` VARCHAR(255) NULL DEFAULT NULL,
  `VALORPECAS` DOUBLE(4,2) NULL DEFAULT NULL,
  `VALORMAOOBRA` VARCHAR(255) NULL,
  `DATAINICIO` DATE NULL DEFAULT NULL,
  `DATAFINAL` DATE NULL DEFAULT NULL,
  `IDMECANICO` INT(11) NULL,
  `IDCARRO` INT(11) NOT NULL,
  `IDSITUACAO` INT NOT NULL,
  `IDSERVICO` INT NULL,
  `IDITEMPECA` INT(11) NULL,
  PRIMARY KEY (`IDORCAMENTO`),
  CONSTRAINT `orcamento_ibfk_3`
    FOREIGN KEY (`IDMECANICO`)
    REFERENCES `oficina`.`mecanico` (`IDMECANICO`),
  CONSTRAINT `fk_orcamento_carro1`
    FOREIGN KEY (`IDCARRO`)
    REFERENCES `oficina`.`carro` (`IDCARRO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_orcamento_situacao1`
    FOREIGN KEY (`IDSITUACAO`)
    REFERENCES `oficina`.`situacao` (`IDSITUACAO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_orcamento_servico1`
    FOREIGN KEY (`IDSERVICO`)
    REFERENCES `oficina`.`servico` (`IDSERVICO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_orcamento_item_peca1`
    FOREIGN KEY (`IDITEMPECA`)
    REFERENCES `oficina`.`item_peca` (`IDITEMPECA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `oficina`.`login`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oficina`.`login` (
  `IDUSUARIO` INT(11) NOT NULL AUTO_INCREMENT,
  `LOGIN` VARCHAR(255) NULL DEFAULT NULL,
  `SENHA` VARCHAR(255) NULL DEFAULT NULL,
  `TIPO` CHAR(1) NULL,
  PRIMARY KEY (`IDUSUARIO`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

INSERT INTO MECANICO (IDMECANICO,NOME) VALUES (1, 'GODOFREDO');
INSERT INTO MECANICO (IDMECANICO,NOME) VALUES (2, 'JEORGE');
INSERT INTO MECANICO (IDMECANICO,NOME) VALUES (3, 'LUCAS');

INSERT INTO SITUACAO (IDSITUACAO, DESITUACAO) VALUES (1, 'Em Aguardo');
INSERT INTO SITUACAO (IDSITUACAO, DESITUACAO) VALUES (2, 'Em Andamento');
INSERT INTO SITUACAO (IDSITUACAO, DESITUACAO) VALUES (3, 'Finalizado');

INSERT INTO SERVICO (IDSERVICO, DESERVICO) VALUES (1, 'Alinhamento');
INSERT INTO SERVICO (IDSERVICO, DESERVICO) VALUES (2, 'Troca de Oleo');
INSERT INTO SERVICO (IDSERVICO, DESERVICO) VALUES (3, 'Troca de Pastilha de Freio');
INSERT INTO SERVICO (IDSERVICO, DESERVICO) VALUES (4, 'Troca de Parabrisa');
INSERT INTO SERVICO (IDSERVICO, DESERVICO) VALUES (5, 'Troca de Embreagem');
INSERT INTO SERVICO (IDSERVICO, DESERVICO) VALUES (6, 'Troca de Disco de Freio');
INSERT INTO SERVICO (IDSERVICO, DESERVICO) VALUES (7, 'Troca de Para-lama');
INSERT INTO SERVICO (IDSERVICO, DESERVICO) VALUES (8, 'Troca do Motor de Arranque');
INSERT INTO SERVICO (IDSERVICO, DESERVICO) VALUES (9, 'Troca de Pecas');