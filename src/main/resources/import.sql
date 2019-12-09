
INSERT INTO PARTNER(partnerID, username, password) VALUES('P1234', 'partner1', 'abcd');
INSERT INTO PARTNER(partnerID, username, password) VALUES('P4567', 'partner2', 'abcd');

INSERT INTO PARTNER_INFO(partnerInfoID, partnerID, name, address, date_added) VALUES('PI1234', 'P1234', 'Loyola University', '123 Sheridan Rd, IL', '123456678');
INSERT INTO PARTNER_INFO(partnerInfoID, partnerID, name, address, date_added) VALUES('PI1235', 'P4567', 'BIR Training Center', '123 Sheridan Rd, IL', '123456678');

INSERT INTO CUSTOMER(customerID, username, password) VALUES('C123', 'customer1', 'abcd');
INSERT INTO CUSTOMER(customerID, username, password) VALUES('C456', 'customer2', 'abcd');

INSERT INTO BOOK(bookID, title, author, description, price, quantity, partnerInfoID) VALUES ('B123','Artificial Intelligence in Practice', 'Bernard Marr', 'Those looking for a practical means of understanding how artificial intelligence serves to enhance data science and use this knowledge to improve their data analytics strategies.', 50.0, 100, 'PI1234');
INSERT INTO BOOK(bookID, title, author, description, price, quantity, partnerInfoID) VALUES ('B456','Deep Learning', 'Ian Goodfellow, Yoshua Bengio and Aaron Courville', 'This best data science book is especially effective for those looking to enter the data-driven machine learning and deep learning avenues of the field.', 35.0, 10, 'PI1234');
INSERT INTO BOOK(bookID, title, author, description, price, quantity, partnerInfoID) VALUES ('B789','Advanced R', 'Hadley Wickham', 'Budding ‘R’ users and those looking to improve their overall programming talents and analytical skills as well as peruse the intricate nuances of this invaluable data-driven language.', 55.0, 15, 'PI1235');
INSERT INTO BOOK(bookID, title, author, description, price, quantity, partnerInfoID) VALUES ('B124','Machine Learning Yearning', 'Andrew Ng', 'Someone who has become all too aware of the machine learning and artificial intelligence craze but needs to get a grip on the subject. One of the best books for data science if you’re looking to hit the ground running with autonomous technologies.', 40.0, 5, 'PI1235');

INSERT INTO CUSTOMER_INFO(customerInfoID, customerID, name, address, date_added) VALUES('CI123', 'C123', 'Loyola University', '123 Sheridan Rd, IL', '123456678');
INSERT INTO CUSTOMER_INFO(customerInfoID, customerID, name, address, date_added) VALUES('CI124', 'C456', 'Loyola University', '123 Sheridan Rd, IL', '123456678');

INSERT INTO BOOK_REVIEW(bookReviewID, content, bookID, customerInfoID, date_created) VALUES('BR123', 'This book is very great! yeahhhhhh', 'B123', 'CI123', '12345656');
INSERT INTO BOOK_REVIEW(bookReviewID, content, bookID, customerInfoID, date_created) VALUES('BR124', 'This book is very great!', 'B123', 'CI123', '12345656');
INSERT INTO BOOK_REVIEW(bookReviewID, content, bookID, customerInfoID, date_created) VALUES('BR125', 'This book is very great!', 'B123', 'CI124', '12345656');

INSERT INTO SHIPPING_ADDRESS(shippingAddressID, customerID, street, city, zipcode) VALUES('S123', 'C123', '7800 Nashville', 'Burbank', '60459');
INSERT INTO SHIPPING_ADDRESS(shippingAddressID, customerID, street, city, zipcode) VALUES('S124', 'C456', '1234 ABC rd', 'Oak Lawn', '60652');

INSERT INTO PAYMENT(paymentID, expires, date_added, amount, card_number) VALUES('CP123', '06/23', '345345346', 200, '13123123123');
INSERT INTO PAYMENT(paymentID, expires, date_added, amount, card_number) VALUES('CP124', '06/23', '565676353', 250, '12312312312');

INSERT INTO ORDER1(orderID, customerInfoID, date_updated, status, shippingAddress, billingAddress, total) VALUES('O123', 'CI123', '234234234', 'pending', '7800 Nashville Ave, Burbank, IL 60459', '7800 Nashville Ave, Burbank, IL 60459', 123.00);
INSERT INTO ORDER1(orderID, customerInfoID, date_updated, status, shippingAddress, billingAddress, total) VALUES('O124', 'CI124', '123134234', 'shipping', '7926 Lorel Ave, Burbank, IL 60459', '7800 Nashville Ave, Burbank, IL 60459', 55.00);

INSERT INTO ORDER_BOOK(orderBookID, orderID, bookID, partnerInfoID, qty, total) VALUES('OB123', 'O123', 'B123', 'PI1234', '1', 50.0);
INSERT INTO ORDER_BOOK(orderBookID, orderID, bookID, partnerInfoID, qty, total) VALUES('OB124', 'O123', 'B456', 'PI1234', '2', 50.0);