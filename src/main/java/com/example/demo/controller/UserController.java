package com.example.demo.controller;

import com.example.demo.utill.FileUploadWithFolder;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
@Slf4j
@RequestMapping("/upload")
@AllArgsConstructor
public class UserController {
    private final FileUploadWithFolder fileUploadWithFolder;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> upload (HttpServletRequest request) throws IOException, ServletException {
        // 파일을 저장할 경로 생성.
        Path SAVE_DIR = Paths.get(System.getProperty("user.dir"), "public");
        // 파일 저장할 경로에 디렉토리 생성.
        if (Files.notExists(SAVE_DIR)) {
            Files.createDirectories(SAVE_DIR);
        }
        // 파일 업로드.
        fileUploadWithFolder.uploadFile(request.getParts(), SAVE_DIR);
        return ResponseEntity.ok().build();
    }
}