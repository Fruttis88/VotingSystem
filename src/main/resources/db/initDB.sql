DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS votes;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS dishes;
DROP TABLE IF EXISTS restaurants;


DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 1;

CREATE TABLE restaurants
(
  id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name       VARCHAR NOT NULL,
  votescount INTEGER NOT NULL
);
CREATE UNIQUE INDEX restaurants_unique_name_idx
  ON restaurants (name);

CREATE TABLE dishes (
  id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  restaurant_id INTEGER NOT NULL,
  name          VARCHAR NOT NULL,
  price         INT     NOT NULL,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);

CREATE TABLE users
(
  id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name       VARCHAR NOT NULL,
  email      VARCHAR NOT NULL,
  password   VARCHAR NOT NULL,
  registered TIMESTAMP           DEFAULT now(),
  hasVote    BOOL                DEFAULT TRUE,
  enabled    BOOL                DEFAULT TRUE
);
CREATE UNIQUE INDEX users_unique_email_idx
  ON users (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE votes (
  id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  restaurant_id INTEGER NOT NULL,
  user_id       INTEGER NOT NULL,
  votedate      TIMESTAMP           DEFAULT now(),
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);