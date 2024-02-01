package com.ravekidd.v2.controller;

import com.ravekidd.v2.exception.RESTException;
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
    ResponseEntity<RESTResponse> getPosts() throws RESTException {
        return service.getPosts();
    }

    @GetMapping("/{id}")
    ResponseEntity<RESTResponse> getPostById() throws RESTException {
        return service.getPostById();
    }

    @GetMapping("/username")
    ResponseEntity<RESTResponse> getPostByUser() throws RESTException {
        return service.getPostByUser();
    }

    @PostMapping
    ResponseEntity<RESTResponse> createPost() throws RESTException {
        return service.createPost();
    }

    @PutMapping("/{id}")
    ResponseEntity<RESTResponse> updatePost() throws RESTException {
        return service.updatePost();
    }

    @PatchMapping("/{id}")
    ResponseEntity<RESTResponse> patchPost() throws RESTException {
        return service.patchPost();
    }

    @PostMapping("/like/{id}")
    ResponseEntity<RESTResponse> likePost() throws RESTException {
        return service.likePost();
    }

    @PostMapping("/like/{id}")
    ResponseEntity<RESTResponse> unlikePost() throws RESTException {
        return service.unlikePost();
    }
    @DeleteMapping("/{id}")
    ResponseEntity<RESTResponse> deletePost() throws RESTException {
        return service.deletePost();
    }

    @DeleteMapping
    ResponseEntity<RESTResponse> deletePosts() throws RESTException {
        return service.deletePosts();
    }
}
