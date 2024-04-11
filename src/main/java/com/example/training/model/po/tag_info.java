package com.example.training.model.po;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tag_info")
@Getter
@Setter
public class tag_info {
    @Id
    private int tag_id;
    private String tag_name;
    private int type;
}
