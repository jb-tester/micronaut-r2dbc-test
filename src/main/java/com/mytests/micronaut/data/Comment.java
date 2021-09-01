package com.mytests.micronaut.data;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;


/**
 * *
 * <p>Created by irina on 8/27/2021.</p>
 * <p>Project: micronaut-r2dbc-test</p>
 * *
 *     id INT NOT NULL PRIMARY KEY,
 *     content VARCHAR(255),
 *     post_id INT REFERENCES posts ON DELETE CASCADE,
 *     author_id INT REFERENCES authors,
 *     version INTEGER
 */
//@Entity
//@Table(name = "comments")
@MappedEntity("comments")
public class Comment {

    @Id
    private Integer id;
    private String content;
    private Integer version;

    //@ManyToOne
    //@JoinColumn(name = "author_id")
    @Relation(Relation.Kind.MANY_TO_ONE)
    private Author author;

    //@ManyToOne
    //@JoinColumn(name="post_id")
    @Relation(Relation.Kind.MANY_TO_ONE)
    private Post post;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", version=" + version +
                ", author=" + author.getNickName() +
                ", post=" + post.getTitle() +
                '}';
    }
}
