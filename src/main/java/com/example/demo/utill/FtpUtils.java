package com.example.demo.utill;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;


@Component
@Slf4j
@RequiredArgsConstructor
public class FtpUtils {
    private final FTPServer ftpServer;
    private FTPClient ftpClient;

    public void connect() throws IOException {
        log.info("ftp Host : {}", ftpServer.getIp());
        log.info("ftp user : {}", ftpServer.getUserName());
        log.info("ftp password : {}", ftpServer.getPassword());

        // FTP Client 객체 생성.
        ftpClient = new FTPClient();
        // FTP Server host, 포트로 연결
        ftpClient.connect(ftpServer.getIp(), ftpServer.getPort());
        // 계정, 비밀번호로 로그인
        ftpClient.login(ftpServer.getUserName(), ftpServer.getPassword());
        // 데이터 손실이 없는 파일 타입으로 설정.
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        // 통신 로깅, true 인자는 login과 같은 민감한 정보를 로깅하지 않기 위함.
        ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out), true));
        // 전송 방식 설정 -> passive 모드
        ftpClient.enterLocalPassiveMode();

    }


    //"/a/b/c/d"
    public void changeDirectories(String targetPath) throws IOException {
        // "[a, b, c, d, e]"
        String[] directories = targetPath.split(File.separator);
        String curPath = "";
        for (String directory : directories) {
            curPath += File.separator + directory;
            if (!ftpClient.changeWorkingDirectory(curPath)) {
                ftpClient.makeDirectory(curPath);
                ftpClient.changeWorkingDirectory(curPath);
            }

        }
    }

    public void uploadFile(MultipartFile[] files, String path) throws IOException {
        if (!ftpClient.changeWorkingDirectory(path)) throw new RuntimeException("FTP 서버 디렉토리에 접근 불가!");
        for (MultipartFile file : files) {
            if (!ftpClient.storeFile(
                    path + File.separator + file.getOriginalFilename(),
                    file.getInputStream())
            ) throw new RuntimeException("FTP 서버에 파일 업로드 불가!");
        }
    }


    public void disconnect() throws IOException {
        ftpClient.logout();
        ftpClient.disconnect();
    }



}

