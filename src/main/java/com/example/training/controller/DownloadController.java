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

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/download")
public class DownloadController {
    private final ExportToCsv exportToCsv = new ExportToCsv();
    private final Logger logger = LogManager.getLogger();

    @GetMapping("/")
    public ResponseEntity<byte[]> exportCsv() {
        byte[] csvBytes;
        csvBytes = exportToCsv.exportCsv();

        if (csvBytes.length != 0) {
            // 設置Header
            HttpHeaders headers = new HttpHeaders();
            // 設置文件類型與編碼
            headers.setContentType(new MediaType("text", "csv", StandardCharsets.UTF_8));
            // 設置為下載
            headers.setContentDispositionFormData("attachment", "output.csv");
            // 返回 CSV 文件内容作为 ResponseEntity, Internal Server 200 OK
            logger.info("download csv");
            return new ResponseEntity<>(csvBytes, headers, HttpStatus.OK);
        } else {
            // Internal Server Error 500
            logger.error("body is null");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
