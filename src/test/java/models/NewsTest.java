package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class NewsTest {
    //Helper
    public News setupNewNews(){
        return new News("hi");
    }

    @Test
    public void User_instantiatesCorrectly() {
        News news= setupNewNews();
        assertTrue(news instanceof News);
    }

    @Test
    public void User_instantiatesWithContent() {
        News news =setupNewNews();
        assertEquals("hi", news.getContent());
    }

}