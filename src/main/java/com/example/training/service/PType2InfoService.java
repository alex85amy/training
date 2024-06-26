package com.example.training.service;

import com.example.training.bean.PType2Info;
import com.example.training.dao.PType2InfoDao;
import com.example.training.util.JDBC;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.List;

public class PType2InfoService {

    private final Connection conn = JDBC.getConn();
    private final Logger logger = LogManager.getLogger();
    private final PType2InfoDao pType2InfoDao = new PType2InfoDao(conn);

    public void add(PType2Info pType2Info) {
        PType2Info data = pType2InfoDao.findByCategoryOrName(pType2Info.getCategory(), pType2Info.getName());
        if (data == null) {
            pType2InfoDao.add(pType2Info);
            logger.info("新增成功");
        } else
            logger.error("新增失敗: 資料重複");
    }

    public void delete(int id) {
        PType2Info data = pType2InfoDao.findById(id);
        if (data != null) {
            pType2InfoDao.delete(id);
            logger.info("刪除成功");
        } else
            logger.error("刪除失敗: 無此資料");
    }

    public void update(int id, PType2Info pType2Info) {
        PType2Info data = pType2InfoDao.findById(id);
        if (data != null) {
            pType2InfoDao.update(id, pType2Info);
            logger.info("修改成功");
        } else
            logger.error("修改失敗: 無此資料");
    }

    public PType2Info findById(int id) {
        PType2Info data = pType2InfoDao.findById(id);
        if (data != null) {
            logger.info("查詢成功");
            return data;
        } else
            logger.error("查詢失敗: 無此資料");
        return null;
    }

    public List<PType2Info> findPageData(int amount, int page) {
        List<PType2Info> list = pType2InfoDao.findPageData(amount, page);
        if (list.size() != 0) {
            logger.info("查詢成功");
            return list;
        } else
            logger.error("查詢失敗: 無此資料");
        return null;
    }


}
