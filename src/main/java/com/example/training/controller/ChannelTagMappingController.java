package com.example.training.controller;

import com.example.training.bean.ChannelTagMapping;
import com.example.training.service.ChannelTagMappingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/channel_tag_mapping")
public class ChannelTagMappingController {

    private final ChannelTagMappingService channelTagMappingService = new ChannelTagMappingService();
    private final Logger logger = LogManager.getLogger();

    @GetMapping("/all")
    public List<ChannelTagMapping> index() {
        logger.info("findAll channel_tag_mapping");
        return channelTagMappingService.findAll();
    }

    @GetMapping("/page")
    public List<ChannelTagMapping> findPageData(@RequestParam("amount") int amount,
                                                @RequestParam("page") int page) {
        logger.info("findChannelTagMapping page: " + page + " in per_page: " + amount);
        return channelTagMappingService.findPageData(amount, page);
    }

    @GetMapping("/{id}")
    public ChannelTagMapping findChannelTagMappingById(@PathVariable("id") int id) {
        logger.info("findChannelTagMappingById: " + id);
        return channelTagMappingService.findById(id);
    }

    @GetMapping("/channel")
    public List<ChannelTagMapping> findBySourceAreaId(@RequestParam("id") String id) {
        logger.info("findChannelTagMappingBySourceAreaId: " + id);
        return channelTagMappingService.findBySourceAreaId(id);
    }

    @GetMapping("/tag")
    public List<ChannelTagMapping> findByTagId(@RequestParam("id") int id) {
        logger.info("findChannelTagMappingByTagId: " + id);
        return channelTagMappingService.findByTagId(id);
    }

    @PostMapping("/add")
    public void addChannelTagMapping(@RequestBody List<ChannelTagMapping> channelTagMappings) {
        for (ChannelTagMapping channelTagMapping : channelTagMappings) {
            channelTagMappingService.add(channelTagMapping);
        }
        logger.info("addChannelTagMapping");
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") int id, ChannelTagMapping channelTagMapping) {
        channelTagMappingService.update(id, channelTagMapping);
        logger.info("updateChannelTagMapping ID: " + id);
    }

    @DeleteMapping("/{id}")
    public void removeChannelTagMapping(@PathVariable("id") int id) {
        channelTagMappingService.delete(id);
        logger.info("removeChannelTagMapping ID: " + id);
    }
}
