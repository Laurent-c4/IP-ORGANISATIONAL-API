package dao;

import models.News;
import models.NewsForDepartment;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oNewsForDepartmentDao implements NewsForDepartmentDao {
    private final Sql2o sql2o;

    public Sql2oNewsForDepartmentDao(Sql2o sql2o){
        this.sql2o=sql2o;
    }

    @Override
    public void add(NewsForDepartment newsForDepartment){
        String sql = "INSERT INTO news (content, type, departmentId, submittedOn) VALUES (:content, :type, :departmentId, now())";
        try(Connection con = sql2o.open()){
            int id =(int) con.createQuery(sql,true)
                    .addParameter("content",newsForDepartment.getNews().getContent())
                    .addParameter("type", newsForDepartment.getNews().getType())
                    .addParameter("departmentId",newsForDepartment.getDepartmentId())
                    .executeUpdate()
                    .getKey();
            newsForDepartment.getNews().setId(id);

        }catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<NewsForDepartment> getAllForADepartment(int departmentId){
        List<NewsForDepartment> a;
        List<News> b;
        try (Connection con = sql2o.open()) {
            a = con.createQuery("SELECT * FROM news WHERE departmentId = :departmentId")
                    .addParameter("departmentId", departmentId )
                    .throwOnMappingFailure(false)
                    .executeAndFetch(NewsForDepartment.class);}

        try (Connection con = sql2o.open()) {
            b = con.createQuery("SELECT * FROM news WHERE departmentId = :departmentId")
                      .addParameter("departmentId", departmentId )
                      .throwOnMappingFailure(false)
                      .executeAndFetch(News.class);}

            for(int i=0;i<a.size();i++){
                a.get(i).setNews(b.get(i));
            }
            return a;

    }

    @Override
    public List<NewsForDepartment> getAll(){
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM news WHERE type = 'department'")
                    .executeAndFetch(NewsForDepartment.class);
        }
    }

    @Override
    public NewsForDepartment findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM news WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(NewsForDepartment.class);
        }
    }


}
