package com.example.demo.Service;

import com.example.demo.DTO.ResponseFile;
import com.example.demo.Entity.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface FileService {
    public List<ResponseFile> getAllFiles();
    public void deleteFile(int id);
}
