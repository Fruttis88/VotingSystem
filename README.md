Vouting Project 
==============================

JSON API using Hibernate/Spring/Spring-MVC without frontend

## Curl commands

For windows use Git Bash


#### Users


##### By Admin

    get all       curl -s http://localhost:8080/grad/api/v1/admin/users  --user admin@gmail.com:admin
    get           curl -s http://localhost:8080/grad/api/v1/admin/users/1  --user admin@gmail.com:admin
    get by email  curl -s http://localhost:8080/grad/api/v1/admin/users/with?email=user@yandex.ru  --user admin@gmail.com:admin
    create        curl -s -X POST -H "Content-Type: application/json" -d '{"name":"newUser", "email": "newMail@gmail.com", "password":"newPassword", "role":"ROLE_USER"}' http://localhost:8080/grad/api/v1/admin/users  --user admin@gmail.com:admin
    update        curl -s -X PUT -H "Content-Type: application/json" -d '{"id":1, "name":"user updated", "password":"password updated"}' http://localhost:8080/grad/api/v1/admin/users/1  --user admin@gmail.com:admin
    delete        curl -s -X DELETE http://localhost:8080/grad/api/v1/admin/users/1  --user admin@gmail.com:admin


##### Any User

    get           curl -s http://localhost:8080/grad/api/v1/profile  --user user@yandex.ru:password
    update        curl -s -X PUT -H "Content-Type: application/json" -d '{"id":1, "name":"user updated", "password":"password updated"}' http://localhost:8080/grad/api/v1/profile  --user user@yandex.ru:password
    delete        curl -s -X DELETE http://localhost:8080/grad/api/v1/profile  --user user@yandex.ru:password


#### Restaurants


##### By Admin

    create        curl -s -X POST -H "Content-Type: application/json" -d '{"name":"new res", "votescount":0}' http://localhost:8080/grad/api/v1/restaurants  --user admin@gmail.com:admin
    update        curl -s -X PUT -H "Content-Type: application/json" -d '{"id":4, "name":"update res", "votescount": "0"}' http://localhost:8080/grad/api/v1/restaurants/4  --user admin@gmail.com:admin
    delete        curl -s -X DELETE http://localhost:8080/grad/api/v1/restaurants/4  --user admin@gmail.com:admin

##### Any User

    get all       curl -s http://localhost:8080/grad/api/v1/restaurants --user user@yandex.ru:password
    get           curl -s http://localhost:8080/grad/api/v1/restaurants/4 --user user@yandex.ru:password


#### Dishes


##### By Admin

    get          curl -s http://localhost:8080/grad/api/v1/restaurants/4/dishes/7  --user admin@gmail.com:admin
    get all      curl -s http://localhost:8080/grad/api/v1/restaurants/4/dishes/all --user admin@gmail.com:admin
    create       curl -s -X POST -H "Content-Type: application/json" -d '{"name":"new dish", "price": "300"}' http://localhost:8080/grad/api/v1/restaurants/4/dishes  --user admin@gmail.com:admin
    update       curl -s -X POST -H "Content-Type: application/json" -d '{"id":7, "name":"update dish", "price": "300"}' http://localhost:8080/grad/api/v1/restaurants/4/dishes/7  --user admin@gmail.com:admin
    delete       curl -s -X DELETE http://localhost:8080/grad/api/v1/restaurants/4/dishes/7  --user admin@gmail.com:admin
    delete menu  curl -s -X DELETE http://localhost:8080/grad/api/v1/restaurants/4/dishes  --user admin@gmail.com:admin

##### Any User

    get menu     curl -s http://localhost:8080/grad/api/v1/restaurants/4/dishes --user user@yandex.ru:password


#### Votes


##### Any User

    create      curl -s -X POST http://localhost:8080/grad/api/v1/restaurants/4/votes --user user@yandex.ru:password