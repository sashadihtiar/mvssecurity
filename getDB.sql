CREATE DATABASE cource;
USE cource;
CREATE TABLE dictionary(
ID INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(20),
discriminator VARCHAR(20)
);
INSERT INTO dictionary VALUES(default,"ADMIN","ROLE");
INSERT INTO dictionary VALUES(default,"USSER","ROLE");

CREATE TABLE users(
ID INT PRIMARY KEY AUTO_INCREMENT,
LOGIN VARCHAR(20),
PASSWORD VARCHAR(20),
NAME INT REFERENCES dictionary(ID)
);
INSERT INTO users VALUES(DEFAULT,"ADMINLOG","ADMINPASS",1);