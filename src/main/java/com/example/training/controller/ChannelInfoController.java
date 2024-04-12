package com.example.training.controller;

import com.example.training.model.dto.ChannelInfoDto;
import com.example.training.service.ChannelInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ChannelInfo")
public class ChannelInfoController {
    @Autowired
    private ChannelInfoService channelInfoService;

    @GetMapping("/all")
    public String index() {
        return channelInfoService.findAll().toString();
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") String id) {
        return channelInfoService.getChannelInfoById(id).toString();
    }

    @PostMapping("/add")
    public String add(ChannelInfoDto channelInfoDto) {
        channelInfoService.add(channelInfoDto);
        return "sucess";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        channelInfoService.delete(id);
        return "sucess";
    }

    @PutMapping("/update/{id}")
    public String update(@PathVariable("id") String id,ChannelInfoDto channelInfoDto){
        channelInfoService.update(channelInfoDto,id);
        return "sucess";
    }
}
