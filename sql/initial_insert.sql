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