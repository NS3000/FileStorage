package com.example.demo.DTO;

import com.example.demo.Entity.File;
import jakarta.validation.Valid;

public class Mapper {
    public static ResponseFile ToResponseFile(File file){
        return new ResponseFile(file.getId(), file.getName(), file.getType(), file.getPath());
    }
}
