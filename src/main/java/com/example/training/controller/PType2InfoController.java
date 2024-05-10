package com.example.training.controller;

import com.example.training.bean.PType2Info;
import com.example.training.service.PType2InfoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/p_type_2_info")
public class PType2InfoController {

    private final PType2InfoService pType2InfoService = new PType2InfoService();
    private final Logger logger = LogManager.getLogger();

    @GetMapping("/all")
    public List<PType2Info> index() {
        logger.info("findAll p_type_2_info");
        return pType2InfoService.findAll();
    }

    @GetMapping("/page")
    public List<PType2Info> findPageData(@RequestParam("amount") int amount,
                                         @RequestParam("page") int page) {
        logger.info("findPType2Info page: " + page + " in per_page: " + amount);
        return pType2InfoService.findPageData(amount, page);

    }

    @GetMapping("/{id}")
    public PType2Info findPType2InfoById(@PathVariable("id") int id) {
        logger.info("findPType2InfoById: " + id);
        return pType2InfoService.findById(id);
    }

    @PostMapping("/add")
    public void addPType2Info(@RequestBody List<PType2Info> pType2Infos) {
        for (PType2Info pType2Info : pType2Infos) {
            pType2InfoService.add(pType2Info);
        }
        logger.info("addPType2Info");
    }

    @PutMapping("/{id}")
    public void updatePType2Info(@PathVariable("id") int id, PType2Info pType2Info) {
        pType2InfoService.update(id, pType2Info);
        logger.info("updatePType2Info ID: " + id);
    }

    @DeleteMapping("/{id}")
    public void removePType2Info(@PathVariable("id") int id) {
        pType2InfoService.delete(id);
        logger.info("removePType2Info ID: " + id);
    }
}
