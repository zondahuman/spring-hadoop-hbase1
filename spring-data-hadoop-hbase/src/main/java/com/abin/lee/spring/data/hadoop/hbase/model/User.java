package com.abin.lee.spring.data.hadoop.hbase.model;

import java.io.Serializable;

/**
 * Created by abin on 2018/2/10 23:14.
 * spring-hadoop-hbase1
 * com.abin.lee.spring.data.hadoop.hbase
 */
public class User implements Serializable{

    private String name;
    private String email;
    private String password;

    public User() {
    }

    public User(String name, String email, String password) {
        super();
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", email=" + email + ", password="
                + password + "]";
    }


}