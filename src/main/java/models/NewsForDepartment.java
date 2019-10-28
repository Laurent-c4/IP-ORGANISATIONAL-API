package models;

import java.util.Objects;

public class NewsForDepartment {
    private News news;
    private int departmentId;

    public NewsForDepartment(News news,int departmentId){
        this.news=news;
        this.departmentId=departmentId;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsForDepartment that = (NewsForDepartment) o;
        return departmentId == that.departmentId &&
                Objects.equals(news, that.news);
    }


    @Override
    public int hashCode() {
        return Objects.hash(news, departmentId);
    }
}
