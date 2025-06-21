package com.example.services;

import com.example.entity.FileUpload;
import com.example.repository.FileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class FileService {

    private FileRepository fileRepository;


    public FileService(FileRepository fileRepository){
        this.fileRepository=fileRepository;
    }

    public FileUpload save(MultipartFile file) throws IOException {

        FileUpload image= new FileUpload();

        image.setName(file.getOriginalFilename());
        image.setType(file.getContentType());
        image.setImage(file.getBytes());


        return fileRepository.save(image);
    }

    public Optional<FileUpload> getFileById(int id) {
        return fileRepository.findById(id);
    }
}
