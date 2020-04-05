DROP TABLE IF EXISTS users_restaurants_day;
DROP TABLE IF EXISTS meals;
DROP TABLE IF EXISTS restaurants;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE roles
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name             VARCHAR(100)                 NOT NULL
);
CREATE UNIQUE INDEX unique_roles_id ON roles (id);

CREATE TABLE users
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name             VARCHAR(100)                 NOT NULL,
  email            VARCHAR(100)                 NOT NULL,
  password         VARCHAR(100)                NOT NULL,
  registered       TIMESTAMP DEFAULT now() NOT NULL,
  enabled          BOOL DEFAULT TRUE       NOT NULL,
  role_id          INTEGER NOT NULL,
  FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX unique_users_id ON users (id);

CREATE TABLE restaurants
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name             VARCHAR(100)                 NOT NULL,
    user_id          INTEGER NOT NULL
);
CREATE UNIQUE INDEX restaurants_id ON restaurants (id);

CREATE TABLE meals
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name             VARCHAR(100)                 NOT NULL,
    price            NUMERIC(16,2)                 NOT NULL,
    lunch_day        DATE NOT NULL,
    restaurants_id   INTEGER NOT NULL,
    user_id          INTEGER NOT NULL,
    FOREIGN KEY (restaurants_id) REFERENCES restaurants (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX meals_id ON meals (id);

CREATE TABLE users_restaurants_day
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    user_id          INTEGER NOT NULL,
    restaurants_id   INTEGER NOT NULL,
    lunch_day        DATE NOT NULL,
    FOREIGN KEY (restaurants_id) REFERENCES restaurants (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX users_restaurants_day_id ON users_restaurants_day (id);
