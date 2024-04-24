package com.example.training.dao;

import com.example.training.bean.ChannelTagMapping;

import java.util.List;

public interface ChannelTagMappingDao {

    void add(ChannelTagMapping channelTagMapping);

    boolean delete(int id);

    boolean update(int id, ChannelTagMapping channelTagMapping);

    String findById(int id);

    String findpagedata(int per_page, int page);

    String findBySourceAreaId(String sourceAreaId);

    String findByTagId(int tagId);

    String findBySIdAndTagId(String sourceAreaId, int tagId);

    String findAll();

    void addBatch(List<ChannelTagMapping> channelTagMappingList);
}
