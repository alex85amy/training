package com.example.training.repository;

import com.example.training.model.po.PType2Info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PType2InfoRepository extends JpaRepository<PType2Info, String> {
    @Query(nativeQuery = true, value = "SELECT * FROM p_type_2_info WHERE category = ?")
    PType2Info findByCategory(String category);
}
