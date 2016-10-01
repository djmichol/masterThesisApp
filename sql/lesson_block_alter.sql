CREATE TABLE `elearning`.`lessons_block` (
  `block_id` INT NOT NULL AUTO_INCREMENT,
  `block_path_id` INT NOT NULL,
  `block_name` VARCHAR(45) NOT NULL,
  `block_description` VARCHAR(4000) NULL,
  PRIMARY KEY (`block_id`),
  UNIQUE INDEX `block_id_UNIQUE` (`block_id` ASC),
  UNIQUE INDEX `block_name_UNIQUE` (`block_name` ASC),
  INDEX `path_id_idx` (`block_path_id` ASC),
  CONSTRAINT `path_id`
    FOREIGN KEY (`block_path_id`)
    REFERENCES `elearning`.`path` (`path_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

    
    ALTER TABLE `elearning`.`lessons` 
DROP FOREIGN KEY `lesson_path_id`;
ALTER TABLE `elearning`.`lessons` 
CHANGE COLUMN `lesson_path_id` `lesson_block_id` INT(11) NOT NULL ,
ADD UNIQUE INDEX `lesson_title_UNIQUE` (`lesson_title` ASC),
ADD INDEX `lesson_block_id_idx` (`lesson_block_id` ASC),
DROP INDEX `lesson_path_id_idx` ;
ALTER TABLE `elearning`.`lessons` 
ADD CONSTRAINT `lesson_block_id`
  FOREIGN KEY (`lesson_block_id`)
  REFERENCES `elearning`.`lessons_block` (`block_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
