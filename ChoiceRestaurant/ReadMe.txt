ChoiceRestaurant - restaurant voting app

////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////Developer guid/////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////
app: rest api
Working profile: tomcat, jpa, postgres

DB:
	Tables: 
		roles
		users
		restaurants
		meals
		users_restaurants_day (contains the result of a user's vote for a restaurant on a specific day)
	Relations:
		users(FK) :- roles
		meals(FK) :- restaurants
		users -: (FK)users_restaurants_day(FK) :- restaurants

Rests:
	Role:
		get all   - GET    http://localhost:8080/choicerestaurant/rest/roles
		get by id - GET    http://localhost:8080/choicerestaurant/rest/roles/{id}
	User:
		get all   - GET    http://localhost:8080/choicerestaurant/rest/users
		get by id - GET    http://localhost:8080/choicerestaurant/rest/users/{id}
		create    - POST   http://localhost:8080/choicerestaurant/rest/users
		update    - PUT    http://localhost:8080/choicerestaurant/rest/users/{id}
		delete    - DELETE http://localhost:8080/choicerestaurant/rest/users/{id}
	Restaurant:
		get all   - GET    http://localhost:8080/choicerestaurant/rest/restaurants
		get by id - GET    http://localhost:8080/choicerestaurant/rest/restaurants/{id}
		create    - POST   http://localhost:8080/choicerestaurant/rest/restaurants/{id}
		update    - PUT    http://localhost:8080/choicerestaurant/rest/restaurants/{id}
		delete    - DELETE http://localhost:8080/choicerestaurant/rest/restaurants/{id}
		get vote  - GET    http://localhost:8080/choicerestaurant/rest/restaurants/statistic/{dateVote}
	Meal:
		get by id - GET    http://localhost:8080/choicerestaurant/rest/meals/{id}
		create    - POST   http://localhost:8080/choicerestaurant/rest/meals
		update    - PUT    http://localhost:8080/choicerestaurant/rest/meals/{id}
		delete    - DELETE http://localhost:8080/choicerestaurant/rest/meals/{id}
	UserRestaurantDay (vote):
		create    - POST   http://localhost:8080/choicerestaurant/rest/userrestaurantday
		delete    - DELETE http://localhost:8080/choicerestaurant/rest/userrestaurantday/{id}

////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////Test guid//////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////
username: admin_1@gmail.com
password: admin_1

Roles:
	get all   - GET    http://localhost:8080/choicerestaurant/rest/roles
	get by id - GET    http://localhost:8080/choicerestaurant/rest/roles/100000

Users:
	get all   - GET    http://localhost:8080/choicerestaurant/rest/users
	get by id - GET    http://localhost:8080/choicerestaurant/rest/users/100002
	create    - POST   http://localhost:8080/choicerestaurant/rest/users
		{
			"name": "User_add",
			"email": "user_add@yandex.ru",
			"password": "password",
			"enabled": true,
			"role": 100001
		}
	get by id - GET    http://localhost:8080/choicerestaurant/rest/users/101000
	update    - PUT    http://localhost:8080/choicerestaurant/rest/users/101000
		{
			"id": 101000,
			"name": "User_add_update",
			"email": "user_add_update@yandex.ru",
			"password": "password",
			"enabled": true,
			"role": 100000
		}
	get by id - GET    http://localhost:8080/choicerestaurant/rest/users/101000
	delete    - DELETE http://localhost:8080/choicerestaurant/rest/users/101000
	get by id - GET    http://localhost:8080/choicerestaurant/rest/users/101000

Restaurant:
	get all   - GET    http://localhost:8080/choicerestaurant/rest/restaurants
	get by id - GET    http://localhost:8080/choicerestaurant/rest/restaurants/100005
	create    - POST   http://localhost:8080/choicerestaurant/rest/restaurants
		{
			"name": "restaurant add",
			"enabled": true
		}
	get by id - GET    http://localhost:8080/choicerestaurant/rest/restaurants/101002
	update    - PUT    http://localhost:8080/choicerestaurant/rest/restaurants/101002	
		{
			"id": 101002,
			"name": "restaurant add update",
			"enabled": false
		}
	get by id - GET    http://localhost:8080/choicerestaurant/rest/restaurants/101002
	delete    - DELETE http://localhost:8080/choicerestaurant/rest/restaurants/101002
	get by id - GET    http://localhost:8080/choicerestaurant/rest/restaurants/101002
	get vote  - GET    http://localhost:8080/choicerestaurant/rest/restaurants/statistic/2022-01-02

Meal:
	get by id - GET    http://localhost:8080/choicerestaurant/rest/meals/100010
	create    - POST   http://localhost:8080/choicerestaurant/rest/meals
		{
			"name": "TEST ADD FOR ITALIA",
			"price": 7.5,
			"lunchDay": "2020-01-01",
			"restaurantId": 100006
		}
	get by id - GET    http://localhost:8080/choicerestaurant/rest/meals/101003
	update    - PUT    http://localhost:8080/choicerestaurant/rest/meals/101003
		{
			"id": 101003,
			"name": "TEST UPDATE FOR ITALIA",
			"price": 7.77,
			"lunchDay": "2020-01-02",
			"restaurantId": 100006
		}
	get by id - GET    http://localhost:8080/choicerestaurant/rest/restaurants/100006
	delete    - DELETE http://localhost:8080/choicerestaurant/rest/meals/101003
	get by id - GET    http://localhost:8080/choicerestaurant/rest/meals/101003
	get by id - GET    http://localhost:8080/choicerestaurant/rest/restaurants/100006


UserRestaurantDay (vote):
	create(wrong user)    - POST   http://localhost:8080/choicerestaurant/rest/userrestaurantday
		{
			"user": 100024,
			"restaurant": 100006,
			"lunchDay": "2022-01-02"
		

	username: user_5@yandex.ru
	password: password_5
	get vote  - GET    http://localhost:8080/choicerestaurant/rest/restaurants/statistic/2022-01-02
	create    - POST   http://localhost:8080/choicerestaurant/rest/userrestaurantday
		{
			"user": 100024,
			"restaurant": 100006,
			"lunchDay": "2022-01-02"
		}
	get vote  - GET    http://localhost:8080/choicerestaurant/rest/restaurants/statistic/2022-01-02
	create(change vote)    - POST   http://localhost:8080/choicerestaurant/rest/userrestaurantday
		{
			"user": 100024,
			"restaurant": 100007,
			"lunchDay": "2022-01-02"
		}
	get vote  - GET    http://localhost:8080/choicerestaurant/rest/restaurants/statistic/2022-01-02
