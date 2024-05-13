package com.example.training.service;

import com.example.training.bean.ChannelInfo;
import com.example.training.dao.ChannelInfoDao;
import com.example.training.util.JDBC;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.List;

public class ChannelInfoService {

    private final Connection conn = JDBC.getConn();
    private final Logger logger = LogManager.getLogger();
    private final ChannelInfoDao channelInfoDao = new ChannelInfoDao(conn);

    public void add(ChannelInfo channelInfo) {
        ChannelInfo data = channelInfoDao.findBySourceAreaId(channelInfo.getSourceAreaId());
        if (data == null) {
            channelInfoDao.add(channelInfo);
        } else
            logger.error("新增失敗: 資料重複");
    }

    public void delete(int id) {
        ChannelInfo data = channelInfoDao.findById(id);
        if (data != null) {
            channelInfoDao.delete(id);
        } else
            logger.error("刪除失敗: 無此資料");
    }

    public void update(int id, ChannelInfo channelInfo) {
        ChannelInfo data = channelInfoDao.findById(id);
        if (data != null) {
            channelInfoDao.update(id, channelInfo);
        } else
            logger.error("修改失敗: 無此資料");
    }

    public ChannelInfo findById(int id) {
        return channelInfoDao.findById(id);
    }

    public List<ChannelInfo> findAll() {
        return channelInfoDao.findAll();
    }

    public List<ChannelInfo> findPageData(int amount, int page) {
        return channelInfoDao.findPageData(amount, page);
    }

}
