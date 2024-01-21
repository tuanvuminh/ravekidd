package com.ravekidd.v2.service.services;

import com.ravekidd.v2.exception.RESTException;
import com.ravekidd.v2.model.rest.RESTResponse;
import com.ravekidd.v2.service.interfaces.IUserServiceV2;
import org.springframework.http.ResponseEntity;

public class UserServiceV2 implements IUserServiceV2 {

    @Override
    public ResponseEntity<RESTResponse> getUsers() throws RESTException {
        return null;
    }

    @Override
    public ResponseEntity<RESTResponse> getUserById() throws RESTException {
        return null;
    }

    @Override
    public ResponseEntity<RESTResponse> getUserByUsername() throws RESTException {
        return null;
    }

    @Override
    public ResponseEntity<RESTResponse> updateUser() throws RESTException {
        return null;
    }

    @Override
    public ResponseEntity<RESTResponse> patchUser() throws RESTException {
        return null;
    }

    @Override
    public ResponseEntity<RESTResponse> deleteUser() throws RESTException {
        return null;
    }

    @Override
    public ResponseEntity<RESTResponse> deleteUsers() throws RESTException {
        return null;
    }
}
