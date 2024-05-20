package com.example.training.controller;

import com.example.training.bean.TagInfo;
import com.example.training.service.TagInfoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag_info")
public class TagInfoController {

    private final TagInfoService tagInfoService = new TagInfoService();
    private final Logger logger = LogManager.getLogger();

    @GetMapping("/page")
    public List<TagInfo> findPageData(@RequestParam("amount") int amount,
                                      @RequestParam("page") int page) {
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
        logger.info("addTagInfo");
        for (TagInfo tagInfo : tagInfos) {
            tagInfoService.add(tagInfo);
        }
    }

    @PutMapping("/{id}")
    public void updateTagInfo(@PathVariable("id") int id,
                              @RequestBody TagInfo tagInfo) {
        logger.info("updateTagInfo ID: " + id);
        tagInfoService.update(id, tagInfo);
    }

    @DeleteMapping("/{id}")
    public void removeTagInfo(@PathVariable("id") int id) {
        logger.info("removeTagInfo ID: " + id);
        tagInfoService.delete(id);
    }
}
