USE PROJECT;

INSERT INTO CSI (CSI_name, CSI_type, CSI_email, CSI_image, CSI_external_link, CSI_venue, CSI_description) VALUES ('soccer', 'club', 'soccer@gmail.com','abcdes', 'witssoccer.co.za', 'wits stadium', '1234567890 abcdefg hijklmn');

INSERT INTO CSI (CSI_name, CSI_type, CSI_email, CSI_image, CSI_external_link, CSI_venue, CSI_description) VALUES ('basketball', 'club', 'basketball@gmail.com','asdf', 'witsbasketball.co.za', 'OMSH', 'wits has a great');

INSERT INTO CSI (CSI_name, CSI_type, CSI_email, CSI_image, CSI_external_link, CSI_venue, CSI_description) VALUES ('wargames', 'social', 'wargames@gmail.com','asdfg' 'witswargames.co.za', 'WSS', 'war games is fun and really awesome');

INSERT INTO CSI (CSI_name, CSI_type, CSI_email, CSI_image, CSI_external_link, CSI_venue, CSI_description) VALUES ('AI', 'interest group', 'AI@gmail.com','zxcv', 'witsAI.co.za', 'WL005', 'AI is really fun and interesting');

INSERT INTO CSI (CSI_name, CSI_type, CSI_email, CSI_image, CSI_external_link, CSI_venue, CSI_description) VALUES ('volleyball', 'club', 'volleyball@gmail.com','lkjh', 'witsvolleyball.co.za', 'OMSH', 'abcdefg hijklmn');

INSERT INTO CSI (CSI_name, CSI_type, CSI_email, CSI_image, CSI_external_link, CSI_venue, CSI_description) VALUES ('debate', 'social', 'dabate@gmail.com','fghj', 'witsdabte.co.za', 'wits stadium', '1234567890 hijklmn');

INSERT INTO CSI (CSI_name, CSI_type, CSI_email, CSI_image, CSI_external_link, CSI_venue, CSI_description) VALUES ('gaming', 'social', 'gaming@gmail.com','cvbn', 'witsgaming.co.za', 'wits stadium', '1234567890 abcdefg ');

INSERT INTO PERSON (PERSON_username, PERSON_email, PERSON_password_hash, CSI_name, PERSON_type) VALUES ('Corry', 'corry@gmail.com', '098765', 'gaming', 'student');

INSERT INTO PERSON (PERSON_username, PERSON_email, PERSON_password_hash, CSI_name, PERSON_type) VALUES ('Joker', 'joer@gmail.com', 'abcde', 'wargames', 'student');

INSERT INTO PERSON (PERSON_username, PERSON_email, PERSON_password_hash, CSI_name, PERSON_type) VALUES ('Batman', 'batman@gmail.com', '56789', 'AI', 'student');

INSERT INTO PERSON (PERSON_username, PERSON_email, PERSON_password_hash, CSI_name, PERSON_type) VALUES ('Barry', 'barry@gmail.com', '109020', 'soccer', 'student');

INSERT INTO PERSON (PERSON_username, PERSON_email, PERSON_password_hash, CSI_name, PERSON_type) VALUES ('Bruce', 'bruce@gmail.com', '102030', 'AI', 'student');

INSERT INTO PERSON (PERSON_username, PERSON_email, PERSON_password_hash, CSI_name, PERSON_type) VALUES ('Killerfrost', 'frost@gmail.com', '908070', 'volleyball', 'student');

INSERT INTO PERSON (PERSON_username, PERSON_email, PERSON_password_hash, CSI_name, PERSON_type) VALUES ('Candice', 'candice@gmail.com', '232526', 'basketball', 'admin');

INSERT INTO NOTIFICATION (CSI_name, NOTIFICATION_message) VALUES ('soccer', 'there is a new event happening');

INSERT INTO NOTIFICATION (CSI_name, NOTIFICATION_message) VALUES ('debate', 'this will be an awesome event');

INSERT INTO NOTIFICATION (CSI_name, NOTIFICATION_message) VALUES ('gaming', 'fifa competition');

INSERT INTO NOTIFICATION (CSI_name, NOTIFICATION_message) VALUES ('wargame', 'wargames championship');

INSERT INTO NOTIFICATION (CSI_name, NOTIFICATION_message) VALUES ('AI', 'machine learning serminar');

INSERT INTO NOTIFICATION (CSI_name, NOTIFICATION_message) VALUES ('cyber', 'hackerthon taking place');

INSERT INTO NOTIFICATION (CSI_name, NOTIFICATION_message) VALUES ('volleyball', 'volleyball tornament');

INSERT INTO NOTIFICATION (CSI_name, NOTIFICATION_message) VALUES ('basketball', 'wits presents basketball tornament');

INSERT INTO NOTIFICATION (CSI_name, NOTIFICATION_message) VALUES ('debate', 'debate competition');
