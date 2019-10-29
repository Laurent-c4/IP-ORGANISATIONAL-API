package models;

import java.sql.Timestamp;
import java.util.Objects;

public class News {
    private int id;
    private String content;
    private String type="general";
    private Timestamp submittedOn;

    public News(String content){
        this.content=content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getSubmittedOn() {
        return submittedOn;
    }

    public void setSubmittedOn(Timestamp submittedOn) {
        this.submittedOn = submittedOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return id == news.id &&
                Objects.equals(content, news.content) &&
                Objects.equals(type, news.type) &&
                Objects.equals(submittedOn, news.submittedOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, type, submittedOn);
    }
}
