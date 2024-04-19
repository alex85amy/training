package com.example.training.controller;

import com.example.training.ExportToCsv;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/export")
public class ExportController {

    @GetMapping("/")
    public String export() {
        ExportToCsv export = new ExportToCsv();
        export.exportQueryResultToCsv();
        return "export output.csv";
    }
}
