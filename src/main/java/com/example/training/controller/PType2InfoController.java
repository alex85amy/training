package com.example.training.controller;

import com.company.bean.PType2Info;
import com.company.daoimpl.PType2InfoDaoImpl;
import org.springframework.web.bind.annotation.*;

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
