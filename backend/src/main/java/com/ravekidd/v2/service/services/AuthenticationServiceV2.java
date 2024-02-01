package com.ravekidd.v2.service.services;

import com.ravekidd.v2.exception.RESTException;
import com.ravekidd.v2.model.rest.RESTResponse;
import com.ravekidd.v2.service.interfaces.IAuthenticationServiceV2;
import org.springframework.http.ResponseEntity;

public class AuthenticationServiceV2 implements IAuthenticationServiceV2 {

    @Override
    public ResponseEntity<RESTResponse> register() throws RESTException {
        return null;
    }

    @Override
    public ResponseEntity<RESTResponse> login() throws RESTException {
        return null;
    }

    @Override
    public ResponseEntity<RESTResponse> logout() throws RESTException {
        return null;
    }
}
