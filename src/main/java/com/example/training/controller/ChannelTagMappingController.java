package com.example.training.controller;

import com.company.bean.ChannelTagMapping;
import com.company.daoimpl.ChannelTagMappingDaoImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/channel_tag_mapping")
public class ChannelTagMappingController {
    private static final String URL = "jdbc:mysql://localhost:3306/training?serverTimezone=Asia/Taipei&characterEncoding=utf-8&useUnicode=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "abc123";

    ChannelTagMappingDaoImpl channelTagMappingDao = new ChannelTagMappingDaoImpl();

    @GetMapping("/all")
    public String index() {
        return channelTagMappingDao.findAll().toString();
    }

    @GetMapping("/{id}")
    public String findChannelTagMappingById(@PathVariable("id") int id) {
        return channelTagMappingDao.findById(id).toString();
    }

    @PostMapping("/add")
    public void addChannelTagMapping(ChannelTagMapping channelTagMapping) {
        channelTagMappingDao.add(channelTagMapping);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") int id, ChannelTagMapping channelTagMapping){
        channelTagMappingDao.update(id,channelTagMapping);
    }

    @DeleteMapping("/{id}")
    public void removeChannelTagMapping(@PathVariable("id") int id) {
        channelTagMappingDao.delete(id);
    }
}
