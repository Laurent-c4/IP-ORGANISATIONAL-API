package dao;

import models.Department;
import models.NewsForDepartment;
import models.User;

import java.util.List;

public interface DepartmentDao {
    //CREATE
    void add(Department department);

    //READ
    Department findById(int id);




    //UPDATE

    //DELETE
    void clearAll();

}
