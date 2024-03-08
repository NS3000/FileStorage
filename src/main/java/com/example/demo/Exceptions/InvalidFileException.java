package com.example.demo.Exceptions;

public class InvalidFileException extends RuntimeException{
    public InvalidFileException(){
        super("File is Invalid Try another File");
    }
}
