# Hospital-management
Hospital management

### Tech stack used and needed to run the project
- Postgres
- Java8
- Redis
  

### To start project first create two tables in DB postgres
- Load all the microservices in intellij
- Create the below table in postgresql
  
-- Table Definition
CREATE TABLE "public"."users" (
    "id" varchar NOT NULL,
    "address" varchar,
    "age" int4,
    "city" varchar,
    "created_date" timestamp,
    "email" varchar,
    "is_active" bool,
    "modified_date" timestamp,
    "name" varchar,
    "password" varchar,
    "role_id" int8,
    "state" varchar,
    "user_id" varchar,
    PRIMARY KEY ("id")
);

-- Table Definition
CREATE TABLE "public"."role" (
    "role_id" int8 NOT NULL,
    "created_date" timestamp,
    "modified_date" timestamp,
    "role_name" varchar,
    PRIMARY KEY ("role_id")
);

-- Table Definition
CREATE TABLE "public"."patient_entity" (
    "id" int8 NOT NULL,
    "admit_date" timestamp,
    "age" int8,
    "creation_date" timestamp,
    "doctor_id" varchar,
    "expenses" float4,
    "is_discharged" bool,
    "name" varchar,
    "room_no" int8,
    PRIMARY KEY ("id")
);

- Execute below query to add the basic roles like 'STAFF' AND 'DOCTOR'
    - insert into role(role_id, created_date, modified_date, role_name) values (1, CURRENT_DATE, CURRENT_DATE, 'STAFF');
    - insert into role(role_id, created_date, modified_date, role_name) values (2, CURRENT_DATE, CURRENT_DATE, 'DOCTOR');
 
### Design considerations 
- Only staff members can add the patients , discharge them , fetch the list 
- List is fetched in paginated manner to avoid unnecessary load
- JWT token authentication
- Once staff member login token is saved in redis to maintain the latest token assigned to that staff member and if someone uses old token to fetch the data system will not allow it
- Complete system is divided into 3 microservice
    - AuthenticationLib for validation of access to api's
    - UserManagement for managing the uses in the system
    - PatientManagement for managing the patient
 
### Steps to run 
- Install redis and run it on localhost 6379
- Install postgres and create table using above mentioned commands
- Run the usermanagement application (developed using intellij editor)
- Run the patientmanagement application (developed using intellij editor)

### Curls 
- To add new user
- curl --location --request POST 'http://localhost:8081/user/add' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkZWVwYW5zaHUiLCJyb2xlIjpbeyJhdXRob3JpdHkiOiJBZG1pbiJ9XSwiZXhwIjoxNjg4ODE0OTQyLCJpYXQiOjE2ODg3OTY5NDIsInVzZXJuYW1lIjoiZGVlcGFuc2h1In0.raAE2BFHcTvvqJ6qZA2YCx6PVejlCK-kQ1-qocJwSj3QZpWYuxqoYvDXklD5K866sQrU3g5IzqPvBuo2JiVPgA' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=DE6F440AB40B5BFBB08D0E2196FE3DCC' \
--data-raw '{
    "address" : "Delhi 121 Delhi",
    "email" : "deepanshugarg2812@gmail2.com",
    "city" : "Delhi",
    "name" : "Deepanshu Garg",
    "password" : "password",
    "role" : "Doctor",
    "username" : "Deepanshu2",
    "state" : "Delhi",
    "age" : 23
  }'

- To Login
  curl --location --request POST 'http://localhost:8081/login' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=5C4D7BCC800E2FD7F970465D9DF1FBCA' \
--data-raw '{
    "userName" : "Deepanshu",
    "password" : "password"

}'

-To Add patient
curl --location --request POST 'http://localhost:8082/patient/add' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjoiU1RBRkYiLCJ1c2VySWQiOiJkZjA3YjYxYS05NDJjLTQwNzktOGFiMy00ZDk3ZTc1ZDI0ZWQiLCJpYXQiOjE3MDE1MTU2ODl9.be5TF2rIt06lWaIsC_XjAsJQPR1rBnE6EApUa3uXYDi7XWn3s51kn2gpijLlzbSDrjI0NCEGwkx6JJSyrf84NA' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=DE6F440AB40B5BFBB08D0E2196FE3DCC' \
--data-raw '{
    "name" : "abc",
    "age" : 20,
    "expenses" : 1000,
    "admitDate" : "2023-12-23",
    "doctorId" : "f5fce224-f334-424c-a6c3-66cfaa16ea83",
    "roomNo" : 101
}'

- To Fetch all patients
curl --location --request GET 'http://localhost:8082/patient/find?page=0&size=2' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjoiU1RBRkYiLCJ1c2VySWQiOiJkZjA3YjYxYS05NDJjLTQwNzktOGFiMy00ZDk3ZTc1ZDI0ZWQiLCJpYXQiOjE3MDE1MTU2ODl9.be5TF2rIt06lWaIsC_XjAsJQPR1rBnE6EApUa3uXYDi7XWn3s51kn2gpijLlzbSDrjI0NCEGwkx6JJSyrf84NA' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=5C4D7BCC800E2FD7F970465D9DF1FBCA' \
--data-raw '{
    "name" : "abc",
    "age" : 20,
    "expenses" : 1000,
    "admitDate" : "2023-12-23",
    "doctorId" : "f5fce224-f334-424c-a6c3-66cfaa16ea83",
    "roomNo" : 101
}'

- To discharge a patient
curl --location --request GET 'http://localhost:8082/patient/discharge?id=100' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjoiU1RBRkYiLCJ1c2VySWQiOiJkZjA3YjYxYS05NDJjLTQwNzktOGFiMy00ZDk3ZTc1ZDI0ZWQiLCJpYXQiOjE3MDE1MDQxMjB9.xNjFNMm8eBWnaTDXZYlKFcIUUpbI0j3sUDfLrvO4dyx3s6ksU6LUEdBVgTZr3HtfwjmxzB55DEau-KaSUyxIiA' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=5C4D7BCC800E2FD7F970465D9DF1FBCA' \
--data-raw '{
    "name" : "abc",
    "age" : 20,
    "expenses" : 1000,
    "admitDate" : "2023-12-23",
    "doctorId" : "f5fce224-f334-424c-a6c3-66cfaa16ea83",
    "roomNo" : 101
}'
