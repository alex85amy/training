package com.example.training.service;

import com.example.training.model.dto.TagInfoDto;
import com.example.training.model.po.TagInfo;
import com.example.training.repository.TagInfoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagInfoService {

    @Autowired
    private TagInfoRepository tagInfoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public TagInfoDto getTagInfoById(Long tagId) {
        Optional<TagInfo> tagInfoOpt = Optional.ofNullable(tagInfoRepository.findByTagId(tagId));
        if (tagInfoOpt.isPresent()) {
            TagInfo tagInfo = tagInfoOpt.get();
            return modelMapper.map(tagInfo, TagInfoDto.class);
        }
        return null;
    }

    public List<TagInfoDto> findAll() {
        List<TagInfo> tagInfos = tagInfoRepository.findAll();
        return tagInfos
                .stream()
                .map(tagInfo -> modelMapper.map(tagInfo, TagInfoDto.class))
                .toList();
    }

    public void add(TagInfoDto tagInfoDto) {
        TagInfo tagInfo = modelMapper.map(tagInfoDto, TagInfo.class);
        tagInfoRepository.save(tagInfo);
    }

    public void update(TagInfoDto tagInfoDto, Long tagId) {
        Optional<TagInfo> tagInfoOpt = Optional.ofNullable(tagInfoRepository.findByTagId(tagId));
        if (tagInfoOpt.isPresent()) {
            TagInfo tagInfo = tagInfoOpt.get();
            tagInfo.setTagName(tagInfoDto.getTagName());
            tagInfo.setType(tagInfoDto.getType());
            tagInfoRepository.save(tagInfo);
        }
    }

    public void delete(Long tagId) {
        Optional<TagInfo> tagInfoOpt = Optional.ofNullable(tagInfoRepository.findByTagId(tagId));
        if (tagInfoOpt.isPresent()) {
            tagInfoRepository.delete(tagInfoOpt.get());
        }
    }
}
