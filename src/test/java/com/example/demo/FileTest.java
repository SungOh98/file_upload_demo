package com.example.demo;

import com.example.demo.utill.FtpUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

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
        ftpUtils.changeDirectories("/home/sung-o/company/LivinAI");
        ftpUtils.uploadFile(files,"/home/sung-o/company/LivinAI");
        ftpUtils.disconnect();
        //when

        //then

    }

    @Test
    public void tmp() throws Exception{
        //given
        String a = "/Users/a/b/c/d";
        a = a.substring(1);
        String[] tmp = a.split("/");
        System.out.println(Arrays.toString(tmp));
        //when

        //then

     }


}
