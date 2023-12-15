
/* Drop Triggers */

DROP TRIGGER TRI_message_mid;
DROP TRIGGER TRI_notification_nid;
DROP TRIGGER TRI_securityUser_suid;
DROP TRIGGER TRI_userProfile_pid;



/* Drop Tables */

DROP TABLE message CASCADE CONSTRAINTS;
DROP TABLE notification CASCADE CONSTRAINTS;
DROP TABLE userProfile CASCADE CONSTRAINTS;
DROP TABLE securityUser CASCADE CONSTRAINTS;



/* Drop Sequences */

DROP SEQUENCE SEQ_message_mid;
DROP SEQUENCE SEQ_notification_nid;
DROP SEQUENCE SEQ_securityUser_suid;
DROP SEQUENCE SEQ_userProfile_pid;




/* Create Sequences */

CREATE SEQUENCE SEQ_message_mid INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_notification_nid INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_securityUser_suid INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_userProfile_pid INCREMENT BY 1 START WITH 1;



/* Create Tables */

CREATE TABLE message
(
	mid number NOT NULL,
	srcSuid number NOT NULL,
	dstSuid number NOT NULL,
	content varchar2(256) NOT NULL,
	status number DEFAULT 0,
	genTime timestamp DEFAULT SYSDATE,
	srcName varchar2(40),
	PRIMARY KEY (mid)
);


CREATE TABLE notification
(
	nid number NOT NULL,
	dstSuid number NOT NULL,
	title varchar2(80) NOT NULL,
	content varchar2(256),
	status number DEFAULT 0,
	genTime timestamp DEFAULT SYSDATE,
	PRIMARY KEY (nid)
);


CREATE TABLE securityUser
(
	suid number NOT NULL,
	suname varchar2(64) NOT NULL UNIQUE,
	pwd char(60),
	email varchar2(64),
	nickname varchar2(40),
	provider varchar2(16) NOT NULL,
	regDate date DEFAULT SYSDATE,
	role varchar2(16) DEFAULT 'ROLE_USER' NOT NULL,
	imgPath varchar2(120),
	PRIMARY KEY (suid)
);


CREATE TABLE userProfile
(
	pid number NOT NULL,
	suid number NOT NULL,
	about varchar2(256),
	company varchar2(40),
	job varchar2(40),
	country varchar2(32),
	phone varchar2(20),
	twitter varchar2(40),
	facebook varchar2(40),
	insta varchar2(40),
	linked varchar2(40),
	PRIMARY KEY (pid)
);



/* Create Foreign Keys */

ALTER TABLE message
	ADD FOREIGN KEY (dstSuid)
	REFERENCES securityUser (suid)
;


ALTER TABLE message
	ADD FOREIGN KEY (srcSuid)
	REFERENCES securityUser (suid)
;


ALTER TABLE notification
	ADD FOREIGN KEY (dstSuid)
	REFERENCES securityUser (suid)
;


ALTER TABLE userProfile
	ADD FOREIGN KEY (suid)
	REFERENCES securityUser (suid)
;



/* Create Triggers */

CREATE OR REPLACE TRIGGER TRI_message_mid BEFORE INSERT ON message
FOR EACH ROW
BEGIN
	SELECT SEQ_message_mid.nextval
	INTO :new.mid
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_notification_nid BEFORE INSERT ON notification
FOR EACH ROW
BEGIN
	SELECT SEQ_notification_nid.nextval
	INTO :new.nid
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_securityUser_suid BEFORE INSERT ON securityUser
FOR EACH ROW
BEGIN
	SELECT SEQ_securityUser_suid.nextval
	INTO :new.suid
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_userProfile_pid BEFORE INSERT ON userProfile
FOR EACH ROW
BEGIN
	SELECT SEQ_userProfile_pid.nextval
	INTO :new.pid
	FROM dual;
END;

/




