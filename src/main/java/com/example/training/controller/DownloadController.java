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

@RestController
@RequestMapping("/download")
public class DownloadController {
    private ExportToCsv exportToCsv = new ExportToCsv();
    private Logger logger = LogManager.getLogger();

    @GetMapping("/")
    public ResponseEntity<byte[]> exportCsv() {
        byte[] csvBytes = exportToCsv.exportCsv();

        if (csvBytes != null) {
            //設置Header
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_PLAIN);
            headers.set("Content-Disposition", "attachment; filename=output.csv; charset=UTF-8");
            // 返回 CSV 文件内容作为 ResponseEntity
            return new ResponseEntity<>(csvBytes, headers, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
