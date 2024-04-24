package com.example.training.service;

import com.example.training.bean.TagInfo;
import com.example.training.dao.TagInfoDao;
import com.example.training.daoimpl.TagInfoDaoImpl;
import com.example.training.util.JDBC;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;

public class TagInfoService {

    private JDBC jdbc = new JDBC();
    private Connection conn = jdbc.getConnection();
    private Logger logger = LogManager.getLogger();
    private TagInfoDao tagInfoDao = new TagInfoDaoImpl(conn);

    public void add(TagInfo tagInfo) {
        String data = tagInfoDao.findByTagId(tagInfo.getTagId());
        if (data == null) {
            tagInfoDao.add(tagInfo);
        }
        logger.error("新增失敗: 資料重複");
    }

    public void delete(int tagId) {
        String data = tagInfoDao.findByTagId(tagId);
        if (data != null) {
            tagInfoDao.delete(tagId);
        }
        logger.error("刪除失敗: 無此資料");
    }

    public void update(int tagId, TagInfo tagInfo) {
        String data = tagInfoDao.findByTagId(tagId);
        if (data != null) {
            tagInfoDao.update(tagId, tagInfo);
        }
        logger.error("修改失敗: 無此資料");
    }

    public String findByTagId(int tagId) {
        return tagInfoDao.findByTagId(tagId);
    }

    public String findpagedata(int per_page, int page) {
        return tagInfoDao.findpagedata(per_page, page);
    }

    public String findAll() {
        return tagInfoDao.findAll();
    }
}
