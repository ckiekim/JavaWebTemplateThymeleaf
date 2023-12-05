
/* Drop Triggers */

DROP TRIGGER TRI_memberProfile_pid;
DROP TRIGGER TRI_message_mid;
DROP TRIGGER TRI_notification_nid;
DROP TRIGGER TRI_profile_pid;



/* Drop Tables */

DROP TABLE memberProfile CASCADE CONSTRAINTS;
DROP TABLE message CASCADE CONSTRAINTS;
DROP TABLE notification CASCADE CONSTRAINTS;
DROP TABLE members CASCADE CONSTRAINTS;



/* Drop Sequences */

DROP SEQUENCE SEQ_memberProfile_pid;
DROP SEQUENCE SEQ_message_mid;
DROP SEQUENCE SEQ_notification_nid;
DROP SEQUENCE SEQ_profile_pid;




/* Create Sequences */

CREATE SEQUENCE SEQ_memberProfile_pid INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_message_mid INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_notification_nid INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_profile_pid INCREMENT BY 1 START WITH 1;



/* Create Tables */

CREATE TABLE memberProfile
(
	pid number NOT NULL,
	mid varchar2(16) NOT NULL,
	about varchar2(256),
	company varchar2(40),
	job varchar2(40),
	country varchar2(32),
	addr varchar2(80),
	phone varchar2(20),
	twitter varchar2(40),
	facebook varchar2(40),
	insta varchar2(40),
	linked varchar2(40),
	PRIMARY KEY (pid)
);


CREATE TABLE members
(
	mid varchar2(16) NOT NULL,
	email varchar2(32) NOT NULL,
	regDate date DEFAULT SYSDATE NOT NULL,
	imgPath varchar2(32),
	PRIMARY KEY (mid)
);


CREATE TABLE message
(
	mid number NOT NULL,
	mfrom varchar2(16) NOT NULL,
	mto varchar2(16) NOT NULL,
	content varchar2(200) NOT NULL,
	status number DEFAULT 0 NOT NULL,
	genTime timestamp DEFAULT SYSDATE,
	mname varchar2(20) NOT NULL,
	PRIMARY KEY (mid)
);


CREATE TABLE notification
(
	nid number NOT NULL,
	nto varchar2(16) NOT NULL,
	title varchar2(80) NOT NULL,
	content varchar2(200) NOT NULL,
	status number DEFAULT 0 NOT NULL,
	genTime timestamp DEFAULT SYSDATE,
	PRIMARY KEY (nid)
);



/* Create Foreign Keys */

ALTER TABLE memberProfile
	ADD FOREIGN KEY (mid)
	REFERENCES members (mid)
;


ALTER TABLE message
	ADD FOREIGN KEY (mto)
	REFERENCES members (mid)
;


ALTER TABLE message
	ADD FOREIGN KEY (mfrom)
	REFERENCES members (mid)
;


ALTER TABLE notification
	ADD FOREIGN KEY (nto)
	REFERENCES members (mid)
;



/* Create Triggers */

CREATE OR REPLACE TRIGGER TRI_memberProfile_pid BEFORE INSERT ON memberProfile
FOR EACH ROW
BEGIN
	SELECT SEQ_memberProfile_pid.nextval
	INTO :new.pid
	FROM dual;
END;

/

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

CREATE OR REPLACE TRIGGER TRI_profile_pid BEFORE INSERT ON profile
FOR EACH ROW
BEGIN
	SELECT SEQ_profile_pid.nextval
	INTO :new.pid
	FROM dual;
END;

/




