package com.example.demo.Service;

import com.example.demo.DTO.Mapper;
import com.example.demo.DTO.ResponseFile;
import com.example.demo.Entity.File;
import com.example.demo.Exceptions.FileNotFoundException;
import com.example.demo.Repository.FileRepository;
import org.hibernate.annotations.DialectOverride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements FileService{

    @Autowired
    FileRepository fileRepository;

    @Override
    public List<ResponseFile> getAllFiles() {
        List<ResponseFile> allFiles = fileRepository.findAll().stream().map(file -> Mapper.ToResponseFile(file)).collect(Collectors.toList());
        return allFiles;
    }

    @Override
    public void deleteFile(int id){

        java.io.File toDelete = new java.io.File(fileRepository.findById(id).orElseThrow(()->new FileNotFoundException()).getPath());
        toDelete.delete();
        fileRepository.deleteById(id);
    }
}
