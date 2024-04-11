package com.example.training.repository;

import com.example.training.model.po.TagInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagInfoRepository extends JpaRepository<TagInfo, Long> {
}
