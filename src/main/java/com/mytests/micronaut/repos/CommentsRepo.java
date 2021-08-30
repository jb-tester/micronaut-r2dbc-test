package com.mytests.micronaut.repos;

import com.mytests.micronaut.data.Comment;
import com.mytests.micronaut.data.Post;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.r2dbc.annotation.R2dbcRepository;
import io.micronaut.data.repository.reactive.ReactiveStreamsCrudRepository;
import org.reactivestreams.Publisher;

/**
 * *
 * <p>Created by irina on 8/30/2021.</p>
 * <p>Project: micronaut-r2dbc-test</p>
 * *
 */
@R2dbcRepository(dialect = Dialect.H2)
public interface CommentsRepo extends ReactiveStreamsCrudRepository<Comment, Integer> {

    Publisher<Comment> findByPostIdAndAuthorId(Integer post_id, Integer author_id);
    @Join(value = "post", type = Join.Type.FETCH)
    Publisher<Comment> findByPostId(Integer post_id);


}
