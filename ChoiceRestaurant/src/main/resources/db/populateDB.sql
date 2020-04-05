DELETE FROM users_restaurants_day;
DELETE FROM meals;
DELETE FROM restaurants;
DELETE FROM users;
DELETE FROM roles;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO roles (id, name) VALUES
(100000, 'Admin'),
(100001, 'User');

INSERT INTO users (id, name, email, password, role_id) VALUES
(100002, 'Admin_1', 'admin_1@gmail.com', 'admin_1', 100000),
(100003, 'User_1', 'user_1@yandex.ru', 'password_1', 100001),
(100004, 'User_2', 'user_2@yandex.ru', 'password_2', 100001);

INSERT INTO restaurants (id, name, user_id) VALUES
(100005, 'Fish home', 100002),
(100006, 'Big Italia', 100002),
(100007, 'Ukraine kitchen', 100002);

INSERT INTO meals (id, name, price, lunch_day, restaurants_id, user_id) VALUES
(100008, 'Fried trout', 10.5, '2020-01-01', 100005, 100002),
(100009, 'Boiled shrimp', 11.5, '2020-01-02', 100005, 100002),
(100010, 'Shark fin soup', 7.5, '2020-01-01', 100005, 100002),
(100011, 'Borscht', 17.5, '2020-01-02', 100007, 100002),
(100012, 'The Kievs cutlets', 3, '2020-01-02', 100007, 100002),
(100013, 'Potato pancakes', 5, '2020-01-01', 100007, 100002),
(100014, 'Fried potatoes with mushrooms', 27.3, '2020-01-01', 100007, 100002);

INSERT INTO users_restaurants_day (id, user_id, restaurants_id, lunch_day) VALUES
(100015, 100003, 100005, '2020-01-01'),
(100016, 100004, 100005, '2020-01-01'),
(100017, 100003, 100005, '2020-01-02'),
(100018, 100004, 100007, '2020-01-02');

