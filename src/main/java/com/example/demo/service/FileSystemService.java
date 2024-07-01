package com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

@Slf4j
public abstract class FileSystemService {
    protected abstract void uploadFile(MultipartFile[] files, Path SAVE_DIR) throws Exception;

    protected final Path getCompanyPath(Path SAVE_DIR, String companyName) throws IOException {
        return SAVE_DIR.resolve(SAVE_DIR.toAbsolutePath() + File.separator + companyName);
    }

    public final void upload(MultipartFile[] files, Path saveDir, String companyName) throws Exception {
        Path companyPath = getCompanyPath(saveDir, companyName);
        log.info("PATH : {}", companyPath);
        uploadFile(files, companyPath);
    }
}
