package com.example.training.controller;

import com.example.training.bean.TagInfo;
import com.example.training.dao.TagInfoDao;
import com.example.training.daoimpl.TagInfoDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag_info")
public class TagInfoController {

    TagInfoDao tagInfoDao = new TagInfoDaoImpl();
    Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/all")
    public String index() {
        logger.info("findAll tag_info");
        return tagInfoDao.findAll();
    }

    @GetMapping("/per_page/{per_page}/page/{page}")
    public String findpagedata(@PathVariable("per_page") int per_page,
                               @PathVariable("page") int page) {
        logger.info("findTagInfo page: " + page + " in per_page: " + per_page);
        return tagInfoDao.findpagedata(per_page, page);
    }

    @GetMapping("/{id}")
    public String findTagInfoById(@PathVariable("id") int id) {
        logger.info("findTagInfoById: " + id);
        return tagInfoDao.findByTagId(id);
    }

    @PostMapping("/add")
    public void addTagInfo(@RequestBody List<TagInfo> tagInfos) {
        for (TagInfo tagInfo : tagInfos) {
            tagInfoDao.add(tagInfo);
        }
        logger.info("addTagInfo");
    }

    @PutMapping("/{id}")
    public void updateTagInfo(@PathVariable("id") int id, TagInfo tagInfo) {
        tagInfoDao.update(id, tagInfo);
        logger.info("updateTagInfo ID: " + id);
    }

    @DeleteMapping("/{id}")
    public void removeTagInfo(@PathVariable("id") int id) {
        tagInfoDao.delete(id);
        logger.info("removeTagInfo ID: " + id);
    }
}
