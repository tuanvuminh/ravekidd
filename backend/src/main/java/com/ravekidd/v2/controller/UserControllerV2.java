package com.ravekidd.v2.controller;

import com.ravekidd.v1.exception.ServerException;
import com.ravekidd.v2.model.rest.RESTResponse;
import com.ravekidd.v2.service.interfaces.IUserServiceV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v2/users")
public class UserControllerV2 {

    private final IUserServiceV2 service;

    @Autowired
    public UserControllerV2(IUserServiceV2 service) {
        this.service = service;
    }

    @GetMapping
    ResponseEntity<RESTResponse> getUsers() throws ServerException {
        return service.getUsers();
    }

    @GetMapping("/{id}")
    ResponseEntity<RESTResponse> getUserById() throws ServerException {
        return service.getUserById();
    }

    @GetMapping("/{username}")
    ResponseEntity<RESTResponse> getUserByUsername() throws ServerException {
        return service.getUserByUsername();
    }

    @PutMapping("/{id}")
    ResponseEntity<RESTResponse> updateUser() throws ServerException {
        return service.updateUser();
    }

    @PatchMapping("/{id}")
    ResponseEntity<RESTResponse> patchUser() throws ServerException {
        return service.patchUser();
    }

    @DeleteMapping("{id}")
    ResponseEntity<RESTResponse> deleteUser() throws ServerException {
        return service.deleteUser();
    }

    @DeleteMapping
    ResponseEntity<RESTResponse> deleteUsers() throws ServerException {
        return service.deleteUsers();
    }
}
