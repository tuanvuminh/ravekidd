package com.ravekidd.v2.service.services;

import com.ravekidd.v1.exception.ServerException;
import com.ravekidd.v2.model.rest.RESTResponse;
import com.ravekidd.v2.service.interfaces.IPostServiceV2;
import org.springframework.http.ResponseEntity;

public class PostServiceV2 implements IPostServiceV2 {

    @Override
    public ResponseEntity<RESTResponse> getPosts() throws ServerException {
        return null;
    }

    @Override
    public ResponseEntity<RESTResponse> getPostById() throws ServerException {
        return null;
    }

    @Override
    public ResponseEntity<RESTResponse> getPostByUser() throws ServerException {
        return null;
    }

    @Override
    public ResponseEntity<RESTResponse> createPost() throws ServerException {
        return null;
    }

    @Override
    public ResponseEntity<RESTResponse> updatePost() throws ServerException {
        return null;
    }

    @Override
    public ResponseEntity<RESTResponse> patchPost() throws ServerException {
        return null;
    }

    @Override
    public ResponseEntity<RESTResponse> likePost() throws ServerException {
        return null;
    }

    @Override
    public ResponseEntity<RESTResponse> unlikePost() throws ServerException {
        return null;
    }

    @Override
    public ResponseEntity<RESTResponse> deletePost() throws ServerException {
        return null;
    }

    @Override
    public ResponseEntity<RESTResponse> deletePosts() throws ServerException {
        return null;
    }
}
