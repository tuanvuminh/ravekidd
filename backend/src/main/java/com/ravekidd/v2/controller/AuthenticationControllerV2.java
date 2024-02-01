package com.ravekidd.v2.controller;

import com.ravekidd.v2.exception.RESTException;
import com.ravekidd.v2.model.rest.RESTResponse;
import com.ravekidd.v2.service.interfaces.IAuthenticationServiceV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/auth")
public class AuthenticationControllerV2 {

    private final IAuthenticationServiceV2 service;

    @Autowired
    public AuthenticationControllerV2(IAuthenticationServiceV2 service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<RESTResponse> register() throws RESTException {
        return service.register();
    }

    @PostMapping("/login")
    public ResponseEntity<RESTResponse> login() throws RESTException {
        return service.login();
    }

    @PostMapping("/logout")
    public ResponseEntity<RESTResponse> logout() throws RESTException {
        return service.logout();
    }
}
