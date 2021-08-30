package com.mytests.micronaut.controllers;

import com.mytests.micronaut.data.Comment;
import com.mytests.micronaut.repos.CommentsRepo;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.reactivex.Flowable;
import io.reactivex.internal.operators.flowable.FlowableAll;
import jakarta.inject.Inject;

import java.util.concurrent.Flow;

/**
 * *
 * <p>Created by irina on 8/30/2021.</p>
 * <p>Project: micronaut-r2dbc-test</p>
 * *
 */
@Controller
public class CommentsController {

    @Inject
    CommentsRepo commentsRepo;

    @Get("/commentsByPost/{p}")
    public Flowable<Comment> commentsByPost(@PathVariable String p) {
        return Flowable.fromPublisher(commentsRepo.findByPostId(Integer.parseInt(p)));
    }
    @Get("/commentsByPostAndAuthor/{a}-{p}")
    public Flowable<Comment> commentsByPostAndAuthor(@PathVariable String a, @PathVariable String p) {
        return Flowable.fromPublisher(commentsRepo.findByPostIdAndAuthorId(Integer.parseInt(p),Integer.parseInt(a)));
    }

    @Get("/commentsByContents/{txt}")
    public Flowable<Comment> byContents(@PathVariable String txt) {
        return Flowable.fromPublisher(commentsRepo.findByContentContains(txt));
    }
}
