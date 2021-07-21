package com.hihi.model;

import javax.persistence.*;

@Entity
@Table(name = "blogs")
public class Blog {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title,content;

    public Blog() {
    }

    public Blog(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String tile) {
        this.title = tile;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
