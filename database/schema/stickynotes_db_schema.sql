/*
    Create DB SQL
*/
CREATE DATABASE STICKYNOTES_DB;

/*
    Create table SQL
*/
CREATE TABLE STICKY_NOTES (
     id numeric(10) not null,
	 UNIQUE_KEY varchar(50) not null,
	 TITLE varchar(250) not null,
     DESCRIPTION varchar(5000) not null,
     REMINDER_DATE DATETIME,
     PHONE varchar(15),
     EMAIL varchar(150),
     DATE_CREATED DATETIME,
     PRIMARY KEY (id)
);

SELECT * FROM STICKY_NOTES;

/*
    Create Indexes on STICKY_NOTE table.
*/
CREATE INDEX REMINDER_DATE_IDX 
ON STICKY_NOTES (REMINDER_DATE);

CREATE INDEX UNIQUE_KEY_IDX 
ON STICKY_NOTES (UNIQUE_KEY);

/*
    CRUD OPS
*/
INSERT INTO stickynotes_db.sticky_notes
(id, UNIQUE_KEY, TITLE, DESCRIPTION, REMINDER_DATE, PHONE, EMAIL, DATE_CREATED)
VALUES ('3', 'AKEY123238', 'Go Team Wildcards again!', 'Wildcards have a great chance at winning FOR SURE!', '2021-12-09 8:00:00', '6039183678', '', '2021-12-07 12:00:00');