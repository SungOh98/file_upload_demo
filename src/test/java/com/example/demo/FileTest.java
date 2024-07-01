package com.example.demo;

import com.example.demo.utill.FtpUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootTest
public class FileTest {

    @Autowired
    FtpUtils ftpUtils;

    @Test
    public void FILE_존재_여부() throws Exception {
        Path path = Paths.get("/Users/jeongseong-o/a/b/c/d/e/f");
        if (Files.notExists(path)) {
            Files.createDirectories(path);
        }
    }

    @Test
    public void FTP_TEST() throws Exception {
        //given
        int i;
        MultipartFile[] files = new MultipartFile[5];
        for (i = 0; i < 5; i++) {
            files[i] = new MockMultipartFile("files", String.format("file%d.txt", i), "text/plain", new byte[0]);
        }
        ftpUtils.connect();
        ftpUtils.uploadFile(files, "/home/sung-o/company/LivinAI");
        ftpUtils.disconnect();
        //when

        //then

    }


}
