package com.ravekidd.v2.service.interfaces;

import com.ravekidd.v2.exception.RESTException;
import com.ravekidd.v2.model.rest.RESTResponse;
import org.springframework.http.ResponseEntity;

public interface IUserServiceV2 {

    ResponseEntity<RESTResponse> getUsers() throws RESTException;

    ResponseEntity<RESTResponse> getUserById() throws RESTException;

    ResponseEntity<RESTResponse> getUserByUsername() throws RESTException;

    ResponseEntity<RESTResponse> updateUser() throws RESTException;

    ResponseEntity<RESTResponse> patchUser() throws RESTException;

    ResponseEntity<RESTResponse> deleteUser() throws RESTException;

    ResponseEntity<RESTResponse> deleteUsers() throws RESTException;
}
