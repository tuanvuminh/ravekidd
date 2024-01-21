package com.ravekidd.v2.service.interfaces;

import com.ravekidd.v2.exception.RESTException;
import com.ravekidd.v2.model.rest.RESTResponse;
import org.springframework.http.ResponseEntity;

public interface IPostServiceV2 {

    ResponseEntity<RESTResponse> getPosts() throws RESTException;

    ResponseEntity<RESTResponse> getPostById() throws RESTException;

    ResponseEntity<RESTResponse> getPostByUser() throws RESTException;

    ResponseEntity<RESTResponse> createPost() throws RESTException;

    ResponseEntity<RESTResponse> updatePost() throws RESTException;

    ResponseEntity<RESTResponse> patchPost() throws RESTException;

    ResponseEntity<RESTResponse> likePost() throws RESTException;

    ResponseEntity<RESTResponse> unlikePost() throws RESTException;

    ResponseEntity<RESTResponse> deletePost() throws RESTException;

    ResponseEntity<RESTResponse> deletePosts() throws RESTException;
}
