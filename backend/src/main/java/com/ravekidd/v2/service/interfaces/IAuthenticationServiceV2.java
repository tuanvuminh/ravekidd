package com.ravekidd.v2.service.interfaces;

import com.ravekidd.v1.exception.ServerException;
import com.ravekidd.v2.model.rest.RESTResponse;
import org.springframework.http.ResponseEntity;

public interface IAuthenticationServiceV2 {
    ResponseEntity<RESTResponse>register() throws ServerException;
    ResponseEntity<RESTResponse>login() throws ServerException;
    ResponseEntity<RESTResponse>logout() throws ServerException;
}
