
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

INSERT INTO birthday (first_name, last_name, date_of_birth, email, phone_number)
VALUES ('Meg', 'Keegan Broughton', '1987-02-02', 'mkeegan89@gmail.com', '5555555555'),
        ('Seth','Broughton','1990-01-05','broughton.24@gmail.con','5555555555');


