package com.example.training.controller;

import com.company.bean.TagInfo;
import com.company.daoimpl.TagInfoDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tag_info")
public class TagInfoController {
    private static final String URL = "jdbc:mysql://localhost:3306/training?serverTimezone=Asia/Taipei&characterEncoding=utf-8&useUnicode=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "abc123";

    TagInfoDaoImpl tagInfoDao = new TagInfoDaoImpl();

    @GetMapping("/all")
    public String index() {
        return tagInfoDao.findAll().toString();
    }

    @GetMapping("/{id}")
    public String findTagInfoById(@PathVariable("id") int id) {
        return tagInfoDao.findByTagId(id).toString();
    }

    @PostMapping("/add")
    public void addTagInfo(TagInfo tagInfo) {
        tagInfoDao.add(tagInfo);
    }

    @PutMapping("/{id}")
    public void updateTagInfo(@PathVariable("id") int id, TagInfo tagInfo) {
        tagInfoDao.update(id, tagInfo);
    }

    @DeleteMapping("/{id}")
    public void removeTagInfo(@PathVariable("id") int id) {
        tagInfoDao.delete(id);
    }
}
