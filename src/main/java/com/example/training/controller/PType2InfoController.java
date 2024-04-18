package com.example.training.controller;

import com.company.bean.PType2Info;
import com.company.daoimpl.PType2InfoDaoImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/p_type_2_info")
public class PType2InfoController {

    PType2InfoDaoImpl pType2InfoDao = new PType2InfoDaoImpl();

    @GetMapping("/all")
    public String index() {
        return pType2InfoDao.findAll().toString();
    }

    @GetMapping("/{category}")
    public String findPType2InfoById(@PathVariable("category") String category) {
        return pType2InfoDao.findByCategory(category).toString();
    }

    @PostMapping("/add")
    public void addPType2Info(PType2Info pType2Info) {
        pType2InfoDao.add(pType2Info);
    }

    @PutMapping("/update/{category}")
    public void updatePType2Info(@PathVariable("category") String category, PType2Info pType2Info) {
        pType2InfoDao.update(category, pType2Info);
    }

    @DeleteMapping("/delete/{category}")
    public void removePType2Info(@PathVariable("category") String category) {
        pType2InfoDao.delete(category);
    }

}
