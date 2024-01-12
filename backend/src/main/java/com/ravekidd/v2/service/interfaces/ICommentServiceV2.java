package com.ravekidd.v2.service.interfaces;

import com.ravekidd.v1.exception.ServerException;
import com.ravekidd.v2.model.rest.RESTResponse;
import org.springframework.http.ResponseEntity;

public interface ICommentServiceV2 {
    ResponseEntity<RESTResponse> getComments() throws ServerException;

    ResponseEntity<RESTResponse> getCommentById() throws ServerException;

    ResponseEntity<RESTResponse> getCommentByUser() throws ServerException;

    ResponseEntity<RESTResponse> addComment() throws ServerException;

    ResponseEntity<RESTResponse> updateComment() throws ServerException;

    ResponseEntity<RESTResponse> deleteComment() throws ServerException;

    ResponseEntity<RESTResponse> likeComment() throws ServerException;

    ResponseEntity<RESTResponse> unlikeComment() throws ServerException;
}
