package com.example.demo.controller;

import com.example.demo.service.FileSystemService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * HTTP only
 * Server(WAS) -> Server File System : WAS 서버컴퓨터와 파일을 저장할 컴퓨터가 같은 경우
 * HTTP + FTP
 * Server(WAS) -> Server File System : WAS 서버컴퓨터와 파일을 저장할 컴퓨터가 같은 경우
 * Local(WAS) -> Server File System : WAS 서버컴퓨터와 파일을 저장할 컴퓨터가 다른 경우
 */
@RestController
@Slf4j
@RequestMapping("/upload")
@AllArgsConstructor
public class UserController {
    private final FileSystemService fileSystemService;


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> upload(@RequestParam("files") MultipartFile[] files,
                                         @RequestParam("companyName") String companyName) throws Exception {
        // 파일들을 저장할 최상위 디렉토리 넘겨주기.
        Path rootSaveDir = Paths.get(System.getProperty("user.home") + File.separator + "company");
        // 파일 업로드.
        fileSystemService.upload(
                files,
                rootSaveDir,
                companyName
        );
        return ResponseEntity.ok().build();
    }
}