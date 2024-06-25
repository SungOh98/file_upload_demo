package com.example.demo.utill;

import jakarta.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Collection;

@Slf4j
@Component
public class FileUploadWithFolder {

    public void uploadFile(Collection<Part> files, Path SAVE_DIR) throws IOException {
        for (var part : files) {
            // FileName 얻기
            String fileName = FilenameUtils.getName(part.getSubmittedFileName());
            // File 경로 구성
            Path file = SAVE_DIR.resolve(fileName);

            try (var inputStream = part.getInputStream()){
                // 해당 파일이 이미 있다면 덮어쓰기.
                Files.copy(inputStream, file, StandardCopyOption.REPLACE_EXISTING);
            }

            log.info("write file: [{}] {}", part.getSize(), file);
        }

    }
}
