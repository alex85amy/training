package com.example.training.controller;

import com.example.training.bean.TagInfo;
import com.example.training.dao.TagInfoDao;
import com.example.training.service.TagInfoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag_info")
public class TagInfoController {

    private TagInfoService tagInfoService = new TagInfoService();
    private Logger logger = LogManager.getLogger();

    @GetMapping("/all")
    public List<TagInfo> index() {
        logger.info("findAll tag_info");
        return tagInfoService.findAll();
    }

    @GetMapping("/amount/{amount}/page/{page}")
    public List<TagInfo> findPageData(@PathVariable("amount") int amount,
                               @PathVariable("page") int page) {
        logger.info("findTagInfo page: " + page + " in per_page: " + amount);
        return tagInfoService.findPageData(amount, page);
    }

    @GetMapping("/{id}")
    public TagInfo findTagInfoById(@PathVariable("id") int id) {
        logger.info("findTagInfoById: " + id);
        return tagInfoService.findByTagId(id);
    }

    @PostMapping("/add")
    public void addTagInfo(@RequestBody List<TagInfo> tagInfos) {
        for (TagInfo tagInfo : tagInfos) {
            tagInfoService.add(tagInfo);
        }
        logger.info("addTagInfo");
    }

    @PutMapping("/{id}")
    public void updateTagInfo(@PathVariable("id") int id, TagInfo tagInfo) {
        tagInfoService.update(id, tagInfo);
        logger.info("updateTagInfo ID: " + id);
    }

    @DeleteMapping("/{id}")
    public void removeTagInfo(@PathVariable("id") int id) {
        tagInfoService.delete(id);
        logger.info("removeTagInfo ID: " + id);
    }
}
