DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS book_copy;
DROP TABLE IF EXISTS book_loan;
DROP TABLE IF EXISTS book_genres;
DROP TABLE IF EXISTS book_authors;
DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS genre;
DROP TABLE IF EXISTS publisher;
DROP TABLE IF EXISTS author;
DROP TABLE IF EXISTS library_branch;
DROP TABLE IF EXISTS borrower;
DROP TABLE IF EXISTS user;


CREATE TABLE borrower (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  address varchar(255) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  phone_number varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE library_branch (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  address varchar(255) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE author (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE publisher (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  address varchar(255) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  phone_number varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE genre (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE book (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  title varchar(255) DEFAULT NULL,
  publisher_id bigint(20) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (publisher_id) REFERENCES publisher (id)
);

CREATE TABLE book_authors (
  book_id bigint(20) NOT NULL,
  author_id bigint(20) NOT NULL,
  PRIMARY KEY (book_id,author_id),
  FOREIGN KEY (author_id) REFERENCES author (id),
  FOREIGN KEY (book_id) REFERENCES book (id)
);

CREATE TABLE book_genres (
  book_id bigint(20) NOT NULL,
  genre_id bigint(20) NOT NULL,
  PRIMARY KEY (book_id,genre_id),
  FOREIGN KEY (book_id) REFERENCES book (id),
  FOREIGN KEY (genre_id) REFERENCES genre (id)
);

CREATE TABLE book_loan (
  book_id bigint(20) NOT NULL,
  borrower_id bigint(20) NOT NULL,
  branch_id bigint(20) NOT NULL,
  date_in date DEFAULT NULL,
  date_out date DEFAULT NULL,
  due_date date DEFAULT NULL,
  PRIMARY KEY (book_id,borrower_id,branch_id),
  FOREIGN KEY (borrower_id) REFERENCES borrower (id),
  FOREIGN KEY (book_id) REFERENCES book (id),
  FOREIGN KEY (branch_id) REFERENCES library_branch (id)
);

CREATE TABLE book_copy (
  book_id bigint(20) NOT NULL,
  branch_id bigint(20) NOT NULL,
  amount bigint(20) DEFAULT NULL,
  PRIMARY KEY (book_id,branch_id),
  FOREIGN KEY (branch_id) REFERENCES library_branch (id),
  FOREIGN KEY (book_id) REFERENCES book (id)
);

CREATE TABLE user (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  email varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  role ENUM('ROLE_LIBRARIAN', 'ROLE_ADMIN') NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY (email)
);
