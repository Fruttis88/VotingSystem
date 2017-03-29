DELETE FROM users;
DELETE FROM user_roles;
DELETE FROM restaurants;
DELETE FROM dishes;
DELETE FROM votes;

ALTER SEQUENCE global_seq RESTART WITH 1;

-- pass: password
INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', '$2a$10$Sh0ZD2NFrzRRJJEKEWn8l.92ROEuzlVyzB9SV1AM8fdluPR0aC1ni');
-- pass: admin
INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', '$2a$10$WejOLxVuXRpOgr4IlzQJ.eT4UcukNqHlAiOVZj1P/nmc8WbpMkiju');
-- pass: password2
INSERT INTO users (name, email, password)
VALUES ('User2', 'user2@gmail.com', '$2a$10$n5P60SQcI85qU3RRHkR4EOKgQN9Ld2mfGiSKj2q.1sXSN1nYqnkzm');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 1),
  ('ROLE_ADMIN', 2),
  ('ROLE_USER', 2),
  ('ROLE_USER', 3);

INSERT INTO restaurants (name) VALUES
  ('Жрем днем'),
  ('Хочу харчо'),
  ('На парах');

INSERT INTO dishes (name, price, restaurant_id) VALUES
  ('Шашлык', 500, 4),
  ('Машлык', 1000, 4),
  ('Супец', 350, 5),
  ('Холодец', 400, 5),
  ('Кукиш с маслом', 240, 6),
  ('Гальваническая развязка из желе', 12000, 6);

INSERT INTO votes (user_id, restaurant_id) VALUES
  (3, 5),
  (2, 5);