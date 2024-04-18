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

    @GetMapping("/{source_id}")
    public String findChannelInfoById(@PathVariable("source_id") String source_id) {
        return channelInfoDao.findBySourceAreaId(source_id).toString();
    }

    @PostMapping("/add")
    public void addChannelInfo(ChannelInfo channelInfo) {
        channelInfoDao.add(channelInfo);
    }

    @PutMapping("/update/{source_id}")
    public void updateChannelInfo(@PathVariable("source_id") String source_id, ChannelInfo channelInfo) {
        channelInfoDao.update(source_id, channelInfo);
    }

    @DeleteMapping("/delete/{source_id}")
    public void removeChannelInfo(@PathVariable("source_id") String source_id) {
        channelInfoDao.delete(source_id);
    }
}
