CREATE DATABASE contactbook;

CREATE TABLE users(
id SERIAL PRIMARY KEY NOT NULL,
login VARCHAR(50) UNIQUE NOT NULL, 
password VARCHAR(50) NOT NULL,
date_born DATE NULL
);

CREATE TABLE  types(
name VARCHAR(50) PRIMARY KEY NOT NULL
);

CREATE TABLE contacts(
id SERIAL PRIMARY KEY NOT NULL,
user_id  INT NOT NULL,
type VARCHAR(50) NOT NULL,
name VARCHAR(50) NOT NULL, 
value VARCHAR(30) NOT NULL,
CONSTRAINT contacts_users_id_fk FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
CONSTRAINT contacts_types_name_fk FOREIGN KEY (type) REFERENCES types(name) ON DELETE RESTRICT ON UPDATE CASCADE
);

--*************************************

INSERT INTO users(login, password, date_born) VALUES
('User-1', '111', '1998-10-05'),
('User-2', '222', '1995-11-05'),
('User-3', '333', '1988-12-05');


INSERT INTO types(name) VALUES
('PHONE'),
('EMAIL');

INSERT INTO contacts(user_id, type, name, value) VALUES
(1, 'PHONE', 'contact-1', '0501111111'),
(1, 'PHONE', 'contact-2', '0501111112'),
(1, 'EMAIL', 'contact-3', 'aa1@gmail.com'),
(2, 'PHONE', 'contact-4', '0502222221'),
(2, 'EMAIL', 'contact-5', 'aa2@gmail.com'),
(2, 'EMAIL', 'contact-6', 'bb2@gmail.com'),
(3, 'EMAIL', 'contact-7', 'aa3@gmail.com'),
(3, 'EMAIL', 'contact-8', 'bb3@gmail.com'),
(3, 'EMAIL', 'contact-9', 'cc3@gmail.com');