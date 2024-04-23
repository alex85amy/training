package com.example.training.dao;

import com.example.training.bean.PType2Info;

public interface PType2InfoDao {

    void add(PType2Info pType2Info);

    boolean delete(int id);

    boolean update(int id, PType2Info pType2Info);

    String findById(int id);

    String findAll();
}
