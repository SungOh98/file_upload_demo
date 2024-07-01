package com.example.demo.utill;

import lombok.Data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class FTPServer {
    @Value("${FTPServer.ip}")
    private String ip;
    @Value("${FTPServer.port}")
    private int port;
    @Value("${FTPServer.username}")
    private String userName;
    @Value("${FTPServer.password}")
    private String password;
}
