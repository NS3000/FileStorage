package com.example.demo.Exceptions;

public class FileNotFoundException extends RuntimeException{

    public FileNotFoundException(){
        super("File Not Found");
    }
}
