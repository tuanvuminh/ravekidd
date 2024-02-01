package com.ravekidd.v2.controller;

import com.ravekidd.v2.exception.RESTException;
import com.ravekidd.v2.model.rest.RESTResponse;
import com.ravekidd.v2.service.interfaces.ICommentServiceV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("v2/comments")
public class CommentControllerV2 {

    private final ICommentServiceV2 service;

    @Autowired
    public CommentControllerV2(ICommentServiceV2 service) {
        this.service = service;
    }

    @GetMapping
    ResponseEntity<RESTResponse> getComments() throws RESTException {
        return service.getComments();
    }

    @GetMapping("/{id}")
    ResponseEntity<RESTResponse> getCommentById() throws RESTException {
        return service.getCommentById();
    }

    @GetMapping("/{username}")
    ResponseEntity<RESTResponse> getCommentByUser() throws RESTException {
        return service.getCommentByUser();
    }

    @PostMapping
    ResponseEntity<RESTResponse> addComment() throws RESTException {
        return service.addComment();
    }

    @PutMapping("/{id}")
    ResponseEntity<RESTResponse> updateComment() throws RESTException {
        return service.updateComment();
    }

    @PostMapping("/like/{id}")
    ResponseEntity<RESTResponse> likeComment() throws RESTException {
        return service.likeComment();
    }

    @DeleteMapping("/like{id}")
    ResponseEntity<RESTResponse> unlikeComment() throws RESTException {
        return service.unlikeComment();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<RESTResponse> deleteComment() throws RESTException {
        return service.deleteComment();
    }
}
