drop table if exists User;



create table User(
	id INT AUTO_INCREMENT PRIMARY KEY,
	username VARCHAR(50) NOT NULL,
	address VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL
);


Insert into User(username, address, email) values ('rrichar1','Atlanta,GA','JavaBeans@gmail.com');