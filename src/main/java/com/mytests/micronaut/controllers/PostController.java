package com.mytests.micronaut.controllers;

import com.mytests.micronaut.data.Author;
import com.mytests.micronaut.data.Post;
import com.mytests.micronaut.repos.AuthorRepo;
import com.mytests.micronaut.repos.PostRepo;
import com.mytests.micronaut.repos.SetupService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import jakarta.inject.Inject;
import org.reactivestreams.Publisher;

/**
 * *
 * <p>Created by irina on 8/27/2021.</p>
 * <p>Project: micronaut-r2dbc-test</p>
 * *
 */
@Controller
public class PostController {

    @Inject
    PostRepo postRepo;

    @Inject
    AuthorRepo authorRepo;

   @Inject
   SetupService service;

   
   @io.micronaut.http.annotation.Post("/addAuthorsAndPosts")
   Flowable<Void> setUp(){
       return Flowable.fromPublisher(service.setupData());
   }
    @Get("/allPosts")
    public Flowable<Post> allPosts() {
        return Flowable.fromPublisher(postRepo.findAll());
    }

    @Get("/allDrafts")
    public Flowable<String> allDrafts() {
        return Flowable.fromPublisher(postRepo.findDrafts());
        //return postRepo.findDrafts();  // for incorrect return type
    }
    @Get("/allPostsByAuthor/{author}")
    public Flowable<Post> allPostsByAuthor(@PathVariable String author) {
        Author _author_ = Single.fromPublisher(authorRepo.findByFullNameContains(author)).blockingGet();
        System.out.println("**********************************");
        System.out.println(_author_.getId());
        System.out.println("**********************************");
        return Flowable.fromPublisher(postRepo.getByAuthor(_author_));
    }
    @Get("/allPostsByAuthor2/{author}")
    public Flowable<Post> allPostsByAuthor2(@PathVariable String author) {
        
        
        return Flowable.fromPublisher(authorRepo.findByFullNameContains(author)).flatMap((_author_ -> postRepo.getByAuthor(_author_)));
    }
    @Get("/allPostsByAuthorId/{author}")
    public Flowable<Post> allPostsByAuthorId(@PathVariable String author) {

        return Flowable.fromPublisher(postRepo.getByAuthorId(Integer.parseInt(author)));
    }

    @Get("/allPostsByAuthorNick/{author}")
    public Flowable<Post> allPostsByAuthorNick(@PathVariable String author) {

        return Flowable.fromPublisher(postRepo.findByAuthorNickName(author));
    }

    @Get("/postsByTitle/{title}")
    public Flowable<Post> postsByTitle(@PathVariable String title) {
        return Flowable.fromPublisher(postRepo.searchPosts(title));
    }

    @Get("/firstPostByVersion/{v}")
    public Publisher<Post> firstPostByVersion(@PathVariable String v) {
        return postRepo.findFirstByVersion(Integer.parseInt(v));
        // return postRepo.findFirstByVersion(Integer.parseInt(v)); // for incorrect return type
    }
}
