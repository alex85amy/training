package com.example.training.controller;

import com.example.training.bean.ChannelTagMapping;
import com.example.training.dao.ChannelTagMappingDao;
import com.example.training.daoimpl.ChannelTagMappingDaoImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/channel_tag_mapping")
public class ChannelTagMappingController {

    private ChannelTagMappingDao channelTagMappingDao = new ChannelTagMappingDaoImpl();
    private Logger logger = LogManager.getLogger();

    @GetMapping("/all")
    public String index() {
        logger.info("findAll channel_tag_mapping");
        return channelTagMappingDao.findAll();
    }

    @GetMapping("/per_page/{per_page}/page/{page}")
    public String findpagedata(@PathVariable("per_page") int per_page,
                               @PathVariable("page") int page) {
        logger.info("findChannelTagMapping page: " + page + " in per_page: " + per_page);
        return channelTagMappingDao.findpagedata(per_page, page);
    }

    @GetMapping("/{id}")
    public String findChannelTagMappingById(@PathVariable("id") int id) {
        logger.info("findChannelTagMappingById: " + id);
        return channelTagMappingDao.findById(id);
    }

    @PostMapping("/add")
    public void addChannelTagMapping(@RequestBody List<ChannelTagMapping> channelTagMappings) {
        for (ChannelTagMapping channelTagMapping : channelTagMappings) {
            channelTagMappingDao.add(channelTagMapping);
        }
        logger.info("addChannelTagMapping");
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") int id, ChannelTagMapping channelTagMapping) {
        channelTagMappingDao.update(id, channelTagMapping);
        logger.info("updateChannelTagMapping ID: " + id);
    }

    @DeleteMapping("/{id}")
    public void removeChannelTagMapping(@PathVariable("id") int id) {
        channelTagMappingDao.delete(id);
        logger.info("removeChannelTagMapping ID: " + id);
    }
}
