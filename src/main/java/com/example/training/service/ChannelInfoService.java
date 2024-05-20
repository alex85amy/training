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
            logger.info("新增成功");
        } else
            logger.error("新增失敗: 資料重複");
    }

    public void delete(int id) {
        ChannelInfo data = channelInfoDao.findById(id);
        if (data != null) {
            channelInfoDao.delete(id);
            logger.info("刪除成功");
        } else
            logger.error("刪除失敗: 無此資料");
    }

    public void update(int id, ChannelInfo channelInfo) {
        ChannelInfo data = channelInfoDao.findById(id);
        if (data != null) {
            channelInfoDao.update(id, channelInfo);
            logger.info("修改成功");
        } else
            logger.error("修改失敗: 無此資料");
    }

    public ChannelInfo findById(int id) {
        ChannelInfo data = channelInfoDao.findById(id);
        if (data != null) {
            logger.info("查詢成功");
            return data;
        } else
            logger.error("查詢失敗: 無此資料");
        return null;
    }

    public List<ChannelInfo> findPageData(int amount, int page) {
        List<ChannelInfo> list = channelInfoDao.findPageData(amount, page);
        if (list.size() != 0) {
            logger.info("查詢成功");
            return list;
        } else
            logger.error("查詢失敗: 無此資料");
        return null;
    }

}
