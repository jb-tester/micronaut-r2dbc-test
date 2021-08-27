package com.mytests.micronaut.data;

import io.micronaut.core.annotation.Creator;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import io.micronaut.data.annotation.Relation;

import java.util.List;

/**
 * *
 * <p>Created by irina on 8/26/2021.</p>
 * <p>Project: micronaut-r2dbc-test</p>
 * *
 */
@MappedEntity("authors")
public class Author {

    @Id
    private Integer id;
    @MappedProperty("nick")
    private String nickName;
    @MappedProperty("name")
    private String fullName;
    /*@Relation(value = Relation.Kind.ONE_TO_MANY, mappedBy = "author", cascade = Relation.Cascade.ALL)
    private List<Post> posts;

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nick) {
        this.nickName = nick;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Creator
    public Author(Integer id, String nickName, String fullName) {
        this.id = id;
        this.nickName = nickName;
        this.fullName = fullName;
    }
}
