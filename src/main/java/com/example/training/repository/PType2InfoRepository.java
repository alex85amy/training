package com.example.training.repository;

import com.example.training.model.po.PType2Info;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PType2InfoRepository extends JpaRepository<PType2Info, String> {
    PType2Info findByCategory(String category);
}
