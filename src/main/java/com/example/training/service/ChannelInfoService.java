package com.example.training.service;

import com.example.training.bean.ChannelInfo;
import com.example.training.dao.ChannelInfoDao;
import com.example.training.daoimpl.ChannelInfoDaoImpl;
import com.example.training.util.JDBC;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;

public class ChannelInfoService {

    private JDBC jdbc = new JDBC();
    private Connection conn = jdbc.getConnection();
    private Logger logger = LogManager.getLogger();
    private ChannelInfoDao channelInfoDao = new ChannelInfoDaoImpl(conn);

    public void add(ChannelInfo channelInfo) {
        String data = channelInfoDao.findBySourceAreaId(channelInfo.getSourceAreaId());
        if (data == null) {
            channelInfoDao.add(channelInfo);
        }
        logger.error("新增失敗: 資料重複");
    }

    public void delete(int id) {
        String data = channelInfoDao.findById(id);
        if (data != null) {
            channelInfoDao.delete(id);
        }
        logger.error("刪除失敗: 無此資料");
    }

    public void update(int id, ChannelInfo channelInfo) {
        String data = channelInfoDao.findById(id);
        if (data != null) {
            channelInfoDao.update(id, channelInfo);
        }
        logger.error("修改失敗: 無此資料");
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

}
