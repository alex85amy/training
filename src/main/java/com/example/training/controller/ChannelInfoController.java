package com.example.training.controller;

import com.example.training.bean.ChannelInfo;
import com.example.training.dao.ChannelInfoDao;
import com.example.training.daoimpl.ChannelInfoDaoImpl;
import com.example.training.util.JDBC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/channel_info")
public class ChannelInfoController {

    ChannelInfoDao channelInfoDao = new ChannelInfoDaoImpl();
    Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/all")
    public String index() {
        logger.info("findAll channel_info");
        return channelInfoDao.findAll();
    }

    @GetMapping("/per_page/{per_page}/page/{page}")
    public String findpagedata(@PathVariable("per_page") int per_page,
                               @PathVariable("page") int page) {
        logger.info("findChannelInfo page: " + page + " in per_page: " + per_page);
        return channelInfoDao.findpagedata(per_page, page);
    }

    @GetMapping("/{id}")
    public String findChannelInfoById(@PathVariable("id") int id) {
        logger.info("findChannelInfoById: " + id);
        return channelInfoDao.findById(id);
    }

    @PostMapping("/add")
    public void addChannelInfo(@RequestBody List<ChannelInfo> channelInfos) {
        for (ChannelInfo channelInfo : channelInfos) {
            channelInfoDao.add(channelInfo);
        }
        logger.info("addChannelInfo");
    }

    @PutMapping("/{id}")
    public void updateChannelInfo(@PathVariable("id") int id, ChannelInfo channelInfo) {
        channelInfoDao.update(id, channelInfo);
        logger.info("updateChannelInfo ID: " + id);
    }

    @DeleteMapping("/{id}")
    public void removeChannelInfo(@PathVariable("id") int id) {
        channelInfoDao.delete(id);
        logger.info("removeChannelInfo ID: " + id);
    }
}
