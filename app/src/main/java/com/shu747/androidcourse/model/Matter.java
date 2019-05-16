package com.shu747.androidcourse.model;

import org.litepal.crud.LitePalSupport;

import java.util.Objects;

/**
 * @Author: shuo747
 * @Date: 2019/5/16 16:19
 */
public class Matter extends LitePalSupport {
    private String title;
    private String content;
    private long id;



    public Matter(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Matter() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matter matter = (Matter) o;
        return Objects.equals(title, matter.title) &&
                Objects.equals(content, matter.content);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, content);
    }
}
