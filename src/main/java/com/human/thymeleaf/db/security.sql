
/* Drop Triggers */

DROP TRIGGER TRI_anniversary_aid;
DROP TRIGGER TRI_board_bid;
DROP TRIGGER TRI_message_mid;
DROP TRIGGER TRI_notification_nid;
DROP TRIGGER TRI_reply_rid;
DROP TRIGGER TRI_schedule_sid;
DROP TRIGGER TRI_securityUser_suid;
DROP TRIGGER TRI_userProfile_pid;



/* Drop Tables */

DROP TABLE anniversary CASCADE CONSTRAINTS;
DROP TABLE reply CASCADE CONSTRAINTS;
DROP TABLE board CASCADE CONSTRAINTS;
DROP TABLE message CASCADE CONSTRAINTS;
DROP TABLE notification CASCADE CONSTRAINTS;
DROP TABLE schedule CASCADE CONSTRAINTS;
DROP TABLE userProfile CASCADE CONSTRAINTS;
DROP TABLE securityUser CASCADE CONSTRAINTS;



/* Drop Sequences */

DROP SEQUENCE SEQ_anniversary_aid;
DROP SEQUENCE SEQ_board_bid;
DROP SEQUENCE SEQ_message_mid;
DROP SEQUENCE SEQ_notification_nid;
DROP SEQUENCE SEQ_reply_rid;
DROP SEQUENCE SEQ_schedule_sid;
DROP SEQUENCE SEQ_securityUser_suid;
DROP SEQUENCE SEQ_userProfile_pid;




/* Create Sequences */

CREATE SEQUENCE SEQ_anniversary_aid INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_board_bid INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_message_mid INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_notification_nid INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_reply_rid INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_schedule_sid INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_securityUser_suid INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_userProfile_pid INCREMENT BY 1 START WITH 1;



/* Create Tables */

CREATE TABLE anniversary
(
	aid number NOT NULL,
	suid number NOT NULL,
	aname varchar2(40) NOT NULL,
	adate char(8) NOT NULL,
	isHoliday number DEFAULT 0,
	PRIMARY KEY (aid)
);


CREATE TABLE board
(
	bid number NOT NULL,
	suid number NOT NULL,
	title varchar2(128) NOT NULL,
	content varchar2(4000),
	modTime timestamp DEFAULT SYSDATE,
	viewCount number DEFAULT 0,
	replyCount number DEFAULT 0,
	likeCount number DEFAULT 0,
	files varchar2(512),
	isDeleted number DEFAULT 0,
	PRIMARY KEY (bid)
);


CREATE TABLE message
(
	mid number NOT NULL,
	srcSuid number NOT NULL,
	dstSuid number NOT NULL,
	content varchar2(256) NOT NULL,
	status number DEFAULT 0,
	genTime timestamp DEFAULT SYSDATE,
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


CREATE TABLE reply
(
	rid number NOT NULL,
	suid number NOT NULL,
	bid number NOT NULL,
	content varchar2(128) NOT NULL,
	regTime timestamp DEFAULT SYSDATE,
	isMine number DEFAULT 0,
	PRIMARY KEY (rid)
);


CREATE TABLE schedule
(
	sid number NOT NULL,
	suid number NOT NULL,
	sdate char(8) NOT NULL,
	title varchar2(40) NOT NULL,
	place varchar2(40),
	startTime char(5),
	endTime char(5),
	isImportant number DEFAULT 0,
	memo varchar2(100),
	PRIMARY KEY (sid)
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
	addr varchar2(40),
	phone varchar2(20),
	twitter varchar2(40),
	facebook varchar2(40),
	insta varchar2(40),
	linked varchar2(40),
	PRIMARY KEY (pid)
);



/* Create Foreign Keys */

ALTER TABLE reply
	ADD FOREIGN KEY (bid)
	REFERENCES board (bid)
;


ALTER TABLE anniversary
	ADD FOREIGN KEY (suid)
	REFERENCES securityUser (suid)
;


ALTER TABLE board
	ADD FOREIGN KEY (suid)
	REFERENCES securityUser (suid)
;


ALTER TABLE message
	ADD FOREIGN KEY (srcSuid)
	REFERENCES securityUser (suid)
;


ALTER TABLE message
	ADD FOREIGN KEY (dstSuid)
	REFERENCES securityUser (suid)
;


ALTER TABLE notification
	ADD FOREIGN KEY (dstSuid)
	REFERENCES securityUser (suid)
;


ALTER TABLE reply
	ADD FOREIGN KEY (suid)
	REFERENCES securityUser (suid)
;


ALTER TABLE schedule
	ADD FOREIGN KEY (suid)
	REFERENCES securityUser (suid)
;


ALTER TABLE userProfile
	ADD FOREIGN KEY (suid)
	REFERENCES securityUser (suid)
;



/* Create Triggers */

CREATE OR REPLACE TRIGGER TRI_anniversary_aid BEFORE INSERT ON anniversary
FOR EACH ROW
BEGIN
	SELECT SEQ_anniversary_aid.nextval
	INTO :new.aid
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_board_bid BEFORE INSERT ON board
FOR EACH ROW
BEGIN
	SELECT SEQ_board_bid.nextval
	INTO :new.bid
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

CREATE OR REPLACE TRIGGER TRI_reply_rid BEFORE INSERT ON reply
FOR EACH ROW
BEGIN
	SELECT SEQ_reply_rid.nextval
	INTO :new.rid
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_schedule_sid BEFORE INSERT ON schedule
FOR EACH ROW
BEGIN
	SELECT SEQ_schedule_sid.nextval
	INTO :new.sid
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




