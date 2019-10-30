
import com.google.gson.Gson;
import dao.*;
import exceptions.ApiException;
import models.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import spark.Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        departmentDao = new Sql2oDepartmentDao(sql2o);
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
            List<Integer> deptIds = new ArrayList<>();
            for (int i = 0; i < departmentDao.getAll().size(); i++) {
                deptIds.add(departmentDao.getAll().get(i).getId());
            }
            if (deptIds.contains(newsForDepartment.getDepartmentId())) {
                newsForDepartment.getNews().setType("department");
                newsForDepartmentDao.add(newsForDepartment);
                res.status(201);
                res.type("application/json");
            } else {
                throw new ApiException(404, String.format("Department of id: \"%s\"  does not exist, please select correct department or add a department if non exists", newsForDepartment.getDepartmentId()));
            }
            return gson.toJson(newsForDepartment);

        });

        //new user
        post("/users/new", "application/json", (req, res) -> {
            User user = gson.fromJson(req.body(), User.class);
            List<Integer> deptIds = new ArrayList<>();
            for (int i = 0; i < departmentDao.getAll().size(); i++) {
                deptIds.add(departmentDao.getAll().get(i).getId());
            }
            if (deptIds.contains(user.getDepartmentId())) {
                userDao.add(user);
                res.status(201);
                res.type("application/json");
            } else {
                throw new ApiException(404, String.format("Department of id: \"%s\"  does not exist, please select correct department or add a department if non exists", user.getDepartmentId()));
            }
            return gson.toJson(user);
        });


        //READ
        //user details
        get("/users/:id", "application/json", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));
            List<Integer> userIds = new ArrayList<>();
            for (int i = 0; i < userDao.getAll().size(); i++) {
                userIds.add(userDao.getAll().get(i).getId());
            }if(!userIds.contains(id)){
                throw new ApiException(404, String.format("User of id: \"%s\"  does not exist, please select correct user or add a user if none exists", id));
            }
            return gson.toJson(userDao.findById(id));
        });

        //department details
        get("/departments/:id", "application/json", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));
            List<Integer> deptIds = new ArrayList<>();
            for (int i = 0; i < departmentDao.getAll().size(); i++) {
                deptIds.add(departmentDao.getAll().get(i).getId());
            }if(!deptIds.contains(id)){
                throw new ApiException(404, String.format("Department of id: \"%s\"  does not exist, please select correct department or add a department if none exists", id));
            }
            return gson.toJson(departmentDao.findById(id));
        });

        //users of a particular department
        get("/departments/:id/users", "application/json", (req, res) -> {
            int departmentId = Integer.parseInt(req.params("id"));
            List<Integer> deptIds = new ArrayList<>();
            for (int i = 0; i < departmentDao.getAll().size(); i++) {
                deptIds.add(departmentDao.getAll().get(i).getId());
            }if(!deptIds.contains(departmentId)){
                throw new ApiException(404, String.format("Department of id: \"%s\"  does not exist, please select correct department or add a department if none exists", departmentId));
            }
            return gson.toJson(userDao.getAllUsersByDepartment(departmentId));
        });

        //news of a particular department
        get("/departments/:id/news", "application/json", (req, res) -> {
            int departmentId = Integer.parseInt(req.params("id"));
            List<Integer> deptIds = new ArrayList<>();
            for (int i = 0; i < departmentDao.getAll().size(); i++) {
                deptIds.add(departmentDao.getAll().get(i).getId());
            }if(!deptIds.contains(departmentId)){
                throw new ApiException(404, String.format("Department of id: \"%s\"  does not exist, please select correct department or add a department if none exists", departmentId));
            }
            return gson.toJson(newsForDepartmentDao.getAllForADepartment(departmentId));
        });

        exception(ApiException.class, (exc, req, res) -> {
            ApiException err = (ApiException) exc;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            res.type("application/json"); //after does not run in case of an exception.
            res.status(err.getStatusCode()); //set the status
            res.body(gson.toJson(jsonMap));  //set the output.
        });
    }
}
