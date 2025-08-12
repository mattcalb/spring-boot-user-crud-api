package com.mattcalb.spring_boot_crud.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<Problem> handleUserNotFound(UserNotFoundException ex,
                                                             WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.USER_NOT_FOUND;
        String detail = ex.getMessage();

        Problem problem = createProblem(status, problemType, detail);

        return ResponseEntity.status(status).body(problem);
    }

    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    private ResponseEntity<Problem> handleEmailAlreadyRegistered(EmailAlreadyRegisteredException ex,
                                                                 WebRequest request) {

        HttpStatus status = HttpStatus.CONFLICT;
        ProblemType problemType = ProblemType.EMAIL_ALREADY_REGISTERED;
        String detail = ex.getMessage();

        Problem problem = createProblem(status, problemType, detail);

        return ResponseEntity.status(status).body(problem);
    }

    @ExceptionHandler(InvalidFormatException.class)
    private ResponseEntity<Problem> handleInvalidFormat(InvalidFormatException ex,
                                                        WebRequest request) {

        String path = ex.getPath()
                .stream()
                .map(ref -> ref.getFieldName())
                .collect(Collectors.joining("."));

        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ProblemType problemType = ProblemType.INVALID_FORMAT;
        String detail = String.format("Property %s has value '%s', which is invalid.", path, ex.getValue());

        Problem problem = createProblem(status, problemType, detail);

        return ResponseEntity.status(status).body(problem);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                               HttpHeaders headers,
                                                               HttpStatusCode status,
                                                               WebRequest request) {

        ProblemType problemType = ProblemType.MESSAGE_NOT_READABLE;
        String detail = "Request body is malformed or missing.";

        Problem problem = createProblem((HttpStatus) status, problemType, detail);

        return ResponseEntity.status(status).body(problem);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {

        List<String> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        ProblemType problemType = ProblemType.METHOD_ARGUMENT_NOT_VALID;
        String detail = "Validation error. Please check fields.";

        Problem problem = new Problem(
                status.value(),
                problemType.getUri(),
                problemType.getTitle(),
                detail,
                fieldErrors
        );

        return ResponseEntity.status(status).body(problem);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
                                                                   HttpHeaders headers,
                                                                   HttpStatusCode status,
                                                                   WebRequest request) {

        ProblemType problemType = ProblemType.URI_NOT_VALID;
        String detail = String.format("URI %s doesn't exist.", ex.getRequestURL());

        Problem problem = createProblem((HttpStatus) status, problemType, detail);

        return ResponseEntity.status(status).body(problem);
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<Problem> handleGenericException(Exception ex,
                                                          WebRequest request) {

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ProblemType problemType = ProblemType.INTERNAL_SERVER_ERROR;
        String detail = "An unexpected error occurred.";

        Problem problem = createProblem(status, problemType, detail);

        return ResponseEntity.status(status).body(problem);
    }

    private Problem createProblem(HttpStatus status, ProblemType type, String detail) {
        return new Problem(
                status.value(),
                type.getUri(),
                type.getTitle(),
                detail
        );
    }

}
