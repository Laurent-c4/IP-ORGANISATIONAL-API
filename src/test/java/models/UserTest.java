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
}