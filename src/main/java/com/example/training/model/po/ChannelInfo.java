package com.example.training.model.po;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "channel_info")
@Getter
@Setter
public class ChannelInfo {
    @Id
    @Column(name = "soure_area_id")
    private String soureAreaId;

    @Column(name = "source_id")
    private String sourceId;

    @Column(name = "is_used")
    private int isUsed;

    @Column(name = "p_type_2")
    private String pType2;
}
