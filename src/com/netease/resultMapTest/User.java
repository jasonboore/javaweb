package com.netease.resultMapTest;

import java.util.List;

public class User {
    private int id;
    private String userName;
    private String sex;
    List<Course> courses;

    public User(Integer id, String userName, String sex) {
        this.id = id;
        this.userName = userName;
        this.sex = sex;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
