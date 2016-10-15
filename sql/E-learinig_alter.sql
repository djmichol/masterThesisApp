CREATE SCHEMA `elearning` ;

CREATE TABLE `elearning`.`users` (
  `usr_id` INT NOT NULL AUTO_INCREMENT,
  `usr_name` VARCHAR(45) NOT NULL,
  `usr_password` VARCHAR(45) NOT NULL,
  `usr_mail` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`usr_id`),
  UNIQUE INDEX `usr_id_UNIQUE` (`usr_id` ASC),
  UNIQUE INDEX `usr_mail_UNIQUE` (`usr_mail` ASC))
COMMENT = 'Tabela uzytkownikow';

CREATE TABLE `elearning`.`roles` (
  `rol_id` INT NOT NULL AUTO_INCREMENT,
  `rol_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`rol_id`),
  UNIQUE INDEX `rol_id_UNIQUE` (`rol_id` ASC),
  UNIQUE INDEX `rol_name_UNIQUE` (`rol_name` ASC))
COMMENT = 'Role uzytkownikow';

CREATE TABLE `elearning`.`users_in_roles` (
  `usr_rol_id` INT NOT NULL AUTO_INCREMENT,
  `usr_rol_usr_id` INT NOT NULL,
  `usr_rol_rol_id` INT NOT NULL,
  PRIMARY KEY (`usr_rol_id`),
  UNIQUE INDEX `usr_rol_id_UNIQUE` (`usr_rol_id` ASC),
  INDEX `usr_rol_usr_id_idx` (`usr_rol_usr_id` ASC),
  INDEX `usr_rol_rol_id_idx` (`usr_rol_rol_id` ASC),
  CONSTRAINT `usr_rol_usr_id`
    FOREIGN KEY (`usr_rol_usr_id`)
    REFERENCES `elearning`.`users` (`usr_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `usr_rol_rol_id`
    FOREIGN KEY (`usr_rol_rol_id`)
    REFERENCES `elearning`.`roles` (`rol_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
COMMENT = 'Role uzytkownikow';

CREATE TABLE `elearning`.`path` (
  `path_id` INT NOT NULL AUTO_INCREMENT,
  `path_name` VARCHAR(200) NOT NULL,
  `path_description` BLOB NOT NULL,
  PRIMARY KEY (`path_id`),
  UNIQUE INDEX `path_id_UNIQUE` (`path_id` ASC))
COMMENT = 'Sciezki rozwoju';

CREATE TABLE `elearning`.`lessons` (
  `lesson_id` INT NOT NULL AUTO_INCREMENT,
  `lesson_path_id` INT NOT NULL,
  `lesson_title` VARCHAR(200) NOT NULL,
  `lesson_article` BLOB NOT NULL,
  PRIMARY KEY (`lesson_id`),
  UNIQUE INDEX `lesson_id_UNIQUE` (`lesson_id` ASC))
COMMENT = 'Lekcje';

CREATE TABLE `elearning`.`lesson_instruction` (
  `instr_id` INT NOT NULL AUTO_INCREMENT,
  `instr_lesson_id` INT NOT NULL,
  `instr_content` VARCHAR(4000) NOT NULL,
  PRIMARY KEY (`instr_id`),
  UNIQUE INDEX `instr_id_UNIQUE` (`instr_id` ASC),
  INDEX `instr_lesson_id_idx` (`instr_lesson_id` ASC),
  CONSTRAINT `instr_lesson_id`
    FOREIGN KEY (`instr_lesson_id`)
    REFERENCES `elearning`.`lessons` (`lesson_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
COMMENT = 'Instrukcje dla lekcji';

CREATE TABLE `elearning`.`lesson_tabs` (
  `tabs_id` INT NOT NULL AUTO_INCREMENT,
  `tabs_lesson_id` INT NOT NULL,
  `tabs_name` VARCHAR(200) NOT NULL,
  `tabs_editor_content` BLOB NOT NULL,
  `tabs_editor_submit` BLOB NOT NULL,
  `tabs_editor_result` BLOB NOT NULL,
  PRIMARY KEY (`tabs_id`),
  UNIQUE INDEX `tabs_id_UNIQUE` (`tabs_id` ASC),
  INDEX `tabs_lesson_id_idx` (`tabs_lesson_id` ASC),
  CONSTRAINT `tabs_lesson_id`
    FOREIGN KEY (`tabs_lesson_id`)
    REFERENCES `elearning`.`lessons` (`lesson_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
COMMENT = 'Zakladni edytora';

CREATE TABLE `elearning`.`users_lessons` (
  `user_lesson_idssons` INT NOT NULL AUTO_INCREMENT,
  `users_lessons_lesson_id` INT NOT NULL,
  `users_lessons_user_id` INT NOT NULL,
  `users_lessons_finished` VARCHAR(1) NOT NULL DEFAULT 'N',
  PRIMARY KEY (`user_lesson_idssons`),
  UNIQUE INDEX `idusers_leuser_lesson_idssons_UNIQUE` (`user_lesson_idssons` ASC),
  INDEX `user_fk_idx` (`users_lessons_user_id` ASC),
  INDEX `lesson_fk_idx` (`users_lessons_lesson_id` ASC),
  CONSTRAINT `user_fk`
    FOREIGN KEY (`users_lessons_user_id`)
    REFERENCES `elearning`.`users` (`usr_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `lesson_fk`
    FOREIGN KEY (`users_lessons_lesson_id`)
    REFERENCES `elearning`.`lessons` (`lesson_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

    CREATE TABLE `elearning`.`user_input_raw_data` (
  `in_dat_raw_id` INT NOT NULL AUTO_INCREMENT,
  `user_input_raw_data_keystrokes` BLOB NOT NULL,
  `user_input_raw_data_mause_movment` BLOB NOT NULL,
  PRIMARY KEY (`in_dat_raw_id`),
  UNIQUE INDEX `data_id_UNIQUE` (`in_dat_raw_id` ASC));


