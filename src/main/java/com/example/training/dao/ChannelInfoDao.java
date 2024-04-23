package com.example.training.dao;

import com.example.training.bean.ChannelInfo;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ChannelInfoDao {

    void add(ChannelInfo channelInfo);

    boolean delete(int id);

    boolean update(int id, ChannelInfo channelInfo);

    String findById(int id);

    String findAll();

    String findpagedata(int per_page, int page);

    void addBatch(List<ChannelInfo> channelInfoList);
}
