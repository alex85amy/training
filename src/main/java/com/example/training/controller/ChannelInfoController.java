package com.example.training.controller;

import com.company.bean.ChannelInfo;
import com.company.daoimpl.ChannelInfoDaoImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/channel_info")
public class ChannelInfoController {
    private static final String URL = "jdbc:mysql://localhost:3306/training?serverTimezone=Asia/Taipei&characterEncoding=utf-8&useUnicode=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "abc123";

    ChannelInfoDaoImpl channelInfoDao = new ChannelInfoDaoImpl();

    @GetMapping("/all")
    public String index() {
        return channelInfoDao.findAll().toString();
    }

    @GetMapping("/{id}")
    public String findChannelInfoById(@PathVariable("id") int id) {
        return channelInfoDao.findById(id).toString();
    }

    @PostMapping("/add")
    public void addChannelInfo(ChannelInfo channelInfo) {
        channelInfoDao.add(channelInfo);
    }

    @PutMapping("/{id}")
    public void updateChannelInfo(@PathVariable("id") int id, ChannelInfo channelInfo) {
        channelInfoDao.update(id, channelInfo);
    }

    @DeleteMapping("/{id}")
    public void removeChannelInfo(@PathVariable("id") int id) {
        channelInfoDao.delete(id);
    }
}
