package com.example.demo.service.impl;

import com.example.demo.service.FileSystemService;
import com.example.demo.utill.FtpUtils;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@Service
//@Primary
@RequiredArgsConstructor
public class FileSystemWithFtp extends FileSystemService {
    private final FtpUtils ftpUtils;
    @Override
    public void uploadFile(MultipartFile[] files, Path companyPath) throws IOException {

        // 연결
        ftpUtils.connect();

        // 파일 경로 상 디렉토리 만들기
        ftpUtils.changeDirectories(companyPath.toString());

        // 파일들 업로드
        ftpUtils.uploadFile(files,  companyPath.toString());

        // 연결 해제
        ftpUtils.disconnect();
    }
}
