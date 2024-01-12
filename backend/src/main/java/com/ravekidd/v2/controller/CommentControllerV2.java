package com.ravekidd.v2.controller;

import com.ravekidd.v1.exception.ServerException;
import com.ravekidd.v2.model.rest.RESTResponse;
import com.ravekidd.v2.service.interfaces.ICommentServiceV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/comments")
public class CommentControllerV2 {

    private final ICommentServiceV2 service;

    @Autowired
    public CommentControllerV2(ICommentServiceV2 service) {
        this.service = service;
    }

    @GetMapping
    ResponseEntity<RESTResponse> getComments() throws ServerException {
        return service.getComments();
    }

    @GetMapping("/{id}")
    ResponseEntity<RESTResponse> getCommentById() throws ServerException {
        return service.getCommentById();
    }

    @GetMapping("/{username}")
    ResponseEntity<RESTResponse> getCommentByUser() throws ServerException {
        return service.getCommentByUser();
    }

    @PostMapping
    ResponseEntity<RESTResponse> addComment() throws ServerException {
        return service.addComment();
    }

    @PutMapping("/{id}")
    ResponseEntity<RESTResponse> updateComment() throws ServerException {
        return service.updateComment();
    }

    @PostMapping("/like/{id}")
    ResponseEntity<RESTResponse> likeComment() throws ServerException {
        return service.likeComment();
    }

    @DeleteMapping("/like{id}")
    ResponseEntity<RESTResponse> unlikeComment() throws ServerException {
        return service.unlikeComment();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<RESTResponse> deleteComment() throws ServerException {
        return service.deleteComment();
    }
}
