# Voting system for deciding where to have lunch
# API Reference

>1. [Users](#users)
>   - [Get the authenticated user](#getAuthUser)
>   - [Update the authenticated user](#updateAuthUser)
>   - [Delete the authenticated user](#deleteAuthUser)
>   - [Get all users](#getAllUsers)
>   - [Get a single user by id](#getUserById)
>   - [Get a single user by e-mail](#getUserByEmail)
>   - [Create a new user by admin](#createUser)
>   - [Update the user by admin](#updateUser)
>   - [Enable/disable the user by admin](#enableUser)
>   - [Delete the user by admin](#deleteUser)
>2. [Restaurants](#restaurants)
>   - [Get all restaurants](#getAllRestaurants)
>   - [Get a single restaurant by id](#getRestaurantById)
>   - [Create a new restaurant](#createRestaurant)
>   - [Update restaurant](#updateRestaurant)
>   - [Delete restaurant](#deleteRestaurant)
>3. [Menu](#menu)
>   - [Get all](#getAll)
>   - [Get menu](#getMenu)
>   - [Get a single dish from the menu](#getDish)
>   - [Create a new dish in the menu](#createDish)
>   - [Update the dish in the menu](#updateDish)
>   - [Delete menu](#deleteMenu)
>   - [Delete a single dish from the menu](#deleteDish)
>4. [Vote](#vote)
>   - [Vote for the restaurant](#voteForRestaurant)
>   - [Get current vote results](#getCurrentVotes)
>5. [Errors](#errors)



# <a name="users">1. Users</a>



## <a name="getAuthUser">Get the authenticated user</a>
Lists profile information when authenticated through basic auth and authorized as a user.
#### HTTP request
    GET /api/v1/profile
#### Parameters
    No parameters.
#### Request body
    Do not supply a request body with this method.
#### Success response
    Status: 200 OK
        
    {
      "id": 1,
      "name": "User",
      "email": "user@yandex.ru",
      "password": "$2a$10$Sh0ZD2NFrzRRJJEKEWn8l.92ROEuzlVyzB9SV1AM8fdluPR0aC1ni"
    }
#### Error response
Status: 401 Unauthorized
#### Sample call
    curl -s http://localhost:8080/votingsystem/api/v1/profile --user user@yandex.ru:password



## <a name="updateAuthUser">Update the authenticated user</a>
Updates profile information of the authenticated user (require basic auth).
#### HTTP request
    PUT /api/v1/profile
#### Parameters
    No parameters.
#### Request body
    {
      "id": 1,
      "name": "Updated",
      "email": "updated@yandex.ru",
      "password": "updated_password"
    }
In the JSON request body, include the following object properties:
- **id** | number  
The ID of the user to update (or authenticated user ID).
- **name** | string | **required**  
The user name.
- **email** | string | **required**  
The user e-mail.  
Must be unique.
- **password** | string | **required**  
The user password.  
Minimum length: 5.  
Maximum length: 70.
#### Success response
    Status: 200 OK
#### Error response
Status: 401 Unauthorized

Status: 409 Conflict.  
Returns an Error JSON object ([see here](#errors)).  

Status: 422 Unprocessable Entity.  
Returns an Error JSON object ([see here](#errors)).
#### Sample call
    curl -s -X PUT http://localhost:8080/votingsystem/api/v1/profile --user user@yandex.ru:password \
    -H "Content-Type:application/json;charset=UTF-8" \
    -d '{
          "id": 1,
          "name": "Updated",
          "email": "updated@yandex.ru",
          "password": "updated_password"
        }'



## <a name="deleteAuthUser">Delete the authenticated user</a>
Deletes the authenticated user profile (require basic auth).
#### HTTP request
    DELETE /api/v1/profile
#### Parameters
    No parameters.
#### Request body
    Do not supply a request body with this method.
#### Success response
    Status: 200 OK
#### Error response
Status: 401 Unauthorized
#### Sample call
    curl -s -X DELETE http://localhost:8080/votingsystem/api/v1/profile --user user@yandex.ru:password



## <a name="getAllUsers">Get all users</a>
Lists all users  when authenticated through basic auth and authorized as an admin.
#### HTTP request
    GET /api/v1/admin/users
#### Parameters
    No parameters.
#### Request body
    Do not supply a request body with this method.
#### Success response
    Status: 200 OK
        
    [
      {
        "id": 1,
        "name": "User",
        "email": "user@yandex.ru",
        "password": "$2a$10$Sh0ZD2NFrzRRJJEKEWn8l.92ROEuzlVyzB9SV1AM8fdluPR0aC1ni",
        "roles": ["ROLE_USER"],
        "registered": "2017-05-25T11:26:08.899+0000",
        "enabled": true
        ]
      },
      {
        "id": 2,
        "name": "Admin",
        "email": "admin@gmail.com",
        "password": "$2a$10$WejOLxVuXRpOgr4IlzQJ.eT4UcukNqHlAiOVZj1P/nmc8WbpMkiju",
         "roles":       [
           "ROLE_ADMIN",
           "ROLE_USER"
        ],
        "registered": "2017-05-25T11:26:08.899+0000",
        "enabled": true
      },
      {
        "id": 3,
        "name": "User2",
        "email": "user2@gmail.com",
        "password": "$2a$10$n5P60SQcI85qU3RRHkR4EOKgQN9Ld2mfGiSKj2q.1sXSN1nYqnkzm",
        "roles": ["ROLE_USER"],
        "registered": "2017-05-25T11:26:08.900+0000",
        "enabled": true
         }
    ]
Returns an array of user objects in JSON format:
- **id** | number  
The ID of the user.  
- **name** | string  
The user name.  
- **email** | string  
The user e-mail (unique).  
- **password** | string  
The encoded user password.
- **roles** | array of enums  
An array of user roles.  
- **registered** | string  
The date and time when the user was created, in ISO 8601 format.
- **enabled** | boolean  
The user status, user may be enabled (true) or disabled (false).

Possible values:  
`["ROLE_USER"]` - is the regular user.  
`["ROLE_ADMIN", "ROLE_USER"]` - is admin.
#### Error response
Status: 401 Unauthorized
#### Sample call
    curl -s http://localhost:8080/votingsystem/api/v1/admin/users --user admin@gmail.com:admin



## <a name="getUserById">Get a single user by id</a>
Shows details for a user, by ID. Requires authentication through basic auth and authorization as an admin.
#### HTTP request
    GET /api/v1/admin/users/:id
#### Parameters
Path parameters:
- **id** | string  
The ID of the user to show.
#### Request body
    Do not supply a request body with this method.
#### Success response
    Status: 200 OK
        
    {
      "id": 1,
         "name": "User",
         "email": "user@yandex.ru",
         "password": "$2a$10$Sh0ZD2NFrzRRJJEKEWn8l.92ROEuzlVyzB9SV1AM8fdluPR0aC1ni",
         "roles": ["ROLE_USER"],
         "registered": "2017-05-25T11:26:08.899+0000",
         "enabled": true
    }
Returns a user object in JSON format:
- **id** | number  
The ID of the user.  
- **name** | string  
The user name.  
- **email** | string  
The user e-mail (unique).  
- **password** | string  
The encoded user password.
- **roles** | array of enums  
An array of user roles.
- **registered** | string  
The date and time when the user was created, in ISO 8601 format.
- **enabled** | boolean  
The user status, user may be enabled (true) or disabled (false).

Possible values:  
`["ROLE_USER"]` - is the regular user.  
`["ROLE_ADMIN", "ROLE_USER"]` - is admin.
#### Error response
Status: 401 Unauthorized

Status: 422 Unprocessable Entity.  
Returns an Error JSON object ([see here](#errors)).
#### Sample call
    curl -s http://localhost:8080/votingsystem/api/v1/admin/users/1 --user admin@gmail.com:admin



## <a name="getUserByEmail">Get a single user by e-mail</a>
Shows details for a user, by e-mail. Requires authentication through basic auth and authorization as an admin.
#### HTTP request
    GET /api/v1/admin/users/by
#### Parameters
Query parameters:
- **email** | string | **required**  
The e-mail of the user to show.
#### Request body
    Do not supply a request body with this method.
#### Success response
    Status: 200 OK
        
    {
      "id": 1,
      "name": "User",
      "email": "user@yandex.ru",
      "password": "$2a$10$Sh0ZD2NFrzRRJJEKEWn8l.92ROEuzlVyzB9SV1AM8fdluPR0aC1ni",
      "roles": ["ROLE_USER"],
      "registered": "2017-05-25T11:26:08.899+0000",
      "enabled": true
    }
Returns a user object in JSON format:
- **id** | number  
The ID of the user.  
- **name** | string  
The user name.  
- **email** | string  
The user e-mail (unique).  
- **password** | string  
The encoded user password.
- **roles** | array of enums  
An array of user roles.  
- **registered** | string  
The date and time when the user was created, in ISO 8601 format.
- **enabled** | boolean  
The user status, user may be enabled (true) or disabled (false).


Possible values:  
`["ROLE_USER"]` - is the regular user.  
`["ROLE_ADMIN", "ROLE_USER"]` - is admin.
#### Error response
Status: 401 Unauthorized

Status: 422 Unprocessable Entity.  
Returns an Error JSON object ([see here](#errors)).
#### Sample call
    curl -s http://localhost:8080/votingsystem/api/v1/admin/users/by?email=user@yandex.ru \
    --user admin@gmail.com:admin



## <a name="createUser">Create a new user by admin</a>
Creates a new user by admin (requires authentication through basic auth and authorization as an admin).
#### HTTP request
    POST /api/v1/admin/users
#### Parameters
    No parameters.
#### Request body
    {
      "name": "newName",
      "email": "newMail@gmail.com",
      "password": "newPass",
      "roles": [
              "ROLE_USER",
              "ROLE_ADMIN"
            ],
      "enabled": true
    }
In the JSON request body, include the following object properties:
- **name** | string | **required**  
The user name.
- **email** | string | **required**  
The user e-mail.  
Must be unique.
- **password** | string | **required**  
The user password.  
Minimum length: 5.  
Maximum length: 70.
- **roles** | array of enums | **required**  
An array of user roles.  
Possible values:  
`["ROLE_USER"]` - is the regular user.  
`["ROLE_ADMIN", "ROLE_USER"]` - is admin.
- **enabled** | boolean  
The user status, user may be enabled (true) or disabled (false).  
Default: true.

#### Success response
    Status: 201 Created
        
    {
      "id": 15,
      "name": "newName",
      "email": "newmail@gmail.com",
      "password": "$2a$10$D3dsqwULGYK0oSUskxNmWOXsxndYX6F43Ys7RwclIODVyGGOQPQHK",
      "roles":    [
        "ROLE_ADMIN",
        "ROLE_USER"
      ],
      "registered": "2017-04-17T21:15:23.526+0000",
      "enabled": true
    }
Returns a user object in JSON format:
- **id** | number  
The ID of the user.  
- **name** | string  
The user name.  
- **email** | string  
The user e-mail (unique).  
- **password** | string  
The encoded user password.
- **roles** | array of enums  
An array of user roles.  
Possible values:  
`["ROLE_USER"]` - is the regular user.  
`["ROLE_ADMIN", "ROLE_USER"]` - is admin.
- **registered** | string  
The date and time when the user was created, in ISO 8601 format.
- **enabled** | boolean  
The user status, user may be enabled (true) or disabled (false).

#### Error response
Status: 401 Unauthorized

Status: 409 Conflict.  
Returns an Error JSON object ([see here](#errors)).  

Status: 422 Unprocessable Entity.  
Returns an Error JSON object ([see here](#errors)).
#### Sample call
    curl -s -X POST http://localhost:8080/votingsystem/api/v1/admin/users --user admin@gmail.com:admin \
    -H "Content-Type:application/json;charset=UTF-8" \
    -d '{
          "name": "newName",
          "email": "newMail@gmail.com",
          "password": "newPass",
          "roles": [
             "ROLE_USER",
             "ROLE_ADMIN"
          ],
          "enabled": true
        }'



## <a name="updateUser">Update the user by admin</a>
Updates the user details by admin (requires authentication through basic auth and authorization as an admin).
#### HTTP request
    PUT /api/v1/admin/users/:id
#### Parameters
Path parameters:
- **id** | string  
The ID of the user to update.
#### Request body
    {
      "id": 1,
      "name": "Updated name",
      "email": "updated@yandex.ru",
      "password": "updated_pass",
      "roles": ["ROLE_USER"],
      "enabled": true
    }
In the JSON request body, include the following object properties:
- **id** | number  
The ID of the user to update.
- **name** | string | **required**  
The user name.
- **email** | string | **required**  
The user e-mail.  
Must be unique.
- **password** | string | **required**  
The user password.  
Minimum length: 5.  
Maximum length: 70.
- **roles** | array of enums | **required**  
An array of user roles.  
Possible values:  
`["ROLE_USER"]` - is the regular user.  
`["ROLE_ADMIN", "ROLE_USER"]` - is admin.
- **enabled** | boolean  
The user status, user may be enabled (true) or disabled (false).

#### Success response
    Status: 200 OK
#### Error response
Status: 401 Unauthorized

Status: 409 Conflict.  
Returns an Error JSON object ([see here](#errors)).  

Status: 422 Unprocessable Entity.  
Returns an Error JSON object ([see here](#errors)).
#### Sample call
    curl -s -X PUT http://localhost:8080/votingsystem/api/v1/admin/users/1 --user admin@gmail.com:admin \
    -H "Content-Type:application/json;charset=UTF-8" \
    -d '{
           "id": 1,
           "name": "Updated name",
           "email": "updated@yandex.ru",
           "password": "updated_pass",
           "roles": [ "ROLE_USER" ],
           "enabled": true
        }'


## <a name="enableUser">Enable/disable the user by admin</a>
Enables and disables the user by admin (requires authentication through basic auth and authorization as an admin).
#### HTTP request
    PATCH /api/v1/admin/users/:id
#### Parameters
Path parameters:
- **id** | string  
The ID of the user to enable/disable.

Query parameters:
- **enabled** | boolean | **required**  
Possible values:  
`true` - enable user;  
`false` - disable user.
#### Request body
    Do not supply a request body with this method.
#### Success response
    Status: 200 OK
#### Error response
Status: 401 Unauthorized

Status: 422 Unprocessable Entity.  
Returns an Error JSON object ([see here](#errors)).
#### Sample call
    curl -s -X PATCH http://localhost:8080/votingsystem/api/v1/admin/users/1?enabled=false \
    --user admin@gmail.com:admin



## <a name="deleteUser">Delete the user by admin</a>
Deletes the user by admin (requires authentication through basic auth and authorization as an admin).
#### HTTP request
    DELETE /api/v1/admin/users/:id
#### Parameters
Path parameters:
- **id** | string  
The ID of the user to delete.
#### Request body
    Do not supply a request body with this method.
#### Success response
    Status: 200 OK
#### Error response
Status: 401 Unauthorized

Status: 422 Unprocessable Entity.  
Returns an Error JSON object ([see here](#errors)).
#### Sample call
    curl -s -X DELETE http://localhost:8080/votingsystem/api/v1/admin/users/1 \
    --user admin@gmail.com:admin



# <a name="restaurants">2. Restaurants</a>



## <a name="getAllRestaurants">Get all restaurants</a>
Lists all restaurants sorted by name with their menus, dishes in menus are also sorted by name
(required authentication through basic auth and authorization as a user).
#### HTTP request
    GET /api/v1/restaurants
#### Parameters
    No parameters.
#### Request body
    Do not supply a request body with this method.
#### Success response
    Status: 200 OK
        
    [
      {
      "id": 6,
      "name": "На парах"
     },
      {
      "id": 4,
      "name": "Жрем днем"
     },
      {
      "id": 5,
      "name": "Хочу харчо"
     }
    ]
Returns an array of restaurant objects with menus in JSON format:
- **id** | number  
The ID of the restaurant.  
- **name** | string  
The restaurant name.  

#### Error response
Status: 401 Unauthorized
#### Sample call
    curl -s http://localhost:8080/votingsystem/api/v1/restaurants --user user@yandex.ru:password



## <a name="getRestaurantById">Get a single restaurant by id</a>
Shows details for the restaurant, by ID.
#### HTTP request
    GET /api/v1/restaurants/:id
#### Parameters
Path parameters:
- **id** | string  
The ID of the restaurant to show.
#### Request body
    Do not supply a request body with this method.
#### Success response
    Status: 200 OK
        
    {
      "id": 6,
      "name": "На парах",
    }
Returns a restaurant object in JSON format:
- **id** | number  
The ID of the restaurant.  
- **name** | string  
The restaurant name.  

#### Error response
Status: 401 Unauthorized

Status: 422 Unprocessable Entity.  
Returns an Error JSON object ([see here](#errors)).
#### Sample call
    curl -s http://localhost:8080/votingsystem/api/v1/restaurants/6 --user user@yandex.ru:password




## <a name="createRestaurant">Create a new restaurant</a>
Creates a new restaurant by admin (requires authentication through basic auth and authorization as an admin).
#### HTTP request
    POST /api/v1/restaurants
#### Parameters
    No parameters.
#### Request body
    {
      "name": "new restaurant"
    }
In the JSON request body, include the following object properties:
- **name** | string | **required**  
The name of the restaurant.
 
#### Success response
    Status: 201 Created
        
    {
      "id": 15,
      "name": "new restaurant"
    }
Returns a restaurant object in JSON format:
- **id** | number  
The ID of the restaurant.  
- **name** | string  
The restaurant name.  

#### Error response
Status: 401 Unauthorized

Status: 409 Conflict.  
Returns an Error JSON object ([see here](#errors)).  

Status: 422 Unprocessable Entity.  
Returns an Error JSON object ([see here](#errors)).
#### Sample call
    curl -s -X POST http://localhost:8080/votingsystem/api/v1/restaurants --user admin@gmail.com:admin \
    -H "Content-Type:application/json;charset=UTF-8" \
    -d '{
          "name": "new restaurant"
        }'



## <a name="updateRestaurant">Update restaurant</a>
Updates the restaurant details by admin (requires authentication through basic auth and authorization as an admin).
#### HTTP request
    PUT /api/v1/restaurants/:id
#### Parameters
Path parameters:
- **id** | string  
The ID of the restaurant to update.
#### Request body
    {
      "id": 6,
      "name": "Updated restaurant"
    }
In the JSON request body, include the following object properties:
- **id** | number  
The ID of the user to update.
- **name** | string | **required**  
The name of the restaurant.

#### Success response
    Status: 200 OK
#### Error response
Status: 401 Unauthorized

Status: 409 Conflict.  
Returns an Error JSON object ([see here](#errors)).  

Status: 422 Unprocessable Entity.  
Returns an Error JSON object ([see here](#errors)).
#### Sample call
    curl -s -X PUT http://localhost:8080/votingsystem/api/v1/restaurants/6 --user admin@gmail.com:admin \
    -H "Content-Type:application/json;charset=UTF-8" \
    -d '{
          "id": 6,
          "name": "Updated restaurant"
        }'



## <a name="deleteRestaurant">Delete restaurant</a>
Deletes the restaurant, by ID (requires authentication through basic auth and authorization as an admin).
#### HTTP request
    DELETE /api/v1/restaurants/:id
#### Parameters
Path parameters:
- **id** | string  
The ID of the restaurant to delete.
#### Request body
    Do not supply a request body with this method.
#### Success response
    Status: 200 OK
#### Error response
Status: 401 Unauthorized

Status: 422 Unprocessable Entity.  
Returns an Error JSON object ([see here](#errors)).
#### Sample call
    curl -s -X DELETE http://localhost:8080/votingsystem/api/v1/restaurants/6 \
    --user admin@gmail.com:admin



# <a name="menu">3. Menu</a>


## <a name="getAll">Get all</a>
Shows a list of dishes (menu) for the restaurant, by the restaurant ID. Dishes in the menu are sorted by their name. Requires authentication through basic auth and authorization as an admin.

#### HTTP request
    GET /api/v1/restaurants/:id/dishes/all
#### Parameters
Path parameters:
- **id** | string  
The ID of the restaurant.
#### Request body
    Do not supply a request body with this method.
#### Success response
    Status: 200 OK
        
    [
      {
      "id": 9,
      "name": "Супец",
      "price": 350,
      "menudate": "2017-05-25"
     },
      {
      "id": 10,
      "name": "Холодец",
      "price": 400,
      "menudate": "2017-05-24"
     }
    ]
Returns an array of the dish objects in JSON format:
- **id** | number  
The ID of the dish in menu.  
- **name** | string  
The name of the dish in menu.  
- **price** | number  
The price of the dish in menu.
- **menudate** | number  
Actual date of menu.
#### Error response
Status: 401 Unauthorized
#### Sample call
    curl -s http://localhost:8080/votingsystem/api/v1/restaurants/5/dishes/all --user admin@gmail.com:admin



## <a name="getMenu">Get menu</a>
Shows a list of dishes (menu) for the restaurant, by the restaurant ID and current day. Dishes in the menu are sorted by their name.  

#### HTTP request
    GET /api/v1/restaurants/:id/dishes
#### Parameters
Path parameters:
- **id** | string  
The ID of the restaurant.
#### Request body
    Do not supply a request body with this method.
#### Success response
    Status: 200 OK
        
    [
      {
      "id": 9,
      "name": "Супец",
      "price": 350,
      "menudate": "2017-05-25"
     }
    ]
Returns an array of the dish objects in JSON format:
- **id** | number  
The ID of the dish in menu.  
- **name** | string  
The name of the dish in menu.  
- **price** | number  
The price of the dish in menu.
- **menudate** | number  
Actual date of menu.
#### Error response
Status: 401 Unauthorized
#### Sample call
    curl -s http://localhost:8080/votingsystem/api/v1/restaurants/5/dishes --user user@yandex.ru:password



## <a name="getDish">Get a single dish from the menu</a>
Shows details for the dish, by ID. Requires authentication through basic auth and authorization as an admin.
#### HTTP request
    GET /api/v1/restaurants/:restaurantID/dishes/:id
#### Parameters
Path parameters:
- **restaurantID** | string  
The ID of the restaurant.
- **id** | string  
The ID of the dish to show.
#### Request body
    Do not supply a request body with this method.
#### Success response
    Status: 200 OK
        
    {
      "id": 9,
      "name": "Супец",
      "price": 350,
      "menudate": "2017-05-25"
    }
Returns the dish object in JSON format:
- **id** | number  
The ID of the dish.  
- **name** | string  
The name of the dish.  
- **price** | number  
The price of the dish.
- **menudate** | number  
Actual date of menu.
#### Error response
Status: 401 Unauthorized

Status: 422 Unprocessable Entity.  
Returns an Error JSON object ([see here](#errors)).
#### Sample call
    curl -s http://localhost:8080/votingsystem/api/v1/restaurants/5/dishes/9 \
    --user admin@gmail.com:admin



## <a name="createDish">Create a new dish in the menu</a>
Adds a new dish to the menu of the restaurant (requires authentication through basic auth and
authorization as an admin).
#### HTTP request
    POST /api/v1/restaurants/:id/dishes
#### Parameters
Path parameters:
- **id** | string  
The ID of the restaurant.
#### Request body
    {
      "name": "newDish",
      "price": 999
    }
In the JSON request body, include the following object properties:
- **name** | string | **required**  
The name of the dish.
- **price** | number | **required**  
The price of the dish.  
Minimum value: 0.
#### Success response
    Status: 201 Created
        
    {
      "id": 16,
      "name": "newDish",
      "price": 999,
      "menudate": "2017-05-25"
    }
Returns the dish object in JSON format:
- **id** | number  
The ID of the dish.  
- **name** | string  
The name of the dish.  
- **price** | number  
The price of the dish.
- **menudate** | number  
Actual date of menu.
#### Error response
Status: 401 Unauthorized

Status: 422 Unprocessable Entity.  
Returns an Error JSON object ([see here](#errors)).
#### Sample call
    curl -s -X POST http://localhost:8080/votingsystem/api/v1/restaurants/5/dishes \
    --user admin@gmail.com:admin \
    -H "Content-Type:application/json;charset=UTF-8" \
    -d '{
          "name": "neDish",
          "price": 999
        }'



## <a name="updateDish">Update the dish in the menu</a>
Updates the dish details, by ID (requires authentication through basic auth and authorization as an admin).
#### HTTP request
    PUT /api/v1/restaurants/:restaurantID/dishes/:id
#### Parameters
Path parameters:
- **restaurantID** | string  
The ID of the restaurant.
- **id** | string  
The ID of the dish to update.
#### Request body
    {
      "id": 10,
      "name": "Updated dish",
      "price": 10
    }
In the JSON request body, include the following object properties:
- **id** | number  
The ID of the dish to update.
- **name** | string | **required**  
The name of the dish.
- **price** | number | **required**  
The price of the dish.  
Minimum value: 0.
#### Success response
    Status: 200 OK
#### Error response
Status: 401 Unauthorized

Status: 422 Unprocessable Entity.  
Returns an Error JSON object ([see here](#errors)).
#### Sample call
    curl -s -X PUT http://localhost:8080/votingsystem/api/v1/restaurants/5/dishes/10 \
    --user admin@gmail.com:admin \
    -H "Content-Type:application/json;charset=UTF-8" \
    -d '{
          "id": 10,
          "name": "Updated dish",
          "price": 10
        }'



## <a name="deleteMenu">Delete menu</a>
Deletes the menu (a list of dishes) of the restaurant, by the restaurant ID
(requires authentication through basic auth and authorization as an admin).
#### HTTP request
    DELETE /api/v1/restaurants/:id/dishes
#### Parameters
Path parameters:
- **id** | string  
The ID of the restaurant.
#### Request body
    Do not supply a request body with this method.
#### Success response
    Status: 200 OK
#### Error response
Status: 401 Unauthorized

Status: 422 Unprocessable Entity.  
Returns an Error JSON object ([see here](#errors)).
#### Sample call
    curl -s -X DELETE http://localhost:8080/votingsystem/api/v1/restaurants/5/dishes \
    --user admin@gmail.com:admin



## <a name="deleteDish">Delete a single dish from the menu</a>
Deletes a single dish from the menu, by ID (requires authentication through basic auth and authorization as an admin).
#### HTTP request
    DELETE /api/v1/restaurants/:restaurantID/dishes/:id
#### Parameters
Path parameters:
- **restaurantID** | string  
The ID of the restaurant.
- **id** | string  
The ID of the dish to delete.
#### Request body
    Do not supply a request body with this method.
#### Success response
    Status: 200 OK
#### Error response
Status: 401 Unauthorized

Status: 422 Unprocessable Entity.  
Returns an Error JSON object ([see here](#errors)).
#### Sample call
    curl -s -X DELETE http://localhost:8080/votingsystem/api/v1/restaurants/5/dishes/9 \
    --user admin@gmail.com:admin



# <a name="vote">4. Vote</a>



## <a name="voteForRestaurant">Vote for the restaurant</a>
Votes for the restaurant.
  
If the user votes again the same day:  
- If it is before 11:00 we assume that he changed his mind.  
- If it is after 11:00 then it is too late, vote can't be changed.
#### HTTP request
    POST /api/v1/restaurants/:id/votes
#### Parameters
Path parameters:
- **id** | string  
The ID of the restaurant.
#### Request body
    Do not supply a request body with this method.
#### Success response
    Status: 200 OK
#### Error response
Status: 401 Unauthorized

Status: 403 Forbidden.  
Returns an Error JSON object ([see here](#errors)).

Status: 422 Unprocessable Entity.  
Returns an Error JSON object ([see here](#errors)).
#### Sample call
    curl -s -X POST http://localhost:8080/votingsystem/api/v1/restaurants/4 \
    --user user@yandex.ru:password



## <a name="getCurrentVotes">Get current vote results</a>
Lists current vote results.
#### HTTP request
    GET /api/v1/restaurants/:id/votes
#### Parameters
Path parameters:
- **id** | string  
The ID of the restaurant.
#### Request body
    Do not supply a request body with this method.
#### Success response
    Status: 200 OK
        
    [
      {
         "restaurantName": "Хочу харчо",
         "voteCount": 2,
         "voteDate": "2017-05-25"
      }
    ]
Returns an array of the votes result objects in JSON format:
- **restaurantName** | string  
The name of the restaurant.  
- **votes** | number  
The number of votes for the restaurant.  
- **date** | string  
The date of the vote (in ISO 8601 format).
#### Error response
Status: 401 Unauthorized
#### Sample call
    curl -s http://localhost:8080/votingsystem/api/v1/restaurants/5/votes --user user@yandex.ru:password



# <a name="errors">5. Errors</a>
This API returns standard HTTP status codes for error responses.  

The response body for all errors except Identity errors includes additional error details in this format:

    {
      "url": "http://localhost:8080/votingSystem/api/v1/admin/users",
      "cause": "ValidationException",
      "details": [
        "password size must be between 5 and 70",
        "name may not be empty",
        "email not a well-formed email address"
      ]
    }
Where:  
- **url** | stirng  
Error documentation link.  
- **cause** | string  
Error name.  
- **details** | array of strings  
Error detailed description.