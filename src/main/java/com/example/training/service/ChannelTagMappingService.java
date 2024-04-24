package com.example.training.service;

import com.example.training.bean.ChannelTagMapping;
import com.example.training.dao.ChannelTagMappingDao;
import com.example.training.daoimpl.ChannelTagMappingDaoImpl;
import com.example.training.util.JDBC;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.List;

public class ChannelTagMappingService {

    private JDBC jdbc = new JDBC();
    private Connection conn = jdbc.getConnection();
    private Logger logger = LogManager.getLogger();
    private ChannelTagMappingDao channelTagMappingDao = new ChannelTagMappingDaoImpl(conn);

    public void add(ChannelTagMapping channelTagMapping) {
        channelTagMappingDao.add(channelTagMapping);
    }

    public void delete(int id) {
        channelTagMappingDao.delete(id);
    }

    public void update(int id, ChannelTagMapping channelTagMapping) {
        channelTagMappingDao.update(id, channelTagMapping);
    }

    public String findById(int id) {
        return channelTagMappingDao.findById(id);
    }

    public String findpagedata(int per_page, int page) {
        return channelTagMappingDao.findpagedata(per_page, page);
    }

    public String findBySourceAreaId(String sourceAreaId) {
        return channelTagMappingDao.findBySourceAreaId(sourceAreaId);
    }

    public String findByTagId(int tagId) {
        return channelTagMappingDao.findByTagId(tagId);
    }

    public String findAll() {
        return channelTagMappingDao.findAll();
    }

    public void addBatch(List<ChannelTagMapping> channelTagMappingList) {
        channelTagMappingDao.addBatch(channelTagMappingList);
    }

}
