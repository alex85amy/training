package com.example.training.controller;

import com.example.training.bean.ChannelTagMapping;
import com.example.training.dao.ChannelTagMappingDao;
import com.example.training.daoimpl.ChannelTagMappingDaoImpl;
import com.example.training.util.JDBC;
import com.example.training.util.ResultSetToJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@RestController
@RequestMapping("/channel_tag_mapping")
public class ChannelTagMappingController {

    JDBC jdbc = new JDBC();
    ChannelTagMappingDao channelTagMappingDao = new ChannelTagMappingDaoImpl();
    Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/all")
    public String index() {
        logger.info("findAll channel_tag_mapping");
        return channelTagMappingDao.findAll().toString();
    }

    @GetMapping("/per_page/{per_page}/page/{page}")
    public String findpagedata(@PathVariable("per_page") int per_page,
                               @PathVariable("page") int page) {
        int offset = (page - 1) * per_page;
        try (Connection conn = jdbc.getConnection();
             Statement statement = conn.createStatement()) {
            String insertSQL = "SELECT * FROM channel_tag_mapping LIMIT " + per_page + " OFFSET " + offset;
            ResultSet rs = statement.executeQuery(insertSQL);
            logger.info("findChannelTagMapping page: " + page + " in per_page: " + per_page);
            return ResultSetToJson.ResultSetToJsonObject(rs, "channel_tag_mapping").toString();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @GetMapping("/{id}")
    public String findChannelTagMappingById(@PathVariable("id") int id) {
        logger.info("findChannelTagMappingById: " + id);
        return channelTagMappingDao.findById(id).toString();
    }

    @PostMapping("/add")
    public void addChannelTagMapping(@RequestBody List<ChannelTagMapping> channelTagMappings) {
        for (ChannelTagMapping channelTagMapping : channelTagMappings) {
            channelTagMappingDao.add(channelTagMapping);
        }
        logger.info("addChannelTagMapping");
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") int id, ChannelTagMapping channelTagMapping) {
        channelTagMappingDao.update(id, channelTagMapping);
        logger.info("updateChannelTagMapping ID: " + id);
    }

    @DeleteMapping("/{id}")
    public void removeChannelTagMapping(@PathVariable("id") int id) {
        channelTagMappingDao.delete(id);
        logger.info("removeChannelTagMapping ID: " + id);
    }
}
