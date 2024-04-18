package com.example.training.controller;

import com.company.bean.TagInfo;
import com.company.daoimpl.TagInfoDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tag_info")
public class TagInfoController {

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

    @PutMapping("/update/{id}")
    public void updateTagInfo(@PathVariable("id") int id, TagInfo tagInfo) {
        tagInfoDao.update(id, tagInfo);
    }

    @DeleteMapping("/delete/{id}")
    public void removeTagInfo(@PathVariable("id") int id) {
        tagInfoDao.delete(id);
    }
}
