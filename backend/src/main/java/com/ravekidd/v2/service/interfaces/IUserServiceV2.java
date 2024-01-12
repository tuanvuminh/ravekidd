package com.ravekidd.v2.service.interfaces;

import com.ravekidd.v1.exception.ServerException;
import com.ravekidd.v2.model.rest.RESTResponse;
import org.springframework.http.ResponseEntity;

public interface IUserServiceV2 {

    ResponseEntity<RESTResponse> getUsers() throws ServerException;

    ResponseEntity<RESTResponse> getUserById() throws ServerException;

    ResponseEntity<RESTResponse> getUserByUsername() throws ServerException;

    ResponseEntity<RESTResponse> updateUser() throws ServerException;

    ResponseEntity<RESTResponse> patchUser() throws ServerException;

    ResponseEntity<RESTResponse> deleteUser() throws ServerException;

    ResponseEntity<RESTResponse> deleteUsers() throws ServerException;
}
