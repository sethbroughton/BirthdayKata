DROP TABLE IF EXISTS birthday;

CREATE TABLE birthday
(
	person_id serial, 
	first_name varchar(64) not null,
	last_name varchar(64) not null,
	date_of_birth date not null,
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

INSERT INTO birthday (first_name, last_name, date_of_birth, phone_number)
VALUES ('Meg', 'Keegan Broughton', '1989-02-24',  '7403361202'),
        ('Bobby','Broughton','2020-03-06','6145808483');

INSERT INTO birthday (first_name, last_name, date_of_birth, phone_number)
VALUES  ('Bobby','Broughton','2000-03-09','6145808483');
        
Select * FROM birthday WHERE (extract (month FROM date_of_birth) = extract (month FROM CURRENT_DATE)) 
AND (extract (day FROM date_of_birth) = extract (day FROM CURRENT_DATE));

Select * from birthday;