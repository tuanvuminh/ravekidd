package com.ravekidd.exception;

public class ServerException extends Exception {
    public ServerException(String message) {
        super(message);
    }

    public ServerException(String message, ServerException ex) {
    }
}
