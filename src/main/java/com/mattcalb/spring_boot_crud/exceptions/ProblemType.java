package com.mattcalb.spring_boot_crud.exceptions;

import lombok.Getter;

@Getter
public enum ProblemType {

    USER_NOT_FOUND("User not found", "/user-not-found"),
    EMAIL_ALREADY_REGISTERED("Email already registered", "/email-already-registered"),
    MESSAGE_NOT_READABLE("Message not readable", "/message-not-readable"),
    METHOD_ARGUMENT_NOT_VALID("Argument not valid", "/argument-not-valid"),
    URI_NOT_VALID("URI not valid", "/uri-not-valid"),
    INVALID_FORMAT("Invalid format", "/invalid-format"),
    INTERNAL_SERVER_ERROR("Internal server error", "/internal-server-error");

    final private String title;
    final private String uri;

    ProblemType(String title, String path) {
        this.title = title;
        this.uri = "https://example.com/problems" + path;
    }

}
