INSERT INTO borrower (id, address, name, phone_number) VALUES (1, '1325 S 76TH Ave. Yakima, Washington', 'Joe Smith', '509-287-4787');
INSERT INTO borrower (id, address, name, phone_number) VALUES (2, '1827 Tod Rd. Arlington, Virginia', 'Steve Cook', '706-287-2998');

INSERT INTO library_branch (id, address, name) VALUES (1, '1837 5th Ave. Seattle, Wa', 'Seattle Library');
INSERT INTO library_branch (id, address, name) VALUES (2, '17637 Fairlakes Parkway Fairfax, Virginia', 'Chantilly Regional Library');
INSERT INTO library_branch (id, address, name) VALUES (3, '17273 Arlington Ave. Washington DC', 'DC Public Library');

INSERT INTO genre (id, name) VALUES (1, 'Fiction');
INSERT INTO genre (id, name) VALUES (2, 'Suspense Fiction');

INSERT INTO publisher (id, address, name, phone_number) VALUES (1, '1234 Arlington Ave. Fairfax Virginia 27363', 'Penguin House Inc.', '762-282-8787');
INSERT INTO publisher (id, address, name, phone_number) VALUES (2, '32605 5th Ave. Seattle, Washington', 'Macmillan Publishers', '206-716-2787');
INSERT INTO publisher (id, address, name, phone_number) VALUES (3, '88781 51 St. Portland, Oregon', 'Simon & Schuster', '506-287-8878');

INSERT INTO author (id, name) VALUES (1, 'J.K. Rolling');
INSERT INTO author (id, name) VALUES (2, 'George Martin');
INSERT INTO author (id, name) VALUES (3, 'Brian McClellan');
INSERT INTO author (id, name) VALUES (4, 'Robert Jordan');
INSERT INTO author (id, name) VALUES (5, 'James Patterson');
INSERT INTO author (id, name) VALUES (6, 'Joe Abercrombie');

INSERT INTO book (id, title, publisher_id) VALUES (1, 'Harry Potter and the Goblet of Fire', 1);
INSERT INTO book (id, title, publisher_id) VALUES (2, 'Sins of Empire', 2);
INSERT INTO book (id, title, publisher_id) VALUES (3, 'Servant of the Crown', 2);
INSERT INTO book (id, title, publisher_id) VALUES (7, 'Game of Thrones', 1);
INSERT INTO book (id, title, publisher_id) VALUES (8, 'Winters Heart', 3);
INSERT INTO book (id, title, publisher_id) VALUES (9, 'The Blade Itself', 1);
INSERT INTO book (id, title, publisher_id) VALUES (10, 'Criss Cross', 3);

INSERT INTO book_authors (book_id, author_id) VALUES (1, 1);
INSERT INTO book_authors (book_id, author_id) VALUES (7, 2);
INSERT INTO book_authors (book_id, author_id) VALUES (2, 3);
INSERT INTO book_authors (book_id, author_id) VALUES (3, 3);
INSERT INTO book_authors (book_id, author_id) VALUES (8, 4);
INSERT INTO book_authors (book_id, author_id) VALUES (10, 5);
INSERT INTO book_authors (book_id, author_id) VALUES (9, 6);

INSERT INTO book_genres (book_id, genre_id) VALUES (1, 1);
INSERT INTO book_genres (book_id, genre_id) VALUES (2, 1);
INSERT INTO book_genres (book_id, genre_id) VALUES (3, 1);
INSERT INTO book_genres (book_id, genre_id) VALUES (7, 1);
INSERT INTO book_genres (book_id, genre_id) VALUES (8, 1);
INSERT INTO book_genres (book_id, genre_id) VALUES (9, 1);
INSERT INTO book_genres (book_id, genre_id) VALUES (10, 2);

INSERT INTO book_copy (book_id, branch_id, amount) VALUES (1, 1, 7);
INSERT INTO book_copy (book_id, branch_id, amount) VALUES (1, 2, 10);
INSERT INTO book_copy (book_id, branch_id, amount) VALUES (3, 1, 30);
INSERT INTO book_copy (book_id, branch_id, amount) VALUES (7, 1, 0);
INSERT INTO book_copy (book_id, branch_id, amount) VALUES (8, 1, 1);
INSERT INTO book_copy (book_id, branch_id, amount) VALUES (10, 1, 9);
INSERT INTO book_copy (book_id, branch_id, amount) VALUES (10, 2, 7);
INSERT INTO book_copy (book_id, branch_id, amount) VALUES (1, 3, 1);
INSERT INTO book_copy (book_id, branch_id, amount) VALUES (2, 3, 1);
INSERT INTO book_copy (book_id, branch_id, amount) VALUES (3, 3, 1);
INSERT INTO book_copy (book_id, branch_id, amount) VALUES (7, 3, 1);
INSERT INTO book_copy (book_id, branch_id, amount) VALUES (8, 3, 1);
INSERT INTO book_copy (book_id, branch_id, amount) VALUES (9, 3, 1);
INSERT INTO book_copy (book_id, branch_id, amount) VALUES (10, 3, 1);

INSERT INTO book_loan (book_id, borrower_id, branch_id, date_in, date_out, due_date) VALUES (1, 1, 2, NULL, '2020-04-20', '2020-04-27');
INSERT INTO book_loan (book_id, borrower_id, branch_id, date_in, date_out, due_date) VALUES (2, 2, 1, NULL, '2020-04-19', '2020-04-26');
INSERT INTO book_loan (book_id, borrower_id, branch_id, date_in, date_out, due_date) VALUES (3, 1, 1, '2020-04-21', '2020-04-18', '2020-04-25');
INSERT INTO book_loan (book_id, borrower_id, branch_id, date_in, date_out, due_date) VALUES (7, 2, 1, NULL, '2020-04-15', '2020-04-22');
INSERT INTO book_loan (book_id, borrower_id, branch_id, date_in, date_out, due_date) VALUES (8, 1, 1, '2020-05-04', '2020-05-03', '2020-05-10');
INSERT INTO book_loan (book_id, borrower_id, branch_id, date_in, date_out, due_date) VALUES (8, 2, 2, '2020-04-19', '2020-04-16', '2020-04-23');

