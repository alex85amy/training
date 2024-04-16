package com.example.training.controller;

import com.company.bean.PType2Info;
import com.company.daoimpl.PType2InfoDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/p_type_2_info")
public class PType2InfoController {

    @Autowired
    PType2InfoDaoImpl pType2InfoDao;

    @GetMapping("/all")
    public String index() {
        return pType2InfoDao.findAll().toString();
    }

    @GetMapping("/{id}")
    public String findPType2InfoById(@PathVariable("id") String id) {
        return pType2InfoDao.findByCategory(id).toString();
    }

    @PostMapping("/add")
    public void addPType2Info(PType2Info pType2Info) {
        pType2InfoDao.add(pType2Info);
    }

    @PutMapping("/update/{id}")
    public void updatePType2Info(@PathVariable("id") String id, PType2Info pType2Info) {
        pType2InfoDao.update(id, pType2Info);
    }

    @DeleteMapping("/delete/{id}")
    public void removePType2Info(@PathVariable("id") String id) {
        pType2InfoDao.delete(id);
    }

}
