package com.mytests.micronaut.repos;

import com.mytests.micronaut.data.Author;
import com.mytests.micronaut.data.Post;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.r2dbc.annotation.R2dbcRepository;
import io.micronaut.data.repository.reactive.ReactiveStreamsCrudRepository;
import io.reactivex.Flowable;
import io.reactivex.Single;
import org.reactivestreams.Publisher;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * *
 * <p>Created by irina on 8/27/2021.</p>
 * <p>Project: micronaut-r2dbc-test</p>
 * *
 */
@R2dbcRepository(dialect = Dialect.H2)
public interface PostRepo extends ReactiveStreamsCrudRepository<Post, Integer> {

    @Query("select p.title from posts p where p.status = 'DRAFT'")
    Publisher<String> findDrafts();

    @Query(value = "SELECT *, auth_.name as auth_name, auth_.nick as auth_nick, auth_.id as auth_id FROM posts p INNER JOIN authors auth_ ON p.author_id = auth_.id WHERE p.title like :name", nativeQuery = true)
    @Join(value = "author", alias = "auth_")
    Publisher<Post> searchPosts(String name);

    @Join(value = "author", type = Join.Type.FETCH)
    Publisher<Post> getByAuthor(Author author);

    @Join(value = "author", type = Join.Type.FETCH)
    Publisher<Post> getByAuthorId(Integer author_id);

    @Join(value = "author", type = Join.Type.FETCH)
    Publisher<Post> findByAuthorNickName(String author_nickName);

    @Join(value = "author", type = Join.Type.FETCH)
    Single<Post> findFirstOrderByIdDesc();

    @NonNull @Join(value = "author", type = Join.Type.FETCH)
    Flowable<Post> findAll();

    @NonNull
    @Override
    @Transactional(Transactional.TxType.MANDATORY)
    <S extends Post> Publisher<S> save(@NonNull @Valid @NotNull S entity);

    @NonNull
    @Override
    @Transactional(Transactional.TxType.MANDATORY)
    <S extends Post> Publisher<S> saveAll(@NonNull @Valid @NotNull Iterable<S> entities);
}
