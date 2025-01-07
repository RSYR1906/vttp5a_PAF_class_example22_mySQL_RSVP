DROP DATABASE IF EXISTS rsvpDB;

-- create database
CREATE DATABASE rsvpDB;

-- select database
USE rsvpDB;

-- create the table
CREATE TABLE rsvp(
    name CHAR(8) NOT NULL,
    email VARCHAR(128) NOT NULL,    -- primary key
    phone CHAR(8),
    confirmDate DATE NOT NULL,
    comments TEXT,

    CONSTRAINT pk_email PRIMARY KEY (email)
);

-- insert multiple rows
INSERT INTO rsvp(
    name, email, phone, confirmDate, comments
)
VALUES
    ("Andy","abc@gmail.com", "99290192", '2025-01-12', "Hello"),
    ("Fred","fred99@gmail.com", "99292392", '2025-01-08', "Testing"),
    ("Lili","lili@gmail.com", "92342392", '2025-01-11', "Hello World");