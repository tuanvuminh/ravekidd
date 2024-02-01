package com.ravekidd.v2.service.interfaces;

import com.ravekidd.v2.exception.RESTException;
import com.ravekidd.v2.model.rest.RESTResponse;
import org.springframework.http.ResponseEntity;

public interface IAuthenticationServiceV2 {

    ResponseEntity<RESTResponse>register() throws RESTException;

    ResponseEntity<RESTResponse>login() throws RESTException;

    ResponseEntity<RESTResponse>logout() throws RESTException;
}
