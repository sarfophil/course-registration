insert into role_tbl(id,role) values (1,'ROLE_ADMIN');
insert into role_tbl(id,role) values (2,'ROLE_FACULTY');
insert into role_tbl(id,role) values (3,'ROLE_STUDENT');

-- Sample Credential/ username: phil password: super
insert into credential(id,username,password) values (1,'phil','$2a$10$r8XxEMr5VT01bp7R3uVziOPdH6XLY7l4wvRdr2URERD4g8qS/kLjy');
insert into credential_roles values (1,1);
