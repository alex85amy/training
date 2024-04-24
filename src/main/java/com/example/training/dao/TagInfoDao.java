package com.example.training.dao;

import com.example.training.bean.TagInfo;

import java.util.List;

public interface TagInfoDao {
    void add(TagInfo tagInfo);

    boolean delete(int tagId);

    boolean update(int tagId, TagInfo tagInfo);

    String findByTagId(int tagId);

    String findpagedata(int per_page, int page);

    String findAll();

    void addBatch(List<TagInfo> tagInfoList);
}
