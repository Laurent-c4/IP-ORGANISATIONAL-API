package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    //Helper
    public User setupNewUser(){
        return new User("c4","CEO","Overseeing Deals",1);
    }

    @Test
    public void User_instantiatesCorrectly() {
        User user= setupNewUser();
        assertTrue(user instanceof User);
    }

    @Test
    public void User_instantiatesWithName() {
        User user=setupNewUser();
        assertEquals("c4", user.getName());
    }
    @Test
    public void User_instantiatesWithPosition() {
        User user=setupNewUser();
        assertEquals("CEO", user.getPosition());
    }

    @Test
    public void User_instantiatesWithRole() {
        User user=setupNewUser();
        assertEquals("Overseeing Deals", user.getRole());
    }

    @Test
    public void User_instantiatesWithDepartmentId() {
        User user=setupNewUser();
        assertEquals(1, user.getDepartmentId());
    }
}