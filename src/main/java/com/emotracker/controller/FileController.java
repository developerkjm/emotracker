// FileController.java
package com.emotracker.controller;

import com.emotracker.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String savedFileName = fileService.saveFile(file);
            return "파일 업로드 성공: " + savedFileName;
        } catch (Exception e) {
            return "파일 업로드 실패: " + e.getMessage();
        }
    }
}
