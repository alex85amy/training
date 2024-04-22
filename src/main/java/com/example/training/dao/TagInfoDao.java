package com.example.training.dao;

import com.example.training.bean.TagInfo;

public interface TagInfoDao {
    void add(TagInfo tagInfo);

    boolean delete(int tagId);

    boolean update(int tagId, TagInfo tagInfo);

    Object findByTagId(int tagId);

    Object findAll();
}
