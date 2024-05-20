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
            logger.info("新增成功");
        } else
            logger.error("新增失敗: 資料重複");
    }

    public void delete(int id) {
        ChannelTagMapping data = channelTagMappingDao.findById(id);
        if (data != null) {
            channelTagMappingDao.delete(id);
            logger.info("刪除成功");
        } else
            logger.error("刪除失敗: 無此資料");
    }

    public void update(int id, ChannelTagMapping channelTagMapping) {
        ChannelTagMapping data = channelTagMappingDao.findById(id);
        if (data != null) {
            channelTagMappingDao.update(id, channelTagMapping);
            logger.info("修改成功");
        } else
            logger.error("修改失敗: 無此資料");
    }

    public ChannelTagMapping findById(int id) {
        ChannelTagMapping data = channelTagMappingDao.findById(id);
        if (data != null) {
            logger.info("查詢成功");
            return data;
        } else
            logger.error("查詢失敗: 無此資料");
        return null;
    }

    public List<ChannelTagMapping> findPageData(int amount, int page) {
        List<ChannelTagMapping> list = channelTagMappingDao.findPageData(amount, page);
        if (list.size() != 0) {
            logger.info("查詢成功");
            return list;
        } else
            logger.error("查詢失敗: 無此資料");
        return null;
    }

    public List<ChannelTagMapping> findBySourceAreaId(String sourceAreaId) {
        List<ChannelTagMapping> list = channelTagMappingDao.findBySourceAreaId(sourceAreaId);
        if (list.size() != 0) {
            logger.info("查詢成功");
            return list;
        } else
            logger.error("查詢失敗: 無此資料");
        return null;
    }

    public List<ChannelTagMapping> findByTagId(int tagId) {
        List<ChannelTagMapping> list = channelTagMappingDao.findByTagId(tagId);
        if (list.size() != 0) {
            logger.info("查詢成功");
            return list;
        } else
            logger.error("查詢失敗: 無此資料");
        return null;
    }


}
