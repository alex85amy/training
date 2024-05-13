package com.example.training.service;

import com.example.training.bean.ChannelTagMapping;
import com.example.training.dao.ChannelTagMappingDao;
import com.example.training.util.JDBC;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.List;

public class ChannelTagMappingService {

    private final Connection conn = JDBC.getConn();
    private final Logger logger = LogManager.getLogger();
    private final ChannelTagMappingDao channelTagMappingDao = new ChannelTagMappingDao(conn);

    public void add(ChannelTagMapping channelTagMapping) {
        ChannelTagMapping data = channelTagMappingDao.findBySIdAndTagId(channelTagMapping.getSourceAreaId(), channelTagMapping.getTagId());
        if (data == null) {
            channelTagMappingDao.add(channelTagMapping);
        }
        logger.error("新增失敗: 資料重複");
    }

    public void delete(int id) {
        ChannelTagMapping data = channelTagMappingDao.findById(id);
        if (data != null) {
            channelTagMappingDao.delete(id);
        }
        logger.error("刪除失敗: 無此資料");
    }

    public void update(int id, ChannelTagMapping channelTagMapping) {
        ChannelTagMapping data = channelTagMappingDao.findById(id);
        if (data != null) {
            channelTagMappingDao.update(id, channelTagMapping);
        }
        logger.error("修改失敗: 無此資料");
    }

    public ChannelTagMapping findById(int id) {
        return channelTagMappingDao.findById(id);
    }

    public List<ChannelTagMapping> findPageData(int amount, int page) {
        return channelTagMappingDao.findPageData(amount, page);
    }

    public ChannelTagMapping findBySourceAreaId(String sourceAreaId) {
        return channelTagMappingDao.findBySourceAreaId(sourceAreaId);
    }

    public ChannelTagMapping findByTagId(int tagId) {
        return channelTagMappingDao.findByTagId(tagId);
    }

    public List<ChannelTagMapping> findAll() {
        return channelTagMappingDao.findAll();
    }

}
