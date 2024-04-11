package com.example.training.model.po;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "p_type_2_info")
@Getter
@Setter
public class PType2Info {
    @Id
    private String category;
    private String name;
}
