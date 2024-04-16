package com.example.training;

import com.company.daoimpl.TagInfoDaoImpl;

public class test {
    public static void main(String[] args) {
        TagInfoDaoImpl tagInfoDao = new TagInfoDaoImpl();
        System.out.println(tagInfoDao.findAll().toString());
    }
}
