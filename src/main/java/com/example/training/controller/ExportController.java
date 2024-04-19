package com.example.training.controller;

import com.example.training.ExportToCsv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/export")
public class ExportController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/")
    public String export() {
        ExportToCsv export = new ExportToCsv();
        export.exportQueryResultToCsv();
        logger.info("export output.csv");
        return "export output.csv";
    }
}
