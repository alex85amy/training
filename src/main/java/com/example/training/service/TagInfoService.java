package com.example.training.service;

import com.example.training.bean.TagInfo;
import com.example.training.dao.TagInfoDao;
import com.example.training.util.JDBC;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.List;

public class TagInfoService {

    private final Connection conn = JDBC.getConn();
    private final Logger logger = LogManager.getLogger();
    private final TagInfoDao tagInfoDao = new TagInfoDao(conn);

    public void add(TagInfo tagInfo) {
        TagInfo data = tagInfoDao.findByTagId(tagInfo.getTagId());
        if (data == null) {
            tagInfoDao.add(tagInfo);
        }
        logger.error("新增失敗: 資料重複");
    }

    public void delete(int tagId) {
        TagInfo data = tagInfoDao.findByTagId(tagId);
        if (data != null) {
            tagInfoDao.delete(tagId);
        }
        logger.error("刪除失敗: 無此資料");
    }

    public void update(int tagId, TagInfo tagInfo) {
        TagInfo data = tagInfoDao.findByTagId(tagId);
        if (data != null) {
            tagInfoDao.update(tagId, tagInfo);
        }
        logger.error("修改失敗: 無此資料");
    }

    public TagInfo findByTagId(int tagId) {
        return tagInfoDao.findByTagId(tagId);
    }

    public List<TagInfo> findPageData(int amount, int page) {
        return tagInfoDao.findPageData(amount, page);
    }

    public List<TagInfo> findAll() {
        return tagInfoDao.findAll();
    }
}
