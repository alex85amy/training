package com.example.training.controller;

import com.company.bean.ChannelTagMapping;
import com.company.daoimpl.ChannelTagMappingDaoImpl;
import com.company.util.ResultSetToJson;
import org.springframework.web.bind.annotation.*;

import java.sql.*;

@RestController
@RequestMapping("/channel_tag_mapping")
public class ChannelTagMappingController {
    private static final String URL = "jdbc:mysql://localhost:3306/training?serverTimezone=Asia/Taipei&characterEncoding=utf-8&useUnicode=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "abc123";

    ChannelTagMappingDaoImpl channelTagMappingDao = new ChannelTagMappingDaoImpl();

    @GetMapping("/all")
    public String index() {
        return channelTagMappingDao.findAll().toString();
    }

    @GetMapping("/per_page/{per_page}/page/{page}")
    public String findpagedata(@PathVariable("per_page") int per_page,
                               @PathVariable("page") int page) {
        int offset = (page - 1) * per_page;
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = conn.createStatement()) {
            String insertSQL = "SELECT * FROM channel_tag_mapping LIMIT " + per_page + " OFFSET " + offset;
            ResultSet rs = statement.executeQuery(insertSQL);
            return ResultSetToJson.ResultSetToJsonObject(rs, "channel_tag_mapping").toString();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @GetMapping("/{id}")
    public String findChannelTagMappingById(@PathVariable("id") int id) {
        return channelTagMappingDao.findById(id).toString();
    }

    @PostMapping("/add")
    public void addChannelTagMapping(ChannelTagMapping channelTagMapping) {
        channelTagMappingDao.add(channelTagMapping);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") int id, ChannelTagMapping channelTagMapping) {
        channelTagMappingDao.update(id, channelTagMapping);
    }

    @DeleteMapping("/{id}")
    public void removeChannelTagMapping(@PathVariable("id") int id) {
        channelTagMappingDao.delete(id);
    }
}
