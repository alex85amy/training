package com.example.training.controller;

import com.example.training.bean.PType2Info;
import com.example.training.dao.PType2InfoDao;
import com.example.training.daoimpl.PType2InfoDaoImpl;
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
@RequestMapping("/p_type_2_info")
public class PType2InfoController {

    JDBC jdbc = new JDBC();
    PType2InfoDao pType2InfoDao = new PType2InfoDaoImpl();
    Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/all")
    public String index() {
        logger.info("findAll p_type_2_info");
        return pType2InfoDao.findAll().toString();
    }

    @GetMapping("/per_page/{per_page}/page/{page}")
    public String findpagedata(@PathVariable("per_page") int per_page,
                               @PathVariable("page") int page) {
        int offset = (page - 1) * per_page;
        try (Connection conn = jdbc.getConnection();
             Statement statement = conn.createStatement()) {
            String insertSQL = "SELECT * FROM p_type_2_info LIMIT " + per_page + " OFFSET " + offset;
            ResultSet rs = statement.executeQuery(insertSQL);
            logger.info("findPType2Info page: " + page + " in per_page: " + per_page);
            return ResultSetToJson.ResultSetToJsonObject(rs, "p_type_2_info").toString();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @GetMapping("/{id}")
    public String findPType2InfoById(@PathVariable("id") int id) {
        logger.info("findPType2InfoById: " + id);
        return pType2InfoDao.findById(id).toString();
    }

    @PostMapping("/add")
    public void addPType2Info(@RequestBody List<PType2Info> pType2Infos) {
        for (PType2Info pType2Info : pType2Infos) {
            pType2InfoDao.add(pType2Info);
        }
        logger.info("addPType2Info");
    }

    @PutMapping("/{id}")
    public void updatePType2Info(@PathVariable("id") int id, PType2Info pType2Info) {
        pType2InfoDao.update(id, pType2Info);
        logger.info("updatePType2Info ID: " + id);
    }

    @DeleteMapping("/{id}")
    public void removePType2Info(@PathVariable("id") int id) {
        pType2InfoDao.delete(id);
        logger.info("removePType2Info ID: " + id);
    }
}
