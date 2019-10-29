package dao;

import models.NewsForDepartment;

import java.util.List;

public interface NewsForDepartmentDao {
    //CREATE
    void add(NewsForDepartment newsForDepartment);

    //READ
    List<NewsForDepartment> getAll();
    NewsForDepartment findById(int id);
    List<NewsForDepartment> getAllForADepartment(int departmentId);

    //UPDATE

    //DELETE

}
