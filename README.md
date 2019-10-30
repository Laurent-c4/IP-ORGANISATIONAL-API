# ORGANISATIONAL API
#### An API for company details
#### By **Laurent Juma**
## Description
This is an API which avails organisational data such as news, users and departments

## Setup/Installation Requirements
* To use this project, clone it.
* Make sure java and postgresql is installed
* Create a postgres database using the commands below, commands are available in create.sql file in root folder of project:
* CREATE DATABASE organisational_api;  
  \c organisational_api;  
  CREATE TABLE users (id SERIAL PRIMARY KEY, name VARCHAR, position VARCHAR, role VARCHAR, departmentid INTEGER);  
  CREATE TABLE departments (id SERIAL PRIMARY KEY, name VARCHAR, description VARCHAR, numberofemployees INTEGER);  
  CREATE TABLE news (id SERIAL PRIMARY KEY, name VARCHAR, position VARCHAR, role VARCHAR, departmentid INTEGER);  
  CREATE DATABASE organisational_api_test WITH TEMPLATE organisational_api;
  
  Change connection credentials in line 28 of App.java according to your postgres username an password  
* Open the project in the IDE, run, then open postman to test the api routes

## Technologies Used
Java
Postgresql

## Support and contact details
Contact +254792599994 for any questions concerning the app. Feel free to give your feedback too.
### License
* MIT License

Copyright (c) 2019 Laurent Juma

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

Copyright (c) 2019 **Laurent Juma**
