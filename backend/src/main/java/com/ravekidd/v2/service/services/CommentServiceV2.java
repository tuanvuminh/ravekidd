package com.ravekidd.v2.service.services;

import com.ravekidd.v1.exception.ServerException;
import com.ravekidd.v2.model.rest.RESTResponse;
import com.ravekidd.v2.service.interfaces.ICommentServiceV2;
import org.springframework.http.ResponseEntity;

public class CommentServiceV2 implements ICommentServiceV2 {

    @Override
    public ResponseEntity<RESTResponse> getComments() throws ServerException {
        return null;
    }

    @Override
    public ResponseEntity<RESTResponse> getCommentById() throws ServerException {
        return null;
    }

    @Override
    public ResponseEntity<RESTResponse> getCommentByUser() throws ServerException {
        return null;
    }

    @Override
    public ResponseEntity<RESTResponse> addComment() throws ServerException {
        return null;
    }

    @Override
    public ResponseEntity<RESTResponse> updateComment() throws ServerException {
        return null;
    }

    @Override
    public ResponseEntity<RESTResponse> likeComment() throws ServerException {
        return null;
    }

    @Override
    public ResponseEntity<RESTResponse> unlikeComment() throws ServerException {
        return null;
    }

    @Override
    public ResponseEntity<RESTResponse> deleteComment() throws ServerException {
        return null;
    }
}
