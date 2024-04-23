package com.example.training.controller;

import com.example.training.ExportToCsv;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/export")
public class ExportController {

    private Logger logger = LogManager.getLogger();

    @GetMapping("/")
    public String export() {
        ExportToCsv export = new ExportToCsv();
        export.exportQueryResultToCsv();
        logger.info("export output.csv");
        return "export output.csv";
    }
}
