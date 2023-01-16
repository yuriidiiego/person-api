package com.attornatus.project.config.error;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ErrorValidationHandler extends ResponseEntityExceptionHandler {
  private final MessageSource messageSource;

  public ErrorValidationHandler(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
    MethodArgumentNotValidException ex,
    HttpHeaders headers,
    HttpStatus status,
    WebRequest request
  ) {
    List<ErrorResponse> errors = new ArrayList<>();
    List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
    fieldErrors.forEach(
      e -> {
        String message = messageSource.getMessage(
          e,
          LocaleContextHolder.getLocale()
        );
        int statusCode = HttpStatus.BAD_REQUEST.value();
        ErrorResponse error = new ErrorResponse(statusCode, message);
        errors.add(error);
      }
    );

    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(
    HttpMessageNotReadableException ex,
    HttpHeaders headers,
    HttpStatus status,
    WebRequest request
  ) {
    if (
      ex.contains(InvalidFormatException.class) ||
      ex.contains(DateTimeParseException.class)
    ) {
      int statusCode = HttpStatus.BAD_REQUEST.value();
      String message = "Invalid date format. Use dd/MM/yyyy";

      return new ResponseEntity<>(
        new ErrorResponse(statusCode, message),
        HttpStatus.BAD_REQUEST
      );
    }

    return new ResponseEntity<>(
      new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage()),
      HttpStatus.BAD_REQUEST
    );
  }

  @ExceptionHandler(ResponseStatusException.class)
  public ResponseEntity<ErrorResponse> handleResponseStatusException(
    ResponseStatusException ex
  ) {
    int statusCode = ex.getStatus().value();
    String message = ex.getReason();

    return new ResponseEntity<>(
      new ErrorResponse(statusCode, message),
      ex.getStatus()
    );
  }
}
