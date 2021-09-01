package com.mytests.micronaut.repos;

import com.mytests.micronaut.data.Author;
import com.mytests.micronaut.data.Post;
import io.micronaut.data.r2dbc.operations.R2dbcOperations;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.Arrays;

/**
 * *
 * <p>Created by irina on 8/27/2021.</p>
 * <p>Project: micronaut-r2dbc-test</p>
 * *
 */
@Singleton
public class SetupService {

    @Inject
    R2dbcOperations operations;

    @Inject
    AuthorRepo authorRepo;
    @Inject
    PostRepo postRepo;

    @Transactional
    public Mono<Void> setupData() {

        int lastAuthorId = authorRepo.findFirstOrderByIdDesc().block().getId();
       
        System.out.println(lastAuthorId);
       int lastPostId = postRepo.findFirstOrderByIdDesc().blockingGet().getId();
        
        System.out.println(lastPostId);
        return Mono.from(operations.withTransaction(status ->
                Flux.from(authorRepo.save(new Author(lastAuthorId + 1, "rex", "vasya vasiliev")))
                        .flatMap((author -> postRepo.saveAll(Arrays.asList(
                                new Post(lastPostId + 1, "important post", "empty", "RELEASE", 1, author),
                                new Post(lastPostId + 2, "not important post", "empty", "RELEASE", 2, author)
                        ))))
                        .thenMany(Flux.from(authorRepo.save(new Author(lastAuthorId + 2, "regina", "vasilisa vasilieva"))))
                        .flatMap((author ->
                                postRepo.save(new Post(lastPostId + 3, "test post", "empty", "RELEASE", 1, author))
                        )).then()
        ));
    }
}
