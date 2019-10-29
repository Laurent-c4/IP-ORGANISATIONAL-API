
import com.google.gson.Gson;
import dao.*;
import models.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import spark.Request;

import static spark.Spark.*;

public class App {

    public static void main(String[] args) {
        Sql2oUserDao userDao;
        Sql2oNewsDao newsDao;
        Sql2oNewsForDepartmentDao newsForDepartmentDao;
        Sql2oDepartmentDao departmentDao;
        Connection conn;
        Gson gson = new Gson();

        String connectionString = "jdbc:postgresql://localhost:5432/organisational_api";
        Sql2o sql2o = new Sql2o(connectionString, "laurent", "laurent");

        userDao = new Sql2oUserDao(sql2o);
        newsDao = new Sql2oNewsDao(sql2o);
        newsForDepartmentDao = new Sql2oNewsForDepartmentDao(sql2o);
        departmentDao =new Sql2oDepartmentDao(sql2o);
        conn = sql2o.open();

        //CREATE
        //department
        post("/departments/new", "application/json", (req, res) -> {
            Department department = gson.fromJson(req.body(), Department.class);
            departmentDao.add(department);
            res.status(201);
            res.type("application/json");
            return gson.toJson(department);
        });

        //news for all departments
        post("/news/general/new", "application/json", (req, res) -> {
            News news = gson.fromJson(req.body(), News.class);
            news.setType("general");
            newsDao.add(news);
            res.status(201);
            res.type("application/json");
            return gson.toJson(news);
        });

        //news for specific department
        post("/news/department/new", "application/json", (req, res) -> {
            NewsForDepartment newsForDepartment = gson.fromJson(req.body(), NewsForDepartment.class);
            newsForDepartment.getNews().setType("department");
            newsForDepartmentDao.add(newsForDepartment);
            res.status(201);
            res.type("application/json");
            return gson.toJson(newsForDepartment);
        });

        //new user
        post("/users/new", "application/json", (req, res) -> {
            User user = gson.fromJson(req.body(), User.class);
            userDao.add(user);
            res.status(201);
            res.type("application/json");
            return gson.toJson(user);
        });


        //READ
        //user details
        get("/users/:id", "application/json", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));
            return gson.toJson(userDao.findById(id));
        });

        //department details
        get("/departments/:id", "application/json", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));
            return gson.toJson(departmentDao.findById(id));
        });

        //users of a particular department
        get("/departments/:id/users", "application/json", (req, res) -> {
            int departmentId = Integer.parseInt(req.params("id"));
            return gson.toJson(userDao.getAllUsersByDepartment(departmentId));
        });

        //news of a particular department
        get("/departments/:id/news", "application/json", (req, res) -> {
            int departmentId = Integer.parseInt(req.params("id"));
            return gson.toJson(newsForDepartmentDao.getAllForADepartment(departmentId));
        });
    }
}
