package com.example.demo.Controllers;

import com.example.demo.DTO.ResponseFile;
import com.example.demo.Service.FileServiceImpl;
import com.example.demo.Service.MailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FileController {

    @Autowired
    FileServiceImpl fileService;
    @Autowired
    MailServiceImpl mailService;

    @GetMapping("/getFile/{id}")
    public ResponseEntity getFile(@PathVariable("id") int id){
        Path path = Path.of(mailService.getFilePath(id));
        Resource resource = null;
        try {
            resource = new UrlResource(path.toUri());
        }catch (Exception exception){exception.printStackTrace();}

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+resource.getFilename()+"\"").body(resource);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam MultipartFile file){
        mailService.saveFile(file);
        return ResponseEntity.ok("File Saved successfully");
    }

    @GetMapping("/list")
    public ResponseEntity<List<ResponseFile>> listFiles(){
        List<ResponseFile> allFiles = fileService.getAllFiles();
        return ResponseEntity.ok(allFiles);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteFile(@PathVariable("id") int id){
        fileService.deleteFile(id);
        return ResponseEntity.ok("File Deleted Successfully");

    }

}
