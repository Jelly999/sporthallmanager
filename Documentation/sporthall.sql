PRAGMA foreign_keys = ON
;


CREATE TABLE user(
user_uuid INTEGER PRIMARY KEY AUTOINCREMENT,
username TEXT NOT NULL,
firstname TEXT NOT NULL,
surname TEXT NOT NULL,
email TEXT NOT NULL,
phone TEXT NOT NULL,
pwd_hash TEXT NOT NULL,
administrator INTEGER DEFAULT 0 CHECK (administrator = 0 OR administrator = 1)
);

CREATE TABLE sporthall(
hallid INTEGER PRIMARY KEY AUTOINCREMENT,
hallname TEXT NOT NULL,
uni_uuid INTEGER,
halltype TEXT NOT NULL,
not_available INETEGER NOT NULL DEFAULT 0 CHECK (not_available = 0 OR not_available = 1),
FOREIGN KEY (uni_uuid) REFERENCES universities(uni_uuid) ON DELETE CASCADE
);

CREATE TABLE reservations(
reserveid INTEGER PRIMARY KEY AUTOINCREMENT,
hallid INTEGER,
sport TEXT NOT NULL DEFAULT 'default',
start_time TEXT NOT NULL,
duration INTEGER NOT NULL,
user_uuid INTEGER,
maxparticipants INTEGER,
recurring_event INTEGER DEFAULT 0 CHECK (recurring_event = 1 OR recurring_event = 0),
FOREIGN KEY (user_uuid) REFERENCES user(user_uuid) ON DELETE CASCADE,
FOREIGN KEY (hallid) REFERENCES sporthall(hallid) ON DELETE CASCADE
);

CREATE TABLE enrolls(
enrollid INTEGER PRIMARY KEY AUTOINCREMENT,
user_uuid INTEGER,
reserveid INTEGER,
FOREIGN KEY (user_uuid) REFERENCES user(user_uuid) ON DELETE CASCADE,
FOREIGN KEY (reserveid) REFERENCES reservations(reserveid) ON DELETE CASCADE
);

CREATE TABLE universities(
uni_uuid INTEGER PRIMARY KEY AUTOINCREMENT,
name TEXT NOT NULL,
address TEXT NOT NULL
);

CREATE TABLE user_access_uni(
access_uuid INTEGER PRIMARY KEY AUTOINCREMENT,
user_uuid INTEGER,
uni_uuid INTEGER,
FOREIGN KEY (user_uuid) REFERENCES user(user_uuid) ON DELETE CASCADE,
FOREIGN KEY (uni_uuid) REFERENCES universities(uni_uuid) ON DELETE CASCADE
);

CREATE TABLE admin_accounts(
adminid INTEGER PRIMARY KEY AUTOINCREMENT,
user_uuid INTEGER,
FOREIGN KEY (user_uuid) REFERENCES user(user_uuid) ON DELETE CASCADE
);


INSERT INTO user(username, firstname, surname, email, phone, pwd_hash, administrator) VALUES(
'admin', 'admin', 'admin', 'admin.admin@adminmail.com', '0500628689', 'hash', 1
);

INSERT INTO user(username, firstname, surname, email, phone, pwd_hash, administrator) VALUES(
'mattim', 'Matti', 'Meikäläinen', 'Matti.Meikalainen@gmail.com', '0505689132', 'hash', 0
);

INSERT INTO user(username, firstname, surname, email, phone, pwd_hash, administrator) VALUES(
'rickv', 'Rick', 'Vang', 'Rick.Vang@webmail.com', '0290909857', 'hash', 0
);

INSERT INTO user(username, firstname, surname, email, phone, pwd_hash, administrator) VALUES(
'jimb', 'Jim', 'Bass', 'jimba89@gmail.com', '0440698602', 'hash', 0
);

INSERT INTO user(username, firstname, surname, email, phone, pwd_hash, administrator) VALUES(
'jonh', 'John', 'Denton', 'Jonny.Boii@memes.com', '0400568223', 'hash', 0
);

INSERT INTO user(username, firstname, surname, email, phone, pwd_hash, administrator) VALUES(
'gregn', 'Greg', 'Novak', 'Gregori.Nova@slavmail.ru', '0440666869', 'hash', 0
);

INSERT INTO user(username, firstname, surname, email, phone, pwd_hash, administrator) VALUES(
'omarm', 'Omar', 'Marshall', 'FieldMarshall@USAmail.com', '0500911420', 'hash', 0
);

INSERT INTO user(username, firstname, surname, email, phone, pwd_hash, administrator) VALUES(
'miac', 'Mia', 'Croft', 'mia.croft@onlinemail.com', '0670884925', 'hash', 0
);

INSERT INTO universities(name, address) VALUES(
'LUT', 'Yliopistonkatu 34, 53850 Lappeenranta'
);

INSERT INTO user_access_uni(user_uuid, uni_uuid) VALUES(
1, 1
);

INSERT INTO user_access_uni(user_uuid, uni_uuid) VALUES(
2, 1
);

INSERT INTO user_access_uni(user_uuid, uni_uuid) VALUES(
3, 1
);

INSERT INTO user_access_uni(user_uuid, uni_uuid) VALUES(
4, 1
);

INSERT INTO user_access_uni(user_uuid, uni_uuid) VALUES(
5, 1
);

INSERT INTO user_access_uni(user_uuid, uni_uuid) VALUES(
6, 1
);

INSERT INTO user_access_uni(user_uuid, uni_uuid) VALUES(
7, 1
);

INSERT INTO user_access_uni(user_uuid, uni_uuid) VALUES(
8, 1
);

INSERT INTO sporthall(hallname, uni_uuid, halltype, not_available) VALUES(
'Gerpiili', 1, 'Multipurpose', 0
);

INSERT INTO sporthall(hallname, uni_uuid, halltype, not_available) VALUES(
'Kerpiili', 1, 'Badminton', 0
);

INSERT INTO sporthall(hallname, uni_uuid, halltype, not_available) VALUES(
'Kerbiili', 1, 'Multipurpose', 0
);

INSERT INTO sporthall(hallname, uni_uuid, halltype, not_available) VALUES(
'Gerbiili', 1, 'Gym', 0
);

INSERT INTO reservations(hallid, sport, start_time, duration, user_uuid, maxparticipants, recurring_event) VALUES(
2, 'Floorball', '2019-07-20T14:00', 2, 2, 20, 0
);

INSERT INTO reservations(hallid, sport, start_time, duration, user_uuid, maxparticipants, recurring_event) VALUES(
2, 'Badminton', '2019-07-20T16:00', 4, 3, 10, 0
);

INSERT INTO enrolls(user_uuid, reserveid) VALUES(
3, 1
);

INSERT INTO enrolls(user_uuid, reserveid) VALUES(
4, 1
);

INSERT INTO enrolls(user_uuid, reserveid) VALUES(
5, 1
);

INSERT INTO enrolls(user_uuid, reserveid) VALUES(
6, 1
);

INSERT INTO enrolls(user_uuid, reserveid) VALUES(
2, 2
);

INSERT INTO enrolls(user_uuid, reserveid) VALUES(
7, 2
);

INSERT INTO enrolls(user_uuid, reserveid) VALUES(
8, 2
);

INSERT INTO enrolls(user_uuid, reserveid) VALUES(
1, 2
);

INSERT INTO admin_accounts(user_uuid) VALUES(
1
);