package com.example.training.repository;

import com.example.training.model.po.ChannelInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelInfoRepository extends JpaRepository<ChannelInfo, String> {
    @Query(nativeQuery = true, value = "SELECT * FROM channel_info WHERE source_area_id = ?")
    ChannelInfo findBySourceAreaId(String sourceAreaId);
}
