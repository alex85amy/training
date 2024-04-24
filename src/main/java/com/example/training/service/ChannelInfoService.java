package com.example.training.service;

import com.example.training.bean.ChannelInfo;
import com.example.training.dao.ChannelInfoDao;
import com.example.training.daoimpl.ChannelInfoDaoImpl;
import com.example.training.util.JDBC;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.List;

public class ChannelInfoService {

    private JDBC jdbc = new JDBC();
    private Connection conn = jdbc.getConnection();
    private Logger logger = LogManager.getLogger();
    private ChannelInfoDao channelInfoDao = new ChannelInfoDaoImpl(conn);

    public void add(ChannelInfo channelInfo) {
        channelInfoDao.add(channelInfo);
    }

    public void delete(int id) {
        channelInfoDao.delete(id);
    }

    public void update(int id, ChannelInfo channelInfo) {
        channelInfoDao.update(id, channelInfo);
    }

    public String findById(int id) {
        return channelInfoDao.findById(id);
    }

    public String findAll() {
        return channelInfoDao.findAll();
    }

    public String findpagedata(int per_page, int page) {
        return channelInfoDao.findpagedata(per_page, page);
    }

    public void addBatch(List<ChannelInfo> channelInfoList) {
        channelInfoDao.addBatch(channelInfoList);
    }
}
