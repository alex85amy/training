package com.example.training.controller;

import com.example.training.util.ExportToCsv;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

@RestController
@RequestMapping("/download")
public class DownloadController {
    private final ExportToCsv exportToCsv = new ExportToCsv();
    private final Logger logger = LogManager.getLogger();

    @GetMapping("/")
    public ResponseEntity<byte[]> exportCsv() {
        byte[] csvBytes = new byte[0];
        try {
            csvBytes = exportToCsv.exportCsv();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
        logger.info("download csv");
        if (csvBytes != null) {
            // 設置Header
            HttpHeaders headers = new HttpHeaders();
            // 設置文件類型與編碼
            headers.setContentType(new MediaType("text", "csv", StandardCharsets.UTF_8));
            // 設置為下載
            headers.setContentDispositionFormData("attachment", "output.csv");
            // 返回 CSV 文件内容作为 ResponseEntity
            return new ResponseEntity<>(csvBytes, headers, HttpStatus.OK);
        } else {
            // Internal Server Error 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
