package com.shu747.androidcourse.model;

import org.litepal.crud.LitePalSupport;

public class BasicData extends LitePalSupport {
    private String name;
    private int flag;



    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BasicData(String name) {
        this.flag = 1;
        this.name = name;
    }

    public BasicData() {
    }
}
