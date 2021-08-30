package com.mytests.micronaut.data;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;

/**
 * *
 * <p>Created by irina on 8/27/2021.</p>
 * <p>Project: micronaut-r2dbc-test</p>
 * *
 */
@MappedEntity("posts")
public class Post {

    @Id
    private Integer id;
    private String title;
    private String content;
    private String status;
    private Integer version;


    @Relation(Relation.Kind.MANY_TO_ONE)
    private Author author;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Post(Integer id, String title, String content, String status, Integer version, Author author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.status = status;
        this.version = version;
        this.author = author;
    }
}
