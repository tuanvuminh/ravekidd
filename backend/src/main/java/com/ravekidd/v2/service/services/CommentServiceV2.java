package com.ravekidd.v2.service.services;

import com.ravekidd.v2.exception.RESTException;
import com.ravekidd.v2.model.rest.RESTResponse;
import com.ravekidd.v2.service.interfaces.ICommentServiceV2;
import org.springframework.http.ResponseEntity;

public class CommentServiceV2 implements ICommentServiceV2 {

    @Override
    public ResponseEntity<RESTResponse> getComments() throws RESTException {
        return null;
    }

    @Override
    public ResponseEntity<RESTResponse> getCommentById() throws RESTException {
        return null;
    }

    @Override
    public ResponseEntity<RESTResponse> getCommentByUser() throws RESTException {
        return null;
    }

    @Override
    public ResponseEntity<RESTResponse> addComment() throws RESTException {
        return null;
    }

    @Override
    public ResponseEntity<RESTResponse> updateComment() throws RESTException {
        return null;
    }

    @Override
    public ResponseEntity<RESTResponse> likeComment() throws RESTException {
        return null;
    }

    @Override
    public ResponseEntity<RESTResponse> unlikeComment() throws RESTException {
        return null;
    }

    @Override
    public ResponseEntity<RESTResponse> deleteComment() throws RESTException {
        return null;
    }
}
