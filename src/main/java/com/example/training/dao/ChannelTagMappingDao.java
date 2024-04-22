package com.example.training.dao;

import com.example.training.bean.ChannelTagMapping;

import java.util.List;

public interface ChannelTagMappingDao {

    void add(ChannelTagMapping channelTagMapping);

    boolean delete(int id);

    boolean update(int id, ChannelTagMapping channelTagMapping);

    Object findById(int id);

    Object findBySourceAreaId(String sourceAreaId);

    Object findByTagId(int tagId);

    Object findAll();

    void addBatch(List<ChannelTagMapping> channelTagMappingList);
}
