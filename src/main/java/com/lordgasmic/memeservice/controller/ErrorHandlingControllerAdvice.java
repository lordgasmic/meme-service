package com.lordgasmic.==PACKAGE_NAME==.controller;

import com.lordgasmic.==PACKAGE_NAME==.model.ValidationErrorResponse;
import com.lordgasmic.==PACKAGE_NAME==.model.Violation;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ErrorHandlingControllerAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ValidationErrorResponse onConstraintValidationException(final ConstraintViolationException e) {
        final ValidationErrorResponse error = new ValidationErrorResponse();
        for (final ConstraintViolation violation : e.getConstraintViolations()) {
            error.getViolations().add(new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
        }
        return error;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ValidationErrorResponse onMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        final ValidationErrorResponse error = new ValidationErrorResponse();
        for (final FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            error.getViolations().add(new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return error;
    }
//
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
//    @ResponseBody
//    ValidationErrorResponse onException(final Exception e) {
//        final ValidationErrorResponse error = new ValidationErrorResponse();
//        error.getViolations().add(new Violation(e, fieldError.getDefaultMessage()));
//        return error;
//    }
}
