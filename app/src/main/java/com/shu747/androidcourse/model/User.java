package com.shu747.androidcourse.model;

import org.litepal.crud.LitePalSupport;

import java.util.Objects;

/**
 * @Author: shuo747
 * @Date: 2019/5/16 16:19
 */
public class User extends LitePalSupport {
    private String username;
    private String password;
    private long id;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
