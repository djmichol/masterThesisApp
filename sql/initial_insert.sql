INSERT INTO users (usr_name,usr_password,usr_mail)
VALUES ('michal','michal','michael9995@wp.pl');

INSERT INTO users (usr_name,usr_password,usr_mail)
VALUES ('natalia','natalia','natalia@wp.pl');

INSERT INTO roles (rol_name)
VALUES ('admin');

INSERT INTO roles (rol_name)
VALUES ('user');

INSERT INTO users_in_roles (usr_rol_usr_id,usr_rol_rol_id)
VALUES (1,1);

INSERT INTO users_in_roles (usr_rol_usr_id,usr_rol_rol_id)
VALUES (1,2);

INSERT INTO users_in_roles (usr_rol_usr_id,usr_rol_rol_id)
VALUES (2,2);

insert into path (path_name,path_description) 
values ('JavaScript','Learn the fundamentals of JavaScript, the programming language of the Web.');

insert into path (path_name,path_description) 
values ('jQuery','Learn how to make your websites interactive and create animations by using jQuery.');



insert into lessons (lesson_path_id,lesson_title,lesson_article) 
values (1,'test lesson 1','Test lekcji JavaScript 1');

insert into lessons (lesson_path_id,lesson_title,lesson_article) 
values (2,'test lesson 2','Test lekcji JavaScript 2');

insert into lesson_instruction (instr_lesson_id,instr_content) 
values (1,'zrob to ');

insert into lesson_instruction (instr_lesson_id,instr_content) 
values (1,'zrob tamto ');

insert into lesson_instruction (instr_lesson_id,instr_content) 
values (2,'zrob to ');

insert into lesson_instruction (instr_lesson_id,instr_content) 
values (2,'zrob tamto ');

insert into lesson_instruction (instr_lesson_id,instr_content) 
values (2,'i jeszcze to ');

insert into lesson_instruction (instr_lesson_id,instr_content) 
values (1,'i to ');

insert into lesson_tabs (tabs_lesson_id,tabs_name,tabs_editor_content) 
values (1,'script.js','//test edytora');

insert into lesson_tabs (tabs_lesson_id,tabs_name,tabs_editor_content) 
values (2,'script.js','//test edytora 2');

insert into lesson_tabs (tabs_lesson_id,tabs_name,tabs_editor_content) 
values (2,'script2.js','//test edytora 2');