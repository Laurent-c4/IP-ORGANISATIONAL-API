package dao;

import models.News;

import java.util.List;

public interface NewsDao {
    //CREATE
    void add(News news);

    //READ
    List<News> getAll();

    News findById(int id);

    //UPDATE

    //DELETE
    void clearAll();

}
