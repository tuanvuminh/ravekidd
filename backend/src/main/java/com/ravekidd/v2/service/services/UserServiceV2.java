package com.ravekidd.v2.service.services;

import com.ravekidd.v1.exception.ServerException;
import com.ravekidd.v2.model.rest.RESTResponse;
import com.ravekidd.v2.service.interfaces.IUserServiceV2;
import org.springframework.http.ResponseEntity;

public class UserServiceV2 implements IUserServiceV2 {

    @Override
    public ResponseEntity<RESTResponse> getUsers() throws ServerException {
        return null;
    }

    @Override
    public ResponseEntity<RESTResponse> getUserById() throws ServerException {
        return null;
    }

    @Override
    public ResponseEntity<RESTResponse> getUserByUsername() throws ServerException {
        return null;
    }

    @Override
    public ResponseEntity<RESTResponse> updateUser() throws ServerException {
        return null;
    }

    @Override
    public ResponseEntity<RESTResponse> patchUser() throws ServerException {
        return null;
    }

    @Override
    public ResponseEntity<RESTResponse> deleteUser() throws ServerException {
        return null;
    }

    @Override
    public ResponseEntity<RESTResponse> deleteUsers() throws ServerException {
        return null;
    }
}
