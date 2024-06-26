package com.example.demo;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileTest {
    @Test
    public void FILE존재여부() throws Exception{
        //given
        Path path = Paths.get("gradle/file-system.proble");
        System.out.println(path.toString());
        System.out.println(path.getFileName());
        System.out.println(path.getName(0));
        System.out.println(path.getRoot());
        //when
        // 현재 디렉토리
        System.out.println(System.getProperty("user.dir"));
        System.out.println(System.getProperty("user.home"));
        //then



     }
}
