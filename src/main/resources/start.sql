CREATE SCHEMA M4L14;


CREATE TABLE clients (
                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                         full_name VARCHAR(128) DEFAULT NULL,
                         not_an_age INTEGER DEFAULT NULL

);

CREATE TABLE accounts (
                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                          balance INTEGER DEFAULT NULL,
                          client_id BIGINT DEFAULT NULL,
                          FOREIGN KEY (client_id) REFERENCES clients(id)

);

INSERT INTO accounts VALUES ()