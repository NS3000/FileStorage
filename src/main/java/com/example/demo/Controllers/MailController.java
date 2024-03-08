package com.example.demo.Controllers;

import com.example.demo.DTO.ResponseFile;
import com.example.demo.Service.FileServiceImpl;
import com.example.demo.Service.MailServiceImpl;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    MailServiceImpl mailService;

    @Value("${address}")
    String serverAddress;

    @GetMapping("/get/{id}")
    public ResponseEntity<String> sendMail(@PathVariable("id") int id,@RequestParam("email") String email){

        mailService.sendSimpleMessage(email,"File Download Link",serverAddress+"api/getFile/"+id);

        return ResponseEntity.ok("Check Mail for Download Link");
    }

}
