package com.ravekidd.v2.service.services;

import com.ravekidd.v2.exception.RESTException;
import com.ravekidd.v2.model.rest.RESTResponse;
import com.ravekidd.v2.service.interfaces.IPostServiceV2;
import org.springframework.http.ResponseEntity;

public class PostServiceV2 implements IPostServiceV2 {

    @Override
    public ResponseEntity<RESTResponse> getPosts() throws RESTException {
        return null;
    }

    @Override
    public ResponseEntity<RESTResponse> getPostById() throws RESTException {
        return null;
    }

    @Override
    public ResponseEntity<RESTResponse> getPostByUser() throws RESTException {
        return null;
    }

    @Override
    public ResponseEntity<RESTResponse> createPost() throws RESTException {
        return null;
    }

    @Override
    public ResponseEntity<RESTResponse> updatePost() throws RESTException {
        return null;
    }

    @Override
    public ResponseEntity<RESTResponse> patchPost() throws RESTException {
        return null;
    }

    @Override
    public ResponseEntity<RESTResponse> likePost() throws RESTException {
        return null;
    }

    @Override
    public ResponseEntity<RESTResponse> unlikePost() throws RESTException {
        return null;
    }

    @Override
    public ResponseEntity<RESTResponse> deletePost() throws RESTException {
        return null;
    }

    @Override
    public ResponseEntity<RESTResponse> deletePosts() throws RESTException {
        return null;
    }
}
