package com.example.training.controller;

import com.company.bean.ChannelTagMapping;
import com.company.daoimpl.ChannelTagMappingDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/channel_tag_mapping")
public class ChannelTagMappingController {

    @Autowired
    ChannelTagMappingDaoImpl channelTagMappingDao;

    @GetMapping("/all")
    public String index() {
        return channelTagMappingDao.findAll().toString();
    }

    @GetMapping("/{id}")
    public String findChannelTagMappingById(@PathVariable("id") int id) {
        return channelTagMappingDao.findByTagId(id).toString();
    }

    @PostMapping("/add")
    public void addChannelTagMapping(ChannelTagMapping channelTagMapping) {
        channelTagMappingDao.add(channelTagMapping);
    }

    @DeleteMapping("/delete/{sourceareaid}/{id}")
    public void removeChannelTagMapping(@PathVariable("sourceareaid") String sourceAreaId,
                                        @PathVariable("id") int id) {
        channelTagMappingDao.delete(sourceAreaId, id);
    }
}
