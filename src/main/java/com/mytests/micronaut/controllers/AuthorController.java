package com.mytests.micronaut.controllers;

import com.mytests.micronaut.data.Author;
import com.mytests.micronaut.repos.AuthorRepo;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import jakarta.inject.Inject;

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
    public Flowable<Author> allAuthors() {
        return Flowable.fromPublisher(authorRepo.findAll());
    }

    @Get("/authorsByNick/{name}")
    public Flowable<Author> authorsByNick(@PathVariable String name)

    {
        return Flowable.fromPublisher(authorRepo.findByNickName(name));
    }

    @Get("/authorByName/{name}")
    public Flowable<Author> authorByName(@PathVariable String name) {
        return Flowable.fromPublisher(authorRepo.findByFullNameContains((name)));
    }
    @Get("/authorById/{id}")
    public Single<Author> authorById(@PathVariable String id) {
       
        return Single.fromPublisher(authorRepo.findById(Integer.parseInt(id)));
    }

    @Get("/lastAuthorId")
    public Integer lastAuthorId() {
        return authorRepo.findFirstOrderByIdDesc().block().getId();
    }
}
