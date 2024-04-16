package com.example.training.controller;

import com.company.bean.ChannelInfo;
import com.company.daoimpl.ChannelInfoDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/channel_info")
public class ChannelInfoController {

    @Autowired
    ChannelInfoDaoImpl channelInfoDao;

    @GetMapping("/all")
    public String index() {
        return channelInfoDao.findAll().toString();
    }

    @GetMapping("/{id}")
    public String findChannelInfoById(@PathVariable("id") String id) {
        return channelInfoDao.findBySourceAreaId(id).toString();
    }

    @PostMapping("/add")
    public void addChannelInfo(ChannelInfo channelInfo) {
        channelInfoDao.add(channelInfo);
    }

    @PutMapping("/update/{id}")
    public void updateChannelInfo(@PathVariable("id") String id, ChannelInfo channelInfo) {
        channelInfoDao.update(id, channelInfo);
    }

    @DeleteMapping("/delete/{id}")
    public void removeChannelInfo(@PathVariable("id") String id) {
        channelInfoDao.delete(id);
    }
}
