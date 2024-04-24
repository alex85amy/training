package com.example.training.service;

import com.example.training.bean.PType2Info;
import com.example.training.dao.PType2InfoDao;
import com.example.training.daoimpl.PType2InfoDaoImpl;
import com.example.training.util.JDBC;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.List;

public class PType2InfoService {

    private JDBC jdbc = new JDBC();
    private Connection conn = jdbc.getConnection();
    private Logger logger = LogManager.getLogger();
    private PType2InfoDao pType2InfoDao = new PType2InfoDaoImpl(conn);

    public void add(PType2Info pType2Info) {
        pType2InfoDao.add(pType2Info);
    }

    public void delete(int id) {
        pType2InfoDao.delete(id);
    }

    public void update(int id, PType2Info pType2Info) {
        pType2InfoDao.update(id, pType2Info);
    }

    public String findById(int id) {
        return pType2InfoDao.findById(id);
    }

    public String findpagedata(int per_page, int page) {
        return pType2InfoDao.findpagedata(per_page, page);
    }

    public String findAll() {
        return pType2InfoDao.findAll();
    }

    public void addBatch(List<PType2Info> pType2InfoList) {
        pType2InfoDao.addBatch(pType2InfoList);
    }
}
