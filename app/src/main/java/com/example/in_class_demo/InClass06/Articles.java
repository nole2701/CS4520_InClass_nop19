package com.example.in_class_demo.InClass06;

import java.util.ArrayList;

public class Articles {

    private ArrayList<Article> articles;


    public Articles() {
    }

    public ArrayList<Article> getHeadlines() {
        return articles;
    }

    public void setHeadlines(ArrayList<Article> headlines) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "Headlines{" +
                "headlines=" + articles +
                '}';
    }

}
