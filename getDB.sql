CREATE DATABASE cource;
USE cource;
CREATE TABLE dictionary(
ID INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(20),
discriminator VARCHAR(20)
);
INSERT INTO dictionary VALUES(default,"ADMIN","ROLE");
INSERT INTO dictionary VALUES(default,"USER","ROLE");

CREATE TABLE users(
ID INT PRIMARY KEY AUTO_INCREMENT,
LOGIN VARCHAR(20),
PASSWORD VARCHAR(20),
NAME VARCHAR(20),
SURNAME VARCHAR(20),
DICTIONARY_ID INT REFERENCES dictionary(ID)
);
INSERT INTO users VALUES(DEFAULT,"ADMINLOG","ADMINPASS","SASHA","DIHTIAR",1);
INSERT INTO users VALUES(DEFAULT,"USERLOG","USERPASS","ANDREY","BLABLA",2);

CREATE TABLE film(
ID INT PRIMARY KEY AUTO_INCREMENT,
NAME VARCHAR(30) NOT NULL,
DURATION INT
);
CREATE TABLE hall(
ID INT PRIMARY KEY AUTO_INCREMENT,
NAME VARCHAR(20),
RWS INT NOT NULL,
PLACES INT NOT NULL
);

CREATE TABLE session (
ID INT PRIMARY KEY AUTO_INCREMENT,
SESSIONSTART TIME,
FILM_ID INT REFERENCES film(ID)ON DELETE CASCADE,
HALL_ID INT REFERENCES hall(ID)
ON DELETE CASCADE
);

CREATE TABLE hallplace (
ID INT PRIMARY KEY AUTO_INCREMENT,
R INT NOT NULL,
P INT NOT NULL,
ID_HALL INT REFERENCES hall(ID)
ON DELETE CASCADE
);
INSERT INTO film VALUES (DEFAULT,"MARVEL",146);
INSERT INTO hall VALUES(DEFAULT ,"BLACK HALL",10,25);

