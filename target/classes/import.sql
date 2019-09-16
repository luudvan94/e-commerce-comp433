
INSERT INTO BOOK(id, title, author, description, price, partnerID) VALUES ('B123','Artificial Intelligence in Practice', 'Bernard Marr', 'Those looking for a practical means of understanding how artificial intelligence serves to enhance data science and use this knowledge to improve their data analytics strategies.', 50.0, 'P1234');
INSERT INTO BOOK(id, title, author, description, price, partnerID) VALUES ('B456','Deep Learning', 'Ian Goodfellow, Yoshua Bengio and Aaron Courville', 'This best data science book is especially effective for those looking to enter the data-driven machine learning and deep learning avenues of the field.', 35.0, 'P1234');
INSERT INTO BOOK(id, title, author, description, price, partnerID) VALUES ('B789','Advanced R', 'Hadley Wickham', 'Budding ‘R’ users and those looking to improve their overall programming talents and analytical skills as well as peruse the intricate nuances of this invaluable data-driven language.', 55.0, 'P1234');
INSERT INTO BOOK(id, title, author, description, price, partnerID) VALUES ('B124','Machine Learning Yearning', 'Andrew Ng', 'Someone who has become all too aware of the machine learning and artificial intelligence craze but needs to get a grip on the subject. One of the best books for data science if you’re looking to hit the ground running with autonomous technologies.', 40.0, 'P1234');

INSERT INTO PARTNER(id, username, password) VALUES('P1234', 'partner1', 'abcd');
INSERT INTO PARTNER(id, username, password) VALUES('P4567', 'partner2', 'abcd');

INSERT INTO PARTNER_INFO(id, partnerID, name, address, date_added) VALUES('PI1234', 'P1234', 'Loyola University', '123 Sheridan Rd, IL', '123456678');