package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class NewsForDepartmentTest {
    //Helper
    public NewsForDepartment setupNewNewsForDepartment(){
        NewsForDepartment newsForDepartment = new NewsForDepartment(new News("hi"),2);
        newsForDepartment.getNews().setType("department");
        return newsForDepartment;
    }

    @Test
    public void NewsForDepartment_instantiatesCorrectly() {
        NewsForDepartment newsForDepartment= setupNewNewsForDepartment();
        assertTrue(newsForDepartment instanceof NewsForDepartment);
    }

    @Test
    public void NewsForDepartment_instantiatesWithContent() {
        NewsForDepartment newsForDepartment= setupNewNewsForDepartment();
        assertEquals("hi",newsForDepartment.getNews().getContent());
    }

    @Test
    public void NewsForDepartment_instantiatesWithCorrectType() {
        NewsForDepartment newsForDepartment= setupNewNewsForDepartment();
        assertEquals("department",newsForDepartment.getNews().getType());
    }

}