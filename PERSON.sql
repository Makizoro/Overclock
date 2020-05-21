USE PROJECT;

CREATE TABLE CSI(
        CSI_name varchar(35),
        CSI_type varchar(35),
        CSI_email varchar(35),
        CSI_image varchar(255),
        CSI_external_link varchar(400),
        CSI_venue varchar(111),
        CSI_description text,
        PRIMARY KEY(CSI_name)
)ENGINE=INNODB;

CREATE TABLE PERSON(
        PERSON_username varchar(35),
        PERSON_email varchar(35),
        PERSON_password_hash varchar(400),
        CSI_name varchar(35),
        PERSON_type varchar(10),
        UNIQUE KEY(CSI_name),
        PRIMARY KEY(PERSON_username),
        FOREIGN KEY(CSI_name) REFERENCES CSI(CSI_name)
)ENGINE=INNODB;

CREATE TABLE REGISTRATION(
        CSI_name varchar(35),
        PERSON_username varchar(35),
        REGISTRATION_date date,
        REGISTRATION_fee varchar(35),
        REGISTRATION_status varchar(35),
        PRIMARY KEY(CSI_name),
        UNIQUE KEY(PERSON_username),
        FOREIGN KEY(CSI_name) REFERENCES CSI(CSI_name),
        FOREIGN KEY(PERSON_username) REFERENCES PERSON(PERSON_username)
)ENGINE=INNODB;

CREATE TABLE EVENTS(
        EVENT_id varchar(35),
        EVENT_name varchar(35),
        EVENT_date date,
        EVENT_description text,
        EVENT_external_link varchar(400),
        EVENT_image varchar(255),
        EVENT_venue varchar(111),
        PRIMARY KEY(EVENT_id)
)ENGINE=INNODB;

CREATE TABLE CSI_EVENT(
        CSI_name varchar(35),
        EVENT_id varchar(35),
        PRIMARY KEY(CSI_name),
        UNIQUE KEY(EVENT_id),
        FOREIGN KEY(CSI_name) REFERENCES CSI(CSI_name),
        FOREIGN KEY(EVENT_id) REFERENCES EVENTS(EVENT_id)
)ENGINE=INNODB;

CREATE TABLE NOTIFICATION(
        CSI_name varchar(35),
        NOTIFICATION_timestamp TIMESTAMP,
        NOTIFICATION_message text,
        PRIMARY KEY(CSI_name),
        FOREIGN KEY(CSI_name) REFERENCES CSI(CSI_name)
)ENGINE=INNODB;