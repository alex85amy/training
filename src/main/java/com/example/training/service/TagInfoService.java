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
            logger.info("新增成功");
        } else
            logger.error("新增失敗: 資料重複");
    }

    public void delete(int tagId) {
        TagInfo data = tagInfoDao.findByTagId(tagId);
        if (data != null) {
            tagInfoDao.delete(tagId);
            logger.info("刪除成功");
        } else
            logger.error("刪除失敗: 無此資料");
    }

    public void update(int tagId, TagInfo tagInfo) {
        TagInfo data = tagInfoDao.findByTagId(tagId);
        if (data != null) {
            tagInfoDao.update(tagId, tagInfo);
            logger.info("修改成功");
        } else
            logger.error("修改失敗: 無此資料");
    }

    public TagInfo findByTagId(int tagId) {
        TagInfo data = tagInfoDao.findByTagId(tagId);
        if (data != null) {
            logger.info("查詢成功");
            return data;
        } else
            logger.error("查詢失敗: 無此資料");
        return null;
    }

    public List<TagInfo> findPageData(int amount, int page) {
        List<TagInfo> list = tagInfoDao.findPageData(amount, page);
        if (list.size() != 0) {
            logger.info("查詢成功");
            return list;
        } else
            logger.error("查詢失敗: 無此資料");
        return null;
    }

}
