package com.ravekidd.v2.controller;

import com.ravekidd.v1.exception.ServerException;
import com.ravekidd.v2.model.rest.RESTResponse;
import com.ravekidd.v2.service.interfaces.IPostServiceV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v2/posts")
public class PostControllerV2 {

    private final IPostServiceV2 service;

    @Autowired
    public PostControllerV2(IPostServiceV2 service) {
        this.service = service;
    }

    @GetMapping
    ResponseEntity<RESTResponse> getPosts() throws ServerException {
        return service.getPosts();
    }

    @GetMapping("/{id}")
    ResponseEntity<RESTResponse> getPostById() throws ServerException {
        return service.getPostById();
    }

    @GetMapping("/username")
    ResponseEntity<RESTResponse> getPostByUser() throws ServerException {
        return service.getPostByUser();
    }

    @PostMapping
    ResponseEntity<RESTResponse> createPost() throws ServerException {
        return service.createPost();
    }

    @PutMapping("/{id}")
    ResponseEntity<RESTResponse> updatePost() throws ServerException {
        return service.updatePost();
    }

    @PatchMapping("/{id}")
    ResponseEntity<RESTResponse> patchPost() throws ServerException {
        return service.patchPost();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<RESTResponse> deletePost() throws ServerException {
        return service.deletePost();
    }

    @DeleteMapping
    ResponseEntity<RESTResponse> deletePosts() throws ServerException {
        return service.deletePosts();
    }

    @PostMapping("/like/{id}")
    ResponseEntity<RESTResponse> likePost() throws ServerException {
        return service.likePost();
    }

    @PostMapping("/like/{id}")
    ResponseEntity<RESTResponse> unlikePost() throws ServerException {
        return service.unlikePost();
    }
}
