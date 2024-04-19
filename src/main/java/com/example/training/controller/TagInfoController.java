package com.example.training.controller;

import com.company.bean.TagInfo;
import com.company.daoimpl.TagInfoDaoImpl;
import com.company.util.ResultSetToJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.List;

@RestController
@RequestMapping("/tag_info")
public class TagInfoController {
    private static final String URL = "jdbc:mysql://localhost:3306/training?serverTimezone=Asia/Taipei&characterEncoding=utf-8&useUnicode=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "abc123";

    TagInfoDaoImpl tagInfoDao = new TagInfoDaoImpl();
    Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/all")
    public String index() {
        logger.info("findAll tag_info");
        return tagInfoDao.findAll().toString();
    }

    @GetMapping("/per_page/{per_page}/page/{page}")
    public String findpagedata(@PathVariable("per_page") int per_page,
                               @PathVariable("page") int page) {
        int offset = (page - 1) * per_page;
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = conn.createStatement()) {
            String insertSQL = "SELECT * FROM tag_info LIMIT " + per_page + " OFFSET " + offset;
            ResultSet rs = statement.executeQuery(insertSQL);
            logger.info("findTagInfo page: " + page + " in per_page: " + per_page);
            return ResultSetToJson.ResultSetToJsonObject(rs, "tag_info").toString();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @GetMapping("/{id}")
    public String findTagInfoById(@PathVariable("id") int id) {
        logger.info("findTagInfoById: " + id);
        return tagInfoDao.findByTagId(id).toString();
    }

    @PostMapping("/add")
    public void addTagInfo(@RequestBody List<TagInfo> tagInfos) {
        for (TagInfo tagInfo : tagInfos) {
            tagInfoDao.add(tagInfo);
        }
        logger.info("addTagInfo");
    }

    @PutMapping("/{id}")
    public void updateTagInfo(@PathVariable("id") int id, TagInfo tagInfo) {
        tagInfoDao.update(id, tagInfo);
        logger.info("updateTagInfo ID: " + id);
    }

    @DeleteMapping("/{id}")
    public void removeTagInfo(@PathVariable("id") int id) {
        tagInfoDao.delete(id);
        logger.info("removeTagInfo ID: " + id);
    }
}
