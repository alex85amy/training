package com.example.training.controller;

import com.example.training.bean.ChannelInfo;
import com.example.training.service.ChannelInfoService;
import jakarta.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/channel_info")
public class ChannelInfoController {

    private final ChannelInfoService channelInfoService = new ChannelInfoService();
    private final Logger logger = LogManager.getLogger();

    @GetMapping("/page")
    public List<ChannelInfo> findPageData(@RequestParam("amount") int amount,
                                          @RequestParam("page") int page) {
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
