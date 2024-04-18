package com.example.training.controller;

import com.company.bean.PType2Info;
import com.company.daoimpl.PType2InfoDaoImpl;
import com.company.util.ResultSetToJson;
import org.springframework.web.bind.annotation.*;

import java.sql.*;

@RestController
@RequestMapping("/p_type_2_info")
public class PType2InfoController {
    private static final String URL = "jdbc:mysql://localhost:3306/training?serverTimezone=Asia/Taipei&characterEncoding=utf-8&useUnicode=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "abc123";

    PType2InfoDaoImpl pType2InfoDao = new PType2InfoDaoImpl();

    @GetMapping("/all")
    public String index() {
        return pType2InfoDao.findAll().toString();
    }

    @GetMapping("/per_page/{per_page}/page/{page}")
    public String findpagedata(@PathVariable("per_page") int per_page,
                               @PathVariable("page") int page) {
        int offset = (page - 1) * per_page;
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = conn.createStatement()) {
            String insertSQL = "SELECT * FROM p_type_2_info LIMIT " + per_page + " OFFSET " + offset;
            ResultSet rs = statement.executeQuery(insertSQL);
            return ResultSetToJson.ResultSetToJsonObject(rs, "p_type_2_info").toString();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @GetMapping("/{id}")
    public String findPType2InfoById(@PathVariable("id") int id) {
        return pType2InfoDao.findById(id).toString();
    }

    @PostMapping("/add")
    public void addPType2Info(PType2Info pType2Info) {
        pType2InfoDao.add(pType2Info);
    }

    @PutMapping("/{id}")
    public void updatePType2Info(@PathVariable("id") int id, PType2Info pType2Info) {
        pType2InfoDao.update(id, pType2Info);
    }

    @DeleteMapping("/{id}")
    public void removePType2Info(@PathVariable("id") int id) {
        pType2InfoDao.delete(id);
    }

}
