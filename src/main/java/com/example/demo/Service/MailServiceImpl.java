package com.example.demo.Service;

import com.example.demo.Entity.File;
import com.example.demo.Exceptions.FileNotFoundException;
import com.example.demo.Exceptions.InvalidFileException;
import com.example.demo.Repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private FileRepository fileRepository;

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    public void saveFile(MultipartFile file){

        if(file.isEmpty()){throw new InvalidFileException();}

        String filePath = "D:\\files\\"+file.getOriginalFilename();
        try{
            Path target = Path.of(filePath);
            file.transferTo(target);
//            Files.copy(file.getInputStream(),target,StandardCopyOption.REPLACE_EXISTING);
            com.example.demo.Entity.File fileToDB =new com.example.demo.Entity.File(file.getOriginalFilename(),file.getContentType(),filePath);
            fileRepository.save(fileToDB);

        } catch (Exception exception){
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
    }

    public String getFilePath(int id){
        File referenceFile = fileRepository.findById(id).orElseThrow(()->new FileNotFoundException());
        return referenceFile.getPath();
    }


}
