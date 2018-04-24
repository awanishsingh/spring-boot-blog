-- WARNING: Do not modify this file. It may break the Tests.
-- Default admin's password is 123

INSERT INTO user (name, email, username, password) VALUES ('Administrator', 'admin@localhost', 'admin', '$2a$04$VXb9pY5bt3YcBd4i4h4/ZeuRC0vF4Evm18m4OKraib9CdvfEbwzZm');

INSERT INTO blog_post (author_id, title, body, create_date, publish_date) VALUES (1, 'Hello Blog', 'Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Aenean eu leo quam. Pellentesque ornare sem lacinia quam venenatis vestibulum. Sed posuere consectetur est at lobortis. Cras mattis consectetur purus sit amet fermentum.', '2018-03-1 17:30:00', '2018-03-2 17:30:00');
INSERT INTO blog_post (author_id, title, body, create_date, publish_date) VALUES (1, 'Hello Blog 2', 'Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Aenean eu leo quam. Pellentesque ornare sem lacinia quam venenatis vestibulum. Sed posuere consectetur est at lobortis. Cras mattis consectetur purus sit amet fermentum.', '2018-03-1 17:35:00', '2018-03-2 17:35:00');
INSERT INTO blog_post (author_id, title, body, create_date) VALUES (1, 'Hello Blog 3', 'Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Aenean eu leo quam. Pellentesque ornare sem lacinia quam venenatis vestibulum. Sed posuere consectetur est at lobortis. Cras mattis consectetur purus sit amet fermentum.', '2018-03-3 17:35:00');

INSERT INTO blog_comment (blog_post_id, commenter_name, title, body, create_date, approve_date) VALUES (1, 'a visitor', 'test comment', 'this is a test comment.', '2018-03-5 17:30:00', '2018-03-6 17:30:00');
INSERT INTO blog_comment (blog_post_id, commenter_name, title, body, create_date, approve_date) VALUES (1, 'jsmith', 'another test comment', 'this is another test comment.', '2018-03-5 17:30:00', '2018-03-6 17:30:00');
INSERT INTO blog_comment (blog_post_id, commenter_name, title, body, create_date, approve_date) VALUES (2, 'salesman', 'another test comment', 'this is another test comment.', '2018-03-5 17:30:00', '2018-03-6 17:30:00');
INSERT INTO blog_comment (blog_post_id, commenter_name, title, body, create_date, approve_date) VALUES (2, 'jack', 'another test comment', 'this is another test comment.', '2018-03-5 17:30:00', '2018-03-6 17:30:00');