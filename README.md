# Hospital-management
Hospital management

### Tech stack used
- Postgres
- Java8

### To start project first create two tables in DB postgres

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

