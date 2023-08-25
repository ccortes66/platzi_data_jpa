package com.platzi.pizzeria.config;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class customRestHandler 
{   
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<GetFieldErrors>> onError400(MethodArgumentNotValidException exception)
    {   
        List<GetFieldErrors> errors = exception.getFieldErrors()
                                                              .stream()
                                                              .map(GetFieldErrors::new)
                                                              .toList();
        return ResponseEntity.badRequest().body(errors);
    }
     
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<GetFieldErrors> onDuplicateValues400()
    {   
        return ResponseEntity.badRequest().body(new GetFieldErrors("Error 400", "Duplicate Values"));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<GetFieldErrors> onErorr404(NoSuchElementException exception)
    {
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GetFieldErrors("Error 404",exception.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<GetFieldErrors> onIllegarArgument400(IllegalArgumentException exception)
    {
       return ResponseEntity.badRequest().body(new GetFieldErrors("Error 400",exception.getMessage()));
    }
}

record GetFieldErrors(String field, String message)
{
    public GetFieldErrors(FieldError error)
    {
        this(error.getField(), error.getDefaultMessage());
    }
}
