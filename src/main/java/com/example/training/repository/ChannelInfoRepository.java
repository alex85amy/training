package com.example.training.repository;

import com.example.training.model.po.ChannelInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelInfoRepository extends JpaRepository<ChannelInfo, String> {
    ChannelInfo findBySoureAreaId(String soureAreaId);
}
