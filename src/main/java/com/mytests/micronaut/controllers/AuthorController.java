package com.mytests.micronaut.controllers;

import com.mytests.micronaut.data.Author;
import com.mytests.micronaut.repos.AuthorRepo;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import jakarta.inject.Inject;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * *
 * <p>Created by irina on 26.08.2021.</p>
 * <p>Project: micronaut-r2dbc-test</p>
 * *
 */
@Controller
public class AuthorController {
    @Inject
    AuthorRepo authorRepo;
    @Get("/allAuthors")
    public Flux<Author> allAuthors() {
        return authorRepo.findAll();
    }

    @Get("/authorsByNick/{name}")
    public Flux<Author> authorsByNick(@PathVariable String name)

    {
        //return Flowable.fromPublisher(authorRepo.findByNickName(name));
        //return Flux.from(authorRepo.findByNickName(name));
        return authorRepo.findByNickName(name);
    }

    @Get("/authorByName/{name}")
    public Flux<Author> authorByName(@PathVariable String name) {
        return authorRepo.findByFullNameContains((name));
    }
    @Get("/authorById/{id}")
    public Mono<Author> authorById(@PathVariable String id) {
       
        return authorRepo.findById(Integer.parseInt(id));
    }

    @Get("/lastAuthorId")
    public Integer lastAuthorId() {
        return authorRepo.findFirstOrderByIdDesc().block().getId();
    }
}
