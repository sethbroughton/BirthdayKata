DROP TABLE IF EXISTS birthday;

CREATE TABLE birthday
(
	person_id serial, 
	first_name varchar(64) not null,
	last_name varchar(64) not null,
	date_of_birth date not null,
	email varchar(64) not null,
	phone_number varchar(64) not null,
	
	constraint pk_person_id primary key (person_id)
);

DROP TABLE IF EXISTS userTable;

CREATE TABLE userTable 
(
  id serial PRIMARY KEY,
  username varchar(255) NOT NULL UNIQUE,  
  password varchar(32) NOT NULL,    
  salt varchar(256) NOT NULL			
);

INSERT INTO birthday (first_name, last_name, date_of_birth, email, phone_number)
VALUES ('Meg', 'Keegan Broughton', '1989-02-24', 'mkeegan89@gmail.com', '7403361202'),
        ('Kevin','Broughton','2020-03-06','broughton.24@gmail.con','6145808483');


