package com.example.training.service;

import com.example.training.bean.ChannelTagMapping;
import com.example.training.dao.ChannelTagMappingDao;
import com.example.training.util.JDBC;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;

public class ChannelTagMappingService {

    private JDBC jdbc = new JDBC();
    private Connection conn = jdbc.getConnection();
    private Logger logger = LogManager.getLogger();
    private ChannelTagMappingDao channelTagMappingDao = new ChannelTagMappingDao(conn);

    public void add(ChannelTagMapping channelTagMapping) {
        String data = channelTagMappingDao.findBySIdAndTagId(channelTagMapping.getSourceAreaId(), channelTagMapping.getTagId());
        if (data == null) {
            channelTagMappingDao.add(channelTagMapping);
        }
        logger.error("新增失敗: 資料重複");
    }

    public void delete(int id) {
        String data = channelTagMappingDao.findById(id);
        if (data != null) {
            channelTagMappingDao.delete(id);
        }
        logger.error("刪除失敗: 無此資料");
    }

    public void update(int id, ChannelTagMapping channelTagMapping) {
        String data = channelTagMappingDao.findById(id);
        if (data != null) {
            channelTagMappingDao.update(id, channelTagMapping);
        }
        logger.error("修改失敗: 無此資料");
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

}
