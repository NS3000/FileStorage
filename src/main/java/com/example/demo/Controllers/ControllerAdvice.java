package com.example.demo.Controllers;

import com.example.demo.Exceptions.FileNotFoundException;
import com.example.demo.Exceptions.InvalidFileException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.UnexpectedTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.rmi.UnexpectedException;
import java.util.List;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<String> fileNotFound(FileNotFoundException exception){
        return new  ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidFileException.class)
    public ResponseEntity<String> invalidFile(InvalidFileException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }

}
