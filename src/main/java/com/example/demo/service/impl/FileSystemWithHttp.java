package com.example.demo.service.impl;

import com.example.demo.service.FileSystemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Slf4j
@Service
@Primary
public class FileSystemWithHttp extends FileSystemService {

    @Override
    public void uploadFile(MultipartFile[] files, Path companyPath) throws IOException {

        if (Files.notExists(companyPath)) Files.createDirectories(companyPath);

        for (MultipartFile file : files) {

            log.info("filePath : {}", file.getOriginalFilename());

            // FileName 얻기
            // MultipartFile.getOriginalFilename : 업로드한 파일의 경로(디렉토리)를 포함한 파일 이름, Path.getFileName : 파일 이름만.
            String fileName = Paths.get(file.getOriginalFilename()).getFileName().toString();
            // 저장할 위치에 File 경로 구성
            Path filePath = companyPath.resolve(fileName);

            // 파일의 데이터를 하나씩 저장.
            try (var inputStream = file.getInputStream()) {
                // 해당 파일이 이미 있다면 덮어쓰기.
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                log.info("Wrote file: [{}] {}", file.getSize(), filePath);
            } catch (IOException e) {
                log.error("Failed to write file : {}", filePath, e);
            }
        }

    }


}
