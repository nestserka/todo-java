# ToDo App

Todo application. 
Tech: Java, JavaScript, FreeMarker, PostgreSQL

IMPORTANT: For this application tasks features were slightly revised. The order of the tasks is based on id (desc order).

## Installation

Todo App uses PostgreSQL v16. 

Install the dependencies if you do not have it running in docker through:

```sh
docker run -d \
  --name my-postgres-container \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres\
  -e POSTGRES_DB=mydatabase \
  -p 5432:5432 \
  Postgres:16
```
If you already have running  db, make sure to change hibernate.cfg.xml file with your password and link. If any changes occur, do not forget to build maven war:

```sh
 <property name="hibernate.connection.url">jdbc:postgresql://my-postgres-container:5432/todo</property>
 ```
 
Check if db is running, executing

```sh
docker ps
```

Create todo db and table task. 
IMPORTANT! For status use "SMALLINT"

```sh
CREATE DATABASE todo;

DO $$ 
BEGIN 
  IF NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'todo') THEN 
    CREATE DATABASE todo; 
  END IF; 
END $$;

CREATE TABLE task (
  id SERIAL PRIMARY KEY,
  description VARCHAR(100) NOT NULL,
  status SMALLINT NOT NULL
);
 INSERT INTO task (description, status) VALUES 
  ('aaa', 1), 
  ('bbb', 2), 
  ('ccc', 0), 
  ('ddd', 1), 
  ('eee', 2), 
  ('fff', 0), 
  ('ggg', 1), 
  ('hhh', 2), 
  ('jjj', 0), 
  ('kkk', 1), 
  ('lll', 2), 
  ('mmm', 0), 
  ('nnn', 1), 
  ('ooo', 2), 
  ('ppp', 0);
```
Create image for the application:

```sh
docker build -t todo-java-app-image .
```
Create docker container and run through port, do not forget to link to db:
```sh
docker run -d --name todo-java-app-container -p 8080:8080 --link my-postgres-container:db todo-java-app-image
```

if you run application correctly, application should be accessed through:
```sh
http://localhost:8080/root/todo
```
You must see the following:

<img width="993" alt="Screenshot 2024-02-03 at 11 45 37 AM" src="https://github.com/nestserka/todo-java/assets/78704791/b41ec88f-23a1-4131-a693-950e2e520829">
