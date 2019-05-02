CREATE DATABASE cource;
USE cource;
CREATE TABLE dictionary(
ID INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(20) default null,
prop_key VARCHAR(30) default null,
discriminator VARCHAR(20)
);
INSERT INTO dictionary VALUES(default,"ADMIN",null,"ROLE");
INSERT INTO dictionary VALUES(default,"USER",null,"ROLE");
INSERT INTO dictionary VALUES(default,"CORP",null,"ROLE");
INSERT INTO dictionary VALUES(default,"USA",null,"CURRENCY");
INSERT INTO dictionary VALUES(default,"UAH",null,"CURRENCY");

CREATE TABLE users(
ID INT PRIMARY KEY AUTO_INCREMENT,
LOGIN VARCHAR(20),
PASSWORD VARCHAR(200),
NAME VARCHAR(20),
SURNAME VARCHAR(20),
DICTIONARY_ID INT REFERENCES dictionary(ID),
PARENT INT default null
);

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
SESSIONSTART TIMESTAMP,
FILM_ID INT REFERENCES film(ID),
HALL_ID INT REFERENCES hall(ID),
UNIQUE (SESSIONSTART,HALL_ID)
);

CREATE TABLE hallplace (
ID INT PRIMARY KEY AUTO_INCREMENT,
R INT NOT NULL,
P INT NOT NULL,
ID_HALL INT REFERENCES hall(ID)
);

CREATE TABLE account (
ID INT PRIMARY KEY AUTO_INCREMENT,
AMOUNT INT NOT NULL,
DICTIONARY_ID INT REFERENCES dictionary(ID),
USER_ID INT REFERENCES users(ID)
);

CREATE TABLE payment (
ID INT PRIMARY KEY AUTO_INCREMENT,
AMOUNT DECIMAL,
ACCOUNT_ID INT REFERENCES account(id),
DICTIONARY_ID INT REFERENCES dictionary(ID)
);

CREATE TABLE ticket (
ID INT PRIMARY KEY AUTO_INCREMENT,
USER_ID INT REFERENCES users(ID),
YOUR_PLACE INT references hallplace(ID),
SESSION_ID INT REFERENCES session(ID),
YOURE_PAYMENT INT REFERENCES payment(ID),
UNIQUE (YOUR_PLACE,SESSION_ID)
);
delimiter ||
create trigger up_hall after insert on hall
     for each row
     begin
     declare r int default 0;
     declare p int default 0;
     while r < NEW.RWS DO
     set r = r+1;
     while p < NEW.PLACES DO
     set p = p+1;
     insert into hallplace values(default,r,p,NEW.ID);
     end while;
     set p = 0;
     end while;
     end;
     ||
create trigger del_hall before delete on hall
    for each row
    begin
    delete from hallplace where ID_HALL = OLD.ID;
    end;
    ||
    create trigger account_sers after insert on users
    for each row
    begin
    insert into account values(default,0,4,NEW.ID);
    insert into account values(default,0,5,NEW.ID);
    end;
    ||
delimiter ;
INSERT INTO users VALUES(DEFAULT,"CORP","$2a$10$Xf3aBaoXSR/w5GOE.6IHBuHQUY5FBwYvhPDrFuEQSyl6HS.yrYRSu","CORP","CORP",3,default );
INSERT INTO users VALUES(DEFAULT,"sashalog","$2a$10$Xf3aBaoXSR/w5GOE.6IHBuHQUY5FBwYvhPDrFuEQSyl6HS.yrYRSu","Sasha","Dihtiar",1,default );


