package com.mytests.micronaut.data;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;

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
}
