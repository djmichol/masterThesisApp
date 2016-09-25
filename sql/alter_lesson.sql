ALTER TABLE `elearning`.`lessons` 
ADD INDEX `lesson_path_id_idx` (`lesson_path_id` ASC);
ALTER TABLE `elearning`.`lessons` 
ADD CONSTRAINT `lesson_path_id`
  FOREIGN KEY (`lesson_path_id`)
  REFERENCES `elearning`.`path` (`path_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

  ALTER TABLE `elearning`.`lesson_tabs` 
CHANGE COLUMN `tabs_editor_submit` `tabs_editor_submit` BLOB NULL ,
CHANGE COLUMN `tabs_editor_result` `tabs_editor_result` BLOB NULL ;

ALTER TABLE `elearning`.`lessons` 
ADD COLUMN `lesson_brief` VARCHAR(4000) NOT NULL AFTER `lesson_article`;
