package com.example.training.controller;

import com.example.training.bean.ChannelInfo;
import com.example.training.service.ChannelInfoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/channel_info")
public class ChannelInfoController {

    private ChannelInfoService channelInfoService = new ChannelInfoService();
    private Logger logger = LogManager.getLogger();

    @GetMapping("/all")
    public List<ChannelInfo> index() {
        logger.info("findAll channel_info");
        return channelInfoService.findAll();
    }

    @GetMapping("/amount/{amount}/page/{page}")
    public List<ChannelInfo> findPageData(@PathVariable("amount") int amount,
                               @PathVariable("page") int page) {
        logger.info("findChannelInfo page: " + page + " in per_page: " + amount);
        return channelInfoService.findPageData(amount, page);
    }

    @GetMapping("/{id}")
    public ChannelInfo findChannelInfoById(@PathVariable("id") int id) {
        logger.info("findChannelInfoById: " + id);
        return channelInfoService.findById(id);
    }

    @PostMapping("/add")
    public void addChannelInfo(@RequestBody List<ChannelInfo> channelInfos) {
        for (ChannelInfo channelInfo : channelInfos) {
            channelInfoService.add(channelInfo);
        }
        logger.info("addChannelInfo");
    }

    @PutMapping("/{id}")
    public void updateChannelInfo(@PathVariable("id") int id, ChannelInfo channelInfo) {
        channelInfoService.update(id, channelInfo);
        logger.info("updateChannelInfo ID: " + id);
    }

    @DeleteMapping("/{id}")
    public void removeChannelInfo(@PathVariable("id") int id) {
        channelInfoService.delete(id);
        logger.info("removeChannelInfo ID: " + id);
    }
}
