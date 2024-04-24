package com.example.training.service;

import com.example.training.bean.PType2Info;
import com.example.training.dao.PType2InfoDao;
import com.example.training.daoimpl.PType2InfoDaoImpl;
import com.example.training.util.JDBC;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;

public class PType2InfoService {

    private JDBC jdbc = new JDBC();
    private Connection conn = jdbc.getConnection();
    private Logger logger = LogManager.getLogger();
    private PType2InfoDao pType2InfoDao = new PType2InfoDaoImpl(conn);


    public void add(PType2Info pType2Info) {
        String data = pType2InfoDao.findByCategoryOrName(pType2Info.getCategory(), pType2Info.getName());
        if (data == null) {
            pType2InfoDao.add(pType2Info);
        }
        logger.error("新增失敗: 資料重複");
    }

    public void delete(int id) {
        String data = pType2InfoDao.findById(id);
        if (data != null) {
            pType2InfoDao.delete(id);
        }
        logger.error("刪除失敗: 無此資料");
    }

    public void update(int id, PType2Info pType2Info) {
        String data = pType2InfoDao.findById(id);
        if (data != null) {
            pType2InfoDao.update(id, pType2Info);
        }
        logger.error("修改失敗: 無此資料");
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

}
