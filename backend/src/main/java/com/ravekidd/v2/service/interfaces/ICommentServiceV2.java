package com.ravekidd.v2.service.interfaces;

import com.ravekidd.v2.exception.RESTException;
import com.ravekidd.v2.model.rest.RESTResponse;
import org.springframework.http.ResponseEntity;

public interface ICommentServiceV2 {

    ResponseEntity<RESTResponse> getComments() throws RESTException;

    ResponseEntity<RESTResponse> getCommentById() throws RESTException;

    ResponseEntity<RESTResponse> getCommentByUser() throws RESTException;

    ResponseEntity<RESTResponse> addComment() throws RESTException;

    ResponseEntity<RESTResponse> updateComment() throws RESTException;

    ResponseEntity<RESTResponse> likeComment() throws RESTException;

    ResponseEntity<RESTResponse> unlikeComment() throws RESTException;

    ResponseEntity<RESTResponse> deleteComment() throws RESTException;
}
