CREATE DATABASE organisational_api;
  \c organisational_api;
  CREATE TABLE users (id SERIAL PRIMARY KEY, name VARCHAR, position VARCHAR, role VARCHAR, departmentid INTEGER);
  CREATE TABLE departments (id SERIAL PRIMARY KEY, name VARCHAR, description VARCHAR, numberofemployees INTEGER);
  CREATE TABLE news (id SERIAL PRIMARY KEY, name VARCHAR, position VARCHAR, role VARCHAR, departmentid INTEGER);
  CREATE DATABASE organisational_api_test WITH TEMPLATE organisational_api;
