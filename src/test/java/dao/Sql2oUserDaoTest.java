package dao;

import models.Department;
import models.User;
import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Arrays;

import static org.junit.Assert.*;

public class Sql2oUserDaoTest {

    private static Connection conn;
    private static Sql2oUserDao userDao;
    private static Sql2oDepartmentDao departmentDao;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/organisational_api_test";
        Sql2o sql2o = new Sql2o(connectionString, "laurent", "laurent");
        userDao = new Sql2oUserDao(sql2o);
        departmentDao=new Sql2oDepartmentDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("clearing database");
        userDao.clearAll(); //clear all restaurants after every test
    }

    @AfterClass
    public static void shutDown() throws Exception{
        conn.close();
        System.out.println("connection closed");
    }

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

    @Test
    public void add_CorrectlyAddsUser() {
        User user = setupNewUser();
        assertEquals(1,userDao.getAll().size());
    }

    @Test
    public void findById_correctlyReturnsUser() {
        User user=setupNewUser();
        User otherUser=new User("savage","NA","Overseeing",1);
        userDao.add(otherUser);
        assertEquals(otherUser,userDao.findById(otherUser.getId()));
    }

    @Test
    public void getAllUsersByDepartment_reurnsCorrectly() {
        Department department=setupNewDepartment();
        User user=setupNewUser();
        User otherUser=new User("savage","NA","Overseeing",department.getId());
        userDao.add(otherUser);
        User otherNewUser=new User("savage","NA","Overseeing",department.getId());
        userDao.add(otherNewUser);
        User[] departmentUsers={otherUser,otherNewUser};
        assertEquals(Arrays.asList(departmentUsers),userDao.getAllUsersByDepartment(department.getId()));

    }

}