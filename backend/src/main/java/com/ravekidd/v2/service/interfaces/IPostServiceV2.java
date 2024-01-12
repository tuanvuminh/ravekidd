package com.ravekidd.v2.service.interfaces;

import com.ravekidd.v1.exception.ServerException;
import com.ravekidd.v2.model.rest.RESTResponse;
import org.springframework.http.ResponseEntity;

public interface IPostServiceV2 {

    ResponseEntity<RESTResponse> getPosts() throws ServerException;

    ResponseEntity<RESTResponse> getPostById() throws ServerException;

    ResponseEntity<RESTResponse> getPostByUser() throws ServerException;

    ResponseEntity<RESTResponse> createPost() throws ServerException;

    ResponseEntity<RESTResponse> updatePost() throws ServerException;

    ResponseEntity<RESTResponse> patchPost() throws ServerException;

    ResponseEntity<RESTResponse> deletePost() throws ServerException;

    ResponseEntity<RESTResponse> deletePosts() throws ServerException;

    ResponseEntity<RESTResponse> likePost() throws ServerException;

    ResponseEntity<RESTResponse> unlikePost() throws ServerException;

    ResponseEntity<RESTResponse> getComments() throws ServerException;
}
