package com.example.controller;

import com.example.entity.FileUpload;
import com.example.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/file")
public class FileController {

    private FileService fileService;

    @Autowired
    public FileController (FileService fileService){
        this.fileService =fileService;
    }


    //upload the image
    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("No file selected to upload");
        }
        try {
            FileUpload upload = fileService.save(file);
            return ResponseEntity.ok(upload);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload failed");
        }
    }

    //download the image
    @GetMapping("/download/{id}")
    public ResponseEntity<?>downloadFile(@PathVariable int id){
            Optional<FileUpload> optionalFileUpload =fileService.getFileById(id);
            if(optionalFileUpload.isPresent()){
                FileUpload file=optionalFileUpload.get();

                return ResponseEntity.ok()
                        .header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"")
                        .header("Content-Type", file.getType())
                        .body(file.getImage());

            }
            else
                return  ResponseEntity.notFound().build();
    }
    }
