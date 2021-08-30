package com.mytests.micronaut.repos;

import com.mytests.micronaut.data.Author;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.r2dbc.annotation.R2dbcRepository;
import io.micronaut.data.repository.reactive.ReactiveStreamsCrudRepository;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * *
 * <p>Created by irina on 8/26/2021.</p>
 * <p>Project: micronaut-r2dbc-test</p>
 * *
 */
@R2dbcRepository(dialect = Dialect.H2)
public interface AuthorRepo extends ReactiveStreamsCrudRepository<Author, Integer> {

   Flux<Author> findByNickName(String nickName);

   
   Publisher<Author> findByFullNameContains(String fullName);

   Mono<Author> findFirstOrderByIdDesc();


}
