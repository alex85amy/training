package com.example.training.service;

import com.example.training.model.dto.ChannelInfoDto;
import com.example.training.model.po.ChannelInfo;
import com.example.training.repository.ChannelInfoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChannelInfoService {

    @Autowired
    private ChannelInfoRepository channelInfoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public void add(ChannelInfoDto channelInfoDto){
        ChannelInfo channelInfo = modelMapper.map(channelInfoDto, ChannelInfo.class);
        channelInfoRepository.save(channelInfo);
    }

    public void update(ChannelInfoDto channelInfoDto, String soureAreaId){
        Optional<ChannelInfo> channelInfoOpt = channelInfoRepository.findById(soureAreaId);
        if(channelInfoOpt.isPresent()){
            ChannelInfo channelInfo = channelInfoOpt.get();

        }
    }
}
