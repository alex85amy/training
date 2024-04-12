package com.example.training.repository;

import com.example.training.model.po.TagInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TagInfoRepository extends JpaRepository<TagInfo, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM tag_info WHERE tag_id = ?")
    TagInfo findByTagId(Long tagId);
}
