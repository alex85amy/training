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


    public void add(TagInfo tagInfo){
        tagInfoDao.add(tagInfo);
    }

    public void delete(int tagId){
        tagInfoDao.delete(tagId);
    }

    public void update(int tagId, TagInfo tagInfo){
        tagInfoDao.update(tagId, tagInfo);
    }

    public String findByTagId(int tagId){
        return tagInfoDao.findByTagId(tagId);
    }

    public String findpagedata(int per_page, int page){
        return tagInfoDao.findpagedata(per_page, page);
    }

    public String findAll(){
        return tagInfoDao.findAll();
    }
}
