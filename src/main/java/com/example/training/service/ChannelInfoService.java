package com.example.training.service;

import com.example.training.model.dto.ChannelInfoDto;
import com.example.training.model.po.ChannelInfo;
import com.example.training.repository.ChannelInfoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChannelInfoService {

    @Autowired
    private ChannelInfoRepository channelInfoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ChannelInfoDto getChannelInfoById(String soureAreaId) {
        Optional<ChannelInfo> channelInfoOpt = Optional.ofNullable(channelInfoRepository.findBySoureAreaId(soureAreaId));
        if (channelInfoOpt.isPresent()) {
            ChannelInfo channelInfo = channelInfoOpt.get();
            return modelMapper.map(channelInfo, ChannelInfoDto.class);
        }
        return null;
    }

    public List<ChannelInfoDto> findAll() {
        List<ChannelInfo> channelInfos = channelInfoRepository.findAll();
        return channelInfos
                .stream()
                .map(channelInfo -> modelMapper.map(channelInfo, ChannelInfoDto.class))
                .toList();
    }

    public void add(ChannelInfoDto channelInfoDto) {
        ChannelInfo channelInfo = modelMapper.map(channelInfoDto, ChannelInfo.class);
        channelInfoRepository.save(channelInfo);
    }

    public void update(ChannelInfoDto channelInfoDto, String soureAreaId) {
        Optional<ChannelInfo> channelInfoOpt = Optional.ofNullable(channelInfoRepository.findBySoureAreaId(soureAreaId));
        if (channelInfoOpt.isPresent()) {
            ChannelInfo channelInfo = channelInfoOpt.get();
            channelInfo.setIsUsed(channelInfoDto.getIsUsed());
            channelInfo.setPType2(channelInfoDto.getPType2());
            channelInfo.setSourceId(channelInfoDto.getSourceId());
            channelInfoRepository.save(channelInfo);
        }
    }

    public void delete(String soureAreaId) {
        Optional<ChannelInfo> channelInfoOpt = Optional.ofNullable(channelInfoRepository.findBySoureAreaId(soureAreaId));
        if (channelInfoOpt.isPresent()) {
            channelInfoRepository.delete(channelInfoOpt.get());
        }
    }
}

