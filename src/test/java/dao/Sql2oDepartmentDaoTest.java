package dao;

import models.Department;
import models.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Arrays;

import static org.junit.Assert.*;

public class Sql2oDepartmentDaoTest {
    private static Connection conn;
    private static Sql2oDepartmentDao departmentDao;
    private static Sql2oUserDao userDao;

    //Helpers
    public User setupNewUser(){
        User user = new User("c4","CEO","Overseeing Deals",1);
        userDao.add(user);
        return user;
    }

    public Department setupNewDepartment(){
        Department department = new Department("finance","money",0);
        departmentDao.add(department);
        return department;
    }


    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/organisational_api_test";
        Sql2o sql2o = new Sql2o(connectionString, "laurent", "laurent");
        departmentDao = new Sql2oDepartmentDao(sql2o);
        userDao = new Sql2oUserDao(sql2o);
        conn = sql2o.open();
    }

    @After //run after every test
    public void tearDown() throws Exception {
        System.out.println("clearing database");
        departmentDao.clearAll();
    }

    @AfterClass
    public static void shutDown() throws Exception{
        conn.close();
        System.out.println("connection closed");
    }

    @Test
    public void add_AddsDeartmentToDatabase() {
        Department department=setupNewDepartment();
        assertEquals(department,departmentDao.findById(department.getId()));
    }
}