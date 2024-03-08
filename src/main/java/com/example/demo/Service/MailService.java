package com.example.demo.Service;

import org.springframework.web.multipart.MultipartFile;

public interface MailService{

    public void sendSimpleMessage(String to, String subject, String text);
    public void saveFile(MultipartFile file);

    public String getFilePath(int id);


}
