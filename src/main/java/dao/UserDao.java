package dao;

import models.User;

import java.util.List;

public interface UserDao {
    //CREATE
    void add(User user);

    //READ
    User findById(int id);
    List<User> getAll();
    List<User> getAllUsersByDepartment(int departmentId);


    //UPDATE

    //DELETE
    void clearAll();

}
